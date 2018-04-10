package com.example.transtion.my5th.AHomeActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import InternetUser.A_Home.SelectClassBrandsItem;
import InternetUser.A_Home.SelectClassUser;
import InternetUser.A_Home.SelectMesItem;
import InternetUser.O_other.OtherGoodUser;
import adapter.afrag_home.SelectBrandItemAdapter;
import adapter.afrag_home.SelectResultItemRecycAdapter;
import adapter.afrag_home.SelectTypeItemAdapter;
import adapter.afrag_other.OgridGoodAdapter;
import customUI.CustomScrollView;
import customUI.TopbarView;
import fifthutil.ImageUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class SelectresultActivity extends BaseActivity {
    TopbarView topbar;
    TextView moren,xiaoliang,price,close;
    LinearLayout layout_price,layout_xiaoliang,layout_screen,layout_top,blin,layout_error;
    ImageView img_price,img_xiaoliang;
    GridView grid;
    OgridGoodAdapter gadapter;
    SelectClassUser user;
    String typeCode,parentTypeCode;
    String brandId="";
    String sortFiled="1";
    String sortDir="1";
    String path= Path.HOST+Path.ip+Path.CLASSSELECT_PATH;
    int now=2;
    int tatol;
   int flage=1;
    boolean pflage=true;
    boolean xflage=true;
    boolean dialogFlage=true;
    boolean reflash=true;
    CustomScrollView customScrollView;
    PopupWindow mPopupWindow;
    RecyclerView recyclerView;
    List<SelectMesItem>horType=new ArrayList<>();
    List<SelectClassBrandsItem>horbrand=new ArrayList<>();

    List<SelectMesItem>selecthorType=new ArrayList<>();
    List<SelectClassBrandsItem>selecthorbrand=new ArrayList<>();

    List<SelectMesItem>dialoghorType=new ArrayList<>();
    List<SelectClassBrandsItem>dialoghorbrand=new ArrayList<>();
    SelectResultItemRecycAdapter recycAdapter;
    int postposition;
    SelectTypeItemAdapter typeadapter;
    SelectBrandItemAdapter brandadapter;
    Map<String,Integer>dialogTypeMap;
    Map<String,Integer>dialogBrandMap;
    String secondtype,titlename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectresult);
        initView();
    }

    private void initView() {
        ImageUtil.clearlist();
        topbar= (TopbarView) findViewById(R.id.topbar);
        moren= (TextView) findViewById(R.id.selectresult_moren);
        xiaoliang= (TextView) findViewById(R.id.selectresult_xiaoliang);
        price= (TextView) findViewById(R.id.selectresult_price);
        close= (TextView) findViewById(R.id.selectresult_close);
        img_price= (ImageView) findViewById(R.id.selectresult_img_price);
        img_xiaoliang= (ImageView) findViewById(R.id.selectresult_img_xiaoliang);
        layout_price= (LinearLayout) findViewById(R.id.selectresult_layout_price);
        layout_screen= (LinearLayout) findViewById(R.id.selectresult_layout_screen);
        layout_top= (LinearLayout) findViewById(R.id.selectresult_layout_top);
        layout_xiaoliang= (LinearLayout) findViewById(R.id.selectresult_layout_xiaoliang);
        blin= (LinearLayout)findViewById(R.id.otherfrag_blin);
        layout_error= (LinearLayout)findViewById(R.id.selectresult_selectnone);
        grid= (GridView) findViewById(R.id.selectresult_grid);
        recyclerView= (RecyclerView) findViewById(R.id.selectresult_recycle);
        typeCode=getIntent().getStringExtra("brandId");
        secondtype=typeCode;
        parentTypeCode=getIntent().getStringExtra("parentTypeCode");
        topbar.setTitle(getIntent().getStringExtra("name"));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        horType.add(new SelectMesItem(typeCode, getIntent().getStringExtra("name")));
        titlename=getIntent().getStringExtra("titlename");
        customScrollView= (CustomScrollView) findViewById(R.id.customScrollview);
        customScrollView.setContext(this);
        customScrollView.setonBorderListener(new CustomScrollView.OnBorderListener() {
            @Override
            public void onBottom() {
                if (now <= tatol)
                    getRefresh();
                else {
                    if (reflash) {
//                        Toast.makeText(SelectresultActivity.this, "已到最后一页", Toast.LENGTH_SHORT).show();
                        blin.setVisibility(View.VISIBLE);
                        reflash = false;
                    }
                }
            }
        });

        String p=getIntent().getStringExtra("position");
        postposition= Integer.parseInt(p)+1;
        recycAdapter=new SelectResultItemRecycAdapter(horType,horbrand,this) {
            @Override
            protected void onClickItem(View v, int position) {
                if(position<horType.size()){
                    dialoghorType.get( dialogTypeMap.get(horType.get(position).getTypeName())).setFlage(false);
                    horType.remove(position);

                    typeCode=getTypeString(horType);
                    recycAdapter.setHorType(horType);
                }else {

                    dialoghorbrand.get(dialogBrandMap.get(horbrand.get( position-horType.size()).getBrandName())).setFlage(false);
                    horbrand.remove(position - horType.size());
                    recycAdapter.setHorbrand(horbrand);
                    brandId = getBrandString(horbrand);

                }
                if(typeCode.length()<6)
                    typeCode=secondtype.substring(0,12);
                getrJson();
                recycAdapter.notifyDataSetChanged();
            }
        };
        recyclerView.setAdapter(recycAdapter);
        getJson();
    }

    private String  getTypeString( List<SelectMesItem>horType){
        if(horType.isEmpty()){
            return parentTypeCode;
        }else{
            String text="";
            for(int i=0;i<horType.size();i++){
                if (i == horType.size() - 1) {
                    text += horType.get(i).getTypeId();
                } else {
                    text += horType.get(i).getTypeId() + ",";
                }
            }
            return text;
        }
    }

    private String getBrandString( List<SelectClassBrandsItem>horBrand){
        String text="";
        for(int i=0;i<horBrand.size();i++){
            if(i==horBrand.size()-1){
                text+=horBrand.get(i).getBrandId();
            }else {
                text+=horBrand.get(i).getBrandId()+",";
            }
        }
        return text;
    }
    private void getJson() {
        ImageUtil.clearlist();
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJsonhaveError(this, path + "?typeCode=" + typeCode + "&brandId=" + brandId + "&sortFiled=" + sortFiled + "&sortType=" + sortDir, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                layout_error.setVisibility(View.GONE);
                loding.disShapeLoding();
                user = HttpConnectionUtil.getSelectClassUser(str);
                tatol = Integer.parseInt(user.getPageCount());
                now = 2;
                customScrollView.setFlage(true);
                if (!user.getList().isEmpty()) {
                    gadapter = new OgridGoodAdapter(user.getList(), SelectresultActivity.this);
                    grid.setAdapter(gadapter);
                }else{
                    layout_error.setVisibility(View.VISIBLE);
                }
                user.getTypes().add(0, new SelectMesItem(secondtype.substring(0, 12), "全部"));
                user.getBrands().add(0, new SelectClassBrandsItem("", "全部", true));
                if (dialogFlage) {
                    user.getTypes().get(postposition).setFlage(true);
                    setDialog();
                    dialogFlage = false;
                }
            }
        }, new HttpConnectionUtil.OnErrorJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                layout_error.setVisibility(View.VISIBLE);
            }
        });

    }

    public void reset(){
        ImageUtil.clearlist();
        blin.setVisibility(View.GONE);
        reflash=true;
    }

    private void getrJson() {
        reset();
        gadapter = new OgridGoodAdapter(new ArrayList<OtherGoodUser>(), SelectresultActivity.this);
        grid.setAdapter(gadapter);
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJsonhaveError(this, path + "?typeCode=" + typeCode + "&brandId=" + brandId + "&sortFiled=" + sortFiled + "&sortType=" + sortDir, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                layout_error.setVisibility(View.GONE);
                loding.disShapeLoding();
                SelectClassUser muser = HttpConnectionUtil.getSelectClassUser(str);
                tatol = Integer.parseInt(muser.getPageCount());
                now = 2;
                customScrollView.setFlage(true);
                user.setList(muser.getList());
                if (!user.getList().isEmpty()) {
                    gadapter = new OgridGoodAdapter(user.getList(), SelectresultActivity.this);
                    grid.setAdapter(gadapter);
                }else{
                    layout_error.setVisibility(View.VISIBLE);
                }
                if (dialogFlage) {
                    user.getTypes().get(postposition).setFlage(true);

                    setDialog();
                    dialogFlage = false;
                }
            }
        }, new HttpConnectionUtil.OnErrorJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                layout_error.setVisibility(View.VISIBLE);
            }
        });

    }

    private void getRefresh() {
        HttpConnectionUtil.getGetJson(this, path + "?typeCode=" +typeCode + "&brandId=" + brandId + "&sortFiled=" + sortFiled +"&code=2"+ "&sortType=" + sortDir+ "&PageIndex=" + now, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                Log.i("selectresult",str);
                SelectClassUser  muser = HttpConnectionUtil.getSelectClassUser(str);
                user.getList().addAll(muser.getList());
                gadapter .setmlist(user.getList());
                gadapter.notifyDataSetChanged();
                now++;
                customScrollView.setFlage(true);
            }
        });

    }





    public void setDialog(){
        View view=View.inflate(this,R.layout.dialog_classselect,null);
        GridView typegrid= (GridView) view.findViewById(R.id.dialog_classselect_classgrid);
        GridView brandgrid= (GridView) view.findViewById(R.id.dialog_classselect_brand);
        dialoghorType=user.getTypes();
        dialoghorType.get(postposition).setFlage(true);
        dialoghorbrand=user.getBrands();
        dialogTypeMap=new HashMap<>();
        dialogBrandMap=new HashMap<>();
        for(int i=0;i< dialoghorType.size();i++){
            dialogTypeMap.put(dialoghorType.get(i).getTypeName(), i);
        }
        for(int i=0;i< dialoghorbrand.size();i++){
            dialogBrandMap.put(dialoghorbrand.get(i).getBrandName(),i);
        }
        typeadapter=new SelectTypeItemAdapter(this,dialoghorType);
        brandadapter=new SelectBrandItemAdapter(this,dialoghorbrand);
        Button reset= (Button) view.findViewById(R.id.dialog_classselect_reset);
        Button sure= (Button) view.findViewById(R.id.dialog_classselect_sure);
        reset.setOnClickListener(this);
        sure.setOnClickListener(this);
        typegrid.setAdapter(typeadapter);
        brandgrid.setAdapter(brandadapter);
//        selecthorType=horType;
        selecthorType.add(dialoghorType.get(postposition));
        typegrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (dialoghorType.get(position).isFlage()) {
                    dialoghorType.get(position).setFlage(false);
                    selecthorType.remove(dialoghorType.get(position));
                } else {
                    if(position==0){
                        for (SelectMesItem item: dialoghorType){
                            item.setFlage(false);
                        }
                        selecthorType=new ArrayList<SelectMesItem>();

                    }else{
                        dialoghorType.get(0).setFlage(false);
                    }
                    dialoghorType.get(position).setFlage(true);
//                    if(position!=0)
                    selecthorType.add(dialoghorType.get(position));
                }
                typeadapter.setList(dialoghorType);
                typeadapter.notifyDataSetChanged();
            }
        });
        brandgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(dialoghorbrand.get(position).isFlage()){
                    dialoghorbrand.get(position).setFlage(false);
                    selecthorbrand.remove(dialoghorbrand.get(position));
                }else {
                    if(position==0){
                        for (SelectClassBrandsItem item: dialoghorbrand){
                            item.setFlage(false);
                        }
                        selecthorbrand=new ArrayList<SelectClassBrandsItem>();
                    }else{
                        dialoghorbrand.get(0).setFlage(false);
                    }
                    dialoghorbrand.get(position).setFlage(true);
                    if(position!=0)
                    selecthorbrand.add(dialoghorbrand.get(position));
                }
                brandadapter.setList(dialoghorbrand);
                brandadapter.notifyDataSetChanged();
            }
        });



        mPopupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopupWindow.setFocusable(true);
    }

    @Override
    public void setListener() {
        moren.setOnClickListener(this);
        layout_price.setOnClickListener(this);
        layout_screen.setOnClickListener(this);
        close.setOnClickListener(this);
        layout_xiaoliang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.selectresult_moren:
                if(flage!=1){
                    flage=1;
                     sortFiled="1";
                     sortDir="1";
                    getrJson();
                    moren.setTextColor(getResources().getColor(R.color.main_color));
                    xiaoliang.setTextColor(getResources().getColor(R.color.individualblack));
                    price.setTextColor(getResources().getColor(R.color.individualblack));
                }
                break;
            case R.id.selectresult_layout_xiaoliang:
//                if(flage!=2){
//                    flage=2;
//                    sortFiled="3";
//                    sortDir="1";
//                    getrJson();
//                    moren.setTextColor(getResources().getColor(R.color.individualblack));
//                    xiaoliang.setTextColor(getResources().getColor(R.color.main_color));
//                    price.setTextColor(getResources().getColor(R.color.individualblack));
//                }

                if(xflage){
                    xflage=false;
                    flage=2;
                    img_xiaoliang.setImageResource(R.drawable.price_up);
                    sortFiled="3";
                    sortDir="1";
                    getrJson();
                    moren.setTextColor(getResources().getColor(R.color.individualblack));
                    xiaoliang.setTextColor(getResources().getColor(R.color.main_color));
                    price.setTextColor(getResources().getColor(R.color.individualblack));
                }else{
                    xflage=true;
                    flage=2;
                    img_xiaoliang.setImageResource(R.drawable.price_down);
                    sortFiled="3";
                    sortDir="0";
                    getrJson();
                    moren.setTextColor(getResources().getColor(R.color.individualblack));
                    xiaoliang.setTextColor(getResources().getColor(R.color.main_color));
                    price.setTextColor(getResources().getColor(R.color.individualblack));
                }
                break;
            case R.id.selectresult_layout_price:
                if(pflage){
                    pflage=false;
                    flage=3;
                    img_price.setImageResource(R.drawable.price_up);
                    sortFiled="2";
                    sortDir="1";
                    getrJson();
                    moren.setTextColor(getResources().getColor(R.color.individualblack));
                    xiaoliang.setTextColor(getResources().getColor(R.color.individualblack));
                    price.setTextColor(getResources().getColor(R.color.main_color));
                }else{
                    pflage=true;
                    flage=3;
                    img_price.setImageResource(R.drawable.price_down);
                    sortFiled="2";
                    sortDir="0";
                    getrJson();
                    moren.setTextColor(getResources().getColor(R.color.individualblack));
                    xiaoliang.setTextColor(getResources().getColor(R.color.individualblack));
                    price.setTextColor(getResources().getColor(R.color.main_color));
                }
                break;
            case R.id.selectresult_layout_screen:
                typeadapter.setList(dialoghorType);
                brandadapter.setList(dialoghorbrand);
                typeadapter.notifyDataSetChanged();
                brandadapter.notifyDataSetChanged();
                mPopupWindow.showAsDropDown(layout_top, 0,2);
                break;
            case R.id.dialog_classselect_reset:
                for (SelectMesItem item: dialoghorType){
                    item.setFlage(false);
                }
                selecthorType=new ArrayList<SelectMesItem>();
                dialoghorType.get(0).setFlage(true);


                user.getTypes().get(postposition).setFlage(false);
                user.getTypes().get(0).setFlage(true);
                user.getBrands().get(0).setFlage(true);
                brandadapter.setList(user.getBrands());
                brandadapter.notifyDataSetChanged();
                typeadapter.setList(user.getTypes());
                typeadapter.notifyDataSetChanged();
                selecthorType=new ArrayList<>();
                selecthorbrand=new ArrayList<>();
                typeCode=parentTypeCode;

                break;
            case R.id.dialog_classselect_sure:
                typeCode=getTypeString(selecthorType);
                brandId = getBrandString(selecthorbrand);
                horType=selecthorType;
                horbrand=selecthorbrand;
                horType.remove( user.getTypes().get(0));
                recycAdapter.setHorType(horType);
                recycAdapter.setHorbrand(horbrand);
                recycAdapter.notifyDataSetChanged();
                mPopupWindow.dismiss();
                getrJson();
                break;
            case R.id.selectresult_close:
                for (SelectMesItem item: dialoghorType){
                    item.setFlage(false);
                }
                selecthorType=new ArrayList<SelectMesItem>();
                dialoghorType.get(0).setFlage(true);
                selecthorType=new ArrayList<>();
                selecthorbrand=new ArrayList<>();
                dialoghorType=user.getTypes();
                dialoghorType.get(postposition).setFlage(false);
                dialoghorType.get(0).setFlage(true);
                dialoghorbrand=user.getBrands();
                typeCode=parentTypeCode;
                brandId ="";
                recycAdapter.setHorType(selecthorType);
                recycAdapter.setHorbrand(selecthorbrand);
                recycAdapter.notifyDataSetChanged();
                topbar.setTitle(titlename);
                getrJson();
                break;

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        new ImageUtil(this).clearband();
    }
}
