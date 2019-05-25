package kirito.peoject.baselib.UI.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.github.ybq.android.spinkit.SpinKitView;
import kirito.peoject.baselib.R;

/**
 * @auther kirito
 * @Date 2019-05-25
 * @NOTE 类说明
 */
public class LoadingDialog extends Dialog {
    private SpinKitView spinKit;
    private TextView mTvText;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoadingDialog(@NonNull Context context) {
        this(context, R.style.MyDialogStyle);

    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        spinKit = findViewById(R.id.spinKit);
        mTvText = findViewById(R.id.tv_text);

    }

    @Override
    public void show() {

        super.show();
        mTvText.setText(TextUtils.isEmpty(message) ? "加载中..." : message);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
