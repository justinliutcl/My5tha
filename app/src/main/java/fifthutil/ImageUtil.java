package fifthutil;

import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Environment;
import android.widget.ImageView;

import com.example.transtion.my5th.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

import java.io.File;

/**
 * Created by baicai on 2016/3/15.
 */
public class ImageUtil {
    private BitmapUtils bitmapUtils;
    private Context mContext;

    public ImageUtil(Context context) {
        // TODO Auto-generated constructor stub
        this.mContext = context;
        File root=new File(Environment.getExternalStorageDirectory(), "5tha");
        if(!root.exists())
            root.mkdirs();
        bitmapUtils = new BitmapUtils(context, root.getAbsolutePath(), 0.5f);
        bitmapUtils.configDefaultLoadingImage(R.drawable.grayandwhite);//默认背景图片
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.grayandwhite);//加载失败图片
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);//设置图片压缩类型

    }
    /**
     *
     * @author sunglasses
     * @category 图片回调函数
     */
    public class CustomBitmapLoadCallBack extends
            DefaultBitmapLoadCallBack<ImageView> {

        @Override
        public void onLoading(ImageView container, String uri,
                              BitmapDisplayConfig config, long total, long current) {
        }

        @Override
        public void onLoadCompleted(ImageView container, String uri,
                                    Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
            // super.onLoadCompleted(container, uri, bitmap, config, from);
            fadeInDisplay(container, bitmap);
        }

        @Override
        public void onLoadFailed(ImageView container, String uri,
                                 Drawable drawable) {
            // TODO Auto-generated method stub
        }
    }

    private static final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(android.R.color.transparent);
    /**
     * @author sunglasses
     * @category 图片加载效果
     * @param imageView
     * @param bitmap
     */
    private void fadeInDisplay(ImageView imageView, Bitmap bitmap) {//目前流行的渐变效果
        final TransitionDrawable transitionDrawable = new TransitionDrawable(
                new Drawable[] { TRANSPARENT_DRAWABLE,
                        new BitmapDrawable(imageView.getResources(), bitmap) });
        imageView.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(500);
    }
    public void display(ImageView container,String url){//外部接口函数
        bitmapUtils.display(container, url, new CustomBitmapLoadCallBack());

    }
    public void clear(){//外部接口函数
        bitmapUtils.clearCache();

    }
}
