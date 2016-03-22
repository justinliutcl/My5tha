package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import InternetUser.TixianHIsUser;
import adapter.TixianHisAdapter;
import customUI.PullToRefreshView;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class DMCgodhisActivity extends BaseActivity implements PullToRefreshView.OnFooterRefreshListener{
    ListView mlist;
    ShareUtil share;
    PullToRefreshView refreshListView;
    int now=1;
    int tatol;
    TixianHisAdapter adapter;
    String path= Path.HOST+Path.ip+Path.TIXIANHIS_PATH;
    TixianHIsUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmcgodhis);
        initview();
    }

    public void initview() {
        share=ShareUtil.getInstanse(this);
        mlist= (ListView) findViewById(R.id.txhis_listview);
        refreshListView= (PullToRefreshView) findViewById(R.id.txhis_list);
        refreshListView.setEnablePullTorefresh(false);
        getJson();
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?MemberId="+share.getMemberID(), loding,new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getTixianHIsUser(str);
                tatol = Integer.parseInt(user.getPageCount());
                adapter = new TixianHisAdapter(user, DMCgodhisActivity.this);
                mlist.setAdapter(adapter);
                loding.disShapeLoding();
            }
        });
    }

    @Override
    public void setListener() {
        refreshListView.setOnFooterRefreshListener(this);
        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JumpUtil.jumpWithValue(DMCgodhisActivity.this,DMDtxdetailsActivity.class,new String[]{"RecordId"},new String[]{user.getList().get(position).getId()},true);
            }
        });
    }

    private void getRefresh(){
        HttpConnectionUtil.getGetJson(this, path + "?MemberId="+share.getMemberID() + "&PageIndex=" + now,null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                TixianHIsUser users = HttpConnectionUtil.getTixianHIsUser(str);
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
        if(now<tatol)
            getRefresh();
        else{
            show("已到最后一页");
            refreshListView.onFooterRefreshComplete();
        }
    }
}
