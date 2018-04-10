package fifthutil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Environment;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.transtion.my5th.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
        if(context!=null) {
            bitmapUtils = new BitmapUtils(context, root.getAbsolutePath(), 0.5f);
            bitmapUtils.configDefaultLoadingImage(R.drawable.cpxq);//默认背景图片
            bitmapUtils.configDefaultLoadFailedImage(R.drawable.cpxq);//加载失败图片
            bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);//设置图片压缩类型
        }
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


    public class TextBitmapLoadCallBack extends
            DefaultBitmapLoadCallBack<ImageView> {

        @Override
        public void onLoading(ImageView container, String uri,
                              BitmapDisplayConfig config, long total, long current) {
        }

        @Override
        public void onLoadCompleted(ImageView container, String uri,
                                    Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
            // super.onLoadCompleted(container, uri, bitmap, config, from);
            setTextimg(container,bitmap);
        }

        @Override
        public void onLoadFailed(ImageView container, String uri,
                                 Drawable drawable) {
            // TODO Auto-generated method stub
        }
    }
    public void setTextimg(ImageView container, Bitmap bitmap){
        container.setImageBitmap(bitmap);
        int bwidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();
        WindowManager wm = ((Activity)mContext).getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        Log.e("====", bwidth + " " + bHeight + " " + width);
        int height = width * bHeight / bwidth;
        ViewGroup.LayoutParams para = container.getLayoutParams();
        para.height = height;
        container.setLayoutParams(para);
    }



    /**
     * @author sunglasses
     * @category 图片加载效果
     * @param imageView
     * @param bitmap
     */
    private static  List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
    private static  List<String> bandimg = Collections.synchronizedList(new LinkedList<String>());

    private void fadeInDisplay(ImageView imageView, Bitmap bitmap) {//目前流行的渐变效果
        final TransitionDrawable transitionDrawable = new TransitionDrawable(
                new Drawable[] { TRANSPARENT_DRAWABLE,
                        new BitmapDrawable(imageView.getResources(), bitmap) });
        imageView.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(500);
    }
    private static final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(android.R.color.transparent);

    public static void clearlist(){
        displayedImages .clear();
    }

    public void display(ImageView container,String url){//外部接口函数

                Glide.with(mContext.getApplicationContext()).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(container);
                displayedImages.add(url);
    }



    public void display4rmhd(ImageView container,String url){//外部接口函数

        Glide.with(mContext.getApplicationContext()).load(url).placeholder(R.drawable.rmhd).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(container);
        displayedImages.add(url);
    }
    public BitmapUtils getBitmapUtils(){//外部接口函数

           return bitmapUtils;
    }
    public void display40grid(ImageView container,String url){//外部接口函数
        bitmapUtils.display(container, url, new CustomBitmapLoadCallBack());
//        Glide.with(mContext.getApplicationContext()).load(url).placeholder(R.drawable.cpxq).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(container);
        displayedImages.add(url);
    }
    public void displaywithoutresume(ImageView container,String url){//外部接口函数

//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
//            boolean firstDisplay = !displayedImages.contains(url);
//            if (firstDisplay) {
        Glide.with(mContext).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(container);
//                displayedImages.add(url);
//            }else{
//                bitmapUtils.display(container, url);
//            }
//        }else{
//            imageLoader.displayImage(url, container, options);
//        }
//        LruCacheUtil lruCache=LruCacheUtil.getInstance();
//        container.setTag("https://www.eff.org/sites/default/files/chrome150_0.jpg");
//        HttpConnectionUtil.setBitmap(container,mContext,lruCache);
//        Picasso.with(mContext).load(url).into(container);

    }


    public void displayText(ImageView container,String url){//外部接口函数

                bitmapUtils.display(container, url, new TextBitmapLoadCallBack());
                displayedImages.add(url);

    }


    public void displaybandwithoutresume(ImageView container,String url){//外部接口函数

            boolean firstDisplay = !bandimg.contains(url);
            if (firstDisplay) {
                Glide.with(mContext).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(container);
                bandimg.add(url);
            }

    }



    public void displaywithoutanim(ImageView container,String url){//外部接口函数
        Glide.with(mContext).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(container);
//        Picasso.with(mContext).load(url).into(container);
    }
    public void display4top(ImageView container,String url){//外部接口函数
        Glide.with(mContext).load(url).placeholder(R.drawable.tplb).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(container);
//        Picasso.with(mContext).load(url).into(container);
    }
    public void display4qqtm(ImageView container,String url){//外部接口函数
        Glide.with(mContext).load(url).placeholder(R.drawable.qqtm).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(container);
//        Picasso.with(mContext).load(url).into(container);

    }
    public void clear(){//外部接口函数
        bitmapUtils.clearCache();
        displayedImages.clear();
    }
    public void clearband(){//外部接口函数
        bandimg.clear();
    }

}
