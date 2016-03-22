package com.example.transtion.my5th.Setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.SignActivity;

import fifthutil.JumpUtil;

public class MysetingActivity extends BaseActivity {
LinearLayout clear;
    TextView version;
    Button out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myseting);
        initView();
    }

    private void initView() {
        clear= (LinearLayout) findViewById(R.id.myseting_layout_clear);
        version= (TextView) findViewById(R.id.myseting_version);
        out= (Button) findViewById(R.id.myseting_back);
    }

    @Override
    public void setListener() {
        clear.setOnClickListener(this);
        out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.myseting_layout_clear:
                loding.showloadingbutton();
                loding.disloadingbutton("清除成功");
                break;
            case R.id.myseting_back:
                share.clear();
                JumpUtil.jump2hdown(this, SignActivity.class, true);
                break;
        }
    }
}
