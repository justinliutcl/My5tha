package com.example.transtion.my5th.AHomeActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transtion.my5th.BShopcar.ShopcarActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.SignActivity;

import java.util.ArrayList;
import java.util.List;

import InternetUser.O_other.DetailItem;
import InternetUser.O_other.EvaluateItem;
import InternetUser.O_other.EvaluateUser;
import InternetUser.O_other.StardIdUser;
import InternetUser.O_other.TextUser;
import InternetUser.O_other.ValueItem;
import adapter.afrag_other.CanshuAdapter;
import adapter.afrag_other.EvaluatelistAdapter;
import adapter.afrag_other.OneImgAdapter;
import adapter.afrag_other.TuwenOneImgAdapter;
import customUI.CountdownView;
import customUI.ListViewForScrollView;
import customUI.PullToRefreshView;
import customUI.PullToRefreshView.OnFooterRefreshListener;
import customUI.PullforscrollToRefreshView;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;
import fifthutil.LodingUtil;
import fifthutil.TopPager;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class TextActivity extends Activity implements View.OnClickListener,OnFooterRefreshListener,
        PullToRefreshView.OnHeaderRefreshListener, PullforscrollToRefreshView.OnFooterRefreshListener, CountdownView.OnCountdownEndListener{

    public static void launch(Context activity, View transitionView, String resId,String objid,String type,String texttype) {
        Intent intent = new Intent(activity, TextActivity.class);
        intent.putExtra("resId", resId);
        intent.putExtra("objid", objid);
        intent.putExtra("type", type);
        intent.putExtra("texttype", texttype);

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation((Activity) activity, transitionView, "adapter_horgrid_img");

        ActivityCompat.startActivity((Activity) activity, intent, options.toBundle());
    }
    ImageUtil imageUtil;
    ShareUtil share;
    ViewPager viewPager;
    ViewGroup group;
    LinearLayout bottomlinear,headView,img,pingjia;
    ImageView back,imageView,cityimg,shopcarimg,collectimg,mAnimImageView;
    TextView goodtype,title,money,outmoney,brand,timetype,tuwen1,tuwenline1,tuwen2,tuwenline2,tuwen3,tuwenline3,showmoreEvaluate,shopcarnum;
    CountdownView time;
    Animation alphaAnimation;
    String path;
    String evaluatePath= Path.HOST+ Path.ip+ Path.TEXT_EVALUTE_PATH;
    String collectPath= Path.HOST+ Path.ip+ Path.TEXT_COLLECT_PATH;
    String add2carPath= Path.HOST+ Path.ip+ Path.TEXT_ADDSHOPCAR_PATH;
    String stardPath= Path.HOST+ Path.ip+ Path.TEXT_STARDID_PATH;
    String objid,type;
    Animation a;
    LinearLayout layout_time,layout_brand;
    TextUser user;
    Button addgoods;
    EvaluatelistAdapter adapter;
    ListViewForScrollView mlist;
    ListView tuwenlist;
    PullforscrollToRefreshView refreshListView;
    PullToRefreshView bottom_refreshListView;
    CanshuAdapter canshuAdapter;
    TuwenOneImgAdapter tuwenOneImgAdapter;
    EvaluatelistAdapter evaluateadapter;
    int now=2;
    int tatol;
    EvaluateUser evaluateUser;
    boolean flage=false;
    boolean isshow=true;
    int tab,cartnum;
   int i=1;
    PopupWindow mPopupWindow;
    String texttype;
    private Animation mAnimation;
    boolean shouldresume=false;
    public static TextActivity instance=null;
    private List<InternetUser.O_other.ValueItem>ValueItem=new ArrayList<InternetUser.O_other.ValueItem>();
    private LodingUtil loding;
    String[]goodtypesum;
    TextView goodprice;
    boolean isload=false;
    Button addshopcar;
    String imgId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        instance=this;
        loding=new LodingUtil(this);
        imageView = (ImageView) findViewById(R.id.adapter_horgrid_img);
        imageUtil=new ImageUtil(this);
        imgId=getIntent().getStringExtra("resId");
        if(!imgId.equals("h5")){
            imageUtil.displaywithoutanim(imageView, imgId);
            ViewCompat.setTransitionName(imageView, "adapter_horgrid_img");
        }

        a= AnimationUtils.loadAnimation(this, R.anim.big);
        objid=getIntent().getStringExtra("objid");
        type=getIntent().getStringExtra("type");
        texttype=getIntent().getStringExtra("texttype");
        share= ShareUtil.getInstanse(this);
        shopcarnum= (TextView) findViewById(R.id.text_shopcarnum);
        collectimg= (ImageView) findViewById(R.id.text_img_collect);
        if(texttype.equals("productId")){
            path= Path.HOST+ Path.ip+ Path.COLLECTTEXT_PATH;
        }else{
            path= Path.HOST+ Path.ip+ Path.TEXT_PATH;
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                initView();
            }
        }, 500);


    }
    private void initView(){
        group = (ViewGroup)findViewById(R.id.viewGroup);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        back= (ImageView) findViewById(R.id.back);
        cityimg= (ImageView) findViewById(R.id.text_img_city);
        shopcarimg= (ImageView) findViewById(R.id.text_img_shopcar);
        collectimg= (ImageView) findViewById(R.id.text_img_collect);
        mAnimImageView = (ImageView) findViewById(R.id.cart_anim_icon);

        goodtype= (TextView) findViewById(R.id.text_goodtype);
        title= (TextView) findViewById(R.id.text_goodtitle);
        money= (TextView) findViewById(R.id.text_goodprice);
        outmoney= (TextView) findViewById(R.id.text_outmoney);
        brand= (TextView) findViewById(R.id.text_brand);
        timetype= (TextView) findViewById(R.id.text_timetype);
        time= (CountdownView) findViewById(R.id.text_time);
        outmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tuwen1= (TextView) findViewById(R.id.text_tuwen1);
        tuwenline1= (TextView) findViewById(R.id.text_tuwenline1);
        tuwen2= (TextView) findViewById(R.id.text_tuwen2);
        tuwenline2= (TextView) findViewById(R.id.text_tuwenline2);
        tuwen3= (TextView) findViewById(R.id.text_tuwen3);
        tuwenline3= (TextView) findViewById(R.id.text_tuwenline3);
        showmoreEvaluate= (TextView) findViewById(R.id.text_showmoreevaluate);


        bottomlinear= (LinearLayout) findViewById(R.id.text_bottomlinearlayout);
        layout_time= (LinearLayout) findViewById(R.id.text_layout_time);
        headView= (LinearLayout) findViewById(R.id.text_headview);
        img=(LinearLayout) findViewById(R.id.text_layout_img);
        layout_brand=(LinearLayout) findViewById(R.id.text_layout_brand);
        pingjia= (LinearLayout) findViewById(R.id.text_layout_pingjia);
        addgoods= (Button) findViewById(R.id.text_addshopcar);
        addgoods.setEnabled(false);
        mlist= (ListViewForScrollView) findViewById(R.id.text_evaluatelist);
        tuwenlist= (ListView) findViewById(R.id.text_tuwenxiangqing);


        refreshListView= (PullforscrollToRefreshView) findViewById(R.id.text_pulllisttop);

        bottom_refreshListView= (PullToRefreshView) findViewById(R.id.text_pulllistbottom);
        refreshListView.setEnablePullTorefresh(false);
        refreshListView.getHeader().setVisibility(View.INVISIBLE);
        refreshListView.setfootText("下拉查看图文详情");
        bottom_refreshListView.setheaderChangeText(new String[]{"上拉查看商品详情", "松开显示商品详情"});
        bottom_refreshListView.setfooterChangeText(new String[]{"上拉加载更多评论", "松开后加载"});
        refreshListView.setfooterChangeText(new String[]{"上拉查看图文详情", "松开显示图文详情"});





        getJson();
        setlistener();
        setAnim();
    }


    private void setAnim() {
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.cart_anim);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mAnimImageView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setlistener() {
        refreshListView.setOnFooterRefreshListener(this);
        bottom_refreshListView.setOnFooterRefreshListener(this);
        bottom_refreshListView.setOnHeaderRefreshListener(this);
        layout_brand.setOnClickListener(this);
        tuwen1.setOnClickListener(this);
        tuwen2.setOnClickListener(this);
        tuwen3.setOnClickListener(this);
        collectimg.setOnClickListener(this);
        shopcarimg.setOnClickListener(this);
        showmoreEvaluate.setOnClickListener(this);
        addgoods.setOnClickListener(this);
        back.setOnClickListener(this);
        time.setOnCountdownEndListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(shouldresume)
            getsumJson();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shouldresume=true;
    }

    private void getsumJson() {
        HttpConnectionUtil.getGetJson(this, path + "?" + texttype + "=" + objid + "&productType=" + type + "&memberId=" + share.getMemberID(), null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getTextUser(str);

                cartnum = user.getCartNumber();
                shopcarnum.setText(cartnum + "");
                if (user.IsCollection()) {
                    collectimg.setImageResource(R.drawable.collect);
                    collectimg.setEnabled(flage);
                    collectimg.startAnimation(a);
                }
            }
        });
    }



    private void getJson() {
        HttpConnectionUtil.getGetJson(this, path + "?" + texttype + "=" + objid + "&productType=" + type + "&memberId=" + share.getMemberID(), null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getTextUser(str);
                addgoods.setEnabled(true);
                Log.i("text----", str);
                isload = true;
                setView();
                setanim();
                setdialog();
                canshuAdapter = new CanshuAdapter(user.getPropertyValueList(), TextActivity.this);
                for (DetailItem item : user.getDetail()) {
                    View v = View.inflate(TextActivity.this, R.layout.adapter_detial_tuwen, null);
                    ImageView imageView = (ImageView) v.findViewById(R.id.adapter_detial_img);
                    imageUtil.displayText(imageView, item.getImg());
                    img.addView(v);
                }


            }
        });
    }
    public void setgoodType(TextView goodselecttype){
        goodselecttype.setText("");
        for(int i=0;i<goodtypesum.length;i++){
            goodselecttype.append("“" + goodtypesum[i]+"” ");
        }
    }
    private void setdialog() {

        View view = View.inflate(this, R.layout.dialog_goodselect, null);
        LinearLayout top= (LinearLayout) view.findViewById(R.id.dialog_goodselect_layout_detail);
        final Button jian= (Button) view.findViewById(R.id.dialog_goodselect_jian);
        final Button jia= (Button) view.findViewById(R.id.dialog_goodselect_jia);
        addshopcar= (Button) view.findViewById(R.id.dialog_goodselect_addshopcar);
        final TextView sum= (TextView) view.findViewById(R.id.dialog_goodselect_sum);
        ImageView close= (ImageView) view.findViewById(R.id.dialog_goodselect_close);
        final ImageView goodimg= (ImageView) view.findViewById(R.id.dialog_goodselect_goodimg);
        imageUtil.display(goodimg,getIntent().getStringExtra("resId"));
         goodprice= (TextView) view.findViewById(R.id.dialog_goodselect_goodmoney);
        final TextView goodselecttype= (TextView) view.findViewById(R.id.dialog_goodselect_goodtype);
        goodprice.setText("￥"+user.getSellPrice());
        goodtypesum=new String[user.getDetailTatilAttributeList().size()];
        int num=Integer.parseInt(user.getStockNumber());
        addshopcar.setOnClickListener(this);
        if(num<1){
            addshopcar.setBackgroundResource(R.color.main_gry);
            addshopcar.setClickable(false);
            addshopcar.setText("已售完");
        }


        String a="";
        for(int i=0;i<user.getStandardDetails().size();i++){
            if(user.getStandardId().equals(user.getStandardDetails().get(i).getStandardId())){
                a+=user.getStandardDetails().get(i).getValueId();
            }
        }

        if(!user.getStandardDetails().isEmpty()) {
            for (int i = 0; i < user.getDetailTatilAttributeList().size(); i++) {
                View detial = View.inflate(this, R.layout.dialog_goodselect_item, null);
                TextView title = (TextView) detial.findViewById(R.id.dialog_goodselect_item_title);
                LinearLayout layout = (LinearLayout) detial.findViewById(R.id.dialog_goodselect_item_layout);
                InternetUser.O_other.ValueItem item = new ValueItem(user.getDetailTatilAttributeList().get(i).getValue().get(0).getValue(), user.getDetailTatilAttributeList().get(i).getValue().get(0).getId());
                ValueItem.add(item);


                for (int j = 0; j < user.getDetailTatilAttributeList().get(i).getValue().size(); j++) {

                    ((TextView) layout.getChildAt(j)).setText(user.getDetailTatilAttributeList().get(i).getValue().get(j).getValue());
                    layout.getChildAt(j).setVisibility(View.VISIBLE);
                    final int finalI = i;
                    final int finalJ = j;
                    if (a.contains(user.getDetailTatilAttributeList().get(finalI).getValue().get(finalJ).getId())) {
                        layout.getChildAt(j).setBackgroundResource(R.drawable.smsbuttonback);
                        ((TextView) layout.getChildAt(j)).setTextColor(getResources().getColor(R.color.white));
                        goodtypesum[i] = user.getDetailTatilAttributeList().get(finalI).getValue().get(finalJ).getValue();
                        ValueItem.get(finalI).setId(user.getDetailTatilAttributeList().get(finalI).getValue().get(finalJ).getId());
                    }

                    layout.getChildAt(j).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout l = ((LinearLayout) v.getParent());
                            for (int k = 0; k < l.getChildCount(); k++) {
                                l.getChildAt(k).setBackgroundResource(R.drawable.grayandwhite_oval);
                                ((TextView) l.getChildAt(k)).setTextColor(getResources().getColor(R.color.individualblack));
                            }
                            v.setBackgroundResource(R.drawable.smsbuttonback);
                            ((TextView) v).setTextColor(getResources().getColor(R.color.white));
                            String id = user.getDetailTatilAttributeList().get(finalI).getValue().get(finalJ).getId();
                            String goodhaveType = user.getDetailTatilAttributeList().get(finalI).getValue().get(finalJ).getValue();
                            String imgUrl = user.getDetailTatilAttributeList().get(finalI).getValue().get(finalJ).getImg();
                            if (imgUrl.length() > 15)
                                imageUtil.display(goodimg, imgUrl);
                            ValueItem.get(finalI).setId(id);
                            goodtypesum[finalI] = goodhaveType;
                            setgoodType(goodselecttype);
                            calculate();

                        }
                    });
                }

                title.setText(user.getDetailTatilAttributeList().get(i).getName());
                top.addView(detial);
            }
        }

        setgoodType(goodselecttype);
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(i>1){
                        i--;
                        sum.setText(i+"");
                        if(i==1) {
                            jian.setBackgroundResource(R.drawable.jian_no);
                            jia.setBackgroundResource(R.drawable.jia);
                            jia.setClickable(true);
                        }
                        if(i+3>Integer.parseInt(user.getLimitNumber())){
                            jia.setBackgroundResource(R.drawable.jia);
                            jia.setClickable(true);
                        }

                    }


            }
        });
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<=Integer.parseInt(user.getLimitNumber())) {
                    i++;
                    sum.setText(i + "");
                    if (i > 1) {
                        jian.setBackgroundResource(R.drawable.jian);


                    }
                    if (i >= Integer.parseInt(user.getLimitNumber())) {
                        jia.setBackgroundResource(R.drawable.jia_no);
                        jia.setClickable(false);
                    }

                }
            }
        });

        close.setOnClickListener(this);

        mPopupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.dialog_updown_anim);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopupWindow.setFocusable(true);
    }
    public void calculate(){
//        addshopcar.setBackgroundResource(R.drawable.buttonback_nooval);
//        addshopcar.setClickable(true);
//        addshopcar.setText("加入购物车");
        String a="";
        for(int j=0;j<user.getStandardDetails().size();j++){
            if(user.getStandardDetails().get(j).getValueId().equals(ValueItem.get(0).getId())){
                a+=user.getStandardDetails().get(j).getStandardId();
            }
        }

        for(int i=1;i<ValueItem.size();i++){
            boolean stardFlage=true;
            for(int j=0;j<user.getStandardDetails().size();j++){
                if(user.getStandardDetails().get(j).getValueId().equals(ValueItem.get(i).getId())){
                   if(a.contains(user.getStandardDetails().get(j).getStandardId())){
                       a=user.getStandardDetails().get(j).getStandardId();
                       stardFlage=false;
                   }
                }
            }
            if(stardFlage){
                addshopcar.setBackgroundResource(R.color.main_gry);
                addshopcar.setClickable(false);
                addshopcar.setText("已售完");
            }else {
                addshopcar.setBackgroundResource(R.drawable.buttonback_nooval);
                addshopcar.setClickable(true);
                addshopcar.setText("加入购物车");
                user.setStandardId(a);
                getStardId();
            }
        }

    }
    private void getevaluateUserJson() {
        HttpConnectionUtil.getGetJson(this, evaluatePath + "?objectId=" + user.getStandardId() + "&memberId=" + share.getMemberID(), null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                evaluateUser = HttpConnectionUtil.getEvaluateUser(str);
                Log.i("textttt", str);
                tatol = Integer.parseInt(evaluateUser.getPageCount());
                setPingjia();
                refreshListView.onFooterRefreshComplete();
                if (flage)
                    setcount(1);

            }
        });
    }
    private void getRefreshJson() {
        HttpConnectionUtil.getGetJson(this, evaluatePath + "?objectId=" + objid + "&memberId=" + share.getMemberID() + "&PageIndex=" + now, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                EvaluateUser u = HttpConnectionUtil.getEvaluateUser(str);
                Log.i("getRefreshJson", str);
                setrefishPingjia(u.getEvaluateList());
                bottom_refreshListView.onFooterRefreshComplete();
                now++;
            }
        });
    }


    private void setView() {
        ImageView []img=new ImageView[user.getStandardImagesList().size()];
        for(int i=0;i<user.getStandardImagesList().size();i++){
            ImageView imageView = new ImageView(this);
            img[i] = imageView;
            img[i].setScaleType(ImageView.ScaleType.FIT_XY);
            img[i].setTag(user.getStandardImagesList().get(i).getImg());

        }
        TopPager t= new TopPager(this,group,img,viewPager);

        adapter=new EvaluatelistAdapter(user.getEvaluateList(),this);
             mlist.setAdapter(adapter);

        imageUtil.display(cityimg, user.getFlag());
        goodtype.setText(user.getState()+"直供"+user.getArea()+"闪电发货");
        title.setText(user.getProductName());
        money.setText(user.getSellPrice());
        outmoney.setText("￥" + user.getMarketPrice());
        brand.setText(user.getBrand());

        if(user.isEventProduct()){

            if(user.getTimeSecond()>0){
                timetype.setText("距离开始还有:");
               time.start(user.getTimeSecond() * 1000);
                addgoods.setBackgroundResource(R.color.main_gry);
                addgoods.setEnabled(false);
            }else if(user.getTimeSecond()<0){
                timetype.setText("距离结束还有:");
                time.start(Math.abs(user.getTimeSecond())*1000);
            }else if(user.getTimeSecond()==0){
               timetype.setText("已结束");
                time.setVisibility(View.GONE);
                addgoods.setBackgroundResource(R.color.main_gry);
                addgoods.setEnabled(false);
            }
            layout_time.setVisibility(View.VISIBLE);
            time.start(user.getTimeSecond() * 1000);
        }else{
            timetype.setVisibility(View.GONE);
            time.setVisibility(View.GONE);
        }

        if(!user.isStandardStatus()){
            addgoods.setBackgroundResource(R.color.main_gry);
            addgoods.setEnabled(false);
            addgoods.setText("该商品已下架");
        }

        if(user.IsCollection()){
            collectimg.setImageResource(R.drawable.collect);
            collectimg.setEnabled(flage);
            collectimg.startAnimation(a);
        }
        cartnum=user.getCartNumber();
        shopcarnum.setText(cartnum+"");

    }

    private void setanim() {
         alphaAnimation=new AlphaAnimation(0.1f,1.0f);
        alphaAnimation.setDuration(400);
        bottomlinear.setVisibility(View.VISIBLE);
        bottomlinear.startAnimation(alphaAnimation);

    }


    public void setcount(int i){
        switch (i){
            case 0:
                tuwen1.setTextColor(getResources().getColor(R.color.main_color));
                tuwenline1.setBackgroundResource(R.color.main_color);
                tuwenline1.setVisibility(View.VISIBLE);

                tuwen2.setTextColor(getResources().getColor(R.color.individualblack));
                tuwenline2.setVisibility(View.GONE);

                tuwen3.setTextColor(getResources().getColor(R.color.individualblack));
                tuwenline3.setVisibility(View.GONE);
                tuwenlist.setVisibility(View.GONE);
                pingjia.setVisibility(View.GONE);
                img.setVisibility(View.VISIBLE);
                bottom_refreshListView.getmFooterView().setVisibility(View.INVISIBLE);
                break;
            case 1:
                tuwenlist.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                tuwen1.setTextColor(getResources().getColor(R.color.individualblack));
                tuwenline1.setVisibility(View.GONE);
                pingjia.setVisibility(View.VISIBLE);
                tuwen2.setTextColor(getResources().getColor(R.color.main_color));
                tuwenline2.setBackgroundResource(R.color.main_color);
                tuwenline2.setVisibility(View.VISIBLE);

                tuwen3.setTextColor(getResources().getColor(R.color.individualblack));
                tuwenline3.setVisibility(View.GONE);

                bottom_refreshListView.getmFooterView().setVisibility(View.VISIBLE);
                break;
            case 2:
                tuwenlist.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
                tuwen1.setTextColor(getResources().getColor(R.color.individualblack));
                tuwenline1.setVisibility(View.GONE);

                tuwen2.setTextColor(getResources().getColor(R.color.individualblack));
                tuwenline2.setVisibility(View.GONE);

                tuwen3.setTextColor(getResources().getColor(R.color.main_color));
                tuwenline3.setBackgroundResource(R.color.main_color);
                tuwenline3.setVisibility(View.VISIBLE);
                tuwenlist.setAdapter(canshuAdapter);
                pingjia.setVisibility(View.GONE);

                bottom_refreshListView.getmFooterView().setVisibility(View.INVISIBLE);
                break;

        }
        tab=i;
    }
    private void setbottomAdapter() {
        if(isshow){
            isshow=false;
            refreshListView.setVisibility(View.GONE);
            bottom_refreshListView.setVisibility(View.VISIBLE);

        }

    }
    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        switch (view.getId()){
            case R.id.text_pulllisttop:


                break;
            case R.id.text_pulllistbottom:
                if(tab!=1){
                    bottom_refreshListView.onFooterRefreshComplete();
                }else{
                    if(now<=tatol)
                        getRefreshJson();
                    else{
                        Toast.makeText(this,"已到最后一页",Toast.LENGTH_SHORT).show();
                        bottom_refreshListView.onFooterRefreshComplete();
                    }
                }
                break;
        }
    }
    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        switch (view.getId()){
            case R.id.text_pulllisttop:
                refreshListView.onHeaderRefreshComplete();
                break;
            case R.id.text_pulllistbottom:
                bottom_refreshListView.setVisibility(View.GONE);
                refreshListView.setVisibility(View.VISIBLE);
                      mlist.setVisibility(View.VISIBLE);

                bottom_refreshListView.onHeaderRefreshComplete();

                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_tuwen1:
                setcount(0);
                 break;
            case R.id.text_tuwen2:
                setcount(1);
                break;
            case R.id.text_tuwen3:
                setcount(2);
                break;
            case R.id.text_img_shopcar:
                if(share.getMemberID().length()<3){
                    Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show();

                }else{
                    JumpUtil.jump(this, ShopcarActivity.class, true);
                }


                break;
            case R.id.text_img_collect:
                if(user!=null){
                   if(!user.IsCollection()){
                       if(share.getMemberID().length()<3){

                           Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show();
                       }else{
                           collectimg.setImageResource(R.drawable.collect);
                           collectimg.startAnimation(a);
                           go2collect();
                       }

                   }
                }
                break;
            case R.id.text_showmoreevaluate:
                      mlist.setVisibility(View.GONE);
                refreshListView.setVisibility(View.GONE);
                bottom_refreshListView.setVisibility(View.VISIBLE);


                flage=true;
                setbottomAdapter();
                getevaluateUserJson();
                break;
            case R.id.dialog_goodselect_addshopcar:
                if(share.getMemberID().length()<3){{
                    Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show();
                    JumpUtil.jumpWithValue(this, SignActivity.class, new String[]{"type"}, new String[]{"text"}, true);
                }
            }else{
                add2shopcar();
                mAnimImageView.setVisibility(View.VISIBLE);
                mAnimImageView.startAnimation(mAnimation);
            }

                break;
            case R.id.dialog_goodselect_buy:

                break;
            case R.id.dialog_goodselect_close:
                mPopupWindow.dismiss();
                break;
            case R.id.text_addshopcar:
                mPopupWindow.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.back:
                onBackPressed();
                break;
            case R.id.text_layout_brand:
                JumpUtil.jumpWithValue(this, PinpaiActivity.class, new String[]{"resId", "brandId"}, new String[]{"1", user.getBrandId()}, true);
                break;

        }
    }


    private void go2collect() {

        HttpConnectionUtil.getJsonJsonwithDialog(this, collectPath, new String[]{"productId", "memberId"}, new String[]{user.getProductId(), share.getMemberID()}, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                Toast.makeText(TextActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                collectimg.setEnabled(flage);
            }
        });
    }

    private void getStardId(){
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, stardPath + "?standardId=" + user.getStandardId(), loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                StardIdUser u = HttpConnectionUtil.getStardIdUser(str);
                user.setLimitNumber(u.getStockNumber());
                goodprice.setText("￥" + u.getSellPrice());
                if (Integer.parseInt(user.getLimitNumber()) < 1) {
                    addshopcar.setBackgroundResource(R.color.main_gry);
                    addshopcar.setClickable(false);
                } else {
                    addshopcar.setBackgroundResource(R.drawable.buttonback_nooval);
                    addshopcar.setClickable(true);
                }
            }
        });
    }

    private void add2shopcar() {
        mPopupWindow.dismiss();
        HttpConnectionUtil.getJsonJsonwithDialog(this, add2carPath, new String[]{"productId", "StandardId", "DetailType", "Number", "Belong", "memberId", "EventDetailId", "LimitNum"},
                new String[]{user.getProductId(), user.getStandardId(), user.getDetailType(), i + "", user.getBlongTo(), share.getMemberID(), user.getDetailId(), user.getLimitNumber()}, null, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        Toast.makeText(TextActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        cartnum += i;
                        int limitnum = Integer.parseInt(user.getLimitNumber()) - i;
                        user.setLimitNumber(limitnum + "");
                        shopcarnum.setText(cartnum + "");
                    }
                });
    }

    @Override
    public void onFooterRefresh(PullforscrollToRefreshView view) {
        if(isload) {
            mlist.setVisibility(View.GONE);
            refreshListView.setVisibility(View.GONE);
            bottom_refreshListView.setVisibility(View.VISIBLE);
            flage = false;
            setcount(0);
            setbottomAdapter();
            getevaluateUserJson();
            if (user.getEvaluateList().isEmpty())
                refreshListView.onFooterRefreshComplete();
        }else{
            view.onFooterRefreshComplete();
        }
    }

    public void setPingjia(){
        pingjia.removeAllViews();
        List<EvaluateItem> mlist=evaluateUser.getEvaluateList();
        for(int i=0;i<mlist.size();i++){
            View  convertView=View.inflate(this, R.layout.adapter_goodsevaluate, null);
            TextView phone = (TextView) convertView.findViewById(R.id.adapter_goodsevaluate_phone);
            TextView text = (TextView) convertView.findViewById(R.id.adapter_goodsevaluate_text);
            LinearLayout starlayout= (LinearLayout) convertView.findViewById(R.id.adapter_goodsvaluate_layout_star);
            GridView grid = (GridView) convertView.findViewById(R.id.adapter_goodsevaluate_grid);
            phone.setText(mlist.get(i).getNickName());
            text.setText(mlist.get(i).getThreaText());
            int num= Integer.parseInt(mlist.get(i).getStarValue());
            for(int j=0;j<num;j++){
                starlayout.getChildAt(j).setBackgroundResource(R.drawable.star);
            }
            if( mlist.get(i).getImageList()!=null){
                List<DetailItem> list= mlist.get(i).getImageList();
                OneImgAdapter adapter=new OneImgAdapter(list,this);
                grid.setAdapter(adapter);
            }
            pingjia.addView(convertView);
        }


    }

    public void setrefishPingjia( List<EvaluateItem> mlist){
        for(int i=0;i<mlist.size();i++){
            View  convertView=View.inflate(this, R.layout.adapter_goodsevaluate, null);
            TextView phone = (TextView) convertView.findViewById(R.id.adapter_goodsevaluate_phone);
            TextView text = (TextView) convertView.findViewById(R.id.adapter_goodsevaluate_text);
            LinearLayout starlayout= (LinearLayout) convertView.findViewById(R.id.adapter_goodsvaluate_layout_star);
            GridView grid = (GridView) convertView.findViewById(R.id.adapter_goodsevaluate_grid);
            phone.setText(mlist.get(i).getNickName());
            text.setText(mlist.get(i).getThreaText());
            int num= Integer.parseInt(mlist.get(i).getStarValue());
            for(int j=0;j<num;j++){
                starlayout.getChildAt(j).setBackgroundResource(R.drawable.star);
            }
            if( mlist.get(i).getImageList()!=null){
                List<DetailItem> list= mlist.get(i).getImageList();
                OneImgAdapter adapter=new OneImgAdapter(list,this);
                grid.setAdapter(adapter);
            }
            pingjia.addView(convertView);
        }


    }

    @Override
    public void onEnd(CountdownView cv) {
        if(user.getTimeSecond()>0){

        }
        timetype.setText("已结束");
        addgoods.setBackgroundResource(R.color.main_gry);
        addgoods.setEnabled(false);
    }
}
