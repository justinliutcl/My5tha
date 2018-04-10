package com.example.transtion.my5th.AHomeActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transtion.my5th.R;

import InternetUser.O_other.PinPaiUser;
import adapter.afrag_other.PinpaigridGoodAdapter;
import customUI.CustomScrollView;
import fifthutil.ImageUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class PinpaiActivity extends Activity  {
    public static void launch(Context activity, View transitionView, String resId,String brandId) {
        Intent intent = new Intent(activity, PinpaiActivity.class);
        intent.putExtra("resId", resId);
        intent.putExtra("brandId", brandId);

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation((Activity)activity, transitionView, "adapter_jxdx_topimg");

        ActivityCompat.startActivity((Activity) activity, intent, options.toBundle());
    }



    int now=2;
    int tatol;
    LinearLayout blin,layout_mes;
    ImageUtil imageUtil;
    ShareUtil share;
    String path= Path.HOST+Path.ip+Path.TEXT_PINPAI_PATH;
    PinPaiUser user;
    PinpaigridGoodAdapter gadapter;
    String brandId;
    ImageView topimg,downimg,back,pincity,brand;
    GridView ggrid;
    TextView title,mes;
    boolean flage=true;
    boolean reflash=true;
    Animation alphaAnimation;
    CustomScrollView customScrollView;
    String ptype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinpai);
        setView();
        setListener();
    }

    private void setView() {
        topimg= (ImageView) findViewById(R.id.adapter_jxdx_topimg);
        imageUtil=new ImageUtil(this);
        imageUtil.displaywithoutanim(topimg, getIntent().getStringExtra("resId"));
        if(!getIntent().getStringExtra("resId").equals("1")){
            ViewCompat.setTransitionName(topimg, "adapter_jxdx_topimg");
            ptype="1";
        }else{
            ptype="2";
            topimg.setVisibility(View.GONE);
        }

        back= (ImageView) findViewById(R.id.back);
        brandId=getIntent().getStringExtra("brandId");
        share= ShareUtil.getInstanse(this);
        blin= (LinearLayout)findViewById(R.id.otherfrag_blin);
        customScrollView= (CustomScrollView) findViewById(R.id.customScrollview);
        customScrollView.setContext(this);
        customScrollView.setonBorderListener(new CustomScrollView.OnBorderListener() {
            @Override
            public void onBottom() {
                if (now <= tatol)
                    getRefresh();
                else {
                    if (reflash) {
                        Toast.makeText(PinpaiActivity.this, "已到最后一页", Toast.LENGTH_SHORT).show();
                        blin.setVisibility(View.VISIBLE);
                        reflash = false;
                    }
                }
            }
        });
        layout_mes= (LinearLayout)findViewById(R.id.pinpai_layout_mes);
        topimg= (ImageView) findViewById(R.id.adapter_jxdx_topimg);
        ggrid= (GridView) findViewById(R.id.pinpai_ggrid);
        title= (TextView) findViewById(R.id.pinpai_title);
        mes= (TextView) findViewById(R.id.pinpai_mes);
        downimg= (ImageView) findViewById(R.id.pinpai_down);
        pincity= (ImageView) findViewById(R.id.pinpai_city);
        brand= (ImageView) findViewById(R.id.pinpai_brand);
        getJson();
    }
    public void setListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        downimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flage) {
                    mes.setSingleLine(false);
                    mes.setText(user.getDescription());
                    downimg.setImageResource(R.drawable.button_up);
                    flage = false;
                } else {
                    mes.setSingleLine(true);
                    mes.setText(user.getDescription());
                    downimg.setImageResource(R.drawable.button_down);
                    flage = true;
                }
            }
        });
    }
    private void getJson() {
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID() + "&brandId=" + brandId+"&type="+ptype, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getPinPaiUser(str);
                tatol = Integer.parseInt(user.getPageCount());
                gadapter = new PinpaigridGoodAdapter(user.getList(), PinpaiActivity.this);

                ggrid.setAdapter(gadapter);
                intoView();
            }
        });

    }

    private void intoView() {
        if(getIntent().getStringExtra("resId").equals("1"))
             imageUtil.display(topimg, user.getBrandImage());
        title.setText(user.getBrandName());
        mes.setText(user.getDescription());
        imageUtil.display(pincity,user.getStateFlag());
        imageUtil.display(brand,user.getBrandLogo());
        setanim();
    }
    private void setanim() {
        alphaAnimation=new AlphaAnimation(0.1f,1.0f);
        alphaAnimation.setDuration(400);
        layout_mes.setVisibility(View.VISIBLE);
        layout_mes.startAnimation(alphaAnimation);
    }
    private void getRefresh(){
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID()+"&brandId="+brandId+"&type="+ptype+ "&PageIndex=" + now, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                PinPaiUser users = HttpConnectionUtil.getPinPaiUser(str);
                user.getList().addAll(users.getList());
                gadapter.setmlist(user.getList());
                gadapter.notifyDataSetChanged();
                now++;
                customScrollView.setFlage(true);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        new ImageUtil(this).clearband();
    }
}
