package com.kirito.planmer.user.presenter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kirito.planmer.user.R;
import com.kirito.planmer.user.model.User;
import com.kirito.planmer.user.server.UserServer;
import com.kirito.planmer.user.view.LoginActivityView;
import io.reactivex.Observable;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.mvp.BaseV;
import kirito.peoject.baselib.mvp.ImageData;
import kirito.peoject.baselib.mvp.NullData;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.baselib.util.SPUtils;
import kirito.peoject.baselib.util.ToastUtils;
import kirito.peoject.constantlibs.UIConstant.Main;
import kirito.peoject.constantlibs.net.HttpUrls;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
public class UserP extends BaseP<UserServer> {
    public Observable<User> login(final BaseActivity<LoginActivityView> activity) {
        final String userName = activity.view.edtUserName.getText().toString();
        String passWord = activity.view.edtPassWord.getText().toString();

        activity.view.showLoading("登录中...");
        return request(getService().login(userName, passWord), new NetCallBack<User>() {
            @Override
            public void onGetData(User user) {

                SPUtils.getInstance().put("token", user.getToken());
                activity.view.dismissLoading();

                LibJumpHelper.startActivity(Main.ACTIVITY_MAIN);
                activity.finish();
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(String message) {
                activity.view.dismissLoading();
                ToastUtils.showShort(message);
            }
        });
    }

    public Observable<ImageData> upload(File file, final BaseV v, final ImageView imageView) {


        RequestBody fileRQ = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileRQ);

        v.showLoading("上传中...");
        return request(getService(HttpUrls.FILE_URL).upload(part), new NetCallBack<ImageData>() {
            @Override
            public void onGetData(ImageData imageData) {

                RequestOptions mRequestOptions = RequestOptions.circleCropTransform();
                Glide.with(imageView.getContext())
                        .load(imageData.getUrl())
                        .apply(mRequestOptions)
                        .into(imageView);
            }

            @Override
            public void onFinish() {
                v.dismissLoading();

            }

            @Override
            public void onFailure(String message) {
                ToastUtils.showShort(message);
            }
        });
    }


}
