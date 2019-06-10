package kirito.peoject.baselib.thirdPart.Retrofit;

import android.text.TextUtils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kirito.peoject.baselib.mvp.BaseM;

import java.util.List;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/28 0028
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/28 0028
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public abstract class ResponseCallBack<T extends BaseM, C extends CodeInterceptor> implements Observer<T> {
    private NetCallBack callBack;
    private List<Disposable> disposables;
    private C codeInf;

    public ResponseCallBack(NetCallBack callBack, List<Disposable> disposables, C c) {
        this.callBack = callBack;
        this.disposables = disposables;
        this.codeInf = c;
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposables.add(d);
    }

    @Override
    public void onNext(T t) {
        if (t == null) {
            callBack.onFailure("出现了些问题...");
        }
        if (codeInf != null && codeInf.isInterceptToError(t.getCode())) {
            callBack.onFailure((t != null && !TextUtils.isEmpty(t.message)) ? t.getMessage() : "猫神经链接断开,请稍等");
            return;
        }
        callBack.onGetData(t);

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        callBack.onFailure("获取到神秘代码" + e.getMessage());
        callBack.onFinish();

    }

    @Override
    public void onComplete() {
        callBack.onFinish();
    }
}
