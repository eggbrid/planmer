package kirito.peoject.baselib.thirdPart.Retrofit;

import kirito.peoject.baselib.mvp.BaseM;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/28 0028
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/28 0028
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public interface ResponseInterceptor {

    String doBeforeParse();
    <T> T doAfterParse(T t);
}
