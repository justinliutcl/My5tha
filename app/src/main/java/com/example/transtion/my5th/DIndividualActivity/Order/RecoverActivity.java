package com.example.transtion.my5th.DIndividualActivity.Order;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.R;

import InternetUser.GoodsorderUser;
import adapter.Individual.AddressAdapter;
import adapter.order.RevyvleOrderAdapter;
import customUI.Loding.ProgressWheel;
import customUI.PullToRefreshView;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class RecoverActivity extends BaseActivity implements PullToRefreshView.OnFooterRefreshListener {
    PullToRefreshView refreshView;
    ListView list;
    int now=2;
    int tatol;
    int orderType=13;
    String path= Path.HOST+Path.ip+Path.ALLORDER_PATH;
    GoodsorderUser user;
    RevyvleOrderAdapter adapter;
    int i=0;
    ProgressWheel progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
        initView();
    }
    LinearLayout orderNone;
    @Override
    public void setListener() {
        refreshView.setOnFooterRefreshListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JumpUtil.jumpWithValue(RecoverActivity.this, OrderdetailsActivity.class, new String[]{"orderId", "type", "orderstate"}, new String[]{user.getList().get(position).getOrderNumber(), user.getList().get(position).getOrderStatusString(), user.getList().get(position).getOrderStatus()}, true);
            }
        });
    }

    private void initView() {
        refreshView= (PullToRefreshView) findViewById(R.id.recover_list);
        list= (ListView) findViewById(R.id.recover_listview);
        progress= (ProgressWheel)findViewById(R.id.progress);
        orderNone= (LinearLayout)findViewById(R.id.myorder_ordernone);
        refreshView.setEnablePullTorefresh(false);
        getJson();
        setListener();
    }



    private void getJson() {
        HttpConnectionUtil.getGetJsonWithProgressOnerror(this, path + "?MemberId=" + share.getMemberID() + "&orderType=" + orderType, progress, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                progress.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
                user = HttpConnectionUtil.getGoodsorderUser(str);
                adapter = new RevyvleOrderAdapter(user, RecoverActivity.this, loding, new AddressAdapter.OnadapterChangeCall() {
                    @Override
                    public void adapterChangeBack(int position) {
                        adapter.notifyDataSetChanged();
                    }
                });
                tatol = Integer.parseInt(user.getPageCount());
                list.setAdapter(adapter);
            }
        }, new HttpConnectionUtil.OnErrorJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                orderNone.setVisibility(View.VISIBLE);
            }
        });
    }

    private void refresh(){
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID()+"&orderType="+orderType+"&PageIndex="+now,null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                GoodsorderUser users = HttpConnectionUtil.getGoodsorderUser(str);
                user.getList().addAll(users.getList());
                adapter.setUser(user);
                adapter.notifyDataSetChanged();
                refreshView.onFooterRefreshComplete();
                now++;

            }
        });
    }
    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        if(now<=tatol)
            refresh();
        else{
            Toast.makeText(this, "已到最后一页", Toast.LENGTH_SHORT).show();
            refreshView.onFooterRefreshComplete();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
