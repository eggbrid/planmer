package kirito.peoject.baselib.manager.permission.listener;

import kirito.peoject.baselib.manager.permission.enums.PermissionEnum;

/**
 * @Description:权限回调监听
 * @Author:王旭
 * @CreatTime:2018/1/29 0029
 * @LastModify(最终修改人):王旭
 * @LastModifyTime(最终修改时间):2018/1/29 0029
 * @LastChekedBy: 王旭
 * @needingAttention(注意事项):
 */

public interface PermissionListener {
     void getPermission(PermissionEnum permissionEnum);
    void notObtainedPermission(PermissionEnum permissionEnum);
}
