package kirito.peoject.testlibs.test.net.activity;

import android.util.Log;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.constantlibs.UIConstant.activity.TestLibs;
import kirito.peoject.testlibs.test.net.model.TestM;
import kirito.peoject.testlibs.test.net.presenter.NetTestP;
import kirito.peoject.testlibs.test.net.view.NetTestView;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 */
@Route(path = TestLibs.TestLibs_net_NetTestActivity)
public class NetTestActivity extends BaseActivity<NetTestView> {

    @Override
    public void afterInitView(NetTestView v) {
        v.post.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == view.post) {
            getP(NetTestP.class).getTestJson(new NetCallBack<TestM>() {
                @Override
                public void onGetData(TestM testM) {
                    view.response.setText(testM.getJson());
                }
                @Override
                public void onFinish() {
                    Log.e(getTag(), "onFinish");
                }

                @Override
                public void onFailure() {
                    Log.e(getTag(), "onFailure");
                }
            });
        }
    }

}
