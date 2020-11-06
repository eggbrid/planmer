package kirito.peoject.baselib.UI;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import kirito.peoject.baselib.R;
import kirito.peoject.baselib.manager.permission.PermissionManager;
import kirito.peoject.baselib.manager.permission.enums.PermissionEnum;
import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.mvp.BaseV;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author:王旭
 * @CreatTime:2019/2/26 0026
 * @LastModify(最终修改人):王旭
 * @LastModifyTime(最终修改时间):2019/2/26 0026
 * @LastChekedBy: 王旭
 * @needingAttention(注意事项):
 */
public abstract class BaseActivity<V extends BaseV> extends AppCompatActivity implements View.OnClickListener {
    private PermissionManager permissionManager;
    private Map<String, BaseP> presenters = new HashMap<>();
    public V view;

    //参数初始化
    public void initParams(Bundle savedInstanceState) {
    }

    //初始化数据
    public void initData() {

    }

    public void afterInitView(V v) {

    }

    //保存数据，原本可以封装到此处，不外抛出，由于害怕用户有其他需要保存的数据，目前抛出
    public Bundle saveParam(Bundle outState) {
        return outState;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置键盘遮挡界面
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        try {
            initParams(savedInstanceState);
            Class[] parameterTypes = {AppCompatActivity.class};
            Constructor<V> constructor = getTClass().getConstructor(parameterTypes);
            Object[] parameters = {this};
            view = constructor.newInstance(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(view.setViewLayout());
        initImmersionBar();
        view.initView();
        afterInitView(view);
        initData();
    }

    public void initImmersionBar(){
        ImmersionBar immersionBar= ImmersionBar.with(this).reset()
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .navigationBarDarkIcon(true)
                .barColor(R.color.black).fullScreen(!view.isShowBar()).fitsSystemWindows(true);
        if (!view.isShowBar()) {
            immersionBar.hideBar(BarHide.FLAG_HIDE_BAR);
        }
        immersionBar.init();
    }


    @Override
    protected void onStart() {
        super.onStart();
        //调用接口，向服务器上传登录时间和类名

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        permissionManager = null;
        for (Map.Entry<String, BaseP> entry : presenters.entrySet()) {
            if (entry.getValue() != null) {
                entry.getValue().cancel();
            }
        }
        System.gc();
    }


    //获取权限使用类
    public PermissionManager getPermissionManager() {
        if (permissionManager == null) {
            permissionManager = new PermissionManager();
        }
        return permissionManager;
    }


    public void onBackClick() {
        finish();
    }

    public String getClassName() {
        String simpleClassName = this.getClass().getSimpleName();
        return simpleClassName;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        Bundle outStates = saveParam(outState);
        if (outStates != null) {
            super.onSaveInstanceState(outStates);
        } else {
            super.onSaveInstanceState(outState);
        }
    }

    /**
     * 辅助子类权限请求
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionEnum.getPermissionEnumByCode(requestCode) != null) {
            if (permissionManager != null) {
                permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }

            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean hasEdittextChange = true;

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (hasEdittextChange && v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public <P extends BaseP> P getP(Class<P> pClass) {
        String tag = pClass.getPackage() + pClass.getName();
        if (presenters.containsKey(tag)) {
            return (P) presenters.get(tag);
        }
        P p = null;
        try {
            p = pClass.newInstance();
            presenters.put(tag, p);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return p;
    }

    public <T extends BaseP> void bindP(T t) {
        String tag = t.getClass().getPackage() + t.getClass().getName();
        presenters.put(tag, t);
    }


    public Class<V> getTClass() {
        Type type = getClass().getGenericSuperclass(); // 判断 是否泛型
        if (type instanceof ParameterizedType) { // 返回表示此类型实际类型参数的Type对象的数组. // 当有多个泛型类时，数组的长度就不是1了
            Type[] ptype = ((ParameterizedType) type).getActualTypeArguments();
            return (Class) ptype[0]; //将第一个泛型T对应的类返回（这里只有一个）
        } else if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            return (Class) rawType;
        } else {
            return null;


        }
    }

    public String getTag() {
        return this.getClassName();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            onBackClick();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
