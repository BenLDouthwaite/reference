package main

import (
	"github.com/go-gl/gl/v4.1-core/gl"
)
var (
	white = []float32 {
		0.9,  0.9,  0.9,
		0.9,  0.9,  0.9,
		0.9,  0.9,  0.9,

		0.9,  0.9,  0.9,
		0.9,  0.9,  0.9,
		0.9,  0.9,  0.9,
	}
)
type square struct {
	drawable uint32

	x int
	y int
}

func (s *square) draw() {
	gl.BindVertexArray(s.drawable)
	gl.DrawArrays(gl.TRIANGLES, 0, int32(len(squarePoints) / 3))
}

func (s *square) updateDrawable() {
	// TODO How do I update the colour here? Based on number of ticks
	//s.drawable = makeVao()
}

func makeSquare(x, y int) *square {
	points := make([]float32, len(squarePoints), len(squarePoints))
	copy(points, squarePoints)

	for i := 0; i < len(points); i++ {
		var position float32
		var size float32
		switch i % 3 {
		case 0:
			size = 1.0 / float32(num)
			position = float32(x) * size
		case 1:
			size = 1.0 / float32(num)
			position = float32(y) * size
		default:
			continue
		}

		if points[i] < 0 {
			points[i] = (position * 2) - 1
		} else {
			points[i] = ((position + size) * 2) - 1
		}
	}

	colour := squareColour;

	if x == 5 && y == 2 {
		colour = white
	}

	return &square {
		drawable: makeVao(points, colour),

		x: x,
		y: y,
	}
}