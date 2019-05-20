package kirito.peoject.baselib.thirdPart.Retrofit;

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
        if (commentParamsAdapter!=null){
            paramsMap = commentParamsAdapter.onParamsAdd(paramsMap);
            if (paramsMap != null || paramsMap.size() > 0) {
                for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                    authorizedUrlBuilder = authorizedUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                }
            }
            paramsMap.clear();
        }
        Request newRequest = oldRequest.newBuilder().method(oldRequest.method(), oldRequest.body()).url(authorizedUrlBuilder.build()).build();
        return chain.proceed(newRequest);

    }
}
