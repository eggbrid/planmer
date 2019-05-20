package com.kirito.planmer.UI;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.kirito.planmer.R;
import kirito.peoject.baselib.mvp.BaseV;

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
    public RadioGroup mBottomTab;
    public TextView mAdd;

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
        mBottomTab = (RadioGroup) findViewById(R.id.bottomTab);
        mAdd = (TextView) findViewById(R.id.Add);
    }



}
