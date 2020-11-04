package com.kirito.planmer.land.view;


import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @Description:
 * @Author: kirito
 * @CreatTime: 2020/11/3
 * @LastModify: kirito
 * @LastModifyTime: 2020/11/3
 * @LastCheckedBy: kirito
 */
public class Render implements GLSurfaceView.Renderer {
    private static final float r=0.8f;

    private static final float LIGHT[]={1.0f, 0.5f, 1.5f};
    private static final float CAMERA[]={0.0f, 0f, 3f};

    private int vCount;
    private Context ctx;
    private FloatBuffer fbVertex;
    private FloatBuffer fbNormal;
    private FloatBuffer fbLight;
    private FloatBuffer fbCamera;
    static float[] mMMatrix = new float[16];
    int mProgram;   // 自定义渲染管线程序id

    int muMVPMatrixHandle;// 总变换矩阵引用id
    int maPositionHandle; // 顶点位置属性引用id
    int maNormalHandle; //顶点法向量属性引用
    int maLightLocationHandle;//光源位置属性引用
    int maCameraHandle;//相机位置属性引用

    public static float[] mProjMatrix = new float[16];// 4x4矩阵 投影用
    public static float[] mVMatrix = new float[16];// 摄像机位置朝向9参数矩阵
    public static float[] mMVPMatrix;// 最后起作用的总变换矩阵

    public Render(Context ctx) {
        super();
        this.ctx = ctx;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0f, 0f, 0f, 1.0f);

        initVertex();

