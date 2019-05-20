package kirito.peoject.baselib.mvp;

import com.google.gson.Gson;

/**
 * @Description:the base model of MVP mode
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 */
public class BaseM {

    /**
     * is parse server http response by yourself ?
     * @return true :by your self ,false :use default
     */
    public boolean isReadResponseBySelf(){
        return false;
    }

    /**
     * if you need parse server http response by yourself
     * you need return true with
     * @see #isReadResponseBySelf()
     * and Override this method to parse your response
     * @param value
     * @param <T>
     * @return
     */
    public  <T> T toBean(String value) {
        return null;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
