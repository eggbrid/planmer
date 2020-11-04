package com.kirito.planmer.land.view;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * @Description:
 * @Author: kirito
 * @CreatTime: 2020/10/31
 * @LastModify: kirito
 * @LastModifyTime: 2020/10/31
 * @LastCheckedBy: kirito
 */
public class Sphere {


    private FloatBuffer makeFloatBufferFromArray(float[] array) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(array.length * Float.SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(array);
        floatBuffer.position(0);
        return floatBuffer;
    }
    public void draw(GL10 gl) {

        float angleA, angleB;
        float cos, sin;
        float r1, r2;
        float h1, h2;
        float step = 2.0f;
        float[][] v = new float[32][3];

        ByteBuffer vbb;
        FloatBuffer vBuf;
//        gl.glEnable(GL10.GL_TEXTURE_2D);

        vbb = ByteBuffer.allocateDirect(v.length * v[0].length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vBuf = vbb.asFloatBuffer();



        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        for (angleA = -90.0f; angleA < 90.0f; angleA += step) {
            int n = 0;

            r1 = (float) Math.cos(angleA * Math.PI / 180.0);
            r2 = (float) Math.cos((angleA + step) * Math.PI / 180.0);
            h1 = (float) Math.sin(angleA * Math.PI / 180.0);
            h2 = (float) Math.sin((angleA + step) * Math.PI / 180.0);

            // 固定纬度, 360 度旋转遍历一条纬线
            for (angleB = 0.0f; angleB <= 360.0f; angleB += step) {

                cos = (float) Math.cos(angleB * Math.PI / 180.0);
                sin = -(float) Math.sin(angleB * Math.PI / 180.0);

                v[n][0] = (r2 * cos);
                v[n][1] = (h2);
                v[n][2] = (r2 * sin);
                v[n + 1][0] = (r1 * cos);
                v[n + 1][1] = (h1);
                v[n + 1][2] = (r1 * sin);

                vBuf.put(v[n]);
                vBuf.put(v[n + 1]);

                n += 2;

                if (n > 31) {
                    vBuf.position(0);

                    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vBuf);
                    gl.glNormalPointer(GL10.GL_FLOAT, 0, vBuf);
                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, n);

                    n = 0;
                    angleB -= step;
                }

            }
            vBuf.position(0);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vBuf);
            gl.glNormalPointer(GL10.GL_FLOAT, 0, vBuf);
//            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureCoords.get(i));
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, n);
        }

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
//        gl.glDisable(GL10.GL_TEXTURE_2D);

    }
}
