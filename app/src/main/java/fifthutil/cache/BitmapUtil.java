package fifthutil.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

import customUI.MyImageView;
import fifthutil.FifUtil;
import httpConnection.HttpConnectionUtil;

public class BitmapUtil {
	private static Context context;
private static LruCacheUtil lruCache = LruCacheUtil.getInstance();
private static DiskLruCacheUtil diskLruCache = DiskLruCacheUtil.getInstance(context);
public static void setImg(MyImageView img){

	new AsyncTask<MyImageView, Void, Bitmap>() {
		MyImageView ivpic;
		String path;
		@Override
		protected Bitmap doInBackground(MyImageView... arg0) {
			ivpic = arg0[0];
			path = arg0[0].getTag().toString();
			if(path!=null){
				Boolean flage = true;
				Bitmap bitmap = lruCache.getBitmap(path);
				if (bitmap != null) {
					flage = false;
				}
				if (flage) {
					InputStream is = diskLruCache.readFromDiskCache(path);
					if (is != null) {
						bitmap = BitmapFactory.decodeStream(is);
						flage = false;
					}
				}
				if (flage) {
					byte[] b = HttpConnectionUtil.getBitmap(path);
					bitmap = HttpConnectionUtil.getBit(b, path, diskLruCache,
							lruCache, context);

				}
				return bitmap;
			}
			return null;
		}
		protected void onPostExecute(Bitmap result) {
			if (result != null){
				ivpic.setImageBitmap(result);
				FifUtil.saveMyBitmap(path, result, context);
			}

		};
	}.execute(img);

}
	public static void setImage(ImageView img){

		new AsyncTask<ImageView, Void, Bitmap>() {
			ImageView ivpic;
			String path;
			@Override
			protected Bitmap doInBackground(ImageView... arg0) {
				ivpic = arg0[0];
				path = arg0[0].getTag().toString();
				if(path!=null){
					Boolean flage = true;
					Bitmap bitmap = lruCache.getBitmap(path);
					if (bitmap != null) {
						flage = false;
					}
					if (flage) {
						InputStream is = diskLruCache.readFromDiskCache(path);
						if (is != null) {
							bitmap = BitmapFactory.decodeStream(is);
							flage = false;
						}
					}
					if (flage) {
						byte[] b = HttpConnectionUtil.getBitmap(path);
						bitmap = HttpConnectionUtil.getBit(b, path, diskLruCache,
								lruCache, context);

					}
					return bitmap;
				}
				return null;
			}
			protected void onPostExecute(Bitmap result) {
				if (result != null){
					fadeInDisplay(ivpic,result);
//					ivpic.setImageBitmap(result);
				}

			};
		}.execute(img);

	}
	private static final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(android.R.color.transparent);
	/**
	 * @author sunglasses
	 * @category 图片加载效果
	 * @param imageView
	 * @param bitmap
	 */
	private static void  fadeInDisplay(ImageView imageView, Bitmap bitmap) {//目前流行的渐变效果
		final TransitionDrawable transitionDrawable = new TransitionDrawable(
				new Drawable[] { TRANSPARENT_DRAWABLE,
						new BitmapDrawable(imageView.getResources(), bitmap) });
		imageView.setImageDrawable(transitionDrawable);
		transitionDrawable.startTransition(500);
	}
}
