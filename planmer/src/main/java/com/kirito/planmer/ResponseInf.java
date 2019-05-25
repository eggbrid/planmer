package com.kirito.planmer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kirito.peoject.baselib.mvp.BaseM;
import kirito.peoject.baselib.thirdPart.Retrofit.ResponseInterceptor;
import kirito.peoject.baselib.util.GsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther kirito
 * @Date 2019-05-25
 * @NOTE 类说明
 */
public class ResponseInf extends ResponseInterceptor {

    @Override
    public ResponBean doBeforeParse(String json) {
        String beanJson = "";
        Map<String,Object> map=new HashMap<>();

        JsonElement root = null;
        JsonParser jsonParser = new JsonParser();
        try {
            root = jsonParser.parse(json);

        } catch (Exception e) {

        }
        if (root != null && root.isJsonObject()) {
            JsonElement codeJson = root.getAsJsonObject().get("code");
            try {
                int code = codeJson.getAsInt();
                map.put("code", code);
            } catch (Exception e) {

            }
            JsonElement MessageJson = root.getAsJsonObject().get("message");
            try {
                String message = MessageJson.getAsString();
                map.put("message", message);

            } catch (Exception e) {

            }
            JsonElement dataJson = root.getAsJsonObject().get("data");

            try {
                beanJson = dataJson.getAsString();
            } catch (Exception e) {

            }
        }

        return new ResponBean(beanJson, map);
    }

    @Override
    public <T extends BaseM> T doAfterParse(T t, ResponBean responBean) {
        if (responBean!=null){
            Map map=responBean.getOtherMap();
            if (map.containsKey("code"))
            t.setCode((Integer) map.get("code"));
            if (map.containsKey("message"))
                t.setMessage((String) map.get("message"));

        }
        return t;
    }
}
