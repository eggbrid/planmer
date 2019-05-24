package com.kirito.planmer.UI;

import android.os.Handler;
import com.kirito.planmer.PlanmerApplication;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.baselib.util.IntentUtil;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;

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
//            IntentUtil.startActivity(this,MainActivity.class);
            LibJumpHelper.startActivity(UserLibs.ACTIVITY_LOPGIN);

            finish();
        }else {

                v.shimmer_view_container.startShimmer();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    IntentUtil.startActivity(SplashActivity.this,MainActivity.class);

                    LibJumpHelper.startActivity(UserLibs.ACTIVITY_LOPGIN);
                    finish();

                }
            },2000);
        }

    }

    @Override
    public void onBackClick() {

    }
}
