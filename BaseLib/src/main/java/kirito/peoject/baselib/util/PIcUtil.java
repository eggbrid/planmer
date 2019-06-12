package kirito.peoject.baselib.util;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import kirito.peoject.baselib.R;

/**
 * @auther kirito
 * @Date 2019-06-11
 * @NOTE 类说明
 */
public class PIcUtil {

    public static void toPic(Fragment fragment){
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picStyle)//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(1)// 最大图片选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(false)// 是否显示拍照按钮 true or false
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false'
                .withAspectRatio(1,1)
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .cropWH(300,300)// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }
    public static void toPic(Activity activity){
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picStyle)//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(1)// 最大图片选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(false)// 是否显示拍照按钮 true or false
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false'
                .withAspectRatio(1,1)
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .cropWH(300,300)// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

}
