package httpConnection;

import android.util.Log;

import com.lidroid.xutils.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UrlConnection {
		public interface eorrCallback{
		public void eorrback(String str);
	}
	    private static int TIME_OUT = 10000;

	    public static String requestPost(String httpurl,Map<String,String> paramsMap,eorrCallback callback){
	        HttpURLConnection conn = null;
	        OutputStream outputStream = null;
	        BufferedReader br = null;
	        try {
	            URL url = new URL(httpurl);
	            conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("Content-Type","application/json");
				conn.setRequestProperty("apikey", "5efb40ae2660e7c80327e7cdf0895758");
//				conn.setRequestProperty("Host","api.5tha.com");




//	            conn.setRequestProperty("Postman-Token","4dc7727d-66b7-0c21-613e-7ffa542c07b8");
				for (String key :  conn.getRequestProperties().keySet()) {
					String b="";
					for (String a:conn.getRequestProperties().get(key)){
						b=a+b;
					}
					Log.i("lifeweeker", "key= " + key + "  and  value= " +b);
				}
	            conn.setRequestMethod("POST");
				conn.setUseCaches(false);
				conn.setFollowRedirects(false);
	            conn.setReadTimeout(TIME_OUT);
	            conn.setConnectTimeout(TIME_OUT);
	            conn.setDoInput(true);
	            conn.setDoOutput(true);
	            conn.connect();

	            String params = mapToString(paramsMap);
	            Log.i("lifeweeker", params);


				outputStream = conn.getOutputStream();
	            outputStream.write(params.getBytes());
	            outputStream.flush();

	            int code = conn.getResponseCode();
	            if(code == 200){
	                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                StringBuffer sb = new StringBuffer();
	                String line;
	                while((line = br.readLine())!=null){
	                    sb.append(line);
	                }
	                return sb.toString();
	            }else{
	                LogUtils.e("code：" + code);
	                LogUtils.e("URL：" + conn.getURL());
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	            LogUtils.e("网络连接出现MalformedURLException异常");
				callback.eorrback("网络连接出现MalformedURLException异常");
	        } catch (IOException e) {
				Log.i("lifeweeker", httpurl);
				LogUtils.e("网络连接出现IO异常");
				callback.eorrback("网络连接出现MalformedURLException异常");
	            e.printStackTrace();
	        }finally {
	            if(outputStream != null){
	                try {
	                    outputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if(br != null){
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if(conn != null){
	                conn.disconnect();
	            }

	        }
	        return "";
	    }


//	public static Bitmap requestimg(String httpurl,eorrCallback callback){
//		HttpURLConnection conn = null;
//		OutputStream outputStream = null;
//		BufferedReader br = null;
//		try {
//			URL url = new URL(httpurl);
//			conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setUseCaches(false);
//			conn.setFollowRedirects(false);
//			conn.setReadTimeout(TIME_OUT);
//			conn.setConnectTimeout(TIME_OUT);
//			conn.setDoInput(true);
//			conn.setDoOutput(true);
//			conn.connect();
//			int code = conn.getResponseCode();
//			if(code == 200 || code==301){
//
////				return readInputStream(conn.getInputStream());
//				return BitmapFactory.decodeStream(conn.getInputStream());
//			}else{
//				LogUtils.e("code：" + code);
//				LogUtils.e("URL：" + conn.getURL());
//			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//			LogUtils.e("网络连接出现MalformedURLException异常");
//		} catch (IOException e) {
//			Log.i("lifeweeker", httpurl);
//			LogUtils.e("网络连接出现IO异常");
//			e.printStackTrace();
//		}finally {
//			if(outputStream != null){
//				try {
//					outputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if(br != null){
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if(conn != null){
//				conn.disconnect();
//			}
//
//		}
//		return null;
//	}
	public static byte[] readInputStream(InputStream inputStream){
		try {
			byte[] buffer = new byte[1024];
			int len = -1;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			outputStream.close();
			inputStream.close();
			return outputStream.toByteArray();
		}catch (Exception e){

		}
		return null;
	}

	/**
	     * 将参数拼成字符串
	     * @param map
	     * @return
	     */
	    private static String mapToString(Map<String,String> map){
//	        if(Utils.collcationIsEmpty(map)){
//	            return "";
//	        }
//	        StringBuilder params = new StringBuilder();
	        Set<String> keySet = map.keySet();
	        Iterator<String> iterator = keySet.iterator();
	        JSONObject jsonObject = new JSONObject();

	        while (iterator.hasNext()){
	            String key = iterator.next();
	            String value = map.get(key);
	            try {
	                jsonObject.put(key,value);
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
//	            params.append(key+"="+value);
//	            params.append("&");
	        }
//	        params.append("Data="+jsonObject.toString());
//	        JSONObject jsonObject1 = new JSONObject();
//	        try {
//	            jsonObject1.put("Data",jsonObject.toString());
//	        } catch (JSONException e) {
//	            e.printStackTrace();
//	        }
//	        params.append("{Data:"+jsonObject.toString()+"}");
//	        LogUtils.d("loginTest","参数："+jsonObject1.toString());
	        //key=value&key2=value2&   去除最后一个&
//	        params.deleteCharAt(params.length()-1);
//	        return params.toString();
//	        return jsonObject1.toString();
	        return jsonObject.toString();
	    }
	    
//	    private static String mapToString(Map<String,String> map){
////	        StringBuilder params = new StringBuilder();
//	        Set<String> keySet = map.keySet();
//	        Iterator<String> iterator = keySet.iterator();
//	        JSONObject jsonObject = new JSONObject();
//
//	        while (iterator.hasNext()){
//	            String key = iterator.next();
//	            String value = map.get(key);
//	            try {
//	                jsonObject.put(key,value);
//	            } catch (JSONException e) {
//	                e.printStackTrace();
//	            }
////	            params.append(key+"="+value);
////	            params.append("&");
//	        }
////	        params.append("Data="+jsonObject.toString());
//	        JSONObject jsonObject1 = new JSONObject();
//	        try {
//	            jsonObject1.put("Data",jsonObject.toString());
//	        } catch (JSONException e) {
//	            e.printStackTrace();
//	        }
////	        params.append("{Data:"+jsonObject.toString()+"}");
//	        //key=value&key2=value2&   去除最后一个&
////	        params.deleteCharAt(params.length()-1);
////	        return params.toString();
//	        return jsonObject1.toString();
//	    }
}