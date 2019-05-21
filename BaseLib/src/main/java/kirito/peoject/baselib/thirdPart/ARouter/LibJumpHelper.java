package kirito.peoject.baselib.thirdPart.ARouter;

import android.app.Activity;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import kirito.peoject.baselib.UI.BaseFragment;

/**
 * @Description: the helper for ali ARouter ，Help us manage the jump
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/26 0026
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class LibJumpHelper {

    /**
     * this function like call startActivity in android
     *
     * @param path
     */
    public static void startActivity(String path,Bundle bundle) {
        Postcard postcard = ARouter.getInstance().build(path).with(bundle);
        postcard.navigation();
    }

    /**
     * this function like call startActivity in android
     *
     * @param path
     */
    public static void startActivity(String path) {
        Postcard postcard = ARouter.getInstance().build(path);
        postcard.navigation();
    }

    /**
     * this function like call startActivityForResult in android
     *
     * @param context
     * @param path
     * @param requestCode
     */
    public static void startActivityForResult(Activity context, String path, int requestCode) {
        ARouter.getInstance().build(path).navigation(context, requestCode);
    }
    /**
     * this function like call startActivityForResult in android
     *
     * @param context
     * @param path
     * @param requestCode
     */
    public static void startActivityForResult(Activity context, String path,Bundle bundle, int requestCode) {
        ARouter.getInstance().build(path).with(bundle).navigation(context, requestCode);
    }


    public static BaseFragment getFragment(String path){
       return (BaseFragment) ARouter.getInstance().build(path).navigation();

    }

}
