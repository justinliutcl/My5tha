package com.example.transtion.my5th.DIndividualActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.transtion.my5th.DIndividualActivity.MyWallet.Commision.DMCReferActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.wxapi.Constants;
import com.example.transtion.my5th.wxapi.Util;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import fifthutil.FifUtil;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class TuijianActivity extends BaseActivity {
LinearLayout tuijian,tpeople,hongbao,erweima;
     String username,usernumber;
    private static final int CONTACT_REQUEST_CODE = 2;
    private IWXAPI api;
    String path= Path.HOST+Path.ip+Path.TUIJIAN_PATH;
    String flage;
    String code;
    String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijian);
        initView();
    }

    private void initView() {
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.pic_hb2);

        flage=getIntent().getStringExtra("flage");
        code=getIntent().getStringExtra("code");
        tuijian= (LinearLayout) findViewById(R.id.tuijian_layout_tuijian);
        tpeople= (LinearLayout) findViewById(R.id.tuijian_layout_tpeople);
        hongbao= (LinearLayout) findViewById(R.id.tuijian_hongbao);
        erweima= (LinearLayout) findViewById(R.id.tuijian_layout_erweima);
        if(flage.equals("1")){
            tuijian.setVisibility(View.VISIBLE);
        }else{
            tuijian.setVisibility(View.GONE);
        }
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
         address= FifUtil.saveHBBitmap(bitmap,this);
    }
    private void share2weixin(int flag) {
        // Bitmap bmp = BitmapFactory.decodeResource(getResources(),
        // R.drawable.weixin_share);

        if (!api.isWXAppInstalled()) {
            Toast.makeText(this, "您还未安装微信客户端",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://baidu.com";
        WXMediaMessage msg = new WXMediaMessage(webpage);

        msg.title = "title";
        msg.description = "白菜";
        Bitmap thumb = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);
        msg.thumbData= Util.bmpToByteArray(thumb,true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }
    @Override
    public void setListener() {
        tuijian.setOnClickListener(this);
        tpeople.setOnClickListener(this);
        hongbao.setOnClickListener(this);
        erweima.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tuijian_layout_tuijian:
                Intent intent = new Intent();
                intent.setClass(this, ContactListView.class);
                Bundle bundle = new Bundle();
                bundle.putString("wNumberStr", "");
                intent.putExtras(bundle);
                startActivityForResult(intent, CONTACT_REQUEST_CODE);
                break;
            case R.id.tuijian_layout_tpeople:
                JumpUtil.jump(this, DMCReferActivity.class, true);
                break;
            case R.id.tuijian_hongbao:
//                share2weixin(1);
                showShare();
                break;
            case R.id.tuijian_layout_erweima:
               JumpUtil.jump(this,PopulazeActivity.class,true);
                break;
        }
    }
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("万券齐发，厚惠如期~");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("进口特卖，正品低价，海外直采，第五大街为您奉上超值优惠券，小主请笑纳!");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(address);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://m.5tha.com/Generalize/ShareCoupons?rid="+code);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        oks.setTitleUrl("http://m.5tha.com/Generalize/ShareCoupons?rid="+code);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CONTACT_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    String numberStr = null;
                    String nameStr = null;
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        numberStr = bundle.getString("numberStr");
                        nameStr = bundle.getString("nameStr");
                    }
                    commit(nameStr,numberStr);
                }
                break;
        }
    }

    private void commit(String name,String num) {
        loding.showShapeLoding();
        HttpConnectionUtil.getJsonJsonwithDialog(this, path, new String[]{"RegisterArray","MobileArray","LoginType","RegistType","MemberId"}, new String[]{name,num,"1","4",share.getMemberID()}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                show("推荐成功");

            }
        });
    }


}
