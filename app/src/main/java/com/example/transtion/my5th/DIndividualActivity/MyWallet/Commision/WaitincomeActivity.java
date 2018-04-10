package com.example.transtion.my5th.DIndividualActivity.MyWallet.Commision;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import InternetUser.IndividualUser.WaitincomeUser;
import adapter.Individual.WaitincomeAdapter;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class WaitincomeActivity extends BaseActivity {
    int now=2;
    int tatol;
    RecyclerView mrecyclerView;
    boolean isCompleteflage=true;
    boolean reflash=true;
    String path= Path.HOST+ Path.ip+ Path.WAITINCOME_PATH;
    WaitincomeUser user;
    WaitincomeAdapter adapter;
    int pageSize=20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitincome);
        initView();
    }

    private void initView() {
        mrecyclerView= (RecyclerView) findViewById(R.id.waitincome_recycle);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getJson();
    }

    @Override
    public void setListener() {
        mrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (isCompleteflage) {
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int nowshow = manager.findLastVisibleItemPosition();
                    int total = manager.getItemCount();
                    if (nowshow > (total - 15)) {
                        isCompleteflage = false;

                        onBottom();
                    }
                }
            }
        });
    }
    public void onBottom() {
        if (now <= tatol)
            getRefresh();
        else {
            if (reflash) {
                       Toast.makeText(WaitincomeActivity.this, "已到最后一页", Toast.LENGTH_SHORT).show();
                reflash = false;
            }
        }
    }
    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(WaitincomeActivity.this, path + "?MemberId=" + share.getMemberID()  + "&pageSize=" + pageSize, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                user = HttpConnectionUtil.getWaitincomeUser(str);
                adapter=new WaitincomeAdapter(user.getList(),WaitincomeActivity.this);
                tatol = Integer.parseInt(user.getPageCount());
                mrecyclerView.setAdapter(adapter);

            }
        });
    }
    private void getRefresh(){
        HttpConnectionUtil.getGetJson(WaitincomeActivity.this, path + "?MemberId=" + share.getMemberID()  + "&PageIndex=" + now + "&pageSize=" + pageSize, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                WaitincomeUser users = HttpConnectionUtil.getWaitincomeUser(str);
                user.getList().addAll(users.getList());
                adapter.notifyDataSetChanged();
                now++;
                isCompleteflage = true;
            }
        });
    }
    @Override
    public void onClick(View v) {

    }
}
