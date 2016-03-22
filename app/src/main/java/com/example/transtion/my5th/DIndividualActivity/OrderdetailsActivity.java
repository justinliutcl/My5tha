package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import InternetUser.order.GoodsDetailUser;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class OrderdetailsActivity extends BaseActivity {
    String path= Path.HOST+Path.ip+Path.ORDERDETAIL_PATH;
    String orderId;
    TextView tradetype,ordernum,logistictype,logisttime,name,phone,address,goodstype,paymoney,totalmoney,feight,couponmoney,shouldmoney,ordertime;
    Button delete,logist,buyagain;
    LinearLayout goods,pay;
    GoodsDetailUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        initView();
    }

    private void initView() {
        orderId=getIntent().getStringExtra("orderId");


        getJson();

    }

    private void getJson() {
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID() + "&orderNumber=" + orderId, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getGoodsDetailUser(str);
                setView();
            }
        });
    }

    private void setView() {

    }


    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
