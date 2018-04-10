package com.example.transtion.my5th.DIndividualActivity.MyWallet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.R;

import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class DMBrechargeActivity extends BaseActivity {
    EditText cardpaw;
    Button commit;
    ShareUtil share;
    String path= Path.HOST+Path.ip+Path.GIFTCARD_PATH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmbrecharge);
        initview();
    }

    public void initview() {
        share=ShareUtil.getInstanse(this);
        cardpaw= (EditText) findViewById(R.id.recharge_cardpwd);
        commit= (Button) findViewById(R.id.recharge_commit);

    }

    @Override
    public void setListener() {
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loding.showShapeLoding();

                push();
            }
        });
    }
    public void push(){
        String password=cardpaw.getText().toString();
        HttpConnectionUtil.getJsonJsonwithDialog(DMBrechargeActivity.this, path, new String[]{"MemberId","Password"}, new String[]{share.getMemberID(),password}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                show("兑换成功");
                DWdqbActivity.instance.finish();
                JumpUtil.jump2finash(DMBrechargeActivity.this);
            }
        });
    }
    @Override
    public void onClick(View v) {
    }
}
