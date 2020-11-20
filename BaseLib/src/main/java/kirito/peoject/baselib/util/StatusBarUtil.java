package kirito.peoject.baselib.util;

import android.content.Context;

/**
 * @Description : 状态栏工具类
 * @Author : wdk
 * @CreatTime :2017/11/15 17:38
 * @LastModify(最终修改人) : wdk
 * @LastModifyTime(最终修改时间) : 2017/11/15 17:38
 * @LastCheckedBy : wdk
 */

public class StatusBarUtil {
    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
