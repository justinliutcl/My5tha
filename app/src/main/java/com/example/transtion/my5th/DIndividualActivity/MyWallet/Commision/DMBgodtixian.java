package com.example.transtion.my5th.DIndividualActivity.MyWallet.Commision;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import adapter.Individual.CustomFragPagerAdapter;
import fifthutil.JumpUtil;
import fragclass.TAlipay;
import fragclass.TBank;
import viewpagerindicator.UnderlinePageIndicator;

public class DMBgodtixian extends FragmentActivity implements View.OnClickListener{
    ViewPager pager;
    UnderlinePageIndicator indica;
    CustomFragPagerAdapter fragadapter;
    Fragment frag[]={new TAlipay(),new TBank()};
    TextView alipay,bank,his;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dmbgodtixian);
        initview();
        setListener();
    }

    private void initview() {
        alipay= (TextView) findViewById(R.id.yjtx_alipay);
        bank= (TextView) findViewById(R.id.yjtx_bank);
        his= (TextView) findViewById(R.id.yjtx_txhis);
        pager= (ViewPager) findViewById(R.id.yjtx_pager);
        indica= (UnderlinePageIndicator) findViewById(R.id.yjtx_indicator);
        back= (ImageView) findViewById(R.id.back);
        setpager();
    }
    private void setListener() {
        alipay.setOnClickListener(this);
        bank.setOnClickListener(this);
        his.setOnClickListener(this);
        back.setOnClickListener(this);
        indica.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {

                switch (arg0) {
                    case 0:
                        alipay.setTextColor(getResources().getColor(R.color.main_color));
                        bank.setTextColor(getResources().getColor(R.color.blackinwhite));
                        break;
                    case 1:
                        alipay.setTextColor(getResources().getColor(R.color.blackinwhite));
                        bank.setTextColor(getResources().getColor(R.color.main_color));
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
            case R.id.yjtx_alipay:
                pager.setCurrentItem(0, true);
                break;
            case R.id.yjtx_bank:
                pager.setCurrentItem(1, true);
                break;
            case R.id.yjtx_txhis:
                JumpUtil.jump(this, DMCgodhisActivity.class, true);
                break;
            case R.id.back:
                JumpUtil.jump2finash(this);
                break;
            default:
                break;
        }
    }
}
