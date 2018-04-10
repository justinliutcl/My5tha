package com.example.transtion.my5th.alipay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.transtion.my5th.BShopcar.GoodsorderActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.shopcar.DingdanUser;
import InternetUser.shopcar.GoodsOrderCoupon;
import adapter.order.CouponGoodAdapter;

public class SelectcouponActivity extends Activity {
    View view;
    List<GoodsOrderCoupon>list;
    ListView mlist;
    CouponGoodAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcoupon);
        mlist= (ListView) findViewById(R.id.coupon_listview);
        if(!DingdanUser.getInstance().getFendanUser().getGoodsOrderUser().getCouponsView().isEmpty()){
            list=DingdanUser.getInstance().getFendanUser().getGoodsOrderUser().getCouponsView();
            double money=getIntent().getDoubleExtra("money",0);
            adapter = new CouponGoodAdapter(list, SelectcouponActivity.this,money);
            mlist.setAdapter(adapter);
            setListener();
        }

    }

    public void setListener() {
        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectcouponActivity.this, GoodsorderActivity.class);
                intent.putExtra("money", list.get(position).getFaceValue());
                intent.putExtra("id", list.get(position).getCouponsId());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


}
