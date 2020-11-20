package com.kirito.planmer.land.view.widget;

import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.kirito.planmer.land.R;
import com.kirito.planmer.land.view.OpenGLView;

import kirito.peoject.baselib.mvp.BaseV;

//import com.moxun.tagcloudlib.view.TagCloudView;

/**
 * @auther kirito
 * @Date 2019-05-21
 * @NOTE 类说明
 */
public class HomeFragmentView extends BaseV {
//    public CommentTitleView title;
//    public TagCloudView tagSphereView;

    public HomeFragmentView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.land_fragment_home;
    }

    @Override
    public void initView() {
        OpenGLView openGLView=   new OpenGLView(activity);
       RelativeLayout rlBg=findViewById(R.id.rlBg);
        rlBg.addView(openGLView);
////        title = findViewById(R.id.title);
////        title.setOnlyTitle("首页");
//        tagSphereView.setAdapter(new TagsAdapter() {
//            @Override
//            public int getCount() {
//                return 100;
//            }
//
//            @Override
//            public View getView(Context context, int position, ViewGroup parent) {
//                View view=new View(context);
//                view.setLayoutParams(new ViewGroup.LayoutParams(40,40));
//                view.setBackgroundColor(Color.rgb(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
//                return view;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return "";
//            }
//
//            @Override
//            public int getPopularity(int position) {
//                return new Random().nextInt(100);
//            }
//
//            @Override
//            public void onThemeColorChanged(View view, int themeColor) {
//
//            }
//        });
    }


}
