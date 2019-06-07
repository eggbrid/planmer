package com.kirito.planmer.calendar.server;

import com.kirito.planmer.calendar.model.SignModel;
import io.reactivex.Observable;
import kirito.peoject.baselib.mvp.NullData;
import retrofit2.http.POST;

/**
 * @auther kirito
 * @Date 2019-06-06
 * @NOTE 类说明
 */
public interface SignServer {
    @POST("/sign/add")
    Observable<SignModel> add();

}
