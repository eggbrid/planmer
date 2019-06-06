package com.kirito.planmer;

import kirito.peoject.baselib.thirdPart.Retrofit.CommentParamsAdapter;
import kirito.peoject.baselib.util.SPUtils;

import java.util.Map;

/**
 * @auther kirito
 * @Date 2019-06-06
 * @NOTE 类说明
 */
public class ParmsAdapter implements CommentParamsAdapter {
    @Override
    public Map<String, String> onParamsAdd(Map<String, String> paramsMap) {
        return paramsMap;
    }

    @Override
    public Map<String, String> onHeaderAdd(Map<String, String> paramsMap) {
        paramsMap.put("token", SPUtils.getInstance().getString("token"));
        return paramsMap;
    }
}
