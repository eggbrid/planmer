package com.kirito.planmer;

import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.baselib.thirdPart.Retrofit.CodeInterceptor;
import kirito.peoject.baselib.util.ActivityUtils;
import kirito.peoject.constantlibs.UIConstant.activity.UserLibs;

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
            case 401:
                LibJumpHelper.startActivity(UserLibs.ACTIVITY_LOPGIN);
                return true;
        }
        return false;
    }
}
