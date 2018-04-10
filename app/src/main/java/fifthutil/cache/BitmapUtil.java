package fifthutil.cache;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import java.io.InputStream;

import InternetUser.Item.BitmapItem;
import customUI.MyImageView;
import fifthutil.FifUtil;
import httpConnection.HttpConnectionUtil;


public class BitmapUtil {
	private  Context context;
private  LruCacheUtil lruCache ;
private  DiskLruCacheUtil diskLruCache ;


	public BitmapUtil(Context context) {
		this.context = context;
		lruCache= LruCacheUtil.getInstance();
		diskLruCache = DiskLruCacheUtil.getInstance(context);

	}

	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what==0){
				BitmapItem item= (BitmapItem) msg.obj;
				item.getImg().setImageBitmap(item.getBitmap());
				int bwidth = item.getImg().getWidth();
				int bHeight = item.getImg().getHeight();
				WindowManager wm = ((Activity)context).getWindowManager();

				int width = wm.getDefaultDisplay().getWidth();
				Log.e("====", bwidth + " " + bHeight + " " + width);
				int height = width * bHeight / bwidth;
				ViewGroup.LayoutParams para = item.getImg().getLayoutParams();
				para.height = height;
				item.getImg().setLayoutParams(para);
			}
		}
	};


	public  void setImg(MyImageView img){
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
	public  void settextImg(final ImageView img,final String path){
//		new AsyncTask<ImageView, Void, Bitmap>() {
//			ImageView ivpic;
//			String path;
//			@Override
//			protected Bitmap doInBackground(ImageView... arg0) {
//				ivpic = arg0[0];
//				path = arg0[0].getTag().toString();
//				if(path!=null){
//					Boolean flage = true;
//					Bitmap bitmap = lruCache.getBitmap(path);
//					if (bitmap != null) {
//						flage = false;
//					}
//					if (flage) {
//						InputStream is = diskLruCache.readFromDiskCache(path);
//						if (is != null) {
//							bitmap = BitmapFactory.decodeStream(is);
//							flage = false;
//						}
//					}
//					if (flage) {
//						byte[] b = HttpConnectionUtil.getBitmap(path);
//						bitmap = HttpConnectionUtil.getBit(b, path, diskLruCache,
//								lruCache, context);
//
//					}
//					return bitmap;
//				}
//				return null;
//			}
//			protected void onPostExecute(Bitmap result) {
//				if (result != null){
//					ivpic.setImageBitmap(result);
//					int bwidth = result.getWidth();
//					int bHeight = result.getHeight();
//					WindowManager wm = ((Activity)context).getWindowManager();
//
//					int width = wm.getDefaultDisplay().getWidth();
//					Log.e("====", bwidth + " " + bHeight + " " + width);
//					int height = width * bHeight / bwidth;
//					ViewGroup.LayoutParams para = ivpic.getLayoutParams();
//					para.height = height;
//					ivpic.setLayoutParams(para);
//				}
//
//			};
//		}.execute(img);

		new Thread(){
			@Override
			public void run() {
				super.run();
				if(path!=null) {
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
					if (bitmap != null){
						BitmapItem item=new BitmapItem(img,bitmap);
						Message mes=new Message();
						mes.what=0;
						mes.obj=item;
						handler.sendMessage(mes);
					}
				}

			}
		}.start();


	}




	public  void setSelectImg(ImageView img, final String sname){
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
					ivpic.setImageBitmap(result);

					FifUtil.saveSelectBitmap(path, result, context,sname);
				}

			};
		}.execute(img);

	}


	public  void setImage(ImageView img){

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
//					fadeInDisplay(ivpic,result);
					ivpic.setImageBitmap(result);
				}

			};
		}.execute(img);

	}








	private  final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(android.R.color.transparent);
	/**
	 * @author sunglasses
	 * @category 图片加载效果
	 * @param imageView
	 * @param bitmap
	 */
	private  void  fadeInDisplay(ImageView imageView, Bitmap bitmap) {//目前流行的渐变效果
		final TransitionDrawable transitionDrawable = new TransitionDrawable(
				new Drawable[] { TRANSPARENT_DRAWABLE,
						new BitmapDrawable(imageView.getResources(), bitmap) });
		imageView.setImageDrawable(transitionDrawable);
		transitionDrawable.startTransition(500);
	}
}
