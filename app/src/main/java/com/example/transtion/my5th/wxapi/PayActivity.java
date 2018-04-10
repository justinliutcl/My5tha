package com.example.transtion.my5th.wxapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class PayActivity extends BaseActivity {
	
	private IWXAPI api;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay);

		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID,false);
		api.registerApp(Constants.APP_ID);
		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
		Button appayBtn = (Button) findViewById(R.id.appay_btn);
		appayBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				String url = "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";
				Button payBtn = (Button) findViewById(R.id.appay_btn);
				payBtn.setEnabled(false);
				Toast.makeText(PayActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
				loding.showShapeLoding();
				HttpConnectionUtil.getJsonJsonwithDialog4we(PayActivity.this, Path.WEPAY_PATH, new String[]{"OrderNumber","TradeStatus","TradeNo","TotalPay","PayAmountTemp","MemberId"}, new String[]{"OR201605090000005359","","","0","0.01",share.getMemberID()}, loding, new HttpConnectionUtil.OnJsonCall() {
					@Override
					public void JsonCallBack(String str) {
						try{
						JSONObject json = new JSONObject(str);
							if(null != json ){
								JSONObject Data=json.getJSONObject("Data");
							PayReq req = new PayReq();
							//req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
							req.appId			= Data.getString("AppId");
							req.partnerId		= Data.getString("PartnerId");
							req.prepayId		= Data.getString("PrepayId");
							req.nonceStr		= Data.getString("NonceStr");
							req.timeStamp		= Data.getString("TimeStamp");
							req.packageValue	= Data.getString("PackageValue");

							req.sign			= Data.getString("Sign");
							req.extData			= "app data"; // optional

							Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
							// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
							api.sendReq(req);
						}

						}catch(Exception e){

				}
					}
				});
//				try{
//					byte[] buf = Util.httpGet(url);
//					if (buf != null && buf.length > 0) {
//						String content = new String(buf);
//						Log.e("get server pay params:", content);
//						JSONObject json = new JSONObject(content);
//						if(null != json && !json.has("retcode") ){
//							PayReq req = new PayReq();
//							//req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
//							req.appId			= json.getString("appid");
//							req.partnerId		= json.getString("partnerid");
//							req.prepayId		= json.getString("prepayid");
//							req.nonceStr		= json.getString("noncestr");
//							req.timeStamp		= json.getString("timestamp");
//							req.packageValue	= json.getString("package");
//							req.sign			= json.getString("sign");
//							req.extData			= "app data"; // optional
//							Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
//							// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//							api.sendReq(req);
//						}else{
//							Log.d("PAY_GET", "返回错误"+json.getString("retmsg"));
//							Toast.makeText(PayActivity.this, "返回错误"+json.getString("retmsg"), Toast.LENGTH_SHORT).show();
//						}
//					}else{
//						Log.d("PAY_GET", "服务器请求错误");
//						Toast.makeText(PayActivity.this, "服务器请求错误", Toast.LENGTH_SHORT).show();
//					}
//				}catch(Exception e){
//					Log.e("PAY_GET", "异常："+e.getMessage());
//					Toast.makeText(PayActivity.this, "异常："+e.getMessage(), Toast.LENGTH_SHORT).show();
//				}
//				payBtn.setEnabled(true);
			}
		});		
	}

	public String go2sign(){
		return null;
	}

	@Override
	public void setListener() {

	}

	@Override
	public void onClick(View v) {

	}
}
