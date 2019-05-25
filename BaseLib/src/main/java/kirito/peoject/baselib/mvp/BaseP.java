package kirito.peoject.baselib.mvp;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import kirito.peoject.baselib.thirdPart.Retrofit.DownloadCallBack;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.baselib.thirdPart.Retrofit.XRetrofit;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: the base presenter
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 */
public class BaseP<S> {
    private List<Disposable> disposables = new ArrayList<>();

    /**
     * get your defined generics server
     * for example
     *<pre>
     *     public DemoP extends BaseP<DemoServer>{
     *         public void getDemoServer(){
     *          DemoServer server=getServer
     *      }
     *     }
     *</pre>
     * @return
     */
    public S getService() {
        return XRetrofit.getServerCall(getSClass());
    }
    public S readyDownload(DownloadCallBack downloadCallBack) {
        return XRetrofit.getDownloadServiceCall(getSClass(),downloadCallBack);
    }

    /**
     * initiate a request
     * @param observable the variable returned by  your server`s method returned value
     * @param netCallBack the interface who will be called when the request finished
     * @param <T> the return model`s generic
     * @return
     */
    public <T extends BaseM> Observable<T> request(Observable<T> observable, NetCallBack netCallBack) {
        return XRetrofit.toRequest(observable, netCallBack, disposables);
    }

    public Observable download(Observable observable, DownloadCallBack downloadCallBack,File file){
        return XRetrofit.toRequest(observable,downloadCallBack,disposables,file);
    }
    public void cancel() {
        if (disposables != null && disposables.size() > 0) {
            for (Disposable disposable : disposables) {
                if (disposable != null && !disposable.isDisposed()) {
                    disposable.dispose();
                }
            }
            disposables.clear();
        }
    }

    public Class<S> getSClass() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] ptype = ((ParameterizedType) type).getActualTypeArguments();
            return (Class) ptype[0];
        } else {
            return null;


        }
    }

}
