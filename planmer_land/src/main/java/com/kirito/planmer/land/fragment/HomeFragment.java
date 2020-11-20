package com.kirito.planmer.land.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.kirito.planmer.land.view.widget.HomeFragmentView;

import kirito.peoject.baselib.UI.BaseFragment;
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
        ImmersionBar.with(this).init();
    }

    @Override
    public void afterInitView(HomeFragmentView view) {

    }

}
