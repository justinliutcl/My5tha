package com.example.transtion.my5th.Setting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.R;

import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class DSnameActivity extends BaseActivity {
    ImageView back;
    TextView sure;
    EditText name;
    String oldname;
    String path= Path.HOST+Path.ip+Path.CHANGE2NAME_PATH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsname);
        intoview();
    }

    private void intoview() {
        back= (ImageView) findViewById(R.id.back);
        sure= (TextView) findViewById(R.id.name_sure);
        name= (EditText) findViewById(R.id.name_name);
        oldname=getIntent().getStringExtra("name");
        name.setText(oldname);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    JumpUtil.jump2finash(this);
                    break;
                case R.id.name_sure:
                    commit();
                    break;

            }
    }

    private void commit() {
        loding.showShapeLoding();
       String nickname= name.getText().toString();
        HttpConnectionUtil.getJsonJsonwithDialog(this, path, new String[]{"memberId","nickname"}, new String[]{share.getMemberID(),nickname}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                show("修改成功");
                JumpUtil.jump2finash(DSnameActivity.this);
            }
        });
    }
}
