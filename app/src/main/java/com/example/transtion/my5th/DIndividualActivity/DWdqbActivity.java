package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import fifthutil.JumpUtil;

public class DWdqbActivity extends BaseActivity {
    TextView mgwb;
    String gwb,god,travel;
    LinearLayout myGwb,myCommission,travelgod,myCoupon,payhis;
    Button paycard,payonline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dwdqb);
        initview();
    }
    public void initview() {
        mgwb= (TextView) findViewById(R.id.wdqb_gwb);
        myGwb= (LinearLayout) findViewById(R.id.wdqb_layout_gwb);
        myCommission= (LinearLayout) findViewById(R.id.wdqb_layout_commission);
        travelgod= (LinearLayout) findViewById(R.id.wdqb_layout_travel);
        myCoupon= (LinearLayout) findViewById(R.id.wdqb_layout_coupon);
        payhis= (LinearLayout) findViewById(R.id.wdqb_layout_payhistory);
        paycard= (Button) findViewById(R.id.wdqb_btn_paycard);
        payonline= (Button) findViewById(R.id.wdqb_btn_payline);
        gwb=getIntent().getStringExtra("gwb");
        god=getIntent().getStringExtra("god");
        travel=getIntent().getStringExtra("travel");
        mgwb.setText(gwb);
    }

    @Override
    public void setListener() {
        myGwb.setOnClickListener(this);
        myCommission.setOnClickListener(this);
        travelgod.setOnClickListener(this);
        myCoupon.setOnClickListener(this);
        paycard.setOnClickListener(this);
        payonline.setOnClickListener(this);
        payhis.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wdqb_layout_gwb:
                JumpUtil.jumpWithValue(this,DMAgwbActivity.class,new String[]{"gwb"},new String[]{gwb},true);
                 break;
            case R.id.wdqb_layout_commission:
                JumpUtil.jumpWithValue(this,DMAgodActivity.class,new String[]{"god"},new String[]{god},true);
                break;
            case R.id.wdqb_layout_travel:
                JumpUtil.jumpWithValue(this,DMAtravelActivity.class,new String[]{"travel"},new String[]{travel},true);
                break;
            case R.id.wdqb_layout_coupon:
                JumpUtil.jump(this, DMAcoupon.class, true);
                break;
            case R.id.wdqb_layout_payhistory:
                JumpUtil.jump(this, DMAinouthis.class, true);
                break;
            case R.id.wdqb_btn_paycard:
                JumpUtil.jump(this,DMBrechargeActivity.class,true);
                break;
            case R.id.wdqb_btn_payline:
                JumpUtil.jump(this,ChonglineActivity.class,true);
                break;
            default:
                break;
        }
    }
}
