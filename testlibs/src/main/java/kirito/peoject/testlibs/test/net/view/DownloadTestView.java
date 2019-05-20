package kirito.peoject.testlibs.test.net.view;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import kirito.peoject.baselib.mvp.BaseV;
import kirito.peoject.testlibs.R;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/3/5 0005
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/3/5 0005
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class DownloadTestView extends BaseV {
    public Button btn;
    public ProgressBar pb;
    public TextView filePath;

    public DownloadTestView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.download_test_actvity;
    }

    @Override
    public void initView() {
        btn=findViewById(R.id.btn);
        pb=findViewById(R.id.pb);
        filePath=findViewById(R.id.filePath);
    }
}
