package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import adapter.order.MyFragmentPagerAdapter;
import fifthutil.JumpUtil;
import fragclass.order.Allorder;
import fragclass.order.WaitDeliver;
import fragclass.order.WaitEvaluate;
import fragclass.order.WaitPay;
import fragclass.order.WaitRecive;
import viewpagerindicator.PagerSlidingTabStrip;

public class DSAllActivity extends FragmentActivity implements View.OnClickListener{
    ViewPager pager;
    PagerSlidingTabStrip indica;

    MyFragmentPagerAdapter fragadapter;
    Fragment frag[]={new Allorder(),new WaitPay(),new WaitRecive(),new WaitDeliver(),new WaitEvaluate()};
    String title[]={"全部订单","代付款","待收货","待发货","待评价"};
    TextView all,waitpay,waitrecive,waitevaluate;
    ImageView back;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dsall);
        initview();
//        setListener();
    }

    private void initview() {
        back= (ImageView) findViewById(R.id.back);
        all= (TextView) findViewById(R.id.myorder_all);
        waitpay= (TextView) findViewById(R.id.myorder_waitpay);
        waitrecive= (TextView) findViewById(R.id.myorder_waitreceive);
        waitevaluate= (TextView) findViewById(R.id.myorder_waitevaluate);
        pager= (ViewPager) findViewById(R.id.myorder_pager);
        indica= (PagerSlidingTabStrip) findViewById(R.id.myorder_indicator);
        count=Integer.parseInt(getIntent().getStringExtra("count"));
        setpager();
        back.setOnClickListener(this);

    }
    private void setListener() {
        back.setOnClickListener(this);
        all.setOnClickListener(this);
        waitpay.setOnClickListener(this);
        waitrecive.setOnClickListener(this);
        waitevaluate.setOnClickListener(this);
        indica.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {

                switch (arg0) {
                    case 0:
                        all.setTextColor(getResources().getColor(R.color.main_color));
                        waitpay.setTextColor(getResources().getColor(R.color.blackinwhite));
                        waitrecive.setTextColor(getResources().getColor(R.color.blackinwhite));
                        waitevaluate.setTextColor(getResources().getColor(R.color.blackinwhite));
                        break;
                    case 1:
                        all.setTextColor(getResources().getColor(R.color.blackinwhite));
                        waitpay.setTextColor(getResources().getColor(R.color.main_color));
                        waitrecive.setTextColor(getResources().getColor(R.color.blackinwhite));
                        waitevaluate.setTextColor(getResources().getColor(R.color.blackinwhite));
                        break;
                    case 2:
                        all.setTextColor(getResources().getColor(R.color.blackinwhite));
                        waitpay.setTextColor(getResources().getColor(R.color.blackinwhite));
                        waitrecive.setTextColor(getResources().getColor(R.color.main_color));
                        waitevaluate.setTextColor(getResources().getColor(R.color.blackinwhite));
                        break;
                    case 3:
                        all.setTextColor(getResources().getColor(R.color.blackinwhite));
                        waitpay.setTextColor(getResources().getColor(R.color.blackinwhite));
                        waitrecive.setTextColor(getResources().getColor(R.color.blackinwhite));
                        waitevaluate.setTextColor(getResources().getColor(R.color.main_color));
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    public void setpager(){
        pager.setOffscreenPageLimit(1);
        fragadapter=new MyFragmentPagerAdapter(getSupportFragmentManager(), frag,title);
        pager.setAdapter(fragadapter);
        indica.setIndicatorColor(getResources().getColor(R.color.main_color));
        indica.setViewPager(pager);
        pager.setCurrentItem(count);
//        indica.setFades(false);
//        indica.setSelectedColor(getResources().getColor(R.color.main_color));

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myorder_all:
                pager.setCurrentItem(0, true);
                break;
            case R.id.myorder_waitpay:
                pager.setCurrentItem(1, true);
                break;
            case R.id.myorder_waitreceive:
                pager.setCurrentItem(2, true);
                break;
            case R.id.myorder_waitevaluate:
                pager.setCurrentItem(3, true);
                break;
            case R.id.back:
                JumpUtil.jump2finash(this);
                break;
            default:
                break;
        }
    }
}
