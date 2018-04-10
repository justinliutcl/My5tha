package com.example.transtion.my5th.DIndividualActivity.Order;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import java.util.List;

import InternetUser.order.GoodsorderItem;
import adapter.Individual.AddressAdapter;
import adapter.order.SelectGoodsOrderAdapter;
import customUI.Loding.ProgressWheel;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class OrderselectActivity extends BaseActivity {
boolean flage=true;
    TextView search,title;
    EditText search_mes;
    ListView list;
    ImageView back;
    List<GoodsorderItem>mlist;
    SelectGoodsOrderAdapter adapter;
    String path= Path.HOST+Path.ip+Path.ORDERSELECT_PATH;
    ProgressWheel progress;
    LinearLayout orderNone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderselect);
        initView();
    }

    private void initView() {
        list=(ListView) findViewById(R.id.orderselect_listview);
        orderNone= (LinearLayout) findViewById(R.id.myorder_ordernone);
        back=(ImageView) findViewById(R.id.back);
        search=(TextView) findViewById(R.id.orderselect_search);
        search_mes=(EditText) findViewById(R.id.orderselect_edit);
        title=(TextView) findViewById(R.id.orderselect_title);
        progress= (ProgressWheel) findViewById(R.id.progress);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        search.setOnClickListener(this);
        search_mes.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                progress.setVisibility(View.VISIBLE);
                String mes = search_mes.getText().toString();
                HttpConnectionUtil.getGetJsonWithProgressOnerror(OrderselectActivity.this, path + "?MemberId=" + share.getMemberID() + "&KeyWord=" + mes, progress, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        orderNone.setVisibility(View.GONE);
                        progress.setVisibility(View.GONE);
                        mlist = HttpConnectionUtil.getSelectGoodsorderItem(str);
                        adapter = new SelectGoodsOrderAdapter(mlist, OrderselectActivity.this, loding, new AddressAdapter.OnadapterChangeCall() {
                            @Override
                            public void adapterChangeBack(int position) {
                                adapter.notifyDataSetChanged();
                            }
                        });
                        list.setAdapter(adapter);
                    }
                }, new HttpConnectionUtil.OnErrorJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        orderNone.setVisibility(View.VISIBLE);
                    }
                });

                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                JumpUtil.jump2finash(this);
                break;
            case R.id.orderselect_search:
                search();
                break;

            default:
                break;
        }
    }
    private void search() {
        if(flage){
            search.setBackgroundResource(R.color.white);
            search.setText("取消");
            title.setVisibility(View.GONE);
            search_mes.setVisibility(View.VISIBLE);
            flage=false;
        }else{
            search.setBackgroundResource(R.drawable.icon_searchblack);
            search.setText("");
            title.setVisibility(View.VISIBLE);
            search_mes.setVisibility(View.GONE);
            flage=true;
        }

    }
}
