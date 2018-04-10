package com.example.transtion.my5th.BShopcar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.AHomeActivity.TextActivity;
import com.example.transtion.my5th.DIndividualActivity.Order.DSAllActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.mActivity.MainActivity;

import fifthutil.JumpUtil;

public class PayResultActivity extends BaseActivity {

    TextView type,mes;
    Button commit,go2home;
    ImageView img;
    String isfendan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);
        initView();
    }

    private void initView() {
        type= (TextView) findViewById(R.id.tv_type);
        mes= (TextView) findViewById(R.id.tv_mes);
        commit= (Button) findViewById(R.id.commit);
        img= (ImageView) findViewById(R.id.iv_img);
        go2home= (Button) findViewById(R.id.go2home);
        isfendan=getIntent().getStringExtra("type");
        if(isfendan.equals("true")){
            go2home.setText("继续支付");
        }else {
            go2home.setText("去首页");
        }
    }

    @Override
    public void setListener() {
        commit.setOnClickListener(this);
        go2home.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.commit:
                    JumpUtil.jumpWithValue2finash(this, DSAllActivity.class, true, new String[]{"count"}, new String[]{"2"});
                    break;
                case R.id.go2home:
                    if(isfendan.equals("true")) {
                        JumpUtil.jump2finish(this, FendanActivity.class, false);
                    }else{
                        if (TextActivity.instance != null) {
                            TextActivity.instance.finish();
                        }
                        if (ShopcarActivity.instance != null) {
                            ShopcarActivity.instance.finish();
                        }
                        MainActivity.instance.finish();
                        JumpUtil.jump2finish(PayResultActivity.this, MainActivity.class, false);
                    }
                    break;
            }
    }
}
