package com.kirito.planmer.user.presenter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kirito.planmer.user.UserUtil;
import com.kirito.planmer.user.model.AllUserModel;
import com.kirito.planmer.user.model.User;
import com.kirito.planmer.user.model.UserInfoModel;
import com.kirito.planmer.user.server.UserServer;
import com.kirito.planmer.user.view.LoginActivityView;
import io.reactivex.Observable;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.mvp.BaseV;
import kirito.peoject.baselib.mvp.ImageData;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.baselib.util.ToastUtils;
import kirito.peoject.constantlibs.Message;
import kirito.peoject.constantlibs.net.HttpUrls;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.greenrobot.eventbus.EventBus;

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
                activity.view.dismissLoading();

                UserUtil.login(user);
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


    public Observable<User> register(final BaseActivity activity, String username, String password, String IVCode) {


        activity.view.showLoading("正在注册...");
        return request(getService().register(username, password, IVCode), new NetCallBack<User>() {
            @Override
            public void onGetData(User user) {
                activity.view.dismissLoading();
                UserUtil.login(user);
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

    public Observable<ImageData> upload(File file, final BaseV v, final ImageView imageView, final OnImageDataGetListener onImageDataGetListener) {


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
                onImageDataGetListener.onImageDataGet(imageData);
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

    public interface OnImageDataGetListener {
        void onImageDataGet(ImageData imageData);
    }


    public Observable<AllUserModel> getUser(final BaseV baseV) {


        baseV.showLoading("正在获取信息...");
        return request(getService().getUser(), new NetCallBack<AllUserModel>() {
            @Override
            public void onGetData(AllUserModel user) {
                baseV.dismissLoading();

                UserUtil.saveUserInfo(user);

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(String message) {
                baseV.dismissLoading();
                ToastUtils.showShort(message);
            }
        });
    }


    public Observable<UserInfoModel> upDateUser(final BaseV baseV, String avatarPath, String nickNameValue) {


        baseV.showLoading("正在上传...");
        return request(getService().upDateUser(avatarPath, nickNameValue), new NetCallBack<UserInfoModel>() {
            @Override
            public void onGetData(UserInfoModel user) {
                baseV.dismissLoading();
                AllUserModel allUserModel = UserUtil.getUserInfo();
                allUserModel.setUserInfo(user);
                UserUtil.saveUserInfo(allUserModel);

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(String message) {
                baseV.dismissLoading();
                ToastUtils.showShort(message);
            }
        });
    }

}
