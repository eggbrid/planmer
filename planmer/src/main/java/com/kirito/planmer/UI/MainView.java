package com.kirito.planmer.UI;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.kirito.planmer.R;

import kirito.peoject.baselib.UI.widget.CommentTitleView;
import kirito.peoject.baselib.mvp.BaseV;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.constantlibs.UIConstant.activity.LandLibs;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 首页入口
 */
public class MainView extends BaseV {
    /**
     * 日历
     */
    public RadioButton mHome;
    /**
     * 首页
     */
    public RadioButton mUser;
    public RadioButton start;


    public RadioGroup mBottomTab;
    public ViewPager2 vp;


    public MainView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        mHome = (RadioButton) findViewById(R.id.Home);
        mUser = (RadioButton) findViewById(R.id.user);
        start = (RadioButton) findViewById(R.id.start);
        mBottomTab = (RadioGroup) findViewById(R.id.bottomTab);
        vp = findViewById(R.id.vp);
        vp.setUserInputEnabled(false);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibJumpHelper.startActivity(LandLibs.ACTIVITY_START);
            }
        });
    }


}
