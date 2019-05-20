package com.kirito.planmer;

import android.app.Application;
import kirito.peoject.baselib.BaseLib;
import kirito.peoject.baselib.thirdPart.Retrofit.XRetrofitConfig;

/**
 * @auther kirito
 * @Date 2019-05-16
 * @NOTE 类说明
 */
public class PlanmerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseLib.init(this,BuildConfig.DEBUG,new XRetrofitConfig());
    }
}
