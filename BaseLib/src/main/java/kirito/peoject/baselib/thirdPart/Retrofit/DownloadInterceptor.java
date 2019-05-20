package kirito.peoject.baselib.thirdPart.Retrofit;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/3/5 0005
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/3/5 0005
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class DownloadInterceptor implements Interceptor {
    private DownloadCallBack downloadCallBack;

    public DownloadInterceptor(DownloadCallBack downloadCallBack) {
        this.downloadCallBack = downloadCallBack;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder().body(new DownloadResponseBody(originalResponse.body(),downloadCallBack)).build();
    }
}
