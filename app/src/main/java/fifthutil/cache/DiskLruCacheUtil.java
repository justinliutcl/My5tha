package fifthutil.cache;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

public class DiskLruCacheUtil {
	private static DiskLruCacheUtil diskLruCacheUtil;
	private  DiskLruCache mDiskLruCache;
	
	private DiskLruCacheUtil(Context context){
		if(mDiskLruCache==null)
			open(context);
	}
	public static DiskLruCacheUtil getInstance(Context context){
		if(diskLruCacheUtil==null)
			diskLruCacheUtil =new DiskLruCacheUtil(context);
		return diskLruCacheUtil;
	}
	/**
	 * �õ�DiskLruCache
	 * @param context
	 * @return
	 */
	private  DiskLruCache open(Context context){
		try {
			File cacheDir=getDiskCacheDir(context,"bitmap");
			if(!cacheDir.exists()){
				cacheDir.mkdirs();
			}
			/**
			 * ��һ��������ݵĻ����ַ
			 * �ڶ�������ָ����ǰ����İ汾��
			 * ���������ָ��ͬһ��key���Զ�Ӧ���ٸ������ļ������Ǵ�1
			 * ���ĸ�����ָ�������Ի�������ֽڵ����
			 * ����DiskLruCache��ʵ��֮�󣬾Ϳ���ִ����ݲ�����д�롢���ʡ��Ƴ��
			 */
			mDiskLruCache=DiskLruCache.open(cacheDir, getAppVersion(context), 1, 10*1024*1024);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mDiskLruCache;
	}
	

	/**
	 * д�뵽����**************************************************�ⲿ����
	 * @param url
	 */
	public  void writeToDiskCache(final String url,final byte [] data,Context context){
       Log.i("aaa", "��disk��д������");
		new Thread(){
			public void run() {
				String key=hashKeyForDisk(url);
				try {
					DiskLruCache.Editor  editor = mDiskLruCache.edit(key);
					if(editor!=null){
						OutputStream outputStream=editor.newOutputStream(0);
						outputStream.write(data, 0, data.length);
						editor.commit();
					}
					//
					mDiskLruCache.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	/**
	 * �ӻ����ж�ȡ**********************************************�ⲿ����
	 */
	public  InputStream readFromDiskCache(String url){
		try {
			//�õ�key
			String key=hashKeyForDisk(url);
			DiskLruCache.Snapshot snapShot =mDiskLruCache.get(key);
			if(snapShot!=null){
				InputStream in =snapShot.getInputStream(0);
				return in;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
 /**
  * ��ȡ�����ַ
  */
	private   File getDiskCacheDir(Context context,String name){
		String cachePath =null;
			/*/data/data/<application package>/cache  */
	    cachePath=context.getCacheDir().getParent();
		
		return new File(cachePath+File.separator+name);
	}
	
	/**
	 * ��ȡ��ǰӦ�ó���İ汾��:
	 * ����·���´洢��������ݶ��ᱻ��������ΪDiskLruCache��Ϊ��Ӧ�ó����а汾���µ�ʱ�����е���ݶ�Ӧ�ô��������»�ȡ��
	 */
	private   int getAppVersion(Context context){
		try {
			PackageInfo info =context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return info.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	private  String bytesToHexString(byte [] bytes){
		StringBuilder str =new StringBuilder();
		for(int i=0;i<bytes.length;i++){
			String hex=Integer.toHexString(0xFF&bytes[i]);
			if(hex.length()==1)
				str.append('0');
			str.append(hex);
		}
		return str.toString();
	}
	
	/**
	 * ��image��url��ַ����Md5����
	 */
	private  String hashKeyForDisk(String key){
		String cacheKey =null;
		try {
			final MessageDigest mDigest =MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey=bytesToHexString(mDigest.digest());
		} catch (Exception e) {
			cacheKey=String.valueOf(key.hashCode());
			e.printStackTrace();
		}
		return cacheKey;
	}
}
















