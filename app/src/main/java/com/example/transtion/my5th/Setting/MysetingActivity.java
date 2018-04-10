package com.example.transtion.my5th.Setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.mActivity.MainActivity;
import com.example.transtion.my5th.mActivity.SignActivity;

import java.util.List;

import InternetUser.A_Home.HostTitle;
import customUI.TopbarView;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class MysetingActivity extends BaseActivity {
LinearLayout clear;
    TextView version;
    Button out;
    TopbarView topbar;
    String str,s;
    String versionName= Path.VERSION;
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
        topbar= (TopbarView) findViewById(R.id.topbar);
        version.setText("v"+versionName);
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
                setSomeThing();
                share.clear();
                List<HostTitle> list= HttpConnectionUtil.getHostTitleList(str);
                for(int i=0; i<list.size();i++){
                    share.setSelectItem(list.get(i).getTypeCode(),"");
                }
                share.setHostTitle(str);
                share.setfirstHost(s);
                MainActivity.instance.finish();
                JumpUtil.jump2hdown(this, SignActivity.class, true);
                break;
        }
    }

    private void setSomeThing() {
        str= share.getHostTitle();
        s =share.getfirstHost();



    }
}
