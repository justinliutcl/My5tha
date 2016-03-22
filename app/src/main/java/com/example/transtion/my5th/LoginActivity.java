package com.example.transtion.my5th;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import InternetUser.AllHost;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import fifthutil.JumpUtil;
import fifthutil.SMSUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;
import togglebutton.ToggleButton;


public class LoginActivity extends BaseActivity implements PlatformActionListener{
    ToggleButton toggleButton;
    Button sendSMS,regist;
    TextView tologin;
    EditText phoneNum,smscode,password;
    ImageView login_qq,login_weichat,login_sina,back;
    SMSUtil smsUtil;
    String regex = "^[A-Za-z0-9]+$";
    private String p="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0-9]|17[0-9])\\d{8}$";
    String path= Path.HOST+Path.ip+Path.REGIST_PATH;
    ShareUtil share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
    }


    public void initview() {
        ShareSDK.initSDK(this);
        share= ShareUtil.getInstanse(this);
        tologin= (TextView) findViewById(R.id.register_tologin);
        login_qq= (ImageView) findViewById(R.id.login_qq);

        login_weichat= (ImageView) findViewById(R.id.login_wechat);
        login_sina= (ImageView) findViewById(R.id.login_weibo);
        back= (ImageView) findViewById(R.id.back);

        sendSMS= (Button) findViewById(R.id.regist_sendSMS);
        regist= (Button) findViewById(R.id.regist);

        phoneNum= (EditText) findViewById(R.id.regist_phone);
        smscode= (EditText) findViewById(R.id.regist_smscode);
        password= (EditText) findViewById(R.id.regist_password);

        smsUtil=new SMSUtil(sendSMS,this,phoneNum);
        toggleButton= (ToggleButton) findViewById(R.id.regist_codeshow);



    }

    @Override
    public void setListener() {
        login_qq.setOnClickListener(this);
        login_weichat.setOnClickListener(this);
        login_sina.setOnClickListener(this);
        tologin.setOnClickListener(this);
        regist.setOnClickListener(this);
        back.setOnClickListener(this);
        toggleButton.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if(on){
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
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
                case R.id.register_tologin:
                    JumpUtil.jump2finish(this, SignActivity.class, true);
                    break;
                case R.id.regist:
                    regist();
                    break;
                case R.id.back:
                   JumpUtil.jump2hdown(this, MainActivity.class, false);
                    break;
                default:
                    break;
            }
    }

    private void regist() {
        String phone=phoneNum.getText().toString();
        String sms=smscode.getText().toString();
        String code=password.getText().toString();
        if(!phone.matches(p)){
            show("请输入正确手机号");
        }else if(!code.matches(regex) || code.length()<6 || code.length()>16){
            show("请输入由数字和字母组成的6-16位密码");
        }else{
            loding.showShapeLoding();
            HttpConnectionUtil.getJsonJsonwithDialog(this, path, new String[]{"loginname", "Password", "Code"}, new String[]{phone, code, sms}, loding,new HttpConnectionUtil.OnJsonCall() {
                @Override
                public void JsonCallBack(String str) {
                    AllHost host = HttpConnectionUtil.getAllHost(str);
                    if (host.isSuccess()) {
//                        show(host.getData());
                        share.setMemberID(host.getData());
                        loding.disShapeLoding();
                        JumpUtil.jump2hdown(LoginActivity.this, MainActivity.class, false);
                    } else {
                        show(str);
                        show(host.getMessage());
                    }
                }
            });
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
        Log.i("5tha","用户资料: "+str);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}
