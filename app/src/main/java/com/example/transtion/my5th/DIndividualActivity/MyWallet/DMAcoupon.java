package com.example.transtion.my5th.DIndividualActivity.MyWallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import adapter.Individual.CustomFragPagerAdapter;
import fragclass.Chuse;
import fragclass.Cnuse;
import fragclass.Cpastuse;
import viewpagerindicator.UnderlinePageIndicator;

public class DMAcoupon extends FragmentActivity implements View.OnClickListener{
    ViewPager pager;
    UnderlinePageIndicator indica;
    CustomFragPagerAdapter fragadapter;
    Fragment frag[]={new Cnuse(),new Chuse(),new Cpastuse()};
    TextView nuse,huse,pastuse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dmacoupon);
        initview();
        setListener();
    }


    private void initview() {
        nuse= (TextView) findViewById(R.id.coupon_nuse);
        huse= (TextView) findViewById(R.id.coupon_huse);
        pastuse= (TextView) findViewById(R.id.coupon_pastuse);
        pager= (ViewPager) findViewById(R.id.coupon_pager);
        indica= (UnderlinePageIndicator) findViewById(R.id.coupon_indicator);
        setpager();
    }
    private void setListener() {
        nuse.setOnClickListener(this);
        huse.setOnClickListener(this);
        pastuse.setOnClickListener(this);
        indica.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {

                switch (arg0) {
                    case 0:
                        nuse.setTextColor(getResources().getColor(R.color.main_color));
                        huse.setTextColor(getResources().getColor(R.color.blackinwhite));
                        pastuse.setTextColor(getResources().getColor(R.color.blackinwhite));
                        break;
                    case 1:
                        nuse.setTextColor(getResources().getColor(R.color.blackinwhite));
                        huse.setTextColor(getResources().getColor(R.color.main_color));
                        pastuse.setTextColor(getResources().getColor(R.color.blackinwhite));
                        break;
                    case 2:
                        nuse.setTextColor(getResources().getColor(R.color.blackinwhite));
                        huse.setTextColor(getResources().getColor(R.color.blackinwhite));
                        pastuse.setTextColor(getResources().getColor(R.color.main_color));
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
        fragadapter=new CustomFragPagerAdapter(getSupportFragmentManager(), frag);
        pager.setAdapter(fragadapter);
        indica.setViewPager(pager);
        indica.setFades(false);
        indica.setSelectedColor(getResources().getColor(R.color.main_color));

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.coupon_nuse:
                pager.setCurrentItem(0, true);
                break;
            case R.id.coupon_huse:
                pager.setCurrentItem(1, true);
                break;
            case R.id.coupon_pastuse:
                pager.setCurrentItem(2, true);
                break;
            case R.id.back:
                break;
            default:
                break;
        }
    }
}
