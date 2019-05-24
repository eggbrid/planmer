package com.kirito.planmer.UI;

import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.kirito.planmer.R;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
public class SplashActivityView extends BaseV {
   public ShimmerFrameLayout shimmer_view_container;
    public SplashActivityView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        shimmer_view_container=findViewById(R.id.shimmer_view_container);
        shimmer_view_container.setShimmer( new Shimmer.ColorHighlightBuilder()
                .setHighlightColor(activity.getResources().getColor(R.color.colorffffff))
                .setFixedWidth(300)
                .setBaseAlpha(0)
                .setDuration(3000).setDirection(Shimmer.Direction.LEFT_TO_RIGHT).setRepeatCount(1).build());
    }

    @Override
    public boolean isShowBar() {
        return false;
    }
}
