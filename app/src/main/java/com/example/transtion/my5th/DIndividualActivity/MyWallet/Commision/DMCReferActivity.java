package com.example.transtion.my5th.DIndividualActivity.MyWallet.Commision;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.R;

import InternetUser.ReferUser;
import adapter.Individual.ReferAdapter;
import customUI.PullToRefreshView;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class DMCReferActivity extends BaseActivity implements PullToRefreshView.OnFooterRefreshListener{
    ListView mlist;
    ShareUtil share;
    PullToRefreshView refreshListView;
    int now=2;
    int tatol;
    ReferAdapter adapter;
    String path= Path.HOST+Path.ip+Path.REFERRER_PATH;
    ReferUser user;
    TextView num;
    boolean reflash=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmcrefer);
        initview();
    }

    public void initview() {
        share=ShareUtil.getInstanse(this);
        mlist= (ListView) findViewById(R.id.refer_listview);
        refreshListView= (PullToRefreshView) findViewById(R.id.refer_list);
        refreshListView.setEnablePullTorefresh(false);
        num= (TextView) findViewById(R.id.refer_referrernum);
        String n=getIntent().getStringExtra("num");
        if(n!=null)
            num.setText(n);

        getJson();
    }
    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID(),loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getReferUserUser(str);
                tatol = Integer.parseInt(user.getPageCount());
                adapter = new ReferAdapter(user, DMCReferActivity.this);
                mlist.setAdapter(adapter);
                num.setText(user.getReferrerCount());
                loding.dismiss();
            }
        });
    }

    @Override
    public void setListener() {
        refreshListView.setOnFooterRefreshListener(this);
        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    private void getRefresh(){
        HttpConnectionUtil.getGetJson(this, path + "?MemberId="+share.getMemberID() + "&PageIndex=" + now,null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                ReferUser users = HttpConnectionUtil.getReferUserUser(str);
                user.getList().addAll(users.getList());
                adapter.setUser(user);
                adapter.notifyDataSetChanged();
                refreshListView.onFooterRefreshComplete();
                now++;
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        if(now<=tatol)
            getRefresh();
        else{
            if(reflash) {
                show("已到最后一页");
                refreshListView.onFooterRefreshComplete();
                reflash=false;
                refreshListView.getmFooterView().setVisibility(View.INVISIBLE);
            }else{
                refreshListView.onFooterRefreshComplete();
            }
        }
    }
}
