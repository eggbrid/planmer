package kirito.peoject.testlibs.test.net.presenter;

import io.reactivex.Observable;
import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.testlibs.test.net.model.TestM;
import kirito.peoject.testlibs.test.net.server.NetTestS;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/28 0028
 */
public class NetTestP extends BaseP<NetTestS> {

    public Observable<TestM> getTestJson(NetCallBack<TestM> callBack) {
        return request(getService().getTestJson(), callBack);
    }
}
