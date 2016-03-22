package com.example.transtion.my5th.Setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import fifthutil.JumpUtil;
import fifthutil.SMSUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class SetpaycodeActivity extends BaseActivity {
EditText phone,smscode,paycode;
    Button sendSMS,sure;
    SMSUtil smsUtil;
    String path= Path.HOST+Path.ip+Path.CHANGE_CODE_PATH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpaycode);
        intoview();

    }

    private void intoview() {
        phone= (EditText) findViewById(R.id.setpaycode_phone);
        smscode= (EditText) findViewById(R.id.setpaycode_smscode);
        paycode= (EditText) findViewById(R.id.setpaycode_paycode);
        sendSMS= (Button) findViewById(R.id.setpaycode_sendsms);
        sure= (Button) findViewById(R.id.setpaycode_sure);
        smsUtil=new SMSUtil(sendSMS,this,phone,"2");

    }

    @Override
    public void setListener() {
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
    }
    private void commit() {
        loding.showShapeLoding();
        String phonenum=phone.getText().toString();
        String loginpwd=paycode.getText().toString();
        String PwdType="2";
        String OldLoginPwd=smscode.getText().toString();
        HttpConnectionUtil.getJsonJsonwithDialog(this, path, new String[]{"memberId", "Paypwd", "PwdType","Code", "Loginname"}, new String[]{share.getMemberID(), loginpwd, PwdType, OldLoginPwd,phonenum}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                show("设置成功");
                JumpUtil.jump2finash(SetpaycodeActivity.this);
            }
        });
    }
    @Override
    public void onClick(View v) {

    }
}
