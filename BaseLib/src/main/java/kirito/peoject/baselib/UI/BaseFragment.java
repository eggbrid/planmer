package kirito.peoject.baselib.UI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import kirito.peoject.baselib.manager.permission.PermissionManager;
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
public abstract class BaseFragment<V extends BaseV> extends Fragment {

    /**
     * 请求队列。
     */
    public Context activity;
    private boolean isInitView = false;//是否与View建立起映射关系[初始化视图]
    private boolean isFirstLoad = true;//第一次加载
    private PermissionManager permissionManager;
    public boolean isFirstLoad() {
        return isFirstLoad;
    }
    private Map<String, BaseP> presenters = new HashMap<>();
    protected V view;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.activity = activity;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        try {
            Class[] parameterTypes={AppCompatActivity.class};
            Constructor<V> constructor=getTClass().getConstructor(parameterTypes);
            Object[] parameters={this.getActivity()};
            view = constructor.newInstance(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        View fv=inflater.inflate(view.setViewLayout(),container,false);
        view.setFragmentView(fv);
        view.initView();
        afterInitView(view);
        initData();
        return fv;
    }

    /**
     * 权限使用类
     *
     * @return
     */
    public PermissionManager getPermissionManager() {
        if (permissionManager == null) {
            permissionManager = new PermissionManager();
        }
        return permissionManager;
    }

    @Override
    public void onViewCreated(View fv, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(fv, savedInstanceState);

        isInitView = true;//视图view已经初始化完毕
        isCanLoadData();
    }


    public abstract void afterInitView(V view);

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInitView) {
            return;
        }
        //视图可见 已经初始化 第一次加载去初始化数据
        if (getUserVisibleHint() && isFirstLoad) {
            initData();
            isFirstLoad = false;
        }
    }

    public  void initData(){

    }

    public boolean isInitView() {
        return isInitView;
    }

    public void setInitView(boolean initView) {
        isInitView = initView;
    }


    @Override
    public void onDestroy() {
        permissionManager = null;
        for (Map.Entry<String, BaseP> entry : presenters.entrySet()) {
            if (entry.getValue() != null) {
                entry.getValue().cancel();
            }
        }
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionManager != null) {
            permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onResume();
        }
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
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        return p;
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
}
