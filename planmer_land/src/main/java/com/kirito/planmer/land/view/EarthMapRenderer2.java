package com.kirito.planmer.land.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;

import com.kirito.planmer.land.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @Description:
 * @Author: kirito
 * @CreatTime: 2020/11/2
 * @LastModify: kirito
 * @LastModifyTime: 2020/11/2
 * @LastCheckedBy: kirito
 * https://blog.csdn.net/gongxiaoou/article/details/89289320
 */
public class EarthMapRenderer2 implements GLSurfaceView.Renderer {
    public EarthMapRenderer2(Context context) {
        this.context = context;
        calculateAttribute();
    }

    Context context;
    private static final String TAG = "EarthMapRenderer";
    private static final int BYTES_PER_FLOAT = 4;
    //顶点位置缓存
    private FloatBuffer vertexBuffer;
    //纹理顶点位置缓存
    private FloatBuffer mTexVertexBuffer;
    private FloatBuffer fbLight;
    private FloatBuffer fbNormal;

    //渲染程序
    private int mProgram;

    //图片生成的位图
    private Bitmap mBitmap;
    //纹理id
    private int textureId;
    private int mHRotateMatrix;

    //向量个数
    private int vCount;

    //相关属性id
    private int mHProjMatrix;
    private int mHViewMatrix;
    private int mHModelMatrix;
    private int mHUTexture;
    private int mHPosition;
    private int mHCoordinate;
    private float[] uRotateMatrix = new float[16];
    //相机矩阵
    private final float[] mViewMatrix = new float[16];
    //投影矩阵
    private final float[] mProjectMatrix = new float[16];

    private final float[] mModelMatrix = new float[16];
    private float x = 0f;
    int maLightLocationHandle;//光源位置属性引用
    int maNormalHandle; //顶点法向量属性引用

    private static final float LIGHT[]={2.0f,-10f, 0f};
    private static final float CAMERA[]={0.0f, 0f, 3f};
//    private FloatBuffer fbCamera;
//    int maCameraHandle;//相机位置属性引用

    //计算顶点坐标和纹理坐标
    private void calculateAttribute() {
        float radius = 1.0f; // 球的半径
        double angleSpan = Math.PI / 90f; // 将球进行单位切分的角度
        ArrayList<Float> alVertix = new ArrayList<>();
        ArrayList<Float> textureVertix = new ArrayList<>();
        for (double vAngle = 0; vAngle < Math.PI; vAngle = vAngle + angleSpan) {

            for (double hAngle = 0; hAngle < 2 * Math.PI; hAngle = hAngle + angleSpan) {
                float x0 = (float) (radius * Math.sin(vAngle) * Math.cos(hAngle));
                float y0 = (float) (radius * Math.sin(vAngle) * Math.sin(hAngle));
                float z0 = (float) (radius * Math.cos((vAngle)));

                float x1 = (float) (radius * Math.sin(vAngle) * Math.cos(hAngle + angleSpan));
                float y1 = (float) (radius * Math.sin(vAngle) * Math.sin(hAngle + angleSpan));
                float z1 = (float) (radius * Math.cos(vAngle));

                float x2 = (float) (radius * Math.sin(vAngle + angleSpan) * Math.cos(hAngle + angleSpan));
                float y2 = (float) (radius * Math.sin(vAngle + angleSpan) * Math.sin(hAngle + angleSpan));
                float z2 = (float) (radius * Math.cos(vAngle + angleSpan));

                float x3 = (float) (radius * Math.sin(vAngle + angleSpan) * Math.cos(hAngle));
                float y3 = (float) (radius * Math.sin(vAngle + angleSpan) * Math.sin(hAngle));
                float z3 = (float) (radius * Math.cos(vAngle + angleSpan));


                float s0 = (float) (-hAngle / Math.PI / 2);
                float s1 = (float) (-(hAngle + angleSpan) / Math.PI / 2);

                float t0 = (float) (vAngle / Math.PI);
                float t1 = (float) ((vAngle + angleSpan) / Math.PI);

                alVertix.add(x1);
                alVertix.add(y1);
                alVertix.add(z1);
                alVertix.add(x0);
                alVertix.add(y0);
                alVertix.add(z0);
                alVertix.add(x3);
                alVertix.add(y3);
                alVertix.add(z3);

                textureVertix.add(s1);// x1 y1对应纹理坐标
                textureVertix.add(t0);
                textureVertix.add(s0);// x0 y0对应纹理坐标
                textureVertix.add(t0);
                textureVertix.add(s0);// x3 y3对应纹理坐标
                textureVertix.add(t1);

                alVertix.add(x1);
                alVertix.add(y1);
                alVertix.add(z1);
                alVertix.add(x3);
                alVertix.add(y3);
                alVertix.add(z3);
                alVertix.add(x2);
                alVertix.add(y2);
                alVertix.add(z2);

                textureVertix.add(s1);// x1 y1对应纹理坐标
                textureVertix.add(t0);
                textureVertix.add(s0);// x3 y3对应纹理坐标
                textureVertix.add(t1);
                textureVertix.add(s1);// x2 y3对应纹理坐标
                textureVertix.add(t1);
            }
        }
        vCount = alVertix.size() / 3;

        ByteBuffer bbl = ByteBuffer.allocateDirect(LIGHT.length * 4);
        bbl.order(ByteOrder.nativeOrder());
        fbLight = bbl.asFloatBuffer();
        fbLight.put(LIGHT);
        fbLight.position(0);

        fbNormal = convertToFloatBuffer(alVertix);
        vertexBuffer = convertToFloatBuffer(alVertix);
        mTexVertexBuffer = convertToFloatBuffer(textureVertix);
//        ByteBuffer bbc = ByteBuffer.allocateDirect(CAMERA.length * 4);
//        bbc.order(ByteOrder.nativeOrder());
//        fbCamera = bbc.asFloatBuffer();
//        fbCamera.put(CAMERA);
//        fbCamera.position(0);
    }

