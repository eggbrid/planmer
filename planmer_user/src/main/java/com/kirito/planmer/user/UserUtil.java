package com.kirito.planmer.user;

import android.app.Activity;
import com.google.gson.Gson;
import com.kirito.planmer.user.model.AllUserModel;
import com.kirito.planmer.user.model.User;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.baselib.util.SPUtils;
import kirito.peoject.baselib.util.SpanUtils;
import kirito.peoject.constantlibs.Message;
import kirito.peoject.constantlibs.UIConstant.Main;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;
import org.greenrobot.eventbus.EventBus;

/**
 * @auther kirito
 * @Date 2019-06-12
 * @NOTE 类说明
 */
public class UserUtil {

    public static void loginOut(){
        SPUtils.getInstance().put("token", "");
        LibJumpHelper.startActivity(UserLibs.ACTIVITY_LOPGIN);
    }
    public static void login(User user){

        SPUtils.getInstance().put("token", user.getToken());
        EventBus.getDefault().post(new Message(Message.REGISTER_FINISH));
        LibJumpHelper.startActivity(Main.ACTIVITY_MAIN);
    }

    public static void saveUserInfo(AllUserModel user){

        SPUtils.getInstance().put("user", new Gson().toJson(user));
        EventBus.getDefault().post(new Message(Message.USER_INFO_UPDATED));

    }

    public static AllUserModel getUserInfo(){
        String json =SPUtils.getInstance().getString("user");
        Gson gson=new Gson();
        AllUserModel allUserModel=gson.fromJson(json,AllUserModel.class);
        return allUserModel;
    }
}
