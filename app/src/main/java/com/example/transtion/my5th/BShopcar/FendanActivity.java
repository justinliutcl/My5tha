package com.example.transtion.my5th.BShopcar;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import java.util.List;

import InternetUser.shopcar.DingdanUser;
import InternetUser.shopcar.FendanItem;
import InternetUser.shopcar.FendanUser;
import adapter.bfrag_shop.FendanAdapter;

public class FendanActivity extends BaseActivity {
    DingdanUser dingdanUser;
    FendanUser user;
    List<FendanItem>list;
    TextView money;
    ListView mlist;
    FendanAdapter adapter;
    public static FendanActivity instance = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fendan);
        instance=this;
        initView();
    }

    private void initView() {
        dingdanUser= DingdanUser.getInstance();
        user=dingdanUser.getFendanUser();
        list=user.getList();
        money= (TextView) findViewById(R.id.fendan_money);
        money.setText(getIntent().getStringExtra("moneysum"));
        mlist= (ListView) findViewById(R.id.fendan_list);
        adapter=new FendanAdapter(this,list);
        mlist.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