    //动态数组转FloatBuffer
    private FloatBuffer convertToFloatBuffer(ArrayList<Float> data) {
        float[] d = new float[data.size()];
        for (int i = 0; i < d.length; i++) {
            d[i] = data.get(i);
        }

        ByteBuffer buffer = ByteBuffer.allocateDirect(data.size() * 4);
        buffer.order(ByteOrder.nativeOrder());
        FloatBuffer ret = buffer.asFloatBuffer();
        ret.put(d);
        ret.position(0);
        return ret;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //将背景设置为灰色
        GLES30.glClearColor(0f, 0f, 0f, 0.0f);

        //编译顶点着色程序
        String vertexShaderStr = ResReadUtils.readResource(context, R.raw.vertex_earth_shader2);
        int vertexShaderId = ShaderUtils.compileVertexShader(vertexShaderStr);
        //编译片段着色程序
        String fragmentShaderStr = ResReadUtils.readResource(context, R.raw.fragment_earth_shader2);
        int fragmentShaderId = ShaderUtils.compileFragmentShader(fragmentShaderStr);
        //连接程序
        mProgram = ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);
        //在OpenGLES环境中使用程序
        GLES30.glUseProgram(mProgram);

        //编译glprogram并获取控制句柄
        mHProjMatrix = GLES20.glGetUniformLocation(mProgram, "uProjMatrix");
        mHViewMatrix = GLES20.glGetUniformLocation(mProgram, "uViewMatrix");
        mHModelMatrix = GLES20.glGetUniformLocation(mProgram, "uModelMatrix");
        mHRotateMatrix = GLES30.glGetUniformLocation(mProgram, "uRotateMatrix");
        mHUTexture = GLES20.glGetUniformLocation(mProgram, "uTexture");
        maLightLocationHandle=GLES20.glGetUniformLocation(mProgram, "uLightLocation");

