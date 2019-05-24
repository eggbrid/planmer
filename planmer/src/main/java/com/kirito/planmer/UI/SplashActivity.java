package com.kirito.planmer.UI;

import android.os.Handler;
import com.kirito.planmer.PlanmerApplication;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.util.IntentUtil;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
public class SplashActivity extends BaseActivity<SplashActivityView> {
    @Override
    public void afterInitView(SplashActivityView v) {
        super.afterInitView(v);
        long startDtime=System.currentTimeMillis()-PlanmerApplication.startTime;

        if (startDtime>2000){
            IntentUtil.startActivity(this,MainActivity.class);
            finish();
        }else {

                v.shimmer_view_container.startShimmer();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    IntentUtil.startActivity(SplashActivity.this,MainActivity.class);
                    finish();

                }
            },2000);
        }

    }
}
