package kirito.peoject.baselib.thirdPart.Retrofit;

import android.util.Log;
import com.google.gson.TypeAdapter;
import kirito.peoject.baselib.BaseLib;
import kirito.peoject.baselib.mvp.BaseM;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @Description:
 * @Author:kirito
 */
final class GsonResponseBodyConverter<T extends BaseM> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private ResponseInterceptor responseInterceptor;
    private final Type type;

    GsonResponseBodyConverter(TypeAdapter<T> adapter, Type type) {
        this.adapter = adapter;
        this.type = type;
        try {
            responseInterceptor = BaseLib.xRetrofitConfig.getResponseInterceptorInstance();
        } catch (Exception e) {
            responseInterceptor = null;
        }
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        ResponseInterceptor.ResponBean responBean = null;
        Log.e("log",response);
        if (responseInterceptor != null) {
            responBean = responseInterceptor.doBeforeParse(response);
            response=responBean.getBeanJson();
        }
        T t = null;
        Class<T> tClass = null;
        try {
            tClass = (Class<T>) type;
        } catch (Exception e) {
        }
        try {
            if (tClass != null && type instanceof Class) {
                t = tClass.newInstance();
                if (t.isReadResponseBySelf()){
                    t = t.toBean(response);

                }else {
                    t = adapter.fromJson(response);

                }
            } else {
                t = adapter.fromJson(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (responseInterceptor != null) {
            t = responseInterceptor.doAfterParse(t,responBean);
        }
        return t;
    }

}
