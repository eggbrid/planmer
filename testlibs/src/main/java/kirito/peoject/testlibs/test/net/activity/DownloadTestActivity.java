package kirito.peoject.testlibs.test.net.activity;

import android.view.View;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.thirdPart.Retrofit.DownloadCallBack;
import kirito.peoject.baselib.util.DownloadUtil;
import kirito.peoject.baselib.util.FileUtils;
import kirito.peoject.baselib.util.PathUtils;
import kirito.peoject.testlibs.R;
import kirito.peoject.testlibs.test.net.presenter.DownloadTestP;
import kirito.peoject.testlibs.test.net.view.DownloadTestView;

import java.io.File;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/3/5 0005
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/3/5 0005
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class DownloadTestActivity extends BaseActivity<DownloadTestView> {
    @Override
    public void afterInitView(DownloadTestView v) {
        super.afterInitView(v);
        view.btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn) {
            DownloadUtil.download("http://www.shprochina.com/game/test.ppt",new File(PathUtils.getDownloadCachePath()),new DownloadCallBack() {
                @Override
                public void onFinish() {

                }

                @Override
                public void onProgress(long download, long all) {
//                    view.filePath.setText("已下载"+download);
                    view.pb.setProgress((int) (download * 100 / all));

                }

                @Override
                public void onFailure() {

                }
            });
        }
    }
    public Runnable runnable=new Runnable() {
        @Override
        public void run() {

        }
    };
}
