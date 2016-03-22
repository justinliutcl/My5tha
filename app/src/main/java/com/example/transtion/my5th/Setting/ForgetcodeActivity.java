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

public class ForgetcodeActivity extends BaseActivity {
    Button sendSMS,commit;
    EditText SMScode,newcode,phonenum;
    SMSUtil smsUtil;
    String path= Path.HOST+Path.ip+Path.CHANGE_CODE_PATH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetcode);
        intoView();
    }

    private void intoView() {
        sendSMS= (Button) findViewById(R.id.forget_sendoldsms);
        commit= (Button) findViewById(R.id.forget_sure);
        SMScode= (EditText) findViewById(R.id.forget_smscode);
        newcode= (EditText) findViewById(R.id.forget_newcode);
        phonenum= (EditText) findViewById(R.id.forget_phone);
        smsUtil=new SMSUtil(sendSMS,this,phonenum,"1");
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loding.showShapeLoding();
                HttpConnectionUtil.getJsonJsonwithDialog(ForgetcodeActivity.this, path, new String[]{}, new String[]{}, loding, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        loding.disShapeLoding();
                        show("更改成功");
                        JumpUtil.jump2finash(ForgetcodeActivity.this);
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