        initShader();

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_CULL_FACE);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;
        Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, -1, 1, 1, 80);

        Matrix.setLookAtM(mVMatrix, 0, CAMERA[0], CAMERA[1], CAMERA[2], 0f, 0f, 0f, 0f, 1.0f, 0.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

        draw();
    }

    public void draw() {
        GLES20.glUseProgram(mProgram);
        Matrix.setRotateM(mMMatrix, 0, 0, 0, 1, 0);

        mMVPMatrix = new float[16];
        Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, mMMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mMVPMatrix, 0);

        GLES20.glUniform3fv(maLightLocationHandle, 1, fbLight);
        //TODO 添加相机位置
        GLES20.glUniform3fv(maCameraHandle, 1, fbCamera);

        GLES20.glVertexAttribPointer(maPositionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, fbVertex);
        GLES20.glVertexAttribPointer(maNormalHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, fbNormal);

        GLES20.glEnableVertexAttribArray(maPositionHandle);
        GLES20.glEnableVertexAttribArray(maNormalHandle);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount);
    }

    //初始化数据
    private void initVertex() {
        ArrayList<Float> alVertix = new ArrayList<Float>();
        final int angleSpan = 10;// 将球进行单位切分的角度
        for (int vAngle = -90; vAngle < 90; vAngle = vAngle + angleSpan)// 垂直方向angleSpan度一份
        {
            for (int hAngle = 0; hAngle <= 360; hAngle = hAngle + angleSpan)// 水平方向angleSpan度一份
            {
                float x0 = (float) (r * Math.cos(Math.toRadians(vAngle)) * Math.cos(Math.toRadians(hAngle)));
                float y0 = (float) (r * Math.cos(Math.toRadians(vAngle)) * Math.sin(Math.toRadians(hAngle)));
                float z0 = (float) (r * Math.sin(Math .toRadians(vAngle)));

                float x1 = (float) (r * Math.cos(Math.toRadians(vAngle)) * Math.cos(Math.toRadians(hAngle + angleSpan)));
                float y1 = (float) (r * Math.cos(Math.toRadians(vAngle)) * Math.sin(Math.toRadians(hAngle + angleSpan)));
                float z1 = (float) (r  * Math.sin(Math.toRadians(vAngle)));

                float x2 = (float) (r * Math.cos(Math.toRadians(vAngle + angleSpan)) * Math.cos(Math.toRadians(hAngle + angleSpan)));
                float y2 = (float) (r * Math.cos(Math.toRadians(vAngle + angleSpan)) * Math.sin(Math.toRadians(hAngle + angleSpan)));
                float z2 = (float) (r  * Math.sin(Math.toRadians(vAngle + angleSpan)));

                float x3 = (float) (r * Math.cos(Math.toRadians(vAngle + angleSpan)) * Math.cos(Math.toRadians(hAngle)));
                float y3 = (float) (r * Math.cos(Math.toRadians(vAngle + angleSpan)) * Math.sin(Math.toRadians(hAngle)));
                float z3 = (float) (r * Math.sin(Math.toRadians(vAngle + angleSpan)));

                alVertix.add(x1);
                alVertix.add(y1);
                alVertix.add(z1);
                alVertix.add(x3);
                alVertix.add(y3);
                alVertix.add(z3);
                alVertix.add(x0);
                alVertix.add(y0);
                alVertix.add(z0);

                alVertix.add(x1);
                alVertix.add(y1);
                alVertix.add(z1);
                alVertix.add(x2);
                alVertix.add(y2);
                alVertix.add(z2);
                alVertix.add(x3);
                alVertix.add(y3);
                alVertix.add(z3);

            }
        }

        vCount = alVertix.size() / 3;
        float vertices[]=new float[alVertix.size()];
        for (int i=0;i<vertices.length;i++) {
            vertices[i]=alVertix.get(i);
        }

        ByteBuffer bbv = ByteBuffer.allocateDirect(vertices.length * 4);
        bbv.order(ByteOrder.nativeOrder());
        fbVertex = bbv.asFloatBuffer();
        fbVertex.put(vertices);
        fbVertex.position(0);

        ByteBuffer bbn = ByteBuffer.allocateDirect(vertices.length * 4);
        bbn.order(ByteOrder.nativeOrder());
        fbNormal = bbn.asFloatBuffer();
        fbNormal.put(vertices);
        fbNormal.position(0);

        ByteBuffer bbl = ByteBuffer.allocateDirect(LIGHT.length * 4);
        bbl.order(ByteOrder.nativeOrder());
        fbLight = bbl.asFloatBuffer();
        fbLight.put(LIGHT);
        fbLight.position(0);

        ByteBuffer bbc = ByteBuffer.allocateDirect(CAMERA.length * 4);
        bbc.order(ByteOrder.nativeOrder());
        fbCamera = bbc.asFloatBuffer();
        fbCamera.put(CAMERA);
        fbCamera.position(0);
    }



    //初始化shader
    private void initShader() {
        String vertex = loadSH("vertex.sh");
        String shader = loadSH("frag.sh");

        int verS = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        if (verS != 0) {
            GLES20.glShaderSource(verS, vertex);
            GLES20.glCompileShader(verS);
        }

        int fragS = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        if (fragS != 0) {
            GLES20.glShaderSource(fragS, shader);
            GLES20.glCompileShader(fragS);
        }
        mProgram = GLES20.glCreateProgram();
        if (mProgram != 0) {
            GLES20.glAttachShader(mProgram, verS);
            GLES20.glAttachShader(mProgram, fragS);
            GLES20.glLinkProgram(mProgram);
        }

        maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

        maNormalHandle= GLES20.glGetAttribLocation(mProgram, "aNormal");
        maLightLocationHandle=GLES20.glGetUniformLocation(mProgram, "uLightLocation");
        //TODO 添加相机引用
        maCameraHandle=GLES20.glGetUniformLocation(mProgram, "uCamera");
    }

    //将sh文件加载进来
    private String loadSH(String fname) {
        String result = null;
        try {
            InputStream in = ctx.getAssets().open(fname);
            int ch = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((ch = in.read()) != -1) {
                baos.write(ch);
            }
            byte[] buff = baos.toByteArray();
            baos.close();
            in.close();
            result = new String(buff, "UTF-8");
            result = result.replaceAll("\\r\\n", "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}