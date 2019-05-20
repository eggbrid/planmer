package kirito.peoject.baselib.thirdPart.Retrofit;

import android.util.Log;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.*;

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
public class DownloadResponseBody extends ResponseBody {
    private ResponseBody responseBody;
    private BufferedSource bufferedSource;
    private DownloadCallBack downloadCallBack;


    public DownloadResponseBody(ResponseBody responseBody, DownloadCallBack downloadCallBack) {
        this.responseBody = responseBody;
        this.downloadCallBack = downloadCallBack;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                final long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (downloadCallBack != null) {
                    Log.e("apply", "Current thread is " + Thread.currentThread().getName());


                    downloadCallBack.onProgress(totalBytesRead, byteCount);
                }
                return bytesRead;
            }
        };
    }
}
