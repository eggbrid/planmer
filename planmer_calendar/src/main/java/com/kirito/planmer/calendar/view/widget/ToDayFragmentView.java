package com.kirito.planmer.calendar.view.widget;

import androidx.appcompat.app.AppCompatActivity;
import com.kirito.planmer.calendar.R;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-05-22
 * @NOTE 类说明
 */
public class ToDayFragmentView extends BaseV {
    public ToDayFragmentView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.item_layout_home;
    }

    @Override
    public void initView() {

    }
}
