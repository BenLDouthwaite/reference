package com.example.ben.myapplication.Graphics;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Ben on 06/09/2015.
 */
public class Hexagon extends Polygon{

    private static final short drawOrder[] = {0,1,2,2,3,0,0,3,4,4,5,0,0,5,6,6,1,0};

    public Hexagon(float[] vertices) {
        super(vertices,drawOrder);
    }

    public static float[] createHexagonVertices(float x, float y, float radius){
        float vertices[] = {
                0.0f + x, 0.0f + y, 0.0f, // center
                radius + x, 0.0f + y, 0.0f, // right
                (radius / 2) + x, radius + y, 0.0f, // top right
                -(radius / 2) + x, radius + y, 0.0f, // top left
                -radius + x, 0.0f + y, 0.0f, // left
                -(radius / 2) + x, -radius + y, 0.0f, // bottom left
                (radius / 2) + x, -radius + y, 0.0f  // bottom right
        };
        return vertices;
        /*
        //FROM A PREVIOUS APPLICATION. MATH FOR HEX COORDINATES
        cornerCoordinates[0].setLocation(xPosition - (size/2), yPosition - ((Math.sqrt(3)* size)/2));
        cornerCoordinates[1].setLocation(xPosition + (size/2), yPosition - ((Math.sqrt(3)* size)/2));
        cornerCoordinates[2].setLocation(xPosition + size, yPosition);
        cornerCoordinates[3].setLocation(xPosition + (size/2), yPosition + ((Math.sqrt(3)* size)/2));
        cornerCoordinates[4].setLocation(xPosition - (size/2), yPosition + ((Math.sqrt(3)* size)/2));
        cornerCoordinates[5].setLocation(xPosition - size, yPosition);
        */
    }


}
