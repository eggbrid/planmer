package com.kirito.planmer.user.fragment;

import android.content.Intent;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kirito.planmer.user.presenter.UserP;
import com.kirito.planmer.user.server.UserServer;
import com.kirito.planmer.user.view.UserView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.baselib.util.PIcUtil;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * @auther kirito
 * @Date 2019-05-20
 * @NOTE 类说明
 */
@Route(path = UserLibs.FRAGMENT_USER)
public class UserFragment extends BaseFragment<UserView> {
    @Override
    public void afterInitView(UserView view) {
        view.mIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPic();
            }
        });
    }
    public void startPic(){
        PIcUtil.toPic(this);
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
                    if (selectList.get(0).isCut()){
                        getP(UserP.class).upload(new File(selectList.get(0).getCutPath()),view,view.mIvAvatar);

                    }


                    break;
            }
        }
    }
}
