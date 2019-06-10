package kirito.peoject.baselib.UI.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import kirito.peoject.baselib.R;

/**
 * @auther kirito
 * @Date 2019-05-21
 * @NOTE 类说明
 */
public class CommentTitleView extends RelativeLayout {

    private View rootView;
    private ImageView mIvLeft;
    private RelativeLayout mRlLeft;
    private TextView mTvTitle;
    private RelativeLayout mRlTitle;
    private ImageView mIvRight;
    private RelativeLayout mRlRight;

    public CommentTitleView(Context context) {
        this(context, null);

    }

    public CommentTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public void init(Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.layout_comment_title, this, false);
        mIvLeft = rootView.findViewById(R.id.ivLeft);
        mRlLeft = rootView.findViewById(R.id.rlLeft);
        mTvTitle = rootView.findViewById(R.id.tvTitle);
        mRlTitle = rootView.findViewById(R.id.rlTitle);
        mIvRight = rootView.findViewById(R.id.ivRight);
        mRlRight = rootView.findViewById(R.id.rlRight);
        addView(rootView);

    }

    /**
     * set has back button title bar with title string
     * @param activity
     * @param title
     */
    public void setNormalTitle(final Activity activity, String title) {
        mRlLeft.setVisibility(VISIBLE);
        mIvLeft.setVisibility(VISIBLE);
        mTvTitle.setText(title);
        mRlRight.setVisibility(INVISIBLE);
        mIvRight.setVisibility(INVISIBLE);
        mRlLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

    }

    /**
     * set only has title string title bar
     * @param title
     */
    public void setOnlyTitle(String title) {
        mRlLeft.setVisibility(INVISIBLE);
        mIvRight.setVisibility(INVISIBLE);
        mIvLeft.setVisibility(INVISIBLE);
        mRlRight.setVisibility(INVISIBLE);
        mTvTitle.setText(title);
    }

    /**
     * set only has title string title bar
     */
    public void setRight(int id,OnClickListener onClickListener) {
        mIvRight.setVisibility(VISIBLE);
        mIvRight.setImageResource(id);
        mRlRight.setVisibility(VISIBLE);
        mRlRight.setOnClickListener(onClickListener);
    }
    /**
     * set only has title string title bar
     */
    public void goneRight() {
        mIvRight.setVisibility(INVISIBLE);
        mRlRight.setVisibility(INVISIBLE);
    }
}
