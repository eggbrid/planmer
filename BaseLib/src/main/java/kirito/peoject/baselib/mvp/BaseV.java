package kirito.peoject.baselib.mvp;

import android.view.View;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import com.gyf.immersionbar.ImmersionBar;
import kirito.peoject.baselib.R;
import kirito.peoject.baselib.UI.widget.LoadingDialog;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 */
public abstract class BaseV {
    protected AppCompatActivity activity;
    protected View fragmentView;
    LoadingDialog loadingDialog;
    protected int barColor= R.color.black;

    public int getBarColor() {
        return barColor;
    }

    public void setBarColor(int barColor) {
        this.barColor = barColor;
    }
    public LoadingDialog getLoadingDialog(String message){
        if (loadingDialog==null){
            loadingDialog=new LoadingDialog(activity);
        }
        loadingDialog.setMessage(message);
        return loadingDialog;
    }
    public void showLoading(String message){
        getLoadingDialog(message).show();
    }
    public void dismissLoading(){
        if (loadingDialog!=null){
            loadingDialog.dismiss();
        }
    }


    public void setFragmentView(View fragmentView) {
        this.fragmentView = fragmentView;
    }

    public BaseV(AppCompatActivity activity) {
        this.activity = activity;
    }

    public abstract int setViewLayout();

    public abstract void initView();

    public <T extends View> T findViewById(@IdRes int id) {
        if (fragmentView != null) {
            return fragmentView.findViewById(id);
        }
        return activity.getDelegate().findViewById(id);
    }
    public boolean isShowBar(){
        return true;
    }


}
