package kirito.peoject.testlibs.test.net.view;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import kirito.peoject.baselib.mvp.BaseV;
import kirito.peoject.testlibs.R;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/3/1 0001
 */
public class NetTestView extends BaseV {
    public Button post;
    public TextView response;

    public NetTestView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.net_test_activity;
    }

    @Override
    public void initView() {
        post=findViewById(R.id.post);
        response=findViewById(R.id.response);
    }

}
