package com.kirito.planmer.user.view;

import android.graphics.Typeface;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import com.kirito.planmer.user.R;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @auther kirito
 * @Date 2019-05-24
 * @NOTE 类说明
 */
public class LoginActivityView extends BaseV {
    public LoginActivityView(AppCompatActivity activity) {
        super(activity);
    }
public TextView tvLogin;
    public TextView tvLoginBtn;
    @Override
    public int setViewLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        tvLogin=findViewById(R.id.tvLogin);
        tvLoginBtn=findViewById(R.id.tvLoginBtn);

        Typeface typeface= ResourcesCompat.getFont(activity,R.font.cat);
        tvLogin.setTypeface(typeface);
    }
}
