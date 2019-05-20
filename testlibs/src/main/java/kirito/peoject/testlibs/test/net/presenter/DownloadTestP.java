package kirito.peoject.testlibs.test.net.presenter;

import io.reactivex.Observable;
import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.thirdPart.Retrofit.DownloadCallBack;
import kirito.peoject.testlibs.test.net.server.DownloadTestS;

import java.io.File;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/28 0028
 */
public class DownloadTestP extends BaseP<DownloadTestS> {
    public Observable toDownload(DownloadCallBack callBack, File file) {
        return download(readyDownload(callBack).toDownload("game/test.ppt"), callBack,file);
    }
}
