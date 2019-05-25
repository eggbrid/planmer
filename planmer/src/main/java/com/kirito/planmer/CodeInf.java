package com.kirito.planmer;

import kirito.peoject.baselib.thirdPart.Retrofit.CodeInterceptor;

/**
 * @auther kirito
 * @Date 2019-05-25
 * @NOTE 类说明
 */
public class CodeInf implements CodeInterceptor {
    @Override
    public boolean isInterceptToError(int code) {
        switch (code) {
            case 200:

                return false;
            case 400:
                return true;
        }
        return false;
    }
}
