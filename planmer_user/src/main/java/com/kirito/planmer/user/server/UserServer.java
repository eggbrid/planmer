package com.kirito.planmer.user.server;

import com.kirito.planmer.user.model.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
public interface UserServer {
    @GET("/user/test")
    Observable<User> login();
}
