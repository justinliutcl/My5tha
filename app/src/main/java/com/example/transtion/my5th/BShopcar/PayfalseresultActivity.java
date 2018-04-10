package com.example.transtion.my5th.BShopcar;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.transtion.my5th.AHomeActivity.TextActivity;
import com.example.transtion.my5th.DIndividualActivity.Order.DSAllActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.mActivity.MainActivity;

import customUI.TopbarView;
import fifthutil.JumpUtil;

public class PayfalseresultActivity extends BaseActivity {
    TopbarView topbarView;
    Button commit,go2home;
    String isfendan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payfalseresult);
        initView();
    }

    private void initView() {
        topbarView= (TopbarView) findViewById(R.id.topbar);
        commit= (Button) findViewById(R.id.commit);
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
        topbarView.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShouyinActivity.instance.finish();
                JumpUtil.jumpWithValue2finash(PayfalseresultActivity.this, DSAllActivity.class, true, new String[]{"count"}, new String[]{"1"});
            }
        });
        go2home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfendan.equals("true")) {
                    JumpUtil.jump2finish(PayfalseresultActivity.this, FendanActivity.class, false);
                }else{
                    if (TextActivity.instance != null) {
                        TextActivity.instance.finish();
                    }
                    if (ShopcarActivity.instance != null) {
                        ShopcarActivity.instance.finish();
                    }
                    MainActivity.instance.finish();
                    JumpUtil.jump2finish(PayfalseresultActivity.this, MainActivity.class, false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
//            if(ShouyinActivity.instance!=null)
//              ShouyinActivity.instance.finish();
//            JumpUtil.jump2finash(PayfalseresultActivity.this);
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
                JumpUtil.jump2finish(PayfalseresultActivity.this, MainActivity.class, false);
            }
            return true;
        }else
            return super.onKeyDown(keyCode, event);

    }
}
