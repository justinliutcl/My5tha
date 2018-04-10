package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import InternetUser.AllHost;
import fifthutil.ImageUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class PopulazeActivity extends BaseActivity {
    String path= Path.HOST+Path.ip+Path.POPULAZE_PATH;
    ImageView img;
    ImageUtil imageUtil;
    AllHost host;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populaze);
        initView();
    }

    private void initView() {
        imageUtil=new ImageUtil(this);
        img= (ImageView) findViewById(R.id.popularize_img);
        getJson();
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?memberId=" + share.getMemberID(), loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                host= HttpConnectionUtil.getAllHost(str);
                imageUtil.display(img, host.getData());
                loding.dismiss();
            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
