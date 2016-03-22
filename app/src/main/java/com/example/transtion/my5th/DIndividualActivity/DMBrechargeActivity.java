package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

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
                loding.showloadingbutton();

                push();
            }
        });
    }
    public void push(){
        HttpConnectionUtil.getJsonJsonwithDialog(DMBrechargeActivity.this, path, new String[]{}, new String[]{}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disloadingbutton("充值成功");
            }
        });
    }
    @Override
    public void onClick(View v) {
    }
}
