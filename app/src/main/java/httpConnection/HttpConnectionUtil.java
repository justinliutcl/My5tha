package httpConnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.transtion.my5th.DIndividualActivity.Addresssave;
import com.example.transtion.my5th.mActivity.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import InternetUser.A_Home.AdvertismentUser;
import InternetUser.A_Home.BanderDetailsUser;
import InternetUser.A_Home.H5user;
import InternetUser.A_Home.HostFragUser;
import InternetUser.A_Home.HostTitle;
import InternetUser.A_Home.JxduItem;
import InternetUser.A_Home.JxdxUser;
import InternetUser.A_Home.QqtmUser;
import InternetUser.A_Home.SelectClassUser;
import InternetUser.A_Home.SelectTitleItem;
import InternetUser.A_Home.TopImgItem;
import InternetUser.A_Home.UpdataUser;
import InternetUser.AddressItem;
import InternetUser.AllHost;
import InternetUser.Chonguser;
import InternetUser.CollectUser;
import InternetUser.Communicationuser;
import InternetUser.Communitionuser;
import InternetUser.CouponHuser;
import InternetUser.CouponPastuser;
import InternetUser.Duiuser;
import InternetUser.GoodsorderUser;
import InternetUser.IndividualFrag.Realname;
import InternetUser.IndividualFrag.Zhongjiang;
import InternetUser.IndividualHost;
import InternetUser.IndividualUser.WaitincomeUser;
import InternetUser.InoutHisUser;
import InternetUser.Item.ChongItem;
import InternetUser.Item.CollectItem;
import InternetUser.Item.CommunicationItem;
import InternetUser.Item.CouponHitem;
import InternetUser.Item.CouponNuseItem;
import InternetUser.Item.CouponPastItem;
import InternetUser.Item.DuiItem;
import InternetUser.Item.InoutHisItem;
import InternetUser.Item.ReferItem;
import InternetUser.Item.TixianHisItem;
import InternetUser.LoginUser;
import InternetUser.O_other.AdverImg;
import InternetUser.O_other.DetailItem;
import InternetUser.O_other.DetailTatilAttributeItem;
import InternetUser.O_other.EvaluateItem;
import InternetUser.O_other.EvaluateUser;
import InternetUser.O_other.OtherGoodUser;
import InternetUser.O_other.OtherUser;
import InternetUser.O_other.PinPaiUser;
import InternetUser.O_other.PropertyValueItem;
import InternetUser.O_other.StardIdUser;
import InternetUser.O_other.TextUser;
import InternetUser.O_other.ValueItem;
import InternetUser.ReferUser;
import InternetUser.SettingUser;
import InternetUser.TixianHIsUser;
import InternetUser.TravelItem;
import InternetUser.TravelUser;
import InternetUser.Txdetails;
import InternetUser.order.GoodsDetailUser;
import InternetUser.order.GoodsorderItem;
import InternetUser.order.LogistDetails;
import InternetUser.order.LogisticsItem;
import InternetUser.order.OrderDetailItem;
import InternetUser.order.OrderTatilAttrItem;
import InternetUser.shopcar.FendanUser;
import InternetUser.shopcar.GoodsOrderUser;
import InternetUser.shopcar.Shopcarlist;
import customUI.Loding.ProgressWheel;
import fifthutil.JumpUtil;
import fifthutil.LodingUtil;
import fifthutil.cache.DiskLruCacheUtil;
import fifthutil.cache.LruCacheUtil;

/**
 * Created by baicai on 2016/2/22.
 */
public class HttpConnectionUtil {
    private static RequestQueue rq;
    private static LruCacheUtil lruCache = LruCacheUtil.getInstance();

