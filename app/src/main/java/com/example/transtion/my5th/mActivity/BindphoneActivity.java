package com.example.transtion.my5th.mActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.transtion.my5th.R;

import InternetUser.AllHost;
import fifthutil.JumpUtil;
import fifthutil.SMSUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class BindphoneActivity extends BasenoscrollActivity {
    EditText authcode,phonenum;
    Button sendoldSMS,commit;
    SMSUtil smsUtil;
    String path= Path.HOST+Path.ip+Path.BANDPHONE_PATH;
    String unionId,Openid,openType,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindphone);
        initView();
    }

    private void initView() {
        phonenum= (EditText) findViewById(R.id.bindphone_phone);
        authcode= (EditText) findViewById(R.id.bindphone_authcode);
        sendoldSMS= (Button) findViewById(R.id.bindphone_sendoldsms);
        commit= (Button) findViewById(R.id.bindphone_commit);
        Intent intent=getIntent();
        unionId=intent.getStringExtra("unionId");
        openType=intent.getStringExtra("openType");
        Openid=intent.getStringExtra("Openid");
        type=intent.getStringExtra("type");
        smsUtil=new SMSUtil(sendoldSMS,this,phonenum,"14","changephone");
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loding.showShapeLoding();
                String phone=phonenum.getText().toString();
                String smscode=authcode.getText().toString();
                print("unionId=="+unionId+"Openid=="+Openid+"openType=="+openType+"unionId=="+unionId);
                HttpConnectionUtil.getJsonJsonwithDialog(BindphoneActivity.this, path, new String[]{"unionId", "Openid", "openType", "RegistType", "Phone", "Code"},
                        new String[]{unionId, Openid, openType, "4", phone, smscode}, loding, new HttpConnectionUtil.OnJsonCall() {
                            @Override
                            public void JsonCallBack(String str) {
                                loding.disShapeLoding();
                                AllHost host = HttpConnectionUtil.getAllHost(str);
                                share.setMemberID(host.getData());
                                if (LoginActivity.Instance != null)
                                    LoginActivity.Instance.finish();
                                if (SignActivity.Instance != null)
                                    SignActivity.Instance.finish();
                                if (type == null)
                                    JumpUtil.jump2hdown(BindphoneActivity.this, MainActivity.class, false);
                                else
                                    JumpUtil.jump2finash(BindphoneActivity.this);
                            }
                        });

            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
