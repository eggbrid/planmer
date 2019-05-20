package com.kirito.planmer.UI;

import android.view.View;
import kirito.peoject.baselib.UI.BaseActivity;

public class MainActivity extends BaseActivity<MainView> {


    @Override
    public void afterInitView(MainView v) {
        v.mAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        super.onClick(v);
        if (v.getId() == view.mAdd.getId()) {

        }
    }
}
