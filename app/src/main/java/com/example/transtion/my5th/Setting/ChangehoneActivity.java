package com.example.transtion.my5th.Setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.R;

import fifthutil.FifUtil;
import fifthutil.JumpUtil;
import fifthutil.SMSUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class ChangehoneActivity extends BaseActivity {
    TextView oldphone;
    EditText authcode;
    Button sendoldSMS,next;
    SMSUtil smsUtil;
    String path= Path.HOST+Path.ip+Path.CHANGE2OLDPHONE;
    String phonenum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changehone);
        initView();
    }

    private void initView() {
        oldphone= (TextView) findViewById(R.id.changephone_oldphone);
        authcode= (EditText) findViewById(R.id.changephone_authcode);
        sendoldSMS= (Button) findViewById(R.id.changephone_sendoldsms);
        next= (Button) findViewById(R.id.changephone_next);
        phonenum=getIntent().getStringExtra("phone");
        smsUtil=new SMSUtil(sendoldSMS,this,phonenum,"22");
        oldphone.setText(FifUtil.getSecretPhone(phonenum));
    }

    @Override
    public void setListener() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loding.showShapeLoding();
               String smscode= authcode.getText().toString();
                HttpConnectionUtil.getJsonJsonwithDialog(ChangehoneActivity.this, path, new String[]{"LoginName", "Code"}, new String[]{phonenum, smscode}, loding, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        loding.disShapeLoding();
                        JumpUtil.jump2finish(ChangehoneActivity.this, ChangenphoneActivity.class, true);

                    }
                });

            }
        });
    }

    @Override
    public void onClick(View v) {
    }
}