    public interface OnJsonCall{
        public void JsonCallBack(String str);
    }
    public interface OnErrorJsonCall{
        public void JsonCallBack(String str);
    }
    public interface OnimgCall{
        public void imgCallBack(Bitmap bitmap);
    }
    public static void getGetJson(final Context context,String url,final LodingUtil loading, final OnJsonCall callback){
        if(rq==null)
            rq= Volley.newRequestQueue(context);

        Log.i("fifth",url);
        StringRequest json=new StringRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
               AllHost host= getAllHost(arg0);

                if(host.isSuccess()){
                    if(host.getCode()!=2)
                        callback.JsonCallBack(arg0);
                    else
                    if (loading!=null){
                        loading.dismiss();
                    }
                }
                else{
                    if (loading!=null){
                        loading.dismiss();
                    }
                    Log.i("lifetime",arg0);
                    if(host.getCode()!=2)
                    Toast.makeText(context, host.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                if (loading!=null)
                   loading.dismiss();
                Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String>head=new HashMap<String,String>();
                head.put("Content-Type", "application/json");
                head.put("apikey", "5efb40ae2660e7c80327e7cdf0895758");
                return head;
            }
        };
        json.setRetryPolicy(new DefaultRetryPolicy(50000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(json);
    }




    public static void getGetJsonhaveError(final Context context,String url,final LodingUtil loading, final OnJsonCall callback,final OnErrorJsonCall errorCallback){
        if(rq==null)
            rq= Volley.newRequestQueue(context);

        Log.i("fifth",url);
        StringRequest json=new StringRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                AllHost host= getAllHost(arg0);
                if(host.isSuccess()){
                    if(host.getCode()!=2) {
                        callback.JsonCallBack(arg0);
                    }
                    else{
                        errorCallback.JsonCallBack(arg0);
                    }
                    if (loading!=null){
                        loading.dismiss();
                    }
                }
                else{
                    if (loading!=null){
                        loading.dismiss();
                    }
                    Log.i("lifetime",arg0);
                    if(host.getCode()!=2)
                        Toast.makeText(context, host.getMessage(), Toast.LENGTH_SHORT).show();
                    else
                        errorCallback.JsonCallBack(arg0);
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                if (loading!=null)
                    loading.dismiss();
                Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
                errorCallback.JsonCallBack("");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String>head=new HashMap<String,String>();
                head.put("Content-Type", "application/json");
                head.put("apikey", "5efb40ae2660e7c80327e7cdf0895758");
                return head;
            }
        };
        json.setRetryPolicy(new DefaultRetryPolicy(50000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(json);
    }




    public static void getGetshopcarJson(final Context context,String url,final LodingUtil loading, final OnJsonCall callback, final LinearLayout layout, final ListView mlist){
        if(rq==null)
            rq= Volley.newRequestQueue(context);

        Log.i("fifth",url);
        StringRequest json=new StringRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                AllHost host= getAllHost(arg0);
                if(host.isSuccess()){
                    if(host.getCode()!=2)
                        callback.JsonCallBack(arg0);
                    else
                    if (loading!=null){
                        loading.dismiss();
                        layout.setVisibility(View.VISIBLE);
                        mlist.setVisibility(View.GONE);
                    }
                }
                else{
                    if (loading!=null){
                        loading.dismiss();
                    }
                    Log.i("lifetime",arg0);
                    if(host.getCode()!=2)
                        Toast.makeText(context, host.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                if (loading!=null)
                    loading.dismiss();
                Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String>head=new HashMap<String,String>();
                head.put("Content-Type", "application/json");
                head.put("apikey", "5efb40ae2660e7c80327e7cdf0895758");
                return head;
            }
        };
        json.setRetryPolicy(new DefaultRetryPolicy(50000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(json);
    }


    public static void getGetshopJson(final Context context,String url,final LodingUtil loading, final OnJsonCall callback){
        if(rq==null)
            rq= Volley.newRequestQueue(context);

        Log.i("fifth",url);
        StringRequest json=new StringRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                AllHost host= getAllHost(arg0);
                if(host.isSuccess()){
                    if(host.getCode()!=2)
                        callback.JsonCallBack(arg0);
                    else
                    if (loading!=null){
                        loading.dismiss();
                    }
                }
                else{
                    if (loading!=null){
                        loading.dismiss();
                    }
                    Log.i("lifetime", arg0);
                    if(host.getCode()==3){
                        JumpUtil.jump(context, Addresssave.class,true);
                        Toast.makeText(context, "请至少添加一个收货地址", Toast.LENGTH_SHORT).show();
                    }else if(host.getCode()!=2)
                        Toast.makeText(context, host.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                if (loading!=null)
                    loading.dismiss();
                Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String>head=new HashMap<String,String>();
                head.put("Content-Type", "application/json");
                head.put("apikey", "5efb40ae2660e7c80327e7cdf0895758");
                return head;
            }
        };
        json.setRetryPolicy(new DefaultRetryPolicy(50000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(json);
    }


    public static void getGetJson4Welcome(final Context context,String url, final OnJsonCall callback){
        if(rq==null)
            rq= Volley.newRequestQueue(context);
        StringRequest json=new StringRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                AllHost host= getAllHost(arg0);
                if(host.isSuccess()){
                    if(host.getCode()!=2)
                        callback.JsonCallBack(arg0);
                }
                else{
                    Log.i("lifetime", arg0);
                    if(host.getCode()!=2)
                         Toast.makeText(context, host.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
                JumpUtil.jump2finish(context, MainActivity.class, true);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String>head=new HashMap<String,String>();
                head.put("Content-Type", "application/json");
                head.put("apikey", "5efb40ae2660e7c80327e7cdf0895758");
                return head;
            }
        };

        rq.add(json);
    }

    public static void getGetJsonwithProgress(final Context context,String url,final ProgressWheel loading, final OnJsonCall callback){
        if(rq==null)
            rq= Volley.newRequestQueue(context);
        StringRequest json=new StringRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                AllHost host= getAllHost(arg0);
                if(host.isSuccess()){
                    if(host.getCode()!=2)
                         callback.JsonCallBack(arg0);
                }
                else{
                    if (loading!=null){
                        loading.setVisibility(View.GONE);
                    }

                    Log.i("lifetime", arg0);
                    if(host.getCode()!=2)
                         Toast.makeText(context, host.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                if (loading!=null)
                    loading.setVisibility(View.GONE);
                Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String>head=new HashMap<String,String>();
                head.put("Content-Type", "application/json");
                head.put("apikey", "5efb40ae2660e7c80327e7cdf0895758");
                return head;
            }
        };

        rq.add(json);
    }

    public static void getGetJsonWithProgressOnerror(final Context context,String url,final ProgressWheel progress, final OnJsonCall callback,final OnErrorJsonCall errorCallback){
        if(rq==null)
            rq= Volley.newRequestQueue(context);
        StringRequest json=new StringRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                AllHost host= getAllHost(arg0);
                Log.i("ttttt",arg0);
                if(host.isSuccess()){
                    if(host.getCode()!=2)
                         callback.JsonCallBack(arg0);
                    else
                        errorCallback.JsonCallBack(arg0);
                }
                else{
                    errorCallback.JsonCallBack(arg0);
                    progress.setVisibility(View.GONE);
                    Log.i("lifetime", arg0);
                    if(host.getCode()!=2)
                          Toast.makeText(context, host.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                progress.setVisibility(View.GONE);
                Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String>head=new HashMap<String,String>();
                head.put("Content-Type", "application/json");
                head.put("apikey", "5efb40ae2660e7c80327e7cdf0895758");
                return head;
            }
        };

        rq.add(json);
    }
    public static Bitmap getBit(byte[] b,String path,DiskLruCacheUtil diskLruCache,LruCacheUtil lruCache,Context context){
        if(b!=null){
            Bitmap bit=BitmapFactory.decodeByteArray(b, 0, b.length);
            diskLruCache.writeToDiskCache(path, b, context);
            lruCache.putBitmap(path, bit);
            return bit;
        }
        return null;
    }
    public static byte[] getBitmap(String path){
        try {
            HttpClient client=new DefaultHttpClient();
            HttpGet get=new HttpGet(path);
            HttpResponse response=client.execute(get);
            if(response.getStatusLine().getStatusCode()==200){

                return EntityUtils.toByteArray(response.getEntity());
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static byte[] getBitmap(String path){
//        byte[] b=  UrlConnection.requestimg(path, new UrlConnection.eorrCallback() {
//            @Override
//            public void eorrback(String str) {
//            }
//        });
//        return b;
//    }




//    public static void getGet(final Context context, final String url,final OnJsonCall callback){
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                final String a=UrlConnection.requestGet("http://1.192.127.27:7777/v1/Register/SendCode?Loginname=15803845778");
//
//                hander.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        callback.JsonCallBack(a);
//                    }
//                });
//                Log.i("lifeweeker", a);
//            }
//        }.start();
//    }

    public static void getJsonJsonwithDialog(final Context context, final String url,final String[] key,final String[] value, final LodingUtil loading,final OnJsonCall callback){
       final Handler handler=new Handler();
        new Thread(){
            @Override
            public void run() {
                super.run();
                Map<String,String>map=new HashMap<String, String>();
                for(int i=0;i<key.length;i++){
                    map.put(key[i], value[i]);
                }
                final String a=UrlConnection.requestPost(url, map, new UrlConnection.eorrCallback() {
                    @Override
                    public void eorrback(String str) {
                        if (loading!=null)
                        loading.dismiss();
                    }
                });

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (a.length() > 1) {
                            AllHost host = getAllHost(a);
                            if (host.isSuccess()){
                                if(host.getCode()!=2)
                                    callback.JsonCallBack(a);
                                else
                                    loading.dismiss();
                            }

                            else {
                                if(host.getCode()!=2)
                                   Toast.makeText(context, host.getMessage(), Toast.LENGTH_SHORT).show();
                                if (loading != null)
                                    loading.dismiss();
                            }
                        } else
                            Log.i("lifetime", a);
                    }
                });
                Log.i("lifeweeker", a);
            }
        }.start();
    }

    public static void getJsonJsonwithDialog4we(final Context context, final String url,final String[] key,final String[] value, final LodingUtil loading,final OnJsonCall callback){
        final Handler handler=new Handler();
        new Thread(){
            @Override
            public void run() {
                super.run();
                Map<String,String>map=new HashMap<String, String>();
                for(int i=0;i<key.length;i++){
                    map.put(key[i], value[i]);
                }
                final String a=UrlConnection.requestPost(url, map, new UrlConnection.eorrCallback() {
                    @Override
                    public void eorrback(String str) {
                        if (loading!=null)
                            loading.dismiss();
                    }
                });

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (a.length() > 1) {
                            callback.JsonCallBack(a);
                        } else
                            Log.i("lifetime", a);
                    }
                });
                Log.i("lifeweeker", a);
            }
        }.start();
    }



    public static AllHost getAllHost(String json){
        AllHost user;
        try {
            JSONObject js=new JSONObject(json);
            user= JSON.parseObject(js.toString(), AllHost.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static IndividualHost getIndividualHost(String json){
        IndividualHost user;
        try {
            JSONObject js=new JSONObject(json);
            user= JSON.parseObject(js.getJSONObject("Data").toString(), IndividualHost.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static LoginUser getLoginMes(String json){
        LoginUser user;
        try {
            JSONObject js=new JSONObject(json);
            user= JSON.parseObject(js.getJSONObject("Data").toString(), LoginUser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TravelUser getTravelUser(String json){
        TravelUser user;
        List<TravelItem> list=new ArrayList<TravelItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            user= JSON.parseObject(Data.toString(), TravelUser.class);
            list=JSON.parseArray(FundDetailList.toString(),TravelItem.class);
            user.setList(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Communitionuser getCommunitionUser(String json){
        Communitionuser user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user= JSON.parseObject(Data.toString(), Communitionuser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Chonguser getChongUser(String json){
        Chonguser user;
        List<ChongItem> list=new ArrayList<ChongItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            user= JSON.parseObject(Data.toString(), Chonguser.class);
            list=JSON.parseArray(FundDetailList.toString(),ChongItem.class);
            user.setList(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Duiuser getDuiUser(String json){
        Duiuser user;
        List<DuiItem> list=new ArrayList<DuiItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            user= JSON.parseObject(Data.toString(), Duiuser.class);
            list=JSON.parseArray(FundDetailList.toString(),DuiItem.class);
            user.setDlist(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Communicationuser getCommunicationuserUser(String json){
        Communicationuser user;
        List<CommunicationItem> list=new ArrayList<CommunicationItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            user= JSON.parseObject(Data.toString(), Communicationuser.class);
            list=JSON.parseArray(FundDetailList.toString(),CommunicationItem.class);
            user.setList(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static TixianHIsUser getTixianHIsUser(String json){
        TixianHIsUser user;
        List<TixianHisItem> list=new ArrayList<TixianHisItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            user= JSON.parseObject(Data.toString(), TixianHIsUser.class);
            list=JSON.parseArray(FundDetailList.toString(),TixianHisItem.class);
            user.setList(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Txdetails getTxdetailsUser(String json){
        Txdetails user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user= JSON.parseObject(Data.toString(), Txdetails.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ReferUser getReferUserUser(String json){
        ReferUser user;
        List<ReferItem> list=new ArrayList<ReferItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            user= JSON.parseObject(Data.toString(), ReferUser.class);
            list=JSON.parseArray(FundDetailList.toString(),ReferItem.class);
            user.setList(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InoutHisUser getInoutHisUser(String json){
        InoutHisUser user=new InoutHisUser();
        List<InoutHisItem> list=new ArrayList<InoutHisItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject data=js.getJSONObject("Data");
            JSONArray FundDetailList=data.getJSONArray("List");
//            user= JSON.parseObject(data.toString(), InoutHisUser.class);
//            list=JSON.parseArray(FundDetailList.toString(),InoutHisItem.class);
            user.setPageCount(data.getString("PageCount"));
            for(int i=0;i<FundDetailList.length();i++){
                JSONObject jss=FundDetailList.getJSONObject(i);
                String OperateTime=jss.getString("OperateTime");
                String FinanceOperaTypeName = jss.getString("FinanceOperaType");
                String Amount=jss.getString("Amount");
                String Description=jss.getString("Description");
                list.add(new InoutHisItem(OperateTime, FinanceOperaTypeName, Amount, Description));
            }
            user.setList(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<CouponNuseItem> getNuserList(String json){
        List<CouponNuseItem> list=new ArrayList<CouponNuseItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            list=JSON.parseArray(FundDetailList.toString(),CouponNuseItem.class);
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CouponHuser getCouponHuser(String json){
        CouponHuser user;
        List<CouponHitem> list=new ArrayList<CouponHitem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            user= JSON.parseObject(Data.toString(), CouponHuser.class);
            list=JSON.parseArray(FundDetailList.toString(),CouponHitem.class);
            user.setList(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CouponPastuser getCouponPastuser(String json){
        CouponPastuser user;
        List<CouponPastItem> list=new ArrayList<CouponPastItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            user= JSON.parseObject(Data.toString(), CouponPastuser.class);
            list=JSON.parseArray(FundDetailList.toString(),CouponPastItem.class);
            user.setList(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static GoodsorderUser getGoodsorderUser(String json){
//        GoodsorderUser user;
//        GoodsorderItem goodsorderItem;
//        OrderDetailItem orderDetailItem;
//
//        List<GoodsorderItem> list=new ArrayList<GoodsorderItem>();
//        try {
//            JSONObject js=new JSONObject(json);
//            JSONObject Data=js.getJSONObject("Data");
//            JSONArray FundDetailList=Data.getJSONArray("List");
//            list=JSON.parseArray(FundDetailList.toString(), GoodsorderItem.class);
//
//            for(int i=0;i<FundDetailList.length();i++){
//                JSONObject Data1=FundDetailList.getJSONObject(i);
//                JSONArray item1=Data1.getJSONArray("OrderDetailViewList");
//                List<OrderDetailItem> detaillist=new ArrayList<OrderDetailItem>();
//                detaillist=JSON.parseArray(item1.toString(), OrderDetailItem.class);
//                list.get(i).setList(detaillist);
//                for(int j=0;j<item1.length();j++){
//                    JSONObject data2=item1.getJSONObject(j);
//                    JSONArray item2=data2.getJSONArray("OrderTatilAttributeList");
//                    List<OrderTatilAttrItem> Tatillist=new ArrayList<OrderTatilAttrItem>();
//                    Tatillist=JSON.parseArray(item2.toString(), OrderTatilAttrItem.class);
//                    list.get(i).getList().get(j).setList(Tatillist);
//                }
//            }
//            user= JSON.parseObject(Data.toString(), GoodsorderUser.class);
//            user.setList(list);
//            return user;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static GoodsorderUser getGoodsorderUser(String json){
        GoodsorderUser user;

        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user= JSON.parseObject(Data.toString(), GoodsorderUser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SettingUser getSetingUser(String json){
        SettingUser user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user= JSON.parseObject(Data.toString(), SettingUser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<AddressItem> getAddressItemList(String json){
        List<AddressItem> list=new ArrayList<AddressItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONArray FundDetailList=js.getJSONArray("Data");
            list=JSON.parseArray(FundDetailList.toString(), AddressItem.class);
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CollectUser getCollectUser(String json){
        CollectUser user;
        List<CollectItem> list=new ArrayList<CollectItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            user= JSON.parseObject(Data.toString(), CollectUser.class);
            list=JSON.parseArray(FundDetailList.toString(),CollectItem.class);
            user.setList(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static GoodsDetailUser getGoodsDetailUser(String json){
        GoodsDetailUser user;
        GoodsorderItem goodsorderItem;
        OrderDetailItem orderDetailItem;
        List<LogisticsItem>order=new ArrayList<LogisticsItem>();

        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user= JSON.parseObject(Data.toString(), GoodsDetailUser.class);
            JSONArray item12=Data.optJSONArray("orderRecordSummaryList");
            if(item12!=null)
             order= JSON.parseArray(item12.toString(),LogisticsItem.class);

                 JSONArray item1=Data.getJSONArray("OrderDetailViewList");
                List<OrderDetailItem> detaillist=new ArrayList<OrderDetailItem>();
                detaillist=JSON.parseArray(item1.toString(), OrderDetailItem.class);

                for(int j=0;j<item1.length();j++){
                    JSONObject data2=item1.getJSONObject(j);
                    JSONArray item2=data2.getJSONArray("OrderTatilAttributeList");
                    List<OrderTatilAttrItem> Tatillist=new ArrayList<OrderTatilAttrItem>();
                    Tatillist=JSON.parseArray(item2.toString(), OrderTatilAttrItem.class);
                    detaillist.get(j).setOrderTatilAttributeList(Tatillist);
                }
            user.setList(detaillist);
            user.setOrderRecordSummaryList(order);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<HostTitle> getHostTitleList(String json){
        List<HostTitle> list=new ArrayList<HostTitle>();
        try {
            JSONObject js=new JSONObject(json);
            JSONArray FundDetailList=js.getJSONArray("Data");
            list=JSON.parseArray(FundDetailList.toString(),HostTitle.class);
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HostFragUser getHostFragUser(String json){
        HostFragUser user;
        List<TopImgItem>top=new ArrayList<TopImgItem>();
        List<QqtmUser>qqtm=new ArrayList<QqtmUser>();
        List<JxdxUser>jxdx=new ArrayList<JxdxUser>();
        List<TopImgItem>hot=new ArrayList<TopImgItem>();

        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray AdvertisementViewList= Data.optJSONArray("AdvertisementViewList");
            if(AdvertisementViewList!=null)
            top=JSON.parseArray(AdvertisementViewList.toString(), TopImgItem.class);
            JSONArray periodViewList= Data.optJSONArray("PeriodViewList");
            if(periodViewList!=null)
             qqtm=JSON.parseArray(Data.getJSONArray("PeriodViewList").toString(), QqtmUser.class);
            JSONArray HotActivityList= Data.optJSONArray("HotActivityList");
            if(HotActivityList!=null)
                 hot=JSON.parseArray(HotActivityList.toString(), TopImgItem.class);

            JSONArray item12=Data.optJSONArray("BrandViewList");
            if(item12!=null) {
                jxdx = JSON.parseArray(item12.toString(), JxdxUser.class);
                for (int j = 0; j < item12.length(); j++) {
                    JSONObject data2 = item12.getJSONObject(j);
                    JSONArray item2 = data2.getJSONArray("BrandProducts");
                    List<JxduItem> Tatillist = new ArrayList<JxduItem>();
                    Tatillist = JSON.parseArray(item2.toString(), JxduItem.class);
                    jxdx.get(j).setBrandProducts(Tatillist);
                }
            }
            user=new HostFragUser(top,qqtm,hot,jxdx);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static OtherUser getOtherUser(String json){
        OtherUser user;
        List<AdverImg> AdvertisementViewList=new ArrayList<AdverImg>();
        List<OtherGoodUser> list=new ArrayList<OtherGoodUser>();
        List<TopImgItem>toplist=new ArrayList<>();
        List<TopImgItem>bottom=new ArrayList<>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            JSONArray SecondProductTypeList=Data.getJSONArray("SecondProductTypeList");
            JSONArray HotActivityList=Data.optJSONArray("HotActivityList");
            JSONArray DetailList=Data.optJSONArray("AdvertisementViewList");
            user= JSON.parseObject(Data.toString(), OtherUser.class);
            list=JSON.parseArray(FundDetailList.toString(), OtherGoodUser.class);
            AdvertisementViewList=JSON.parseArray(SecondProductTypeList.toString(), AdverImg.class);
            if(DetailList!=null)
                toplist=JSON.parseArray(DetailList.toString(), TopImgItem.class);
            if(HotActivityList!=null){
                bottom=JSON.parseArray(HotActivityList.toString(), TopImgItem.class);
            }

            user.setHotActivityList(bottom);
            user.setSecondProductTypeList(AdvertisementViewList);
            user.setAdvertisementViewList(toplist);

            user.setList(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TextUser getTextUser(String json){
        TextUser user;
         List<DetailItem>Detail=new ArrayList<>();
         List<EvaluateItem>EvaluateList=new ArrayList<>();
         List<DetailItem>standardImagesList=new ArrayList<>();
         List<DetailTatilAttributeItem> DetailTatilAttributeList=new ArrayList<>();
         List<PropertyValueItem>PropertyValueList=new ArrayList<>();

        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), TextUser.class);

            Detail=JSON.parseArray(Data.getJSONArray("Detail").toString(), DetailItem.class);
            EvaluateList=JSON.parseArray(Data.getJSONArray("EvaluateList").toString(), EvaluateItem.class);
            standardImagesList=JSON.parseArray(Data.getJSONArray("StandardImagesList").toString(), DetailItem.class);
            DetailTatilAttributeList=JSON.parseArray(Data.getJSONArray("DetailTatilAttributeList").toString(), DetailTatilAttributeItem.class);
            PropertyValueList=JSON.parseArray(Data.getJSONArray("PropertyValueList").toString(), PropertyValueItem.class);

            JSONArray item12=Data.getJSONArray("DetailTatilAttributeList");
            JSONArray item1=Data.optJSONArray("EvaluateList");
//            for(int j=0;j<item1.length();j++){
//                JSONObject data2=item1.getJSONObject(j);
//                JSONArray item2=data2.optJSONArray("ImageList");
//                if(item2!=null)
//                {
//                    List<DetailItem> Tatillist=new ArrayList<DetailItem>();
//                    Tatillist=JSON.parseArray(item2.toString(), DetailItem.class);
//                    EvaluateList.get(j).setImageList(Tatillist);
//                }
//
//            }
            for(int j=0;j<item12.length();j++){
                JSONObject data2=item12.getJSONObject(j);
                JSONArray item2=data2.getJSONArray("Value");
                List<ValueItem> Tatillist=new ArrayList<ValueItem>();
                Tatillist=JSON.parseArray(item2.toString(), ValueItem.class);
                DetailTatilAttributeList.get(j).setValue(Tatillist);
            }
            user.setDetail(Detail);
            user.setEvaluateList(EvaluateList);
            user.setStandardImagesList(standardImagesList);
            user.setDetailTatilAttributeList(DetailTatilAttributeList);
            user.setPropertyValueList(PropertyValueList);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static EvaluateUser getEvaluateUser(String json){
        EvaluateUser user;
        List<EvaluateItem> list=new ArrayList<EvaluateItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            user= JSON.parseObject(Data.toString(), EvaluateUser.class);
            list=JSON.parseArray(FundDetailList.toString(),EvaluateItem.class);
            for(int j=0;j<FundDetailList.length();j++) {
                JSONObject data2=FundDetailList.getJSONObject(j);
                JSONArray item2=data2.optJSONArray("ImageList");
                if(item2!=null)
                {
                    List<DetailItem> Tatillist=new ArrayList<DetailItem>();
                    Tatillist=JSON.parseArray(item2.toString(), DetailItem.class);
                    list.get(j).setImageList(Tatillist);
                }

            }
            user.setEvaluateList(list);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PinPaiUser getPinPaiUser(String json){
        PinPaiUser user;
        List<OtherGoodUser> list=new ArrayList<OtherGoodUser>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user= JSON.parseObject(Data.toString(), PinPaiUser.class);
            String count=Data.getString("PageCount");
            user.setPageCount(count);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  List<SelectTitleItem> getSelectItem(String json){
        List<SelectTitleItem> list=new ArrayList<SelectTitleItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONArray Data=js.getJSONArray("Data");
            list=JSON.parseArray(Data.toString(),SelectTitleItem.class);
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static SelectClassUser getSelectClassUser(String json){
        SelectClassUser user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), SelectClassUser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Shopcarlist getShopcarlist(String json){
        Shopcarlist user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), Shopcarlist.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FendanUser getFendanUser(String json){
        FendanUser user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), FendanUser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static  List<GoodsorderItem> getSelectGoodsorderItem(String json){
        List<GoodsorderItem> list=new ArrayList<GoodsorderItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONArray FundDetailList=js.getJSONArray("Data");
            list=JSON.parseArray(FundDetailList.toString(), GoodsorderItem.class);


            for(int i=0;i<FundDetailList.length();i++){
                JSONObject Data1=FundDetailList.getJSONObject(i);
                JSONArray item1=Data1.getJSONArray("OrderDetailViewList");
                List<OrderDetailItem> detaillist=new ArrayList<OrderDetailItem>();
                detaillist=JSON.parseArray(item1.toString(), OrderDetailItem.class);
                list.get(i).setOrderDetailViewList(detaillist);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static BanderDetailsUser getBanderDetailsUser(String json){
        BanderDetailsUser user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), BanderDetailsUser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LogistDetails getLogistDetails(String json){
        LogistDetails user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), LogistDetails.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Realname getRealname(String json){
        Realname user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), Realname.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static StardIdUser getStardIdUser(String json){
        StardIdUser user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), StardIdUser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static GoodsOrderUser getGoodsorderUsers(String json){
        GoodsOrderUser user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), GoodsOrderUser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static H5user getH5users(String json){
        H5user user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), H5user.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static UpdataUser getUpdataUser(String json){
        UpdataUser user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), UpdataUser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WaitincomeUser getWaitincomeUser(String json){
        WaitincomeUser user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), WaitincomeUser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AdvertismentUser getAdvertismentUser(String json){
        AdvertismentUser user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), AdvertismentUser.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Zhongjiang getZhongjiang(String json){
        Zhongjiang user;
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user=JSON.parseObject(Data.toString(), Zhongjiang.class);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