        mHPosition = GLES20.glGetAttribLocation(mProgram, "aPosition");
        mHCoordinate = GLES20.glGetAttribLocation(mProgram, "aCoordinate");
        maNormalHandle= GLES20.glGetAttribLocation(mProgram, "aNormal");
        //TODO 添加相机引用
//        maCameraHandle=GLES20.glGetUniformLocation(mProgram, "uCamera");
        //加载纹理
        textureId = loadTexture(context, R.drawable.ass);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置绘制窗口
        GLES30.glViewport(0, 0, width, height);
        Log.e("wangxu", 11111 + "");
        setSize(width, height);
    }

    public void setSize(int width, int height) {
        //计算宽高比
        float ratio = (float) width / height;
        //透视投影矩阵/视锥
        Matrix.perspectiveM(mProjectMatrix, 0, 60, ratio, 1f, 300f);
        //设置相机位置
        Matrix.setLookAtM(mViewMatrix, 0, 0f, 4f, 2f, 0.0f, 0.0f, 0f, 0f, 0f, 1f);
        //模型矩阵
        Matrix.setIdentityM(mModelMatrix, 0);
//        Matrix.rotateM(mViewMatrix, 0, x, 0, 0, 1);
//        Matrix.rotateM(mViewMatrix, 0, 30, 1, 0, 0);

        //Matrix.rotateM(mViewMatrix,0,180,0,0,1);
    }

    public void rotate(float x) {
        this.x = x;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //把颜色缓冲区设置为我们预设的颜色
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);


        GLES30.glUniformMatrix4fv(mHRotateMatrix, 1, false, uRotateMatrix, 0);

        GLES30.glUniformMatrix4fv(mHProjMatrix, 1, false, mProjectMatrix, 0);
        GLES30.glUniformMatrix4fv(mHViewMatrix, 1, false, mViewMatrix, 0);
        GLES30.glUniformMatrix4fv(mHModelMatrix, 1, false, mModelMatrix, 0);
        GLES20.glUniform3fv(maLightLocationHandle, 1, fbLight);
//        GLES20.glUniform3fv(maCameraHandle, 1, fbCamera);

        GLES30.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES30.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);

        GLES30.glEnableVertexAttribArray(mHPosition);
        GLES30.glVertexAttribPointer(mHPosition, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);
        GLES20.glVertexAttribPointer(maNormalHandle, 3, GLES20.GL_FLOAT, false, 0, fbNormal);
        GLES30.glEnableVertexAttribArray(maNormalHandle);

        GLES30.glEnableVertexAttribArray(mHCoordinate);
        GLES30.glVertexAttribPointer(mHCoordinate, 2, GLES20.GL_FLOAT, false, 0, mTexVertexBuffer);

        GLES30.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount);

        GLES30.glDisableVertexAttribArray(mHCoordinate);
        GLES30.glDisableVertexAttribArray(mHPosition);
//        GLES30.rota
//        gl.glRotatex(x,0,0,1);

        Matrix.rotateM(mViewMatrix, 0, x, 0, 0, 1);
        if (x<=-1){
            x=x+1;
        }else if (x>=1){
            x=x-1;
        }else if (x<1&&x>=0||x<=0&&x>-1){
            x=0.05f;
        }


    }

    private int loadTexture(Context context, int resourceId) {
        final int[] textureIds = new int[1];
        //创建一个纹理对象
        GLES30.glGenTextures(1, textureIds, 0);
        if (textureIds[0] == 0) {
            Log.e(TAG, "Could not generate a new OpenGL textureId object.");
            return 0;
        }
        final BitmapFactory.Options options = new BitmapFactory.Options();
        //这里需要加载原图未经缩放的数据
        options.inScaled = false;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        if (mBitmap == null) {
            Log.e(TAG, "Resource ID " + resourceId + " could not be decoded.");
            GLES30.glDeleteTextures(1, textureIds, 0);
            return 0;
        }
        //绑定纹理到OpenGL
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureIds[0]);

        //设置默认的纹理过滤参数
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR_MIPMAP_LINEAR);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);

        //加载bitmap到纹理中
        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, mBitmap, 0);

        //生成MIP贴图
        GLES30.glGenerateMipmap(GLES30.GL_TEXTURE_2D);

        //数据如果已经被加载进OpenGL,则可以回收该bitmap
        mBitmap.recycle();

        //取消绑定纹理
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, 0);

        return textureIds[0];
    }
}