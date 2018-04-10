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
import android.widget.TextView;

import com.example.transtion.my5th.AHomeActivity.WebActivity;
import com.example.transtion.my5th.R;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


public class LoginActivity extends BasenoscrollActivity implements PlatformActionListener{
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
    String type;
    String thridSignPath= Path.HOST+Path.ip+Path.THRID_LOGIN_PATH;
    public static LoginActivity Instance;
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

    private void commit() {
        HttpConnectionUtil.getGetJsonhaveError(this, thridSignPath + "?unionId=" +unionid + "&Openid=" + openid + "&openType="+opentype, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                AllHost host= HttpConnectionUtil.getAllHost(str);
                if(host.getData()!=null){
                    share.setMemberID(host.getData());
                    if(type==null)
                        JumpUtil.jump2hdown(LoginActivity.this, MainActivity.class, false);
                    else
                        JumpUtil.jump2finash(LoginActivity.this);
                }else{
                    JumpUtil.jumpWithValue(LoginActivity.this, BindphoneActivity.class, new String[]{"type","unionId","Openid","openType"}, new String[]{"text",unionid,openid,opentype}, true);
                }
            }
        }, new HttpConnectionUtil.OnErrorJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                JumpUtil.jumpWithValue(LoginActivity.this, BindphoneActivity.class, new String[]{"type","unionId","Openid","openType"}, new String[]{"text",unionid,openid,opentype}, true);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
    }


    public void initview() {

        Instance=this;
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
        type=getIntent().getStringExtra("type");


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
                    weibo.removeAccount(true);
                    weibo.SSOSetting(false);  //设置false表示使用SSO授权方式
                    weibo.setPlatformActionListener(this); // 设置分享事件回调
                    weibo.showUser(null);

                    break;
                case R.id.register_tologin:
                    JumpUtil.jumpWithValue2finash(this, SignActivity.class, true, new String[]{"type"}, new String[]{"text"});
                    break;
                case R.id.regist:
                    regist();
                    break;
                case R.id.back:
                    if(type==null)
                        JumpUtil.jump2hdown(this, MainActivity.class, false);
                    else
                        JumpUtil.jump2finash(this);
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
        }else if(!PwdSecurity(code)){
            show("请输入至少由数字和字母组成的6-16位密码");
        }
        else{
            loding.showShapeLoding();
            HttpConnectionUtil.getJsonJsonwithDialog(this, path, new String[]{"loginname", "Password", "Code","LoginType","RegistType"}, new String[]{phone, code, sms,"1","4"}, loding,new HttpConnectionUtil.OnJsonCall() {
                @Override
                public void JsonCallBack(String str) {
                    AllHost host = HttpConnectionUtil.getAllHost(str);
                    if (host.isSuccess()) {
//                        show(host.getData());
                        share.setMemberID(host.getData());
                        loding.disShapeLoding();
                        if(type==null) {
                            JumpUtil.jump2hdown(LoginActivity.this, MainActivity.class, false);
                            JumpUtil.jumpWithValue(LoginActivity.this, WebActivity.class, new String[]{"address"}, new String[]{"https://sale.5tha.com/hnewer/index?memberid="}, true);
                        }
                        else
                            JumpUtil.jump2finash(LoginActivity.this);
                    } else {
//                        show(str);
                        show(host.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(type==null)
                JumpUtil.jump2hdown(LoginActivity.this, MainActivity.class, false);
            else
                JumpUtil.jump2finash(LoginActivity.this);
            return true;
        }else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> res) {
        //解析部分用户资料字段
        Log.i("lifetime", "回调了。。。。。。。。。");
//        Iterator iter = res.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            Object key = entry.getKey();
//            Object val = entry.getValue();
//            Log.i("lifetime", key+"。。。。。。。。。"+val);
//        }
        Log.i("lifetime", platform.getName());

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
//        String id,name,description,profile_image_url;
//        id=res.get("id").toString();//ID
//        name=res.get("name").toString();//用户名
//        description=res.get("description").toString();//描述
//        profile_image_url=res.get("profile_image_url").toString();//头像链接
//        String str="ID: "+id+";\n"+
//                "用户名： "+name+";\n"+openid
//                "描述："+description+";\n"+
//                "用户头像地址："+profile_image_url;
//        Log.i("5tha","用户资料: "+str);
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
        hand.sendEmptyMessage(1);
    }

    public boolean PwdSecurity(String payPwdNew)
    {
        int index = 0;
        String reg = "[0-9]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(payPwdNew);
        boolean r1 =  m.find();
        String characterReg = "[a-zA-Z]";
        boolean r2 =  Pattern.compile(characterReg).matcher(payPwdNew).find();
        String specialReg = "[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&;—|{}【】‘；：”“'。，、？]";
        boolean r3 = Pattern.compile(specialReg).matcher(payPwdNew).find();
        String bland = "^[^ ]+$";
        boolean r4 = Pattern.compile(bland).matcher(payPwdNew).find();
        if (r1) index = index + 1;
        if (r2) index = index + 1;
        if (r3) index = index + 1;
        if (!r4) index = 0;
        return index >= 2;
    }


    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        hand.sendEmptyMessage(2);
    }

    @Override
    public void onCancel(Platform platform, int i) {

        hand.sendEmptyMessage(2);
    }
}
