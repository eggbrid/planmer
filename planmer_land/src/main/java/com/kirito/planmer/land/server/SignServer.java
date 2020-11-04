package com.kirito.planmer.land.server;

import com.kirito.planmer.land.model.SignModel;

import io.reactivex.Observable;
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
