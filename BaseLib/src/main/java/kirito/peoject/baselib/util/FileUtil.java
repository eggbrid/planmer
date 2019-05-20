package kirito.peoject.baselib.util;

import android.content.Context;

import java.io.File;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/3/2 0002
 */
public class FileUtil {
    /**
     * save file into your application package path
     * it don`t need readOrWrite permission
     */
    public static void saveFileToPKGPath(Context context, File file) {
        String appPKGPath = context.getFilesDir().getPath();//file

    }

    /**
     * save file to path
     * it maybe need readOrWrite permission
     *
     * @param path
     */
    public static void saveToPath(String path) {

    }

    public static void hasSD() {

    }

    public static void removeFile() {

    }

    public static File creatFile() {
        return new File("");
    }


}
