package kirito.peoject.baselib.thirdPart.Retrofit;

import android.util.Log;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: the interceptor before OkHttpClient call true request
 * @Author:kirito
 * @CreatTime:2019/2/27 0027
 */
public class RetrofitInterceptor implements Interceptor {
    private  CommentParamsAdapter commentParamsAdapter;//the comment params adapter
    private Map<String, String> paramsMap = new HashMap<>();
    private Map<String, String> headerMap = new HashMap<>();

    public CommentParamsAdapter getCommentParamsAdapter() {
        return commentParamsAdapter;
    }

    public void setCommentParamsAdapter(CommentParamsAdapter commentParamsAdapter) {
        this.commentParamsAdapter = commentParamsAdapter;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url().newBuilder().scheme(oldRequest.url().scheme()).host(oldRequest.url().host());
        Request.Builder builder= oldRequest.newBuilder().method(oldRequest.method(), oldRequest.body());

        if (commentParamsAdapter!=null){
            headerMap=commentParamsAdapter.onHeaderAdd(headerMap);
            paramsMap = commentParamsAdapter.onParamsAdd(paramsMap);
            if (paramsMap != null || paramsMap.size() > 0) {
                for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                    authorizedUrlBuilder = authorizedUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                }
            }
            paramsMap.clear();

            builder=builder.url(authorizedUrlBuilder.build());
            if (headerMap != null || headerMap.size() > 0) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    builder=builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            headerMap.clear();
        }
        Request newRequest =builder.build();

        Log.e("wangxu", newRequest.url().toString());

        return chain.proceed(newRequest);

    }
}
