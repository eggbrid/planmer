package kirito.peoject.baselib.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author:王旭
 * @CreatTime:2018/10/18 0018
 * @LastModify(最终修改人):王旭
 * @LastModifyTime(最终修改时间):2018/10/18 0018
 * @LastChekedBy: 王旭
 * @needingAttention(注意事项):
 */
public class LiuHaiUtil {
    public static int hasLiuHai(Context c) {
        if (Rom.isEmui()) {
            return hasNotchAtHuawei(c) ? getNotchSizeAtHuawei(c) : 0;
        } else if (Rom.isMiui()) {
            return hasNotchAtMi(c) ? getStatusBarHeight(c) : 0;
        } else if (Rom.isVivo()) {
            return hasNotchAtVivo(c) ? (int) DensityUtil.dip2px(c, 27) : 0;
        } else if (Rom.isOppo()) {
            return hasNotchAtOPPO(c) ? 80 : 0;
        } else {
            return 0;
        }
    }

    public static int getTrueStatusBar(Context c) {
        return hasLiuHai(c) == 0 ? StatusBarUtil.getStatusBarHeight(c) : hasLiuHai(c);
    }

    /**
     * 华为的是否有刘海
     *
     * @param context
     * @return
     */
    public static boolean hasNotchAtHuawei(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "hasNotchAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "hasNotchAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "hasNotchAtHuawei Exception");
        } finally {
            return ret;
        }
    }

    /**
     * 获取华为刘海的高度
     *
     * @param context
     * @return
     */
    public static int getNotchSizeAtHuawei(Context context) {
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);
            return ret[1];
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "getNotchSizeAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "getNotchSizeAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "getNotchSizeAtHuawei Exception");
        }
        return 0;
    }

    public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
    public static final int VIVO_FILLET = 0x00000008;//是否有圆角

    /**
     * vivo是否有刘海
     *
     * @param context
     * @return
     */
    public static boolean hasNotchAtVivo(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "hasNotchAtVivo ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "hasNotchAtVivo NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "hasNotchAtVivo Exception");
        } finally {
            return ret;
        }
    }

    /**
     * oppo是否有刘海
     *
     * @param context
     * @return
     */
    public static boolean hasNotchAtOPPO(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /**
     * 小米是否有刘海屏幕
     *
     * @return
     */
    public static boolean hasNotchAtMi(Context context) {

        int result = 0;
        try {
            ClassLoader classLoader = context.getClassLoader();
            @SuppressWarnings("rawtypes")
            Class SystemProperties = classLoader.loadClass("android.os.SystemProperties");
            //参数类型
            @SuppressWarnings("rawtypes")
            Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = int.class;
            Method getInt = SystemProperties.getMethod("getInt", paramTypes);
            //参数
            Object[] params = new Object[2];
            params[0] = new String("ro.miui.notch");
            params[1] = new Integer(0);
            result = (Integer) getInt.invoke(SystemProperties, params);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result == 1;


    }


    /**
     * 小米的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }


    /**
     * 获取手机厂商
     *
     * @return
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    public static class Rom {

        private static final String TAG = "Rom";

        public static final String ROM_MIUI = "MIUI";
        public static final String ROM_EMUI = "EMUI";
        public static final String ROM_FLYME = "FLYME";
        public static final String ROM_OPPO = "OPPO";
        public static final String ROM_SMARTISAN = "SMARTISAN";
        public static final String ROM_VIVO = "VIVO";
        public static final String ROM_QIKU = "QIKU";

        private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
        private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
        private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
        private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
        private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";

        private static String sName;
        private static String sVersion;

        /**
         * 是否是华为
         *
         * @return
         */
        public static boolean isEmui() {
            return check(ROM_EMUI);
        }

        /**
         * 是否是小米
         *
         * @return
         */
        public static boolean isMiui() {
            return check(ROM_MIUI);
        }

        /**
         * 是否是vivo
         *
         * @return
         */
        public static boolean isVivo() {
            return check(ROM_VIVO);
        }

        /**
         * 是否是oppo
         *
         * @return
         */
        public static boolean isOppo() {
            return check(ROM_OPPO);
        }

        public static boolean isFlyme() {
            return check(ROM_FLYME);
        }

        public static boolean is360() {
            return check(ROM_QIKU) || check("360");
        }

        public static boolean isSmartisan() {
            return check(ROM_SMARTISAN);
        }

        public static String getName() {
            if (sName == null) {
                check("");
            }
            return sName;
        }

        public static String getVersion() {
            if (sVersion == null) {
                check("");
            }
            return sVersion;
        }

        public static boolean check(String rom) {
            if (sName != null) {
                return sName.equals(rom);
            }

            if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_MIUI))) {
                sName = ROM_MIUI;
            } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_EMUI))) {
                sName = ROM_EMUI;
            } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_OPPO))) {
                sName = ROM_OPPO;
            } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_VIVO))) {
                sName = ROM_VIVO;
            } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_SMARTISAN))) {
                sName = ROM_SMARTISAN;
            } else {
                sVersion = Build.DISPLAY;
                if (sVersion.toUpperCase().contains(ROM_FLYME)) {
                    sName = ROM_FLYME;
                } else {
                    sVersion = Build.UNKNOWN;
                    sName = Build.MANUFACTURER.toUpperCase();
                }
            }
            return sName.equals(rom);
        }

        public static String getProp(String name) {
            String line = null;
            BufferedReader input = null;
            try {
                Process p = Runtime.getRuntime().exec("getprop " + name);
                input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
                line = input.readLine();
                input.close();
            } catch (IOException ex) {
                Log.e(TAG, "Unable to read prop " + name, ex);
                return null;
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return line;
        }
    }

    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getDpi(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取 虚拟按键的高度
     *
     * @param context
     * @return
     */
    public static int getBottomStatusHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;

    }
}
