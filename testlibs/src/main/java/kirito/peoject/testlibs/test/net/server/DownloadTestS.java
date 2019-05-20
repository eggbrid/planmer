package kirito.peoject.testlibs.test.net.server;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/3/5 0005
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/3/5 0005
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public interface DownloadTestS {
    @Streaming
    @GET()
    Observable<ResponseBody> toDownload(@Url String url);
}
