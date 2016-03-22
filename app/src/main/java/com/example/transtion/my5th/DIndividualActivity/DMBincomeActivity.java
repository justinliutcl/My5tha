package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import InternetUser.Communicationuser;
import adapter.CommunicationAdapter;
import customUI.PullToRefreshView;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class DMBincomeActivity extends BaseActivity implements PullToRefreshView.OnFooterRefreshListener{
ImageView back;
    TextView screen;
    ListView mlist;
    PullToRefreshView refreshListView;
    int now=1;
    int tatol;
    ShareUtil share;
    Communicationuser user;
    String path= Path.HOST+Path.ip+Path.INCOME_PATH;
    CommunicationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmbincome);
        initview();
    }

    public void initview() {
        screen= (TextView) findViewById(R.id.godIncome_screen);
        mlist= (ListView) findViewById(R.id.godIncome_listview);
        back= (ImageView) findViewById(R.id.back);
        refreshListView= (PullToRefreshView) findViewById(R.id.godIncome_list);
        refreshListView.setEnablePullTorefresh(false);
        share=ShareUtil.getInstanse(this);
        getJson();
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID(),loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getCommunicationuserUser(str);
                tatol = Integer.parseInt(user.getPageCount());
                adapter = new CommunicationAdapter(user, DMBincomeActivity.this);
                mlist.setAdapter(adapter);
                loding.disShapeLoding();
            }
        });
    }
    private void getRefresh(){
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID()+"&PageIndex="+now,null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                Communicationuser users = HttpConnectionUtil.getCommunicationuserUser(str);
                user.getList().addAll(users.getList());
                adapter.setUser(user);
                adapter.notifyDataSetChanged();
                refreshListView.onFooterRefreshComplete();
                now++;
            }
        });
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        screen.setOnClickListener(this);
        refreshListView.setOnFooterRefreshListener(this);
        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.godIncome_screen:
                    break;
                case R.id.back:
                    JumpUtil.jump2finash(this);
                    break;
                default:
                    break;

            }
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
