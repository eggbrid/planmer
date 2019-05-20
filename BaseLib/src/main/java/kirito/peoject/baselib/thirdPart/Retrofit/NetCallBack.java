package kirito.peoject.baselib.thirdPart.Retrofit;

/**
 * @Description:net call back
 * @Author:kirito
 * @CreatTime:2019/2/28 0028
 */
public interface NetCallBack<T> {
    void onGetData(T t);
   void onFinish();
   void onFailure();
}
