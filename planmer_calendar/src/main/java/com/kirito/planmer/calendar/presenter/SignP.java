package com.kirito.planmer.calendar.presenter;

import android.widget.TextView;
import com.kirito.planmer.calendar.R;
import com.kirito.planmer.calendar.model.SignModel;
import com.kirito.planmer.calendar.server.SignServer;
import com.study.xuan.shapebuilder.shape.ShapeBuilder;
import io.reactivex.Observable;
import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.baselib.util.SizeUtils;
import kirito.peoject.baselib.util.ToastUtils;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * @auther kirito
 * @Date 2019-06-07
 * @NOTE 类说明
 */
public class SignP extends BaseP<SignServer> {

    public Observable<SignModel> add(final TextView day, final TextView signBtn) {
        signBtn.setText("签到中...");
        signBtn.setEnabled(false);
        ShapeBuilder.create()
                .Radius(SizeUtils.dp2px(5))
                .Type(RECTANGLE)
                .Solid(day.getContext().getResources().getColor(R.color.app_color_light_text))
                .build(signBtn);
        return request(getService().add(), new NetCallBack<SignModel>() {
            @Override
            public void onGetData(SignModel data) {
                ShapeBuilder.create()
                        .Radius(SizeUtils.dp2px(5))
                        .Type(RECTANGLE)
                        .Solid(signBtn.getContext().getResources().getColor(R.color.app_color_light))
                        .build(signBtn);
                signBtn.setEnabled(false);
                signBtn.setText("已签到");
                if (data!=null){
                    day.setText(data.getDays()+"");

                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(String message) {
                signBtn.setText("签到");
                signBtn.setEnabled(true);
                ShapeBuilder.create()
                        .Radius(SizeUtils.dp2px(5))
                        .Type(RECTANGLE)
                        .Solid(signBtn.getContext().getResources().getColor(R.color.app_color))
                        .build(signBtn);
                ToastUtils.showShort(message);

            }
        });

    }
}
