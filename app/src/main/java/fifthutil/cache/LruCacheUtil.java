package fifthutil.cache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

public class LruCacheUtil implements ImageCache {
	private LruCache<String, Bitmap> memoryCache;
	private static LruCacheUtil lruCacheUtil;

	private LruCacheUtil() {
		int maxSize = (int) Runtime.getRuntime().maxMemory() / 8;
		if (memoryCache == null)
			memoryCache = new LruCache<String, Bitmap>(maxSize) {
				@Override
				protected int sizeOf(String key, Bitmap value) {
					// TODO Auto-generated method stub
					return value.getRowBytes()*value.getHeight();
				}
			};
	}
	
	public static LruCacheUtil getInstance(){
		if(lruCacheUtil==null)
			lruCacheUtil = new LruCacheUtil();
		return lruCacheUtil;
	}
	
	public void putBitmap(String url,Bitmap bm){
		if(url!=null && bm!=null){
			if(memoryCache.get(url)==null){
				memoryCache.put(url, bm);
				Log.i("aaa", "�洢��lrucache");
			}
		}
	}
	
	public Bitmap getBitmap(String url){
		if(url!=null){
			return memoryCache.get(url);
		}
		return null;
	}
}

















