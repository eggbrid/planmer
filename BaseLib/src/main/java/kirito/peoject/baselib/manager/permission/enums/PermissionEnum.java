package kirito.peoject.baselib.manager.permission.enums;


import kirito.peoject.baselib.manager.permission.PermissionConstants;

/**
 * @Description:
 * @Author:王旭
 * @CreatTime:2018/1/29 0029
 * @LastModify(最终修改人):王旭
 * @LastModifyTime(最终修改时间):2018/1/29 0029
 * @LastChekedBy: 王旭
 * @needingAttention(注意事项):
 */

public enum  PermissionEnum {


    CALENDAR(PermissionConstants.PERMISSION_CALENDAR, PermissionConstants.calendarPermissions),
    CAMERA(PermissionConstants.PERMISSION_CAMERA, PermissionConstants.cameraPermissions),
    CONTACTS(PermissionConstants.PERMISSION_CONTACTS, PermissionConstants.contactsPermissions),
    LOCATION(PermissionConstants.PERMISSION_LOCATION, PermissionConstants.locationPermissions),
    MICROPHONE(PermissionConstants.PERMISSION_MICROPHONE, PermissionConstants.microphonePermissions),
    PHONE(PermissionConstants.PERMISSION_PHONE, PermissionConstants.phonePermissions),
    SENSORS(PermissionConstants.PERMISSION_SENSORS, PermissionConstants.sensorsPermissions),
    SMS(PermissionConstants.PERMISSION_SMS, PermissionConstants.smsPermissions),
    STORAGE(PermissionConstants.PERMISSION_STORAGE, PermissionConstants.storagePermissions),
    PIC_GROUP(PermissionConstants.PERMISSION_PIC_GROUP, PermissionConstants.picGroupPermissions),
    CALL_PHONE(PermissionConstants.CALL_PHONE, PermissionConstants.callPhonePermissions),
    READ_PHONE_STATE(PermissionConstants.PERMISSION_READ_PHONE_STATE, PermissionConstants.readPhoneStatePermissions);

    private int code;
    private String[] permission;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String[] getPermission() {
        return permission;
    }

    public void setPermission(String[] permission) {
        this.permission = permission;
    }

    PermissionEnum(int code, String[] permission) {
        this.code = code;
        this.permission = permission;
    }

    public static PermissionEnum getPermissionEnumByCode(int code) {
        for (PermissionEnum permissionEnum : PermissionEnum.values()) {
            if (code == permissionEnum.getCode()) {
                return permissionEnum;
            }
        }
        return null;
    }

}
