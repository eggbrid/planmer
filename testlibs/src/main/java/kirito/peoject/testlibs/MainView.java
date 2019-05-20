package kirito.peoject.testlibs;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import kirito.peoject.baselib.mvp.BaseV;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/3/1 0001
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/3/1 0001
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class MainView extends BaseV {
    public Button net;
    public Button download;
    public MainView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.test_main;
    }

    @Override
    public void initView() {
        net=findViewById(R.id.net);
        download=findViewById(R.id.download);
    }

}
