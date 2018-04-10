package com.example.transtion.my5th.AHomeActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import InternetUser.IndividualFrag.Zhongjiang;
import adapter.Individual.CustomFragPagerAdapter;
import adapter.Individual.ZhongjiangAdapter;
import customUI.PullToRefreshView;
import fifthutil.JumpUtil;
import fragclass.Choujiang;
import fragclass.Hongbao;
import httpConnection.Path;
import viewpagerindicator.UnderlinePageIndicator;

public class MessageActivity extends FragmentActivity implements View.OnClickListener{
ListView list;
    Zhongjiang user;
    ZhongjiangAdapter adapter;
    PullToRefreshView refreshView;
    int now=2;
    int tatol;
    boolean reflash=true;
    String type[]={"1","2"};
    String mtype;
    String path=Path.HOST+Path.ip+ Path.Zhognjiang_PATH;


    TextView chongzhi,duihuan;
    ViewPager pager;
    UnderlinePageIndicator indica;
    CustomFragPagerAdapter fragadapter;
    Fragment frag[]={new Choujiang(),new Hongbao()};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);
        initview();
        setListener();
    }

    public void initview() {
        chongzhi= (TextView) findViewById(R.id.mgwb_chongzhi);
        duihuan= (TextView) findViewById(R.id.mgwb_duihuan);
        pager= (ViewPager) findViewById(R.id.mgwb_pager);
        indica= (UnderlinePageIndicator) findViewById(R.id.mgwb_indicator);
        setpager();
    }

    public void setListener() {
        chongzhi.setOnClickListener(this);
        duihuan.setOnClickListener(this);

        indica.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {

                switch (arg0) {
                    case 0:
                        duihuan.setTextColor(getResources().getColor(R.color.main_color));
                        chongzhi.setTextColor(getResources().getColor(R.color.blackinwhite));
                        break;
                    case 1:
                        duihuan.setTextColor(getResources().getColor(R.color.blackinwhite));
                        chongzhi.setTextColor(getResources().getColor(R.color.main_color));
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
        switch (v.getId()) {
            case R.id.back:
                JumpUtil.jump2finash(this);
                break;
            case R.id.mgwb_duihuan:
                pager.setCurrentItem(0, true);
                break;
            case R.id.mgwb_chongzhi:
                pager.setCurrentItem(1, true);
                break;

            default:
                break;
        }
    }
}
