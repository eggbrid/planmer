package kirito.peoject.baselib.manager.permission;

import android.content.pm.PackageManager;
import android.os.Build;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.UI.BaseFragment;
import kirito.peoject.baselib.manager.permission.enums.PermissionEnum;
import kirito.peoject.baselib.manager.permission.listener.PermissionListener;


/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/1/29 0029
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/1/29 0029
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */

public class PermissionManager {

    private SparseArray<PermissionListener> listeners = new SparseArray();


    public void requestPermissions(BaseActivity activity, PermissionEnum permissionEnum, PermissionListener permissionListener) {
        listeners.put(listeners.size(), permissionListener);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean isNeedCheckSelfPermission = false;
            for (String s : permissionEnum.getPermission()) {
                if (ContextCompat.checkSelfPermission(activity, s) != PackageManager.PERMISSION_GRANTED) {
                    isNeedCheckSelfPermission = true;
                }
            }
            if (isNeedCheckSelfPermission) {
                activity.requestPermissions(permissionEnum.getPermission(), permissionEnum.getCode());
            } else {
                permissionListener.getPermission(permissionEnum);
            }
        } else {
            permissionListener.getPermission(permissionEnum);
        }
    }

    public void requestPermissions(BaseFragment fragment, PermissionEnum permissionEnum, PermissionListener permissionListener) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean isNeedCheckSelfPermission = false;
            for (String s : permissionEnum.getPermission()) {
                if (ActivityCompat.checkSelfPermission(fragment.getContext(), s) != PackageManager.PERMISSION_GRANTED) {
                    isNeedCheckSelfPermission = true;
                }
            }
            if (isNeedCheckSelfPermission) {
                listeners.put(listeners.size(), permissionListener);
                fragment.requestPermissions(permissionEnum.getPermission(), permissionEnum.getCode());
            } else {
                permissionListener.getPermission(permissionEnum);
            }
//            fragment.requestPermissions(permissionEnum.getPermission(), permissionEnum.getCode());
        } else {
            permissionListener.getPermission(permissionEnum);
        }
    }


    /**
     * 回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        try {

            if (grantResults==null){
                return;
            }
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    for (int j = 0; j < listeners.size(); j++) {
                        listeners.get(j).notObtainedPermission(PermissionEnum.getPermissionEnumByCode(requestCode));
                    }
                    return;
                }
            }
            for (int j = 0; j < listeners.size(); j++) {
                listeners.get(j).getPermission(PermissionEnum.getPermissionEnumByCode(requestCode));
            }
        } catch (Exception e) {
        } finally {
            listeners.clear();
        }
    }


}
