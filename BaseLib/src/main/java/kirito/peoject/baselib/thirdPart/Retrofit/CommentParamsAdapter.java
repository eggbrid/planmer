package kirito.peoject.baselib.thirdPart.Retrofit;

import java.util.Map;

/**
 * @Description:a interface for add comment params
 * @Author:kirito
 * @CreatTime:2019/2/27 0027
 * @LastChekedBy: kirito
 */
public interface CommentParamsAdapter {
     /**
      * in XRetrofit ,it replaced default {@link okhttp3.OkHttpClient}with a new OkHttpClient who has {@link RetrofitInterceptor}
      * before true request ,RetrofitInterceptor will call this function to add comment params
      * @param paramsMap
      * @return
      */
     Map<String ,String > onParamsAdd(Map<String,String> paramsMap);


     Map<String ,String > onHeaderAdd(Map<String,String> paramsMap);


}
