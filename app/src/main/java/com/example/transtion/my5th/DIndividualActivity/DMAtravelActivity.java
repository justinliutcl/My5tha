package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import InternetUser.TravelUser;
import adapter.TravelAdapter;
import customUI.PullToRefreshView;
import fifthutil.UpProgress;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class DMAtravelActivity extends BaseActivity implements PullToRefreshView.OnFooterRefreshListener{

    String path= Path.HOST+Path.ip+Path.TRAVEL_PATH;

    TextView havepoint,haichapoint;
    ProgressBar bar;

    UpProgress upProgress;
    TravelUser user;
    TravelAdapter adapter;
    ListView mlist;
    ShareUtil share;
    PullToRefreshView refreshListView;
    int now=1;
    int tatol;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmatravel);
        initview();
    }

    public void initview() {
        havepoint= (TextView) findViewById(R.id.travel_havepoint);
        haichapoint= (TextView) findViewById(R.id.travel_haichapoint);
        bar= (ProgressBar) findViewById(R.id.travel_prograssbar);
        refreshListView= (PullToRefreshView) findViewById(R.id.travel_list);
        mlist= (ListView) findViewById(R.id.travel_listview);
        havepoint.setText(getIntent().getStringExtra("travel"));
        haichapoint.setText(16000-Integer.parseInt(getIntent().getStringExtra("travel"))+"");
        refreshListView.setEnablePullTorefresh(false);
        upProgress=new UpProgress(bar,16000,Integer.parseInt(getIntent().getStringExtra("travel")),havepoint);
        share=ShareUtil.getInstanse(this);
        upProgress.go();
        getJson();
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID(), loding,new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getTravelUser(str);
                tatol=Integer.parseInt(user.getPageCount());
                adapter = new TravelAdapter(user, DMAtravelActivity.this);
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
                TravelUser users = HttpConnectionUtil.getTravelUser(str);
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
    private void setLastUpdateTime() {
        String text = formatDateTime(System.currentTimeMillis());
//        refreshListView.setLastUpdatedLabel(text);
    }
    private String formatDateTime(long time) {
        if (0 == time) {
            return "";
        }
        return mDateFormat.format(new Date(time));
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
