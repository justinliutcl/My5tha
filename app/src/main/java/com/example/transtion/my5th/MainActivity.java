package com.example.transtion.my5th;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import fifthutil.JumpUtil;
import fragment.AHomefrag;
import fragment.BShopfrag;
import fragment.CCommunityfrag;
import fragment.DIndividualfrag;
import sharedPreferencesUtil.ShareUtil;

public class MainActivity extends FragmentActivity {
    boolean flage=true;
    ShareUtil share;
    FragmentTabHost tabHost;
    Handler hand=new Handler(){
        public void handleMessage(android.os.Message msg) {
            flage=true;
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initview();
    }
    public void initview() {
        share=ShareUtil.getInstanse(this);
        tabHost=(FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(MainActivity.this, getSupportFragmentManager(), R.id.main_view);
        tabHost.addTab(setSpec(tabHost.newTabSpec("首页"), R.drawable.a_shouye), AHomefrag.class, null);
        tabHost.addTab(setSpec(tabHost.newTabSpec("购物袋"),  R.drawable.b_shop), BShopfrag.class, null);
        tabHost.addTab(setSpec(tabHost.newTabSpec("社区"), R.drawable.c_communition), CCommunityfrag.class, null);
        tabHost.addTab(setSpec(tabHost.newTabSpec("我的"), R.drawable.d_individual), DIndividualfrag.class, null);
        tabHost.getTabWidget().setDividerDrawable(null);
        if(share.getMemberID().length()<2){
            tabHost.getTabWidget().getChildTabViewAt(3).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    JumpUtil.jump2hdown(MainActivity.this, LoginActivity.class, true);
                }
            });
            tabHost.getTabWidget().getChildTabViewAt(1).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    JumpUtil.jump2hdown(MainActivity.this,LoginActivity.class, true);
                }
            });
        }

    }


    public TabHost.TabSpec setSpec(TabHost.TabSpec tabSpec,int res){
        View view=View.inflate(MainActivity.this, R.layout.tabhost_item, null);
        TextView text= (TextView) view.findViewById(R.id.tab_text);
        ImageView img=(ImageView) view.findViewById(R.id.tab_img);
        img.setBackgroundResource(res);
        text.setText(tabSpec.getTag().toString());
        return tabSpec.setIndicator(view);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return true;
        }else
            return super.onKeyDown(keyCode, event);
    }

    public void exit(){
        if(flage){
            flage=false;
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            hand.sendEmptyMessageDelayed(0, 2000);
        }else{
            finish();
        }
    }
}
