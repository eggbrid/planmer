package com.kirito.planmer.land.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * @Description:
 * @Author: kirito
 * @CreatTime: 2020/10/31
 * @LastModify: kirito
 * @LastModifyTime: 2020/10/31
 * @LastCheckedBy: kirito
 */
public class OpenGLView extends GLSurfaceView {

    private EarthMapRenderer2 mRenderer;
//private Render mRenderer;
    private float mDownX = 0.0f;
    private float mDownY = 0.0f;

    public OpenGLView(Context context) {
        super(context);
        setEGLContextClientVersion(2);

        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);
//        setBackgroundColor(Color.argb(0,0,0,0));
        mRenderer = new EarthMapRenderer2(context);
//        mRenderer=new Render(context);
        this.setRenderer(mRenderer);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                return true;
            case MotionEvent.ACTION_UP:
                return true;
            case MotionEvent.ACTION_MOVE:
                float mX = event.getX();
                float mY = event.getY();
                mRenderer.rotate((mDownX-mX)/10  );

//                mRenderer.mLightX += (mX - mDownX) / 10;
//                mRenderer.mLightY -= (mY - mDownY) / 10;
                mDownX = mX;
                mDownY = mY;
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}

