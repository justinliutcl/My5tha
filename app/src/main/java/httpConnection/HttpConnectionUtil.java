package httpConnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
import InternetUser.IndividualHost;
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
import InternetUser.ReferUser;
import InternetUser.SettingUser;
import InternetUser.TixianHIsUser;
import InternetUser.TravelItem;
import InternetUser.TravelUser;
import InternetUser.Txdetails;
import InternetUser.order.GoodsDetailUser;
import InternetUser.order.GoodsorderItem;
import InternetUser.order.OrderDetailItem;
import InternetUser.order.OrderTatilAttrItem;
import customUI.Loding.ProgressWheel;
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
    public interface OnimgCall{
        public void imgCallBack(Bitmap bitmap);
    }
    public static void getGetJson(final Context context,String url,final LodingUtil loading, final OnJsonCall callback){
        if(rq==null)
            rq= Volley.newRequestQueue(context);
        StringRequest json=new StringRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
               AllHost host= getAllHost(arg0);
                if(host.isSuccess()){
                    callback.JsonCallBack(arg0);
                }
                else{
                    if (loading!=null)
                        loading.dismiss();
                    Log.i("lifetime",arg0);
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

        rq.add(json);
    }

    public static void getGetJsonWithProgress(final Context context,String url,final ProgressWheel progress, final OnJsonCall callback){
        if(rq==null)
            rq= Volley.newRequestQueue(context);
        StringRequest json=new StringRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                AllHost host= getAllHost(arg0);
                if(host.isSuccess()){
                    callback.JsonCallBack(arg0);
                }
                else{
                    progress.setVisibility(View.GONE);
                    Log.i("lifetime",arg0);
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
                            if (host.isSuccess())
                                callback.JsonCallBack(a);
                            else {
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
            user= JSON.parseObject(js.toString(), LoginUser.class);
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

    public static GoodsorderUser getGoodsorderUser(String json){
        GoodsorderUser user;
        GoodsorderItem goodsorderItem;
        OrderDetailItem orderDetailItem;

        List<GoodsorderItem> list=new ArrayList<GoodsorderItem>();
        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            JSONArray FundDetailList=Data.getJSONArray("List");
            list=JSON.parseArray(FundDetailList.toString(), GoodsorderItem.class);


            for(int i=0;i<FundDetailList.length();i++){
                JSONObject Data1=FundDetailList.getJSONObject(i);
                JSONArray item1=Data1.getJSONArray("OrderDetailViewList");
                List<OrderDetailItem> detaillist=new ArrayList<OrderDetailItem>();
                detaillist=JSON.parseArray(item1.toString(), OrderDetailItem.class);
                list.get(i).setList(detaillist);
                for(int j=0;j<item1.length();j++){
                    JSONObject data2=item1.getJSONObject(j);
                    JSONArray item2=data2.getJSONArray("OrderTatilAttributeList");
                    List<OrderTatilAttrItem> Tatillist=new ArrayList<OrderTatilAttrItem>();
                    Tatillist=JSON.parseArray(item2.toString(), OrderTatilAttrItem.class);
                    list.get(i).getList().get(j).setList(Tatillist);
                }
            }
            user= JSON.parseObject(Data.toString(), GoodsorderUser.class);
            user.setList(list);
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

        try {
            JSONObject js=new JSONObject(json);
            JSONObject Data=js.getJSONObject("Data");
            user= JSON.parseObject(Data.toString(), GoodsDetailUser.class);
                 JSONArray item1=Data.getJSONArray("OrderDetailViewList");
                List<OrderDetailItem> detaillist=new ArrayList<OrderDetailItem>();
                detaillist=JSON.parseArray(item1.toString(), OrderDetailItem.class);

                for(int j=0;j<item1.length();j++){
                    JSONObject data2=item1.getJSONObject(j);
                    JSONArray item2=data2.getJSONArray("OrderTatilAttributeList");
                    List<OrderTatilAttrItem> Tatillist=new ArrayList<OrderTatilAttrItem>();
                    Tatillist=JSON.parseArray(item2.toString(), OrderTatilAttrItem.class);
                    detaillist.get(j).setList(Tatillist);
                }

            user.setList(detaillist);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
