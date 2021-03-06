package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import InternetUser.CollectUser;
import adapter.Individual.CollectuseAdapter;
import customUI.CustomScrollView;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class CollectActivity extends BaseActivity{

    CollectUser user;
    CollectuseAdapter adapter;
    ListView mlist;
    int now=2;
    int tatol;
    String path= Path.HOST+Path.ip+Path.COLLECT_PATH;
    boolean reflash=true;
    CustomScrollView customScrollView;
    LinearLayout blin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initview();
    }
    public void initview() {
        mlist= (ListView) findViewById(R.id.collect_listview);
        customScrollView= (CustomScrollView) findViewById(R.id.customScrollview);
        customScrollView.setContext(this);
        blin= (LinearLayout)findViewById(R.id.otherfrag_blin);
        customScrollView.setonBorderListener(new CustomScrollView.OnBorderListener() {
            @Override
            public void onBottom() {
                if(now<=tatol)
                    getRefresh();
                else{
                    if(reflash){
                        Toast.makeText(CollectActivity.this, "已到最后一页", Toast.LENGTH_SHORT).show();
                        blin.setVisibility(View.VISIBLE);
                        reflash=false;
                    }
                }
            }
        });
        getJson();
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID(), loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getCollectUser(str);
                tatol = Integer.parseInt(user.getPageCount());
                adapter = new CollectuseAdapter(user, CollectActivity.this, loding) {

                    @Override
                    protected void ondeleteClickItem(View v, int position) {
                        adapter.notifyDataSetChanged();
                    }
                };
                mlist.setAdapter(adapter);
                loding.disShapeLoding();
            }
        });
    }

    @Override
    public void setListener() {

    }
    private void getRefresh(){
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID() + "&PageIndex=" + now, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                CollectUser users = HttpConnectionUtil.getCollectUser(str);
                user.getList().addAll(users.getList());
                adapter.setUser(user);
                adapter.notifyDataSetChanged();
                customScrollView.setFlage(true);
                now++;
            }
        });
    }
    @Override
    public void onClick(View v) {

    }
    public void jugle(){
        if(now<=tatol)
            getRefresh();
        else{
            if(reflash){
                show("已到最后一页");
                reflash=false;
            }
        }
    }
}
