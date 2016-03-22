package com.example.transtion.my5th.Setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class ChangeLoginActivity extends BaseActivity {
EditText oldcode,newcode;
    TextView forget;
    Button save;
    String path= Path.HOST+Path.ip+Path.CHANGE_CODE_PATH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_login);
        intoView();
    }

    private void intoView() {
        oldcode= (EditText) findViewById(R.id.changelogincode_oldcode);
        newcode= (EditText) findViewById(R.id.changelogincode_newcode);
        forget= (TextView) findViewById(R.id.changelogincode_forgetcode);
        save= (Button) findViewById(R.id.changelogincode_save);
    }

    @Override
    public void setListener() {
        forget.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.changelogincode_forgetcode:
                JumpUtil.jump2finish(this,ForgetcodeActivity.class,true);
                break;
            case R.id.changelogincode_save:
                commit();
                break;


        }
    }

    private void commit() {
        loding.showShapeLoding();
        String loginpwd=newcode.getText().toString();
        String PwdType="1";
        String OldLoginPwd=oldcode.getText().toString();
        HttpConnectionUtil.getJsonJsonwithDialog(this, path, new String[]{"memberId","LoginPwd","PwdType","OldLoginPwd"}, new String[]{share.getMemberID(),loginpwd,PwdType,OldLoginPwd}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                    show("保存成功");
                JumpUtil.jump2finash(ChangeLoginActivity.this);
            }
        });
    }
}
