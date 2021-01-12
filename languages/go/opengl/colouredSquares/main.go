package main

import (
	"fmt"
	"log"
	"runtime"
	"strings"
	"time"

	"github.com/go-gl/gl/v4.1-core/gl" // OR: github.com/go-gl/gl/v2.1/gl
	"github.com/go-gl/glfw/v3.2/glfw"
)

const (
	width  = 500
	height = 500

	// TODO swap to something useful
	num = 8

	fps = 1

	vertexShaderSource = `
		#version 410
		layout(location = 0) in vec3 vp;
		layout(location = 1) in vec3 vc;
		out vec3 fragmentColor;
		void main() {
			gl_Position = vec4(vp, 1.0);
			fragmentColor = vc;
			//if (vp[0] < -0.5 && vp[1] < -0.5)
			//	fragmentColor = vec3(1,1,1);
			//else
			//	fragmentColor = vc;
		}
	` + "\x00"

	fragmentShaderSource = `
		#version 410
		in vec3 fragmentColor;
		out vec4 color;
		void main() {
			color = vec4(fragmentColor, 1.0);
		}
	` + "\x00"
)

var (
	squarePoints = []float32{
		-0.5, 0.5, 0,
		-0.5, -0.5, 0,
		0.5, -0.5, 0,

		-0.5, 0.5, 0,
		0.5, -0.5, 0,
		0.5, 0.5, 0,
	}

	squareColour = []float32{
		0.9,  0.1,  0.1,
		0.1,  0.9,  0.1,
		0.1,  0.1,  0.9,

		0.9,  0.1,  0.1,
		0.1,  0.1,  0.9,
		0.1,  0.9,  0.1,
	}
)

func main() {
	runtime.LockOSThread()

	window := initGlfw()
	defer glfw.Terminate()
	program := initOpenGL()

	squares := makeSquares()

	for !window.ShouldClose() {

		// need to check colour in the main loop
		t := time.Now()

		draw(squares, window, program)

		time.Sleep(time.Second/time.Duration(fps) - time.Since(t))
	}
}

func makeSquares() [][]*square {
	squares := make([][]*square, num, num)
	for x := 0; x < num; x ++ {
		for y := 0; y < num; y++ {
			s := makeSquare(x, y)
			squares[x] = append(squares[x], s)
		}
	}
	return squares
}

func draw(squares [][]*square, window *glfw.Window, program uint32) {

	// Background
	gl.ClearColor(0.1, 0.2, 0.3, 1.0)
	gl.Clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT)
	gl.UseProgram(program)

	for x :=  range squares {
		for y, s := range squares[x] {

			if x == 5 && y == 2 {
				s.updateDrawable()
			}
			s.draw()
		}
	}

	gl.DrawArrays(gl.TRIANGLES, 0, int32(len(squarePoints)/3))

	glfw.PollEvents()
	window.SwapBuffers()
}

// initGlfw initializes glfw and returns a Window to use.
func initGlfw() *glfw.Window {
	if err := glfw.Init(); err != nil {
		panic(err)
	}
	glfw.WindowHint(glfw.Resizable, glfw.False)
	glfw.WindowHint(glfw.ContextVersionMajor, 4)
	glfw.WindowHint(glfw.ContextVersionMinor, 1)
	glfw.WindowHint(glfw.OpenGLProfile, glfw.OpenGLCoreProfile)
	glfw.WindowHint(glfw.OpenGLForwardCompatible, glfw.True)

	window, err := glfw.CreateWindow(width, height, "Conway's Game of Life", nil, nil)
	if err != nil {
		panic(err)
	}
	window.MakeContextCurrent()

	return window
}

// initOpenGL initializes OpenGL and returns an intiialized program.
func initOpenGL() uint32 {
	if err := gl.Init(); err != nil {
		panic(err)
	}
	version := gl.GoStr(gl.GetString(gl.VERSION))
	log.Println("OpenGL version", version)

	vertexShader, err := compileShader(vertexShaderSource, gl.VERTEX_SHADER)
	if err != nil {
		panic(err)
	}

	fragmentShader, err := compileShader(fragmentShaderSource, gl.FRAGMENT_SHADER)
	if err != nil {
		panic(err)
	}

	prog := gl.CreateProgram()
	gl.AttachShader(prog, vertexShader)
	gl.AttachShader(prog, fragmentShader)
	gl.LinkProgram(prog)
	return prog
}

// makeVao initializes and returns a vertex array from the points provided.
func makeVao(points []float32, pointsColors []float32) uint32 {

	var vbo uint32
	gl.GenBuffers(1, &vbo)
	gl.BindBuffer(gl.ARRAY_BUFFER, vbo)
	gl.BufferData(gl.ARRAY_BUFFER, 4*len(points), gl.Ptr(points), gl.STATIC_DRAW)

	var colorVbo uint32
	gl.GenBuffers(1, &colorVbo)
	gl.BindBuffer(gl.ARRAY_BUFFER, colorVbo)
	gl.BufferData(gl.ARRAY_BUFFER, 4*len(pointsColors), gl.Ptr(pointsColors), gl.STATIC_DRAW)

	var vao uint32
	gl.GenVertexArrays(1, &vao)
	gl.BindVertexArray(vao)

	// Points - Index 0 because in the vertex shader, that maps to position
	gl.EnableVertexAttribArray(0)
	gl.BindBuffer(gl.ARRAY_BUFFER, vbo)
	gl.VertexAttribPointer(0, 3, gl.FLOAT, false, 0, nil)

	// Points Colours - Index 1 because in the vertex shader, that maps to colour
	gl.EnableVertexAttribArray(1)
	gl.BindBuffer(gl.ARRAY_BUFFER, colorVbo)
	gl.VertexAttribPointer(1, 3, gl.FLOAT, false, 0, nil)

	return vao
}

func compileShader(source string, shaderType uint32) (uint32, error) {
	shader := gl.CreateShader(shaderType)

	csources, free := gl.Strs(source)
	gl.ShaderSource(shader, 1, csources, nil)
	free()
	gl.CompileShader(shader)

	var status int32
	gl.GetShaderiv(shader, gl.COMPILE_STATUS, &status)
	if status == gl.FALSE {
		var logLength int32
		gl.GetShaderiv(shader, gl.INFO_LOG_LENGTH, &logLength)

		log := strings.Repeat("\x00", int(logLength+1))
		gl.GetShaderInfoLog(shader, logLength, nil, gl.Str(log))

		return 0, fmt.Errorf("failed to compile %v: %v", source, log)
	}

	return shader, nil
}