package com.kirito.planmer.user.server;

import com.kirito.planmer.user.model.User;
import io.reactivex.Observable;
import kirito.peoject.baselib.mvp.ImageData;
import kirito.peoject.baselib.mvp.NullData;
import okhttp3.MultipartBody;
import retrofit2.http.*;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
public interface UserServer {
    @GET("/user/login")
    Observable<User> login(@Query("userName") String userName,@Query("passWord") String passWord);

    @Multipart
    @POST("/file/upload")
    Observable<ImageData> upload(@Part MultipartBody.Part body);

}
