package kirito.peoject.baselib.thirdPart.Retrofit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
public abstract  class ResponseCallBack<T>  implements Observer<T> {
   private NetCallBack callBack;
   private List<Disposable> disposables;
    public ResponseCallBack(NetCallBack callBack, List<Disposable> disposables){
        this.callBack=callBack;
        this.disposables=disposables;
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposables.add(d);
    }

    @Override
    public void onNext(T t) {
        callBack.onGetData(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        callBack.onFailure();
    }

    @Override
    public void onComplete() {
        callBack.onFinish();
    }
}
