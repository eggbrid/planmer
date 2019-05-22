package kirito.peoject.baselib;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;
import com.simple.spiderman.SpiderMan;
import kirito.peoject.baselib.thirdPart.Retrofit.XRetrofitConfig;

/**
 * @Description: the base libs for android project
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/26 0026
 * @LastChekedBy: kirito
 * @needingAttention(注意事项): In this class, you can only set some basic properties.
 */
public class BaseLib {
    public static XRetrofitConfig xRetrofitConfig;

    /**
     * init in application
     *
     * @param application
     */

    public static void init(Application application, boolean isDebug, XRetrofitConfig config) {
        if (isDebug) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
//        SpiderMan.init(application);
        ARouter.init(application);
        xRetrofitConfig = config;
    }

}
