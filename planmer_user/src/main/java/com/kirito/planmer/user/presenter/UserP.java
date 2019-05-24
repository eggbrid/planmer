package com.kirito.planmer.user.presenter;

import com.kirito.planmer.user.model.User;
import com.kirito.planmer.user.server.UserServer;
import io.reactivex.Observable;
import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
public class UserP extends BaseP<UserServer> {
    public Observable<User> login(NetCallBack<User> callBack) {
        return request(getService().login(), callBack);
    }
}
