package com.kirito.planmer.calendar.server;

import com.kirito.planmer.calendar.model.HomeData;
import com.kirito.planmer.calendar.model.TaskInfoModel;
import io.reactivex.Observable;
import kirito.peoject.baselib.mvp.NullData;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @auther kirito
 * @Date 2019-06-06
 * @NOTE 类说明
 */
public interface TaskServer {
    @POST("/task/add")
    Observable<NullData> add(@Query("start_time") long start_time, @Query("title") String title, @Query("content") String content, @Query("d_time") long d_time, @Query("day") int day);
    @POST("/task/home")
    Observable<HomeData> getHome();
    @POST("/task/changeStatus")
    Observable<TaskInfoModel> changeStatus(@Query("status") int status , @Query("task_info_id") int task_info_id);
}
