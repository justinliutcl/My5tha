package com.example.transtion.my5th.BShopcar;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import InternetUser.order.LogistDetails;
import adapter.order.LogistDetailsAdapter;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class LogisticActivity extends BaseActivity {
    ListView mlist;
    TextView company,number,time;
    String path= Path.HOST+Path.ip+Path.LOGISTIC_DETAILS_PATH;
    String orderNumber;
    LogistDetails user;
    LogistDetailsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic);
        initView();
    }

    private void initView() {
        company= (TextView) findViewById(R.id.logistic_company);
        number= (TextView) findViewById(R.id.logistic_number);
        time= (TextView) findViewById(R.id.logistic_time);
        mlist= (ListView) findViewById(R.id.logistic_list);
        orderNumber=getIntent().getStringExtra("orderNumber");
        getJson();
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID() + "&OrderNumber=" + orderNumber, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                user = HttpConnectionUtil.getLogistDetails(str);
                setView();
            }
        });
    }

    private void setView() {
        company.setText(user.getOperatingCompany());
        number.setText(user.getOperaNumber());
        time.setText(user.getOperationTime());
        adapter=new LogistDetailsAdapter(user.getOrderRecordSummaryList(),this);
        mlist.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
