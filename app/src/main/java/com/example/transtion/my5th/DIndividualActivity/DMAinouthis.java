package com.example.transtion.my5th.DIndividualActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import InternetUser.InoutHisUser;
import adapter.InouthisAdapter;
import customUI.PullToRefreshView;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class DMAinouthis extends BaseActivity implements PullToRefreshView.OnFooterRefreshListener{
    String path= Path.HOST+Path.ip+Path.INOUTHIS_PATH;
    String paths= Path.HOST+Path.ip+Path.INOUT_SELECT_PATH;
    PullToRefreshView refreshView;
    ListView list;
    int now=1;
    int tatol;
    InoutHisUser user;
    ShareUtil share;
    InouthisAdapter adapter;
    ImageView back;
    PopupWindow pop_right;
    TextView select;
    boolean flage;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmainouthis);
        initview();
    }

    public void initview() {
        flage=true;
        share=ShareUtil.getInstanse(this);
        back= (ImageView) findViewById(R.id.back);
        refreshView= (PullToRefreshView)findViewById(R.id.inout_list);
        list= (ListView)findViewById(R.id.inout_listview);
        refreshView.setEnablePullTorefresh(false);
        select= (TextView) findViewById(R.id.inout_select);
        getJson();
        setPop();
    }

    private void setPop() {
        View view=View.inflate(this,R.layout.pop_right,null);
        TextView gouwu= (TextView) view.findViewById(R.id.pop_select_gouwu);
        TextView chongzhika= (TextView) view.findViewById(R.id.pop_select_chongzhika);
        TextView tixian= (TextView) view.findViewById(R.id.pop_select_tixian);
        TextView yongjin= (TextView) view.findViewById(R.id.pop_select_yongjin);
        TextView zaixianchong= (TextView) view.findViewById(R.id.pop_select_zaixianchong);
        TextView zhifubao= (TextView) view.findViewById(R.id.pop_select_zhifubao);
        TextView yinhangka= (TextView) view.findViewById(R.id.pop_select_yinhangka);
        gouwu.setOnClickListener(this);
        chongzhika.setOnClickListener(this);
        tixian.setOnClickListener(this);
        yongjin.setOnClickListener(this);
        zaixianchong.setOnClickListener(this);
        zhifubao.setOnClickListener(this);
        yinhangka.setOnClickListener(this);
        pop_right=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        pop_right.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);
        pop_right.setBackgroundDrawable(new ColorDrawable(0));
        pop_right.setFocusable(true);
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID(), loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getInoutHisUser(str);
                adapter = new InouthisAdapter(user, DMAinouthis.this);
                tatol=Integer.parseInt(user.getPageCount());
                list.setAdapter(adapter);
                loding.disShapeLoding();
            }
        });
    }

    private void refresh(){
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID()+"&PageIndex="+now,null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                InoutHisUser users = HttpConnectionUtil.getInoutHisUser(str);
                user.getList().addAll(users.getList());
                adapter.setUser(user);
                adapter.notifyDataSetChanged();
                refreshView.onFooterRefreshComplete();
                now++;
            }
        });
    }

    private void getSJson(String key) {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, paths + "?MemberId=" + share.getMemberID()+"&Key="+key,loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getInoutHisUser(str);
                adapter = new InouthisAdapter(user, DMAinouthis.this);
                tatol=Integer.parseInt(user.getPageCount());
                list.setAdapter(adapter);
                loding.disShapeLoding();
            }
        });
    }

    private void refreshS(){
        HttpConnectionUtil.getGetJson(this, paths + "?MemberId=" + share.getMemberID()+"&Key="+key+"&PageIndex="+now,null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                InoutHisUser users = HttpConnectionUtil.getInoutHisUser(str);
                user.getList().addAll(users.getList());
                adapter.setUser(user);
                adapter.notifyDataSetChanged();
                refreshView.onFooterRefreshComplete();
                now++;
            }
        });
    }

    @Override
    public void setListener() {
        refreshView.setOnFooterRefreshListener(this);
        select.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.inout_select:
                pop_right.showAsDropDown(select, 0, 5);
                break;
                case R.id.pop_select_gouwu:
                    selectItem("购物");
                    pop_right.dismiss();
                    break;
                case R.id.pop_select_chongzhika:
                    selectItem("充值卡充值");
                    pop_right.dismiss();
                    break;
                case R.id.pop_select_tixian:
                    selectItem("提现");
                    pop_right.dismiss();
                    break;
                case R.id.pop_select_yongjin:
                    selectItem("佣金收入");
                    pop_right.dismiss();
                    break;
                case R.id.pop_select_zaixianchong:
                    selectItem("在线充值");
                    pop_right.dismiss();
                    break;
                case R.id.pop_select_zhifubao:
                    selectItem("支付宝提现");
                    pop_right.dismiss();
                    break;
                case R.id.pop_select_yinhangka:
                    selectItem("银行卡提现");
                    pop_right.dismiss();
                    break;

            }
    }

    private void selectItem(String key) {
        now=1;
        flage=false;
        this.key=key;
        getSJson(key);
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        if(now<tatol){
            if(flage)
              refresh();
            else
                refreshS();
        }
        else{
            Toast.makeText(this, "已到最后一页", Toast.LENGTH_SHORT).show();
            refreshView.onFooterRefreshComplete();
        }
    }
}
