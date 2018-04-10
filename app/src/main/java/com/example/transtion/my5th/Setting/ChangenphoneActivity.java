package com.example.transtion.my5th.Setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.R;

import fifthutil.JumpUtil;
import fifthutil.SMSUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class ChangenphoneActivity extends BaseActivity {
    EditText authcode,phonenum;
    Button sendoldSMS,next;
    SMSUtil smsUtil;
    String path= Path.HOST+Path.ip+Path.CHANGE2NEWPHONE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changenphone);
        intoView();
    }

    private void intoView() {
        phonenum= (EditText) findViewById(R.id.changephone_newphone);
        authcode= (EditText) findViewById(R.id.changephone_authcode);
        sendoldSMS= (Button) findViewById(R.id.changephone_sendoldsms);
        next= (Button) findViewById(R.id.changephone_next);
        smsUtil=new SMSUtil(sendoldSMS,this,phonenum,"22","changephone");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loding.showShapeLoding();
                String phone=phonenum.getText().toString();
                String smscode=authcode.getText().toString();
                HttpConnectionUtil.getJsonJsonwithDialog(ChangenphoneActivity.this, path, new String[]{"memberId","Code","Loginname"}, new String[]{share.getMemberID(),smscode,phone}, loding, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                            loding.disShapeLoding();
                        JumpUtil.jump2finash(ChangenphoneActivity.this);
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
