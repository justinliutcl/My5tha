package com.example.transtion.my5th.AHomeActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import InternetUser.A_Home.BanderDetailsUser;
import InternetUser.A_Home.ThirdTypesItem;
import adapter.afrag_other.HorTwoRecycleAdapter;
import adapter.afrag_other.OgridGoodAdapter;
import adapter.afrag_other.SelectResultItemRecycAdapter;
import customUI.CustomScrollView;
import customUI.PullToRefreshView;
import fifthutil.ImageUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class BanderdetailsDActivity extends BaseActivity  implements PullToRefreshView.OnFooterRefreshListener, PullToRefreshView.OnHeaderRefreshListener {
    int now=2;
    int tatol;
    String path= Path.HOST+Path.ip+Path.BANDERDETAILS_DETAILS_PATH;
    String selectpath= Path.HOST+Path.ip+Path.BANDERDETAILS_DETAILS_PATH;
    LinearLayout blin;
    OgridGoodAdapter gadapter;
    HorTwoRecycleAdapter twoadapter;
    SelectResultItemRecycAdapter onehoradapter;
    ImageView topimg;
    ImageUtil imageUtil;
    GridView ggrid;
    PopupWindow mPopupWindow;
    RecyclerView bandrecyclerView,typerecyclerView;
    TextView paixu;
    String typecode;
    BanderDetailsUser user;
    boolean reflash=true;
    int paixu_post=0;
    int type_post=0;
    CustomScrollView customScrollView;
    String  sortFiled="1";
    String sortType="1";
    boolean flage=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banderdetails_d);
        initView();
    }

    private void initView() {
        ImageUtil.clearlist();
        imageUtil=new ImageUtil(this);
        ggrid= (GridView) findViewById(R.id.banderdetails_grid_ggrid);
        topimg= (ImageView) findViewById(R.id.banderdetails_iv_top);
        bandrecyclerView= (RecyclerView) findViewById(R.id.banderdetails_recycle_bander);
        typerecyclerView= (RecyclerView) findViewById(R.id.banderdetails_recycle_type);
        bandrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        typerecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        blin= (LinearLayout) findViewById(R.id.banderdetails_layout_blin);
        paixu= (TextView) findViewById(R.id.banderdetails_paixu);
        typecode=getIntent().getStringExtra("typecode");
        customScrollView= (CustomScrollView) findViewById(R.id.customScrollview);
        customScrollView.setContext(this);
        customScrollView.setonBorderListener(new CustomScrollView.OnBorderListener() {
            @Override
            public void onBottom() {
                if(now<=tatol)
                    getRefresh();
                else{
                    if(reflash){
//                        Toast.makeText(BanderdetailsDActivity.this,"已到最后一页",Toast.LENGTH_SHORT).show();
                        blin.setVisibility(View.VISIBLE);
                        reflash=false;
                    }
                }
            }
        });
        getJson(path);
        setDialog();
    }

    @Override
    public void setListener() {
        paixu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banderdetails_paixu:
                mPopupWindow.showAsDropDown(paixu, 0,2);
                break;
        }
    }
    private void getJson(String path) {
        loding.showShapeLoding();
        ImageUtil.clearlist();
        blin.setVisibility(View.GONE);
        reflash=true;
        HttpConnectionUtil.getGetJson(this, path + "?typeCode=" + typecode+"&isAll="+"1"+"&sortFiled="+sortFiled+"&sortType="+sortType, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                if(flage){
                    user = HttpConnectionUtil.getBanderDetailsUser(str);
                }else{
                    BanderDetailsUser muser=HttpConnectionUtil.getBanderDetailsUser(str);
                    user.setList(muser.getList());
                }

                tatol = Integer.parseInt(user.getPageCount());
                now=2;
                customScrollView.setFlage(true);
                gadapter = new OgridGoodAdapter(user.getList(), BanderdetailsDActivity.this);
                if(flage){
                    twoadapter=new HorTwoRecycleAdapter(BanderdetailsDActivity.this,user.getBrandViewList());
                    user.getThirdTypes().add(0,new ThirdTypesItem(typecode,"全部","",true));
                    onehoradapter=new SelectResultItemRecycAdapter(BanderdetailsDActivity.this,user.getThirdTypes()) {
                        @Override
                        protected void onClickItem(View v, int position) {
                            if(type_post!=position){
                                onehoradapter.getHorType().get(position).setFlage(true);
                                onehoradapter.getHorType().get(type_post).setFlage(false);
                                onehoradapter.notifyDataSetChanged();
                                typecode=user.getThirdTypes().get(position).getTypeId();
                                type_post=position;
                                typerecyclerView.smoothScrollToPosition(position);
                                getJson(selectpath);
                            }
                        }
                    };
                    if(user.getAdvertisementView()!=null){
                        topimg.setVisibility(View.VISIBLE);
                        imageUtil.display(topimg, user.getAdvertisementView().getImgSrc());

                    }

                    bandrecyclerView.setAdapter(twoadapter);
                    typerecyclerView.setAdapter(onehoradapter);
                    flage=false;
                }
                ggrid.setAdapter(gadapter);

            }
        });

    }
    private void getJson2refresh() {
        HttpConnectionUtil.getGetJson(this, path + "?typeCode=" + typecode+"&isAll="+"1"+"&pageIndex="+now, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getBanderDetailsUser(str);
                tatol = Integer.parseInt(user.getPageCount());
                now++;
                blin.setVisibility(View.GONE);
                reflash=true;
                user.getThirdTypes().add(0,new ThirdTypesItem("","全部","",true));
                if(user.getAdvertisementView()!=null)
                    imageUtil.display(topimg, user.getAdvertisementView().getImgSrc());
                gadapter.setmlist(user.getList());
                onehoradapter.setHorType(user.getThirdTypes());
                twoadapter.setList(user.getBrandViewList());
                gadapter.notifyDataSetChanged();
                onehoradapter.notifyDataSetChanged();
                twoadapter.notifyDataSetChanged();
                customScrollView.setFlage(true);

            }
        });

    }
    private void getRefresh(){
        HttpConnectionUtil.getGetJson(this, path + "?typeCode="+typecode +"&isAll="+"0"+"&sortFiled="+sortFiled+"&sortType="+sortType+ "&PageIndex=" + now, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                BanderDetailsUser users = HttpConnectionUtil.getBanderDetailsUser(str);
                user.getList().addAll(users.getList());
                gadapter.setmlist(user.getList());
                gadapter.notifyDataSetChanged();
                now++;
                customScrollView.setFlage(true);
            }
        });

    }
    @Override
    public void onFooterRefresh(PullToRefreshView view) {
    }
    public void setDialog(){
        View view=View.inflate(this,R.layout.pop_paixu,null);
        final LinearLayout layout= (LinearLayout) view.findViewById(R.id.pop_paixu_layout_all);
        for(int i=0;i<layout.getChildCount();i++){
            final int finalI = i;
            layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (finalI != paixu_post) {
                        LinearLayout l = (LinearLayout) ((LinearLayout) layout.getChildAt(finalI)).getChildAt(0);
                        ((TextView) l.getChildAt(0)).setTextColor(getResources().getColor(R.color.main_color));
                        l.getChildAt(1).setBackgroundResource(R.drawable.icon_sure);
                        LinearLayout l2 = (LinearLayout) ((LinearLayout) layout.getChildAt(paixu_post)).getChildAt(0);
                        ((TextView) l2.getChildAt(0)).setTextColor(getResources().getColor(R.color.individualblack));
                        l2.getChildAt(1).setBackgroundColor(0xffffffff);

                        paixu_post = finalI;
                        mPopupWindow.dismiss();
                        switch (finalI) {
                            case 0:
                                sortFiled="1";
                                 sortType="1";
                                break;
                            case 1:
                                sortFiled="3";
                                sortType="0";
                                break;
                            case 2:
                                sortFiled="3";
                                sortType="1";
                                break;
                            case 3:
                                sortFiled="2";
                                sortType="0";
                                break;
                            case 4:
                                sortFiled="2";
                                sortType="1";
                                break;
                        }

                        getJson(selectpath);
                    }
                }
            });
        }


        mPopupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopupWindow.setFocusable(true);
    }
    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        getJson2refresh();
    }
}
