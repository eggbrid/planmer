package com.kirito.planmer.user.activity;

import android.content.Intent;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kirito.planmer.user.R;
import com.kirito.planmer.user.UserUtil;
import com.kirito.planmer.user.model.AllUserModel;
import com.kirito.planmer.user.presenter.UserP;
import com.kirito.planmer.user.view.UserInfoActivityView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.mvp.ImageData;
import kirito.peoject.baselib.util.PIcUtil;
import kirito.peoject.constantlibs.Message;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

/**
 * @auther kirito
 * @Date 2019-06-12
 * @NOTE 类说明
 */
public class UserInfoActivity extends BaseActivity<UserInfoActivityView> {
    private boolean isEdit = false;

    private String imagePath="";

    @Override
    public void afterInitView(UserInfoActivityView v) {
        super.afterInitView(v);
        view.mTitle.setNormalTitle(this, "用户信息");
        view.mTvSave.setOnClickListener(this);
        view.mIvAvatar.setOnClickListener(this);
        toView();
        loadCache();
        getP(UserP.class).getUser(view);
        EventBus.getDefault().register(this);

    }

    public void loadCache(){
        AllUserModel allUserModel= UserUtil.getUserInfo();
        if (allUserModel!=null){
if (allUserModel.getUserInfo()!=null){
    RequestOptions mRequestOptions = RequestOptions.circleCropTransform();
    view.mEdtNickName.setText(allUserModel.getUserInfo().nickName);
    Glide.with(view.mIvAvatar.getContext())
            .load(allUserModel.getUserInfo().getAvatar())
            .apply(mRequestOptions)
            .into(view.mIvAvatar);
}
     if (allUserModel.getUser()!=null){
         view.mTvInvitationCodeValue.setText(allUserModel.getUser().getInvitationCode());

     }
        }
    }


    public void toView() {
        isEdit = false;
        view.mIvAvatar.setEnabled(false);
        view.mEdtNickName.setEnabled(false);
        view.mTvSave.setVisibility(View.GONE);
        view.mTitle.setRight(R.drawable.ic_edit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toEdit();
            }
        });
    }

    public void toEdit() {
        isEdit = true;
        view.mIvAvatar.setEnabled(true);
        view.mEdtNickName.setEnabled(true);
        view.mTvSave.setVisibility(View.VISIBLE);
        view.mTitle.goneRight();

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId()==view.mTvSave.getId()){
            toView();
            String nickName=view.mEdtNickName.getText().toString();
            getP(UserP.class).upDateUser(view,imagePath,nickName);
        }else if(v.getId()==view.mIvAvatar.getId()){
            PIcUtil.toPic(this);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    ;
                    if (selectList.get(0).isCut()) {
                        getP(UserP.class).upload(new File(selectList.get(0).getCutPath()), view, view.mIvAvatar, new UserP.OnImageDataGetListener() {
                            @Override
                            public void onImageDataGet(ImageData imageData) {
                                imagePath=imageData.getUrl();
                            }
                        });

                    }


                    break;
            }
        }
    }
    @Subscribe()
    public void onMessageEvent(Message event) {
        if (event.code == Message.USER_INFO_UPDATED) {
            loadCache();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

}
