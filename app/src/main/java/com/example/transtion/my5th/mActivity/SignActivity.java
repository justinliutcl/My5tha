package com.example.transtion.my5th.mActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.Setting.ForgetcodeActivity;

import java.util.HashMap;

import InternetUser.AllHost;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import fifthutil.Code;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;
import togglebutton.ToggleButton;

public class SignActivity extends BasenoscrollActivity implements PlatformActionListener {
    TextView toregist,toregist2,forgetcode,yzmtext;
    EditText phoneNum,password,yzmedit;
    ImageView login_qq,login_weichat,login_sina,back,yzmimg;
    Button sign,yzmbtn;
    LinearLayout yzmlayout;
    ToggleButton toggleButton;
    ShareUtil share;
    private String p="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0-9]|17[0-9])\\d{8}$";
    int i;
    String signPath= Path.HOST+Path.ip+Path.LOGIN_PATH;
    String thridSignPath= Path.HOST+Path.ip+Path.THRID_LOGIN_PATH;
    String type;
    public static SignActivity Instance;
    String unionid;
    String openid;
    String opentype;
    final Handler hand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){

                Log.i("lifetime", "用户资料: " + opentype);
                commit();
            }
            if(msg.what==2){
                loding.disShapeLoding();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initview();

    }
    public void initview() {
        Instance=this;
        i=3;
        toregist= (TextView) findViewById(R.id.sign_toregist);
        toregist2= (TextView) findViewById(R.id.sign_toregist2);
        forgetcode= (TextView) findViewById(R.id.sign_forgetcode);
        yzmtext= (TextView) findViewById(R.id.sign_text_yzm);

        yzmlayout= (LinearLayout) findViewById(R.id.sign_layout_yzm);

        login_qq= (ImageView) findViewById(R.id.login_qq);
        login_weichat= (ImageView) findViewById(R.id.login_wechat);
        login_sina= (ImageView) findViewById(R.id.login_weibo);
        back= (ImageView) findViewById(R.id.back);

        phoneNum= (EditText) findViewById(R.id.sign_phone);
        password= (EditText) findViewById(R.id.sign_password);
        yzmedit= (EditText) findViewById(R.id.sign_yzmedit);
        yzmimg= (ImageView) findViewById(R.id.sign_yzmimg);
        yzmbtn= (Button) findViewById(R.id.sign_yzmbtn);

        sign= (Button) findViewById(R.id.sign);
        toggleButton= (ToggleButton) findViewById(R.id.sign_codeshow);

        share=ShareUtil.getInstanse(this);

        yzmimg.setImageBitmap(Code.getInstance().createBitmap());
        type=getIntent().getStringExtra("type");

    }

    @Override
    public void setListener() {
        login_qq.setOnClickListener(this);
        login_weichat.setOnClickListener(this);
        login_sina.setOnClickListener(this);
        sign.setOnClickListener(this);
        toregist.setOnClickListener(this);
        toregist2.setOnClickListener(this);
        forgetcode.setOnClickListener(this);
        back.setOnClickListener(this);
        yzmimg.setOnClickListener(this);
        yzmbtn.setOnClickListener(this);
        toggleButton.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_qq:
                ShareSDK.initSDK(this);
                loding.showShapeLoding();
                Platform qq= ShareSDK.getPlatform(this, QZone.NAME);
                qq.removeAccount(true);
                qq.SSOSetting(false);
                qq.setPlatformActionListener(this);
                qq.showUser(null);
                break;
            case R.id.login_wechat:
                ShareSDK.initSDK(this);
                loding.showShapeLoding();
                Platform wechat= ShareSDK.getPlatform(this, Wechat.NAME);
                wechat.SSOSetting(false);
                wechat.setPlatformActionListener(this);
                wechat.showUser(null);
                loding.disShapeLoding();
                break;
            case R.id.login_weibo:
                ShareSDK.initSDK(this);
                loding.showShapeLoding();
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.SSOSetting(false);  //设置false表示使用SSO授权方式
                weibo.setPlatformActionListener(this); // 设置分享事件回调
                weibo.showUser(null);
                break;
            case R.id.sign_toregist:
                JumpUtil.jumpWithValue2finash(this, LoginActivity.class, false, new String[]{"type"}, new String[]{type});
                break;
            case R.id.sign_toregist2:
                JumpUtil.jumpWithValue2finash(this, LoginActivity.class, false, new String[]{"type"}, new String[]{type});
                break;
            case R.id.sign:
               if( panduan())
                    sign();
                else{
                   show("请输入正确手机号和密码");
                   i--;
                   if(i<1){
                       yzmlayout.setVisibility(View.VISIBLE);
                       yzmtext.setVisibility(View.VISIBLE);
                   }
               }
                break;
            case R.id.back:
                if(type==null)
                    JumpUtil.jump2hdown(SignActivity.this, MainActivity.class, false);
                else
                    JumpUtil.jump2finash(SignActivity.this);
                break;
            case R.id.sign_forgetcode:
                JumpUtil.jump(this, ForgetcodeActivity.class, false);
                break;
            case R.id.sign_yzmbtn:
                yzmimg.setImageBitmap(Code.getInstance().createBitmap());
                break;
            default:
                break;
        }
    }

    private boolean panduan() {
        String loginName=phoneNum.getText().toString();
        String Password=password.getText().toString();
        if(loginName.matches(p) && Password.length()>5 && password.length()<17)
            return true;
            else
            return  false;
    }

    private void sign() {
        String loginName=phoneNum.getText().toString();
        String Password=password.getText().toString();
        String yzm=yzmedit.getText().toString();
        if(i>0){
            loding.showShapeLoding();
            HttpConnectionUtil.getJsonJsonwithDialog(this, signPath, new String[]{"LoginName", "Password", "IsVerified","Type"}, new String[]{loginName, Password, "true","4"}, loding, new HttpConnectionUtil.OnJsonCall() {
                @Override
                public void JsonCallBack(String str) {
                    AllHost host = HttpConnectionUtil.getAllHost(str);
                    loding.disShapeLoding();
                    if (host.isSuccess()) {
//                        show(host.getData());
                        share.setMemberID(host.getData());
                        if(type==null)
                            JumpUtil.jump2hdown(SignActivity.this, MainActivity.class, false);
                        else
                            JumpUtil.jump2finash(SignActivity.this);
                    } else {
                        show(host.getMessage());
                        i--;
                        if (i < 1) {
                            yzmlayout.setVisibility(View.VISIBLE);
                            yzmtext.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }else if(yzm.equals(Code.getInstance().getCode())){
            loding.showShapeLoding();
            HttpConnectionUtil.getJsonJsonwithDialog(this, signPath, new String[]{"LoginName", "Password", "IsVerified"}, new String[]{loginName, Password, "true"},loding, new HttpConnectionUtil.OnJsonCall() {
                @Override
                public void JsonCallBack(String str) {
                    loding.disShapeLoding();
                    AllHost host=HttpConnectionUtil.getAllHost(str);
                    if(host.isSuccess()){
//                        show(host.getData());
                        share.setMemberID(host.getData());
                        if(type==null)
                            JumpUtil.jump2hdown(SignActivity.this, MainActivity.class, false);
                        else
                            JumpUtil.jump2finash(SignActivity.this);
                    }else{
                        show(host.getMessage());
                        i--;
                        if(i<1){
                            yzmlayout.setVisibility(View.VISIBLE);
                            yzmtext.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }else{
            show("请输入正确验证码");
            if(i<1){
                yzmlayout.setVisibility(View.VISIBLE);
                yzmtext.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(type==null)
                JumpUtil.jump2hdown(SignActivity.this, MainActivity.class, false);
            else
                JumpUtil.jump2finash(SignActivity.this);
            return true;
        }else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> res) {
        //解析部分用户资料字段
        Log.i("lifetime", "回调了。。。。。。。。。");
//            Iterator iter = res.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            Object key = entry.getKey();
//            Object val = entry.getValue();
//            Log.i("lifetime", key+"。。。。。。。。。"+val);
//            }
        Log.i("lifetime",platform.getName());
        Log.i("lifetime","id-----------"+ platform.getDb().getUserId());
        Log.i("lifetime","getUserName-----------"+ platform.getDb().getUserName());
        Log.i("lifetime","getUserIcon-----------"+ platform.getDb().getUserIcon());
        Log.i("lifetime","getUserGender-----------"+ platform.getDb().getUserGender());
        Log.i("lifetime","getPlatformNname-----------"+ platform.getDb().getPlatformNname());
        Log.i("lifetime","unionid-----------"+ platform.getDb().get("unionid"));
        Log.i("lifetime","getToken-----------"+ platform.getDb().getToken());
        Log.i("lifetime","getTokenSecret-----------"+ platform.getDb().getTokenSecret());
        Log.i("lifetime","getExpiresIn-----------"+ platform.getDb().getExpiresIn());
        Log.i("lifetime","getExpiresTime-----------"+ platform.getDb().getExpiresTime());
        Log.i("lifetime","getPlatformVersion-----------"+ platform.getDb().getPlatformVersion());
        Log.i("lifetime", "用户资料: " + opentype);
//        String id,name,description,profile_image_url;
//        id=res.get("id").toString();//ID
//        name=res.get("name").toString();//用户名
//        description=res.get("description").toString();//描述
//        profile_image_url=res.get("profile_image_url").toString();//头像链接
//        String str="ID: "+id+";\n"+
//                "用户名： "+name+";\n"+
//                "描述："+description+";\n"+
//                "用户头像地址："+profile_image_url;
//        Log.i("lifetime", "用户资料: " + str);
//        show("用户资料: " + str);
        unionid=platform.getDb().get("unionid");
        openid=platform.getDb().get("openid");
        opentype="3";
        if(platform.getDb().getPlatformNname().equals("Wechat")){
            opentype="3";
        }
        if(platform.getDb().getPlatformNname().equals("QZone")){
            opentype="1";
            openid=platform.getDb().getUserId();
            unionid="";
        }
        if(platform.getDb().getPlatformNname().equals("SinaWeibo")){
            opentype="2";
            openid=platform.getDb().getUserId();
            unionid="";
        }

        Log.i("lifetime", "用户资料: " + opentype);
        hand.sendEmptyMessage(1);


//        HttpConnectionUtil.getGetJsonhaveError(SignActivity.this, thridSignPath + "?unionId=" + platform.getDb().get("unionid") + "&Openid=" + platform.getDb().get("openid") + "&openType=3", loding, new HttpConnectionUtil.OnJsonCall() {
//            @Override
//            public void JsonCallBack(String str) {
//                loding.disShapeLoding();
//                Log.i("lifetime", "用户资料: " + str);
//                AllHost host= HttpConnectionUtil.getAllHost(str);
//                if(host.getData()!=null){
//                    share.setMemberID(host.getData());
//                    if(type==null)
//                        JumpUtil.jump2hdown(SignActivity.this, MainActivity.class, false);
//                    else
//                        JumpUtil.jump2finash(SignActivity.this);
//                }else{
//                    JumpUtil.jumpWithValue(SignActivity.this, BindphoneActivity.class, new String[]{"type","unionId","Openid","openType"}, new String[]{"text",unionid,openid,opentype}, true);
//                }
//            }
//        }, new HttpConnectionUtil.OnErrorJsonCall() {
//            @Override
//            public void JsonCallBack(String str) {
//                JumpUtil.jumpWithValue(SignActivity.this, BindphoneActivity.class, new String[]{"type","unionId","Openid","openType"}, new String[]{"text",unionid,openid,opentype}, true);
//            }
//        });
    }

    public void commit(){
        HttpConnectionUtil.getGetJsonhaveError(this, thridSignPath + "?unionId=" +unionid + "&Openid=" + openid + "&openType="+opentype, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                Log.i("lifetime", "用户资料: " + str);
                AllHost host= HttpConnectionUtil.getAllHost(str);
                if(host.getData()!=null){
                    share.setMemberID(host.getData());
                    if(type==null)
                        JumpUtil.jump2hdown(SignActivity.this, MainActivity.class, false);
                    else
                        JumpUtil.jump2finash(SignActivity.this);
                }else{
                    JumpUtil.jumpWithValue(SignActivity.this, BindphoneActivity.class, new String[]{"type","unionId","Openid","openType"}, new String[]{type,unionid,openid,opentype}, true);
                }
            }
        }, new HttpConnectionUtil.OnErrorJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                JumpUtil.jumpWithValue(SignActivity.this, BindphoneActivity.class, new String[]{"type","unionId","Openid","openType"}, new String[]{type,unionid,openid,opentype}, true);
            }
        });
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        loding.disShapeLoding();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        loding.disShapeLoding();
    }
}
