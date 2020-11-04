package com.kirito.planmer.land.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.kirito.planmer.land.R;
import com.kirito.planmer.land.view.HomeFragmentView;
import com.kirito.planmer.land.view.OpenGLView;

import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.constantlibs.UIConstant.activity.CalendarLibs;
import kirito.peoject.constantlibs.UIConstant.activity.LandLibs;

/**
 * @auther kirito
 * @Date 2019-05-21
 * @NOTE 类说明
 */
@Route(path = LandLibs.FRAGMENT_HOME)
//https://blog.csdn.net/yarkey09/article/details/36627693?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~first_rank_v2~rank_v28-1-36627693.nonecase&utm_term=android%20画球体&spm=1000.2123.3001.4430
public class HomeFragment extends BaseFragment<HomeFragmentView> {
    @Override
    public void initImmersionBar() {
        ImmersionBar immersionBar= ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_BAR);
        immersionBar.init();
    }

    @Override
    public void afterInitView(HomeFragmentView view) {

    }

}
