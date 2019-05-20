package kirito.peoject.baselib.thirdPart.Retrofit;

import io.reactivex.Observable;
import kirito.peoject.baselib.mvp.BaseP;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

import java.io.File;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/3/6 0006
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/3/6 0006
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class DownloadPresenter extends BaseP<DownloadServer> {


    public void toDownload(String url,DownloadCallBack callBack, File file) {
         download(readyDownload(callBack).toDownload(url), callBack,file);
    }

}
