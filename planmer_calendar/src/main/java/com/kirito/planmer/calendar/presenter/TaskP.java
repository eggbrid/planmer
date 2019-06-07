package com.kirito.planmer.calendar.presenter;

import android.graphics.Color;
import android.text.TextUtils;
import com.kirito.planmer.calendar.R;
import com.kirito.planmer.calendar.activity.AddTaskActivity;
import com.kirito.planmer.calendar.fragment.ToDayFragment;
import com.kirito.planmer.calendar.model.HomeData;
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
    //添加任务
    public Observable<NullData> add(final AddTaskActivity activity) {
        final String title = activity.view.mEdtTitle.getText().toString();
        if (TextUtils.isEmpty(title)) {
            ToastUtils.showShort("您还没有写任务名称呢~");
            return null;
        }

        String content = activity.view.mEdtContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showShort("随便写点任务内容吧~");
            return null;

        }
        if (activity.dTime == -1) {
            ToastUtils.showShort("执行一次任务需要多久呢？");
            return null;
        }
        if (activity.day == -1) {
            ToastUtils.showShort("这个任务持续几天呢？");
            return null;
        }
        if (activity.startTime == -1) {
            ToastUtils.showShort("这个任务什么时候开始呀？");
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

    //添加任务
    public Observable<HomeData> getHome(final ToDayFragment toDayFragment, final ToDayFragmentView view) {
        view.showLoading("正在加载...");

        return request(getService().getHome(), new NetCallBack<HomeData>() {
            @Override
            public void onGetData(HomeData data) {

                if (data != null) {
                    TaskModel taskModel = data.getTask();
                    if (taskModel != null) {
                        view.mTvTaskContent.setText(taskModel.getContent());

                        view.mTvTask.setText(taskModel.getTitle());

                        String text = " 持续";
                        String times = "";

                        long h = taskModel.getdTime() / (60 * 60 * 1000);
                        if (h > 0) {
                            text = text + h + "时";
                            times = h + "时";

                        }
                        long m = (taskModel.getdTime() - h * 60 * 60 * 1000) / (60 * 1000);
                        if (m > 0) {
                            text = text + m + "分";
                            times = times + m + "分";

                        }
                        long s = (taskModel.getdTime() - h * 60 * 60 * 1000 - m * 60 * 1000) / 1000;
                        if (s > 0) {
                            text = text + s + "秒";
                            times = times + s + "秒";
                        }

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd日");
                        view.mTvTime.setText(TimeUtils.date2String(new Date(taskModel.getStarTime()), simpleDateFormat) + "-" + TimeUtils.date2String(new Date(taskModel.getEndTime()), simpleDateFormat) + text);
                        view.tvRemainingTime.setText(times);
                        toDayFragment.times = taskModel.getdTime();

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
                    }else{
                        ShapeBuilder.create()
                                .Radius(SizeUtils.dp2px(5))
                                .Type(RECTANGLE)
                                .Solid(view.tvBtnSign.getContext().getResources().getColor(R.color.app_color))
                                .build(view.tvBtnSign);
                        view.tvBtnSign.setEnabled(true);
                        view.tvBtnSign.setText("签到");
                    }

                    if (data.getSign()!=null){
                        view.mTvDay.setText(data.getSign().getDays()+"");
                    }
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
