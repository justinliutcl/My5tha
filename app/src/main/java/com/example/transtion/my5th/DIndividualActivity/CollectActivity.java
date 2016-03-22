package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import InternetUser.CollectUser;
import adapter.AddressAdapter;
import adapter.CollectuseAdapter;
import customUI.PullToRefreshView;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class CollectActivity extends BaseActivity implements PullToRefreshView.OnFooterRefreshListener{

    CollectUser user;
    CollectuseAdapter adapter;
    ListView mlist;
    PullToRefreshView refreshListView;
    int now=1;
    int tatol;
    String path= Path.HOST+Path.ip+Path.COLLECT_PATH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initview();
    }
    public void initview() {
        refreshListView= (PullToRefreshView) findViewById(R.id.collect_list);
        mlist= (ListView) findViewById(R.id.collect_listview);
        refreshListView.setEnablePullTorefresh(false);
        getJson();
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID(), loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getCollectUser(str);
                tatol = Integer.parseInt(user.getPageCount());
                adapter = new CollectuseAdapter(user, CollectActivity.this, loding, new AddressAdapter.OnadapterChangeCall() {
                    @Override
                    public void adapterChangeBack(int position) {
                        adapter.notifyDataSetChanged();
                    }
                });
                mlist.setAdapter(adapter);
                loding.disShapeLoding();
            }
        });
    }

    @Override
    public void setListener() {
        refreshListView.setOnFooterRefreshListener(this);
    }
    private void getRefresh(){
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID()+"&PageIndex="+now, null,new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                CollectUser users = HttpConnectionUtil.getCollectUser(str);
                user.getList().addAll(users.getList());
                adapter.setUser(user);
                adapter.notifyDataSetChanged();
                refreshListView.onFooterRefreshComplete();
//                setLastUpdateTime();
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
