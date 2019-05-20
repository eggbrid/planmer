package kirito.peoject.baselib.util;

import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.thirdPart.Retrofit.DownloadCallBack;
import kirito.peoject.baselib.thirdPart.Retrofit.DownloadPresenter;

import java.io.File;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/3/6 0006
 */
public class DownloadUtil {

    /**
     *
     * @param url
     * @param file
     * @param downloadCallBack
     * @return
     */
    public static BaseP download(String url, File file, DownloadCallBack downloadCallBack) {
        DownloadPresenter dp = new DownloadPresenter();
        dp.toDownload(url, downloadCallBack, file);
        return dp;
    }
    public static BaseP downloadByUrlName(String url, File file, DownloadCallBack downloadCallBack) {
        DownloadPresenter dp = new DownloadPresenter();
        dp.toDownload(url, downloadCallBack, file);
        return dp;
    }
}
