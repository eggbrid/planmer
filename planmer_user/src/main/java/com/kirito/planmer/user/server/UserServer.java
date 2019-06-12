package com.kirito.planmer.user.server;

import com.kirito.planmer.user.model.AllUserModel;
import com.kirito.planmer.user.model.User;
import com.kirito.planmer.user.model.UserInfoModel;
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
    @POST("/user/login")
    Observable<User> login(@Query("userName") String userName,@Query("passWord") String passWord);

    @Multipart
    @POST("/file/upload")
    Observable<ImageData> upload(@Part MultipartBody.Part body);


    @POST("/user/register")
    Observable<User> register(@Query("userName") String userName,@Query("passWord") String passWord,@Query("invitationCode") String invitationCode);

    @POST("/user/getUser")
    Observable<AllUserModel> getUser();
    @POST("/user/upDateUser")
    Observable<UserInfoModel> upDateUser(@Query("avatar") String avatar, @Query("nickName") String nickName);

}
