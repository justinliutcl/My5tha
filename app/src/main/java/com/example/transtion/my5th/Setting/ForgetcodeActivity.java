package com.example.transtion.my5th.Setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.MainActivity;
import com.example.transtion.my5th.mActivity.SignActivity;

import fifthutil.JumpUtil;
import fifthutil.SMSUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class ForgetcodeActivity extends BaseActivity {
    Button sendSMS,commit;
    EditText SMScode,newcode,phonenum;
    SMSUtil smsUtil;
    String path= Path.HOST+Path.ip+Path.CHANGE_CODE_PATH;
    String phone;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetcode);
        intoView();
    }

    private void intoView() {
        if(share.getMemberID().length()<3){
            path= Path.HOST+Path.ip+Path.CHANGE_FORGETCODE_PATH;
        }
        sendSMS= (Button) findViewById(R.id.forget_sendoldsms);
        commit= (Button) findViewById(R.id.forget_sure);
        SMScode= (EditText) findViewById(R.id.forget_smscode);
        newcode= (EditText) findViewById(R.id.forget_newcode);
        phonenum= (EditText) findViewById(R.id.forget_phone);
        smsUtil=new SMSUtil(sendSMS,this,phonenum,"1");
        phone=getIntent().getStringExtra("phone");
        if(phone!=null)
            smsUtil.setForgetPhone(phone);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loding.showShapeLoding();
               String Loginname= phonenum.getText().toString();
                String Code= SMScode.getText().toString();
                String LoginPwd= newcode.getText().toString();
                if(share.getMemberID().length()>3) {
                    HttpConnectionUtil.getJsonJsonwithDialog(ForgetcodeActivity.this, path, new String[]{"memberId", "LoginPwd", "PwdType", "Code", "Loginname"}, new String[]{share.getMemberID(), LoginPwd, "1", Code, Loginname}, loding, new HttpConnectionUtil.OnJsonCall() {
                        @Override
                        public void JsonCallBack(String str) {
                            loding.disShapeLoding();
                            show("更改成功");
                            MainActivity.instance.finish();
                            DSetingActivity.instance.finish();
                            JumpUtil.jump2hdown(ForgetcodeActivity.this, SignActivity.class, true);
                        }
                    });
                }else{
                    HttpConnectionUtil.getJsonJsonwithDialog(ForgetcodeActivity.this, path, new String[]{"LoginName", "Code", "LoginPwd"}, new String[]{ Loginname,Code,LoginPwd}, loding, new HttpConnectionUtil.OnJsonCall() {
                        @Override
                        public void JsonCallBack(String str) {
                            loding.disShapeLoding();
                            show("更改成功");
                            JumpUtil.jump2finash(ForgetcodeActivity.this);
                        }
                    });
                }
            }
        });
        type=getIntent().getStringExtra("type");
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
