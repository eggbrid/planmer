package kirito.peoject.testlibs;

import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.util.IntentUtil;
import kirito.peoject.constantlibs.UIConstant.activity.TestLibs;
import kirito.peoject.testlibs.test.net.activity.DownloadTestActivity;
import kirito.peoject.testlibs.test.net.activity.NetTestActivity;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 */
@Route(path = TestLibs.TestLibs_MainActivity)
public class MainActivity extends BaseActivity<MainView> {
    @Override
    public void afterInitView(MainView v) {
        v.net.setOnClickListener(this);
        v.download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == view.net) {
            IntentUtil.startActivity(this, NetTestActivity.class);
        }else if (v == view.download) {
            IntentUtil.startActivity(this, DownloadTestActivity.class);
        }
    }
}
