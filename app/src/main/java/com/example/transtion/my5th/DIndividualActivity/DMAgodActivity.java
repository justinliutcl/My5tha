package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import InternetUser.Communitionuser;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class DMAgodActivity extends BaseActivity {

    TextView communition,num;
    LinearLayout more,tixian,shouru;
    ShareUtil share;
    String path;
    Communitionuser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmagod);
        initview();
    }

    public void initview() {
        communition= (TextView) findViewById(R.id.community_god);
        num= (TextView) findViewById(R.id.community_num);
        more= (LinearLayout) findViewById(R.id.community_layout_more);
        tixian= (LinearLayout) findViewById(R.id.community_layout_tixian);
        shouru= (LinearLayout) findViewById(R.id.community_layout_shouru);
        share= ShareUtil.getInstanse(this);
        path= Path.HOST+Path.ip+Path.COMMUNITION_PATH+share.getMemberID();
        getJson();

    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path,loding,new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                    user=HttpConnectionUtil.getCommunitionUser(str);
                communition.setText(user.getCommission());
                num.setText(user.getNumber());
                loding.disShapeLoding();
            }
        });
    }

    @Override
    public void setListener() {
        more.setOnClickListener(this);
        tixian.setOnClickListener(this);
        shouru.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.community_layout_more:
                    JumpUtil.jumpWithValue(this,DMCReferActivity.class,new String[]{"num"},new String[]{user.getNumber()},true);
                    break;
                case R.id.community_layout_tixian:
                    JumpUtil.jump(this,DMBgodtixian.class,true);
                    break;
                case R.id.community_layout_shouru:
                    JumpUtil.jump(this,DMBincomeActivity.class,true);
                    break;
                default:
                    break;
            }
    }
}
