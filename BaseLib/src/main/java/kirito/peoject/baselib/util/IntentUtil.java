package kirito.peoject.baselib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/26 0026
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class IntentUtil {
    /**
     * @param context 上下文
     * @param classes 目标class
     */
    public static void startActivity(Context context, Class<? extends Activity> classes) {
        startActivity(context, classes, null);
    }

    /**
     * @param context 上下文
     * @param classes 目标class
     * @param bundle  数据
     */
    public static void startActivity(Context context, Class<? extends Activity> classes, Bundle bundle) {
        startActivity(-100,context, classes, bundle);
    }


    /**
     * @param context 上下文
     * @param classes 目标class
     */
    public static void startActivity(Activity context, Class<? extends Activity> classes) {
        startActivity(context, classes, -1);
    }

    /**
     * @param context    上下文
     * @param classes    目标class
     * @param resultCode 返回标识
     */
    public static void startActivity(Activity context, Class<? extends Activity> classes, int resultCode) {
        startActivity(context, classes, null, resultCode);
    }

    /**
     * @param context 上下文
     * @param classes 目标class
     * @param bundle  数据
     */
    public static void startActivity(Activity context, Class<? extends Activity> classes, Bundle bundle) {
        startActivity(context, classes, bundle, -1);
    }

    /**
     * @param context    上下文
     * @param classes    目标class
     * @param bundle     数据
     * @param resultCode 返回标识
     */
    public static void startActivity(Activity context, Class<? extends Activity> classes, Bundle bundle, int resultCode) {
        startActivity(context, classes, bundle, resultCode, android.R.anim.slide_in_left, android.R.anim.slide_out_right,-100);
    }
    /**
     * @param context    上下文
     * @param classes    目标class
     * @param inAnim     进入动画
     * @param outFade    退出动画
     */
    public static void startActivity(Activity context, Class<? extends Activity> classes, int inAnim, int outFade) {
        startActivity(context, classes, null, -1, inAnim, outFade,-100);
    }
    /**
     * @param context    上下文
     * @param classes    目标class
     * @param bundle     数据
     * @param inAnim     进入动画
     * @param outFade    退出动画
     */
    public static void startActivity(Activity context, Class<? extends Activity> classes, Bundle bundle, int inAnim, int outFade) {
        startActivity(context, classes, bundle, -1, inAnim, outFade,-100);
    }
    /**
     * @param context    上下文
     * @param classes    目标class
     * @param bundle     数据
     * @param resultCode 返回标识
     * @param inAnim     进入动画
     * @param outFade    退出动画
     */
    public static void startActivity(Activity context, Class<? extends Activity> classes, Bundle bundle, int resultCode, int inAnim, int outFade, int flag) {
        Intent intent = new Intent(context, classes);
        if (flag!=-100){
            intent.addFlags(flag);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivityForResult(intent, resultCode);
//        context.overridePendingTransition(inAnim, outFade);
    }
    public static void startActivity(int flag, Context context, Class<? extends Activity> classes, Bundle bundle) {
        Intent intent = new Intent(context, classes);
        if (flag!=-100){
            intent.addFlags(flag);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

}
