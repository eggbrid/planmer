package com.kirito.planmer.land.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;

import com.kirito.planmer.land.R;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static javax.microedition.khronos.opengles.GL10.GL_DIFFUSE;
import static javax.microedition.khronos.opengles.GL10.GL_LIGHT0;
import static javax.microedition.khronos.opengles.GL10.GL_LIGHT_MODEL_AMBIENT;
import static javax.microedition.khronos.opengles.GL10.GL_POSITION;
import static javax.microedition.khronos.opengles.GL10.GL_SHININESS;
import static javax.microedition.khronos.opengles.GL10.GL_SPECULAR;

/**
 * @Description:
 * @Author: kirito
 * @CreatTime: 2020/10/31
 * @LastModify: kirito
 * @LastModifyTime: 2020/10/31
 * @LastCheckedBy: kirito
 */
public class OpenGLRenderer4 implements GLSurfaceView.Renderer {
    public OpenGLRenderer4(Context context) {
        this.context = context;
    }
    private int texture;

    private Context context;
    // 环境光
    private final float[] mat_ambient = {0.2f, 0.3f, 0.4f, 1f};
    private FloatBuffer mat_ambient_buf;
    // 平行入射光
    private final float[] mat_diffuse = {0.3f, 0.3f, 0.3f, 1f};
    private FloatBuffer mat_diffuse_buf;
    // 高亮区域
    private final float[] mat_specular = {0.2f * 0.4f, 0.2f * 0.6f, 0.2f * 0.8f, 1f};
    private FloatBuffer mat_specular_buf;


    private final float[] light_position = {1.0f, 1.0f, 1.0f, 1.0f};//1.0表示光源为点坐标x,y,z
    private FloatBuffer light_position_buf;

    private final float[] white_light = {1.0f, 1.0f, 1.0f, 1.0f};   //光源的颜色
    private FloatBuffer white_light_buf;

    private final float[] lmodel_ambient = {0.2f, 0.2f, 0.2f, 1.0f};//微弱环境光，使物体可见
    private FloatBuffer lmodel_ambient_buf;


    private Sphere mSphere = new Sphere();


    public volatile float mLightX = 10f;
    public volatile float mLightY = 10f;
    public volatile float mLightZ = 10f;

    @Override
    public void onDrawFrame(GL10 gl) {

        // 清楚屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // 重置当前的模型观察矩阵
        gl.glLoadIdentity();

        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);

        // 材质
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambient_buf);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL_DIFFUSE, mat_diffuse_buf);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL_SPECULAR, mat_specular_buf);
//        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL_SHININESS, mat_specular_buf);


//        gl.glLightfv(GL_LIGHT0,GL_POSITION,light_position_buf);//光源编号-7，光源特性，参数数据
//        gl.glLightfv(GL_LIGHT0,GL_DIFFUSE,white_light_buf);
//        gl.glLightfv(GL_LIGHT0,GL_SPECULAR,white_light_buf);
//        gl.glLightModelfv(GL_LIGHT_MODEL_AMBIENT,lmodel_ambient_buf); //指定全局的环境光，物体才能可见//*/

        // 镜面指数 0~128 越小越粗糙
        gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL_SHININESS, 96.0f);

        //光源位置
        float[] light_position = {mLightX, mLightY, mLightZ, 0.0f};
        ByteBuffer mpbb = ByteBuffer.allocateDirect(light_position.length * 4);
        mpbb.order(ByteOrder.nativeOrder());
        FloatBuffer mat_posiBuf = mpbb.asFloatBuffer();
        mat_posiBuf.put(light_position);
        mat_posiBuf.position(0);
        gl.glLightfv(GL_LIGHT0, GL_POSITION, mat_posiBuf);
        gl.glTranslatef(0.0f, 0.0f, -3.0f);

        mSphere.draw(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        // 设置输出屏幕大小
        gl.glViewport(0, 0, width, height);

        // 设置投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // 重置投影矩阵
        gl.glLoadIdentity();
        // 设置视口大小
        // gl.glFrustumf(0, width, 0, height, 0.1f, 100.0f);
        gl.glClearColor(0,0,0,0);

        GLU.gluPerspective(gl, 90.0f, (float) width / height, 0.1f, 50.0f);

        // 选择模型观察矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // 重置模型观察矩阵
        gl.glLoadIdentity();

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {

        // 对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        // 背景：黑色
        gl.glClearColor(0f, 0f, 0f, 1f);
        // 启动阴影平滑
        gl.glShadeModel(GL10.GL_SMOOTH);

        // 复位深度缓存
        gl.glClearDepthf(1.0f);
        // 启动深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // 所做深度测试的类型
        gl.glDepthFunc(GL10.GL_LEQUAL);
        initTexture(gl,R.drawable.er);
        initBuffers();

    }
    public int initTexture(GL10 gl, int resourceId) {
        int[] textures = new int[1];
        gl.glGenTextures(1, textures, 0);
        int currTextureId = textures[0];
        gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

        InputStream is = context.getResources().openRawResource(resourceId);
        Bitmap bitmapTmp;
        try {
            bitmapTmp = BitmapFactory.decodeStream(is);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmapTmp, 0);
        bitmapTmp.recycle();
        return currTextureId;
    }

    private void initBuffers() {
        ByteBuffer bufTemp = ByteBuffer.allocateDirect(mat_ambient.length * 4);
        bufTemp.order(ByteOrder.nativeOrder());
        mat_ambient_buf = bufTemp.asFloatBuffer();
        mat_ambient_buf.put(mat_ambient);
        mat_ambient_buf.position(0);

        bufTemp = ByteBuffer.allocateDirect(mat_diffuse.length * 4);
        bufTemp.order(ByteOrder.nativeOrder());
        mat_diffuse_buf = bufTemp.asFloatBuffer();
        mat_diffuse_buf.put(mat_diffuse);
        mat_diffuse_buf.position(0);

        bufTemp = ByteBuffer.allocateDirect(mat_specular.length * 4);
        bufTemp.order(ByteOrder.nativeOrder());
        mat_specular_buf = bufTemp.asFloatBuffer();
        mat_specular_buf.put(mat_specular);
        mat_specular_buf.position(0);

        bufTemp = ByteBuffer.allocateDirect(light_position.length * 4);
        bufTemp.order(ByteOrder.nativeOrder());
        light_position_buf = bufTemp.asFloatBuffer();
        light_position_buf.put(light_position);
        light_position_buf.position(0);

        bufTemp = ByteBuffer.allocateDirect(white_light.length * 4);
        bufTemp.order(ByteOrder.nativeOrder());
        white_light_buf = bufTemp.asFloatBuffer();
        white_light_buf.put(white_light);
        white_light_buf.position(0);


        bufTemp = ByteBuffer.allocateDirect(lmodel_ambient.length * 4);
        bufTemp.order(ByteOrder.nativeOrder());
        lmodel_ambient_buf = bufTemp.asFloatBuffer();
        lmodel_ambient_buf.put(lmodel_ambient);
        lmodel_ambient_buf.position(0);


    }
}