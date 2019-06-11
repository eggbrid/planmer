package com.kirito.planmer.calendar.presenter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.kirito.planmer.calendar.R;
import com.kirito.planmer.calendar.activity.AddTaskActivity;
import com.kirito.planmer.calendar.fragment.ToDayFragment;
import com.kirito.planmer.calendar.model.HomeData;
import com.kirito.planmer.calendar.model.TaskInfoModel;
import com.kirito.planmer.calendar.model.TaskModel;
import com.kirito.planmer.calendar.server.TaskServer;
import com.kirito.planmer.calendar.view.widget.ToDayFragmentView;
import com.study.xuan.shapebuilder.shape.ShapeBuilder;
import io.reactivex.Observable;
import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.mvp.NullData;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.baselib.util.SizeUtils;
import kirito.peoject.baselib.util.TimeUtils;
import kirito.peoject.baselib.util.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
public class TaskP extends BaseP<TaskServer> {
    //添加计划
    public Observable<NullData> add(final AddTaskActivity activity) {
        final String title = activity.view.mEdtTitle.getText().toString();
        if (TextUtils.isEmpty(title)) {
            ToastUtils.showShort("您还没有写计划名称呢~");
            return null;
        }

        String content = activity.view.mEdtContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showShort("随便写点计划内容吧~");
            return null;

        }
        if (activity.dTime == -1) {
            ToastUtils.showShort("执行一次计划需要多久呢？");
            return null;
        }
        if (activity.day == -1) {
            ToastUtils.showShort("这个计划持续几天呢？");
            return null;
        }
        if (activity.startTime == -1) {
            ToastUtils.showShort("这个计划什么时候开始呀？");
            return null;
        }

        activity.view.showLoading("提交中...");
        return request(getService().add(activity.startTime, title, content, activity.dTime, activity.day), new NetCallBack<NullData>() {
            @Override
            public void onGetData(NullData data) {
                ToastUtils.showShort("提交成功！");


                activity.finish();
            }

            @Override
            public void onFinish() {
                activity.view.dismissLoading();
            }

            @Override
            public void onFailure(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    //添加计划
    public Observable<HomeData> getHome(final ToDayFragmentView view) {
        view.showLoading("正在加载...");

        return request(getService().getHome(), new NetCallBack<HomeData>() {
            @Override
            public void onGetData(HomeData data) {

                if (data != null) {
                    TaskModel taskModel = data.getTask();
                    if (taskModel != null) {
                        view.taskCardView.setVisibility(View.VISIBLE);
                        view.rlNoTask.setVisibility(View.GONE);
                        view.taskCardView.setData(taskModel);
                        if (data.getTaskInfo() != null) {
                            view.taskCardView.setTask(data.getTaskInfo());
                        }
                    }else{
                        view.taskCardView.setVisibility(View.GONE);
                        view.rlNoTask.setVisibility(View.VISIBLE);

                    }
                    int isTodaySign = data.getIsTodaySign();
                    if (isTodaySign == 1) {
                        ShapeBuilder.create()
                                .Radius(SizeUtils.dp2px(5))
                                .Type(RECTANGLE)
                                .Solid(view.tvBtnSign.getContext().getResources().getColor(R.color.app_color_light))
                                .build(view.tvBtnSign);
                        view.tvBtnSign.setEnabled(false);
                        view.tvBtnSign.setText("已签到");
                    } else {
                        ShapeBuilder.create()
                                .Radius(SizeUtils.dp2px(5))
                                .Type(RECTANGLE)
                                .Solid(view.tvBtnSign.getContext().getResources().getColor(R.color.app_color))
                                .build(view.tvBtnSign);
                        view.tvBtnSign.setEnabled(true);
                        view.tvBtnSign.setText("签到");
                    }

                    if (data.getSign() != null) {
                        view.tvDay.setText(data.getSign().getDays() + "");
                    }
                }

            }

            @Override
            public void onFinish() {
                view.dismissLoading();
                view.srlRefresh.finishRefresh();

            }

            @Override
            public void onFailure(String message) {
                ToastUtils.showShort(message);
            }
        });
    }


    public void changeStatus(final ToDayFragmentView view, int status, int id) {
        view.showLoading("...");
        request(getService().changeStatus(status, id), new NetCallBack<TaskInfoModel>() {
            @Override
            public void onGetData(TaskInfoModel o) {
                if (o != null) {
                    view.taskCardView.setTask(o);
                }

            }

            @Override
            public void onFinish() {
                view.dismissLoading();
            }

            @Override
            public void onFailure(String message) {
                ToastUtils.showShort(message);

            }
        });
    }

}
