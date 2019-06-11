package com.kirito.planmer.calendar.view.widget;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.kirito.planmer.calendar.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-05-22
 * @NOTE 类说明
 */
public class ToDayFragmentView extends BaseV {

    public TextView mTvSign;
    public TaskCardView taskCardView;
    public TextView tvBtnSign;
    public SmartRefreshLayout srlRefresh;
    public RelativeLayout rlNoTask;
    public TextView tvDay;

    public ToDayFragmentView(AppCompatActivity activity) {
        super(activity);

    }

    @Override
    public int setViewLayout() {
        return R.layout.item_layout_home;
    }

    @Override
    public void initView() {
        taskCardView = findViewById(R.id.taskCardView);
        mTvSign = findViewById(R.id.tvSign);
        tvDay=findViewById(R.id.tvDay);
        tvBtnSign = findViewById(R.id.tvBtnSign);
        srlRefresh = findViewById(R.id.srlRefresh);
        rlNoTask = findViewById(R.id.rlNoTask);
    }
}
