package com.example.transtion.my5th;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class SignActivity extends BaseActivity implements PlatformActionListener {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initview();

    }
    public void initview() {
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
                Platform qq= ShareSDK.getPlatform(this, QZone.NAME);
                qq.SSOSetting(false);
                qq.setPlatformActionListener(this);
                qq.authorize();
                break;
            case R.id.login_wechat:
                Platform wechat= ShareSDK.getPlatform(this, Wechat.NAME);
                wechat.SSOSetting(false);
                wechat.setPlatformActionListener(this);
                wechat.authorize();
                break;
            case R.id.login_weibo:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.SSOSetting(false);  //设置false表示使用SSO授权方式
                weibo.setPlatformActionListener(this); // 设置分享事件回调
                weibo.authorize();
                break;
            case R.id.sign_toregist:
                JumpUtil.jump2finish(this, LoginActivity.class, false);
                break;
            case R.id.sign_toregist2:
                JumpUtil.jump2finish(this, LoginActivity.class, false);
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
                JumpUtil.jump2hdown(this, MainActivity.class, false);
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
            HttpConnectionUtil.getJsonJsonwithDialog(this, signPath, new String[]{"LoginName", "Password", "IsVerified"}, new String[]{loginName, Password, "true"}, loding, new HttpConnectionUtil.OnJsonCall() {
                @Override
                public void JsonCallBack(String str) {
                    AllHost host = HttpConnectionUtil.getAllHost(str);
                    loding.disShapeLoding();
                    if (host.isSuccess()) {
//                        show(host.getData());
                        share.setMemberID(host.getData());
                        JumpUtil.jump2hdown(SignActivity.this, MainActivity.class, false);
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
                        JumpUtil.jump2hdown(SignActivity.this,MainActivity.class,false);
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
            JumpUtil.jump2hdown(this, MainActivity.class, false);
            return true;
        }else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> res) {
        //解析部分用户资料字段
        String id,name,description,profile_image_url;
        id=res.get("id").toString();//ID
        name=res.get("name").toString();//用户名
        description=res.get("description").toString();//描述
        profile_image_url=res.get("profile_image_url").toString();//头像链接
        String str="ID: "+id+";\n"+
                "用户名： "+name+";\n"+
                "描述："+description+";\n"+
                "用户头像地址："+profile_image_url;
        Log.i("lifetime", "用户资料: " + str);
        show("用户资料: " + str);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Log.i("lifetime", "用户资料: " + "asd");
        show("用户资料: " + "asd");
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Log.i("lifetime", "用户资料: " + "cancle");
        show("用户资料: " + "cancle");
    }
}
