package com.kirito.planmer.calendar.server;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @auther kirito
 * @Date 2019-06-06
 * @NOTE 类说明
 */
public interface TaskServer {
    @POST("/task/add")
    Observable<Object> add(@Query("start_time") long start_time, @Query("title") String title, @Query("content") String content, @Query("d_time") int d_time, @Query("day") int day);
}
