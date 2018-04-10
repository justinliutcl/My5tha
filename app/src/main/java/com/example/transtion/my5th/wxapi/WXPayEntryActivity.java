package com.example.transtion.my5th.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.AHomeActivity.TextActivity;
import com.example.transtion.my5th.BShopcar.FendanActivity;
import com.example.transtion.my5th.BShopcar.ShopcarActivity;
import com.example.transtion.my5th.BShopcar.ShouyinActivity;
import com.example.transtion.my5th.DIndividualActivity.MyWallet.ChonglineActivity;
import com.example.transtion.my5th.DIndividualActivity.Order.DSAllActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.MainActivity;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import InternetUser.PayUser.WePayState;
import InternetUser.shopcar.DingdanUser;
import InternetUser.shopcar.FendanUser;
import customUI.TopbarView;
import fifthutil.JumpUtil;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	TextView type,mes;
	Button commit,godingdan,go2order,go2home;
	ImageView img;
	FendanUser user;
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	TopbarView topPager;
    private IWXAPI api;
	private static final int JUMPTIME = 1000;
	int paycode;
	LinearLayout success,showorder;
	boolean isfendan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
		initview();
    }
	private void initview(){
		type= (TextView) findViewById(R.id.tv_type);
		mes= (TextView) findViewById(R.id.tv_mes);
		commit= (Button) findViewById(R.id.commit);
		godingdan = (Button) findViewById(R.id.go2dingdan);
		go2order= (Button) findViewById(R.id.wepay_btn_showorder);
		go2home= (Button) findViewById(R.id.wepay_btn_go2home);
		img= (ImageView) findViewById(R.id.iv_img);
		topPager= (TopbarView) findViewById(R.id.topbar);
		success= (LinearLayout) findViewById(R.id.wepay_layout_success);
		showorder= (LinearLayout) findViewById(R.id.wepay_layout_showorder);
		user= DingdanUser.getInstance().getFendanUser();
		if(user==null)
			user=new FendanUser();
		isFendan();
		topPager.getLeftView().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		commit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isfendan){
					JumpUtil.jump2finish(WXPayEntryActivity.this, FendanActivity.class, false);
				}else{
					if (TextActivity.instance != null) {
						TextActivity.instance.finish();
					}
					if (ShopcarActivity.instance != null) {
						ShopcarActivity.instance.finish();
					}
					MainActivity.instance.finish();
					JumpUtil.jump2finish(WXPayEntryActivity.this, MainActivity.class, false);
				}
				onBackPressed();
			}
		});
		godingdan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextActivity.instance != null) {
					TextActivity.instance.finish();
				}
				if (ShopcarActivity.instance != null) {
					ShopcarActivity.instance.finish();
				}
				if(DSAllActivity.instance!=null)
					DSAllActivity.instance.finish();
				if(ShouyinActivity.instance!=null)
					ShouyinActivity.instance.finish();
				JumpUtil.jumpWithValue2finash(WXPayEntryActivity.this, DSAllActivity.class, true, new String[]{"count"}, new String[]{"2"});
			}
		});
		go2order.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextActivity.instance != null) {
					TextActivity.instance.finish();
				}
				if (ShopcarActivity.instance != null) {
					ShopcarActivity.instance.finish();
				}
				if(DSAllActivity.instance!=null)
					DSAllActivity.instance.finish();
				if(ShouyinActivity.instance!=null)
					ShouyinActivity.instance.finish();
				JumpUtil.jumpWithValue2finash(WXPayEntryActivity.this, DSAllActivity.class, true, new String[]{"count"}, new String[]{"1"});
			}
		});
		go2home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				if(ShouyinActivity.instance!=null)
