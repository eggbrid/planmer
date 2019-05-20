package kirito.peoject.baselib.util;

import java.lang.reflect.*;

import static com.google.gson.internal.$Gson$Types.getRawType;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/28 0028
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/28 0028
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class GetGenericUtil<T> {
    //得到泛型类T
    public Class<T> getTClass() {
        Type type = getClass().getGenericSuperclass(); // 判断 是否泛型
        if (type instanceof ParameterizedType) { // 返回表示此类型实际类型参数的Type对象的数组. // 当有多个泛型类时，数组的长度就不是1了
            Type[] ptype = ((ParameterizedType) type).getActualTypeArguments();
            return (Class) ptype[0]; //将第一个泛型T对应的类返回（这里只有一个）
        } else if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            return (Class) rawType;
        } else {
            return null;


        }
    }
}
