package kirito.peoject.baselib.manager.permission;

import android.Manifest;

/**
 * @Description:权限常量类
 * @Author:kirito
 * @CreatTime:2018/1/29 0029
 * @LastModify(最终修改人):王旭
 * @LastModifyTime(最终修改时间):2018/1/29 0029
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */

public class PermissionConstants {
    /**
     * 权限请求码
     */
    final static public int PERMISSION_CALENDAR = 100;//日历权限
    public static String[] calendarPermissions = {
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR};

    final static public int PERMISSION_CAMERA = 101;//相机权限
    public static String[] cameraPermissions = {
            Manifest.permission.CAMERA};

    final static public int PERMISSION_CONTACTS = 102;//通讯录权限
    public static String[] contactsPermissions = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS};

    final static public int PERMISSION_LOCATION = 103;//地理位置权限
    public static String[] locationPermissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    final static public int PERMISSION_MICROPHONE = 104;//话筒权限
    public static String[] microphonePermissions = {
            Manifest.permission.RECORD_AUDIO};

    final static public int PERMISSION_PHONE = 105;//电话权限
    public static String[] phonePermissions = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.ADD_VOICEMAIL,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.USE_SIP};

    final static public int PERMISSION_SENSORS = 106;//传感器权限
    public static String[] sensorsPermissions = {
            Manifest.permission.BODY_SENSORS};
    final static public int PERMISSION_SMS = 107;//SMS权限
    public static String[] smsPermissions = {
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS};
    final static public int PERMISSION_STORAGE = 108;//文件读取权限
    public static String[] storagePermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    final static public int PERMISSION_PIC_GROUP = 109;//头像照片等组合权限
    public static String[] picGroupPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    final static public int CALL_PHONE = 106;//电话权限
    public static String[] callPhonePermissions = {
            Manifest.permission.CALL_PHONE};
    final static public int PERMISSION_READ_PHONE_STATE = 110;//获取手机信息
    public static String[] readPhoneStatePermissions = {
            Manifest.permission.READ_PHONE_STATE
        };

}