//					ShouyinActivity.instance.finish();
//				JumpUtil.jump2finash(WXPayEntryActivity.this);
				if(isfendan){
					JumpUtil.jump2finish(WXPayEntryActivity.this, FendanActivity.class, false);
				}else
				{
//				ShouyinActivity.instance.finish();
//				JumpUtil.jump2finash(WXPayEntryActivity.this);
					if (TextActivity.instance != null) {
						TextActivity.instance.finish();
					}
					if (ShopcarActivity.instance != null) {
						ShopcarActivity.instance.finish();
					}
					if(DSAllActivity.instance!=null)
						DSAllActivity.instance.finish();
					if(ShouyinActivity.instance!=null)
						ShouyinActivity.instance.finish();
					MainActivity.instance.finish();
					JumpUtil.jump2finish(WXPayEntryActivity.this, MainActivity.class, false);
				}


			}
		});

		if(isfendan)
			commit.setText("继续支付");
		else
			commit.setText("去首页");
	}
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		initview();
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			WePayState wePayState=WePayState.getInstance();
			paycode=resp.errCode;
				switch (resp.errCode){
					case 0:
						topPager.setTitle("支付成功");
						type.setText("支付成功");
						mes.setText("支付完成，请在收到商品之后再确认收货哦！");
						if(wePayState.getState()==0){
							if(ChonglineActivity.instace!=null)
								ChonglineActivity.instace.finish();
						} else if(wePayState.getState()==1){
							ShouyinActivity.instance.finish();
							success.setVisibility(View.VISIBLE);
						if (wePayState.getInitType().equals("1"))
							user.getList().get(wePayState.getPosition()).getList().get(0).setFlage(true);
					}else{

						}
						success.setVisibility(View.VISIBLE);
						break;
					case -2:
						img.setBackgroundResource(R.drawable.fail2);
						topPager.setTitle("支付失败");
						type.setText("支付失败");
						mes.setText("该订单为您保存30分钟，30分钟后如果仍未付款，系统将自动取消该订单");
						showorder.setVisibility(View.VISIBLE);
								if(isfendan){
									go2home.setText("继续支付");
								}else{
									go2home.setText("去首页");
								}
						break;
					case -1:
 						img.setBackgroundResource(R.drawable.fail2);
						topPager.setTitle("支付失败");
						type.setText("支付失败");
						mes.setText("该订单为您保存30分钟，30分钟后如果仍未付款，系统将自动取消该订单");
						showorder.setVisibility(View.VISIBLE);
						if(isfendan){
							go2home.setText("继续支付");
						}else{
							go2home.setText("去首页");
						}
						break;
				}
		}
	}

	public void Jump(){
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				JumpUtil.jump2finash(WXPayEntryActivity.this);
			}
		}, JUMPTIME);
	}

	public void isFendan(){

			WePayState wePayState = WePayState.getInstance();
			if (wePayState.getInitType().equals("1")) {
				int aount=0;
				for (int i = 0; i < user.getList().size(); i++) {
					if (!user.getList().get(i).getList().get(0).isFlage()) {
//						JumpUtil.jump2finish(WXPayEntryActivity.this, FendanActivity.class, false);
						aount++;
						break;
					}
				}
				if(aount==0){
					isfendan=false;
				}else{
					isfendan=true;
				}
			}else{
				isfendan=false;
			}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			if(paycode==0) {
				if(isfendan){
					JumpUtil.jump2finish(WXPayEntryActivity.this, FendanActivity.class, false);
				}else{
					JumpUtil.jump2finash(WXPayEntryActivity.this);
				}
			}else
			if(isfendan){
				JumpUtil.jump2finish(WXPayEntryActivity.this, FendanActivity.class, false);
			}else
			{
//				ShouyinActivity.instance.finish();
//				JumpUtil.jump2finash(WXPayEntryActivity.this);
				if (TextActivity.instance != null) {
					TextActivity.instance.finish();
				}
				if (ShopcarActivity.instance != null) {
					ShopcarActivity.instance.finish();
				}
				if(DSAllActivity.instance!=null)
					DSAllActivity.instance.finish();
				if(ShouyinActivity.instance!=null)
					ShouyinActivity.instance.finish();


				MainActivity.instance.finish();
				JumpUtil.jump2finish(WXPayEntryActivity.this, MainActivity.class, false);
			}
			return true;
		}
		else
			return super.onKeyDown(keyCode, event);

	}
}