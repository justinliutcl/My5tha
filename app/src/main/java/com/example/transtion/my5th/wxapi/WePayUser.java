package com.example.transtion.my5th.wxapi;

import android.content.Context;
import android.util.Log;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by 不爱白菜 on 2016/5/10.
 */
public class WePayUser {
    public static void wePay(Context context, final LodingUtil loding,String ordernumber,double money){
        IWXAPI api;
        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID, false);
        api.registerApp(Constants.APP_ID);
        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID);
        loding.showShapeLoding();
        final IWXAPI finalApi ;
        finalApi=WXAPIFactory.createWXAPI(context, null);
        finalApi.registerApp(Constants.APP_ID);

        HttpConnectionUtil.getJsonJsonwithDialog4we(context, Path.WEPAY_PATH, new String[]{"OrderNumber", "TradeStatus", "TradeNo", "TotalPay", "PayAmountTemp","MemberId"}, new String[]{ordernumber, "", "", "0", money+"", ShareUtil.getInstanse(context).getMemberID()}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                try {
                    JSONObject json = new JSONObject(str);
                    if (null != json) {
                        JSONObject Data = json.getJSONObject("Data");
                        PayReq req = new PayReq();
                        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                        req.appId = Data.getString("AppId");
                        req.partnerId = Data.getString("PartnerId");
                        req.prepayId = Data.getString("PrepayId");
                        req.nonceStr = Data.getString("NonceStr");
                        req.timeStamp = Data.getString("TimeStamp");
                        req.packageValue = Data.getString("PackageValue");
                        req.sign =  Data.getString("Sign");
//                        req.extData = "app data"; // optional
                        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                        finalApi.sendReq(req);
                    }

                } catch (Exception e) {

                }
            }
        });
    }

    public static void wePayonline(Context context, final LodingUtil loding,double money){
        IWXAPI api;
        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID, false);
        api.registerApp(Constants.APP_ID);
        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID);
        loding.showShapeLoding();
        final IWXAPI finalApi ;
        finalApi=WXAPIFactory.createWXAPI(context, null);
        finalApi.registerApp(Constants.APP_ID);

        HttpConnectionUtil.getJsonJsonwithDialog4we(context, Path.WEPAY_PATH, new String[]{ "PayAmountTemp","MemberId"}, new String[]{ money+"", ShareUtil.getInstanse(context).getMemberID()}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                try {
                    JSONObject json = new JSONObject(str);
                    if (null != json) {
                        JSONObject Data = json.getJSONObject("Data");
                        PayReq req = new PayReq();
                        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                        req.appId = Data.getString("AppId");
                        req.partnerId = Data.getString("PartnerId");
                        req.prepayId = Data.getString("PrepayId");
                        req.nonceStr = Data.getString("NonceStr");
                        req.timeStamp = Data.getString("TimeStamp");
                        req.packageValue = Data.getString("PackageValue");
                        req.sign =  Data.getString("Sign");
//                        req.extData = "app data"; // optional
                        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                        finalApi.sendReq(req);
                    }

                } catch (Exception e) {

                }
            }
        });
    }


    public static void print(String str){
        Log.i("fifth", str);
    }
}
