package com.example.transtion.my5th.DIndividualActivity.MyWallet;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.R;

import java.util.ArrayList;

import InternetUser.InoutHisUser;
import InternetUser.Item.InoutHisItem;
import adapter.Individual.InouthisAdapter;
import customUI.PullToRefreshView;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class DMAinouthis extends BaseActivity implements PullToRefreshView.OnFooterRefreshListener{
    String path= Path.HOST+Path.ip+Path.INOUTHIS_PATH;
    String paths= Path.HOST+Path.ip+Path.INOUT_SELECT_PATH;
    PullToRefreshView refreshView;
    ListView list;
    int now=2;
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
        gouwu.setOnClickListener(this);
        chongzhika.setOnClickListener(this);
        tixian.setOnClickListener(this);
        yongjin.setOnClickListener(this);
        pop_right=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        pop_right.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);
        pop_right.setBackgroundDrawable(new ColorDrawable(0));
        pop_right.setFocusable(true);
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID()+"&pageSize=15", loding, new HttpConnectionUtil.OnJsonCall() {
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
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID()+"&PageIndex="+now+"&pageSize=15",null, new HttpConnectionUtil.OnJsonCall() {
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
        HttpConnectionUtil.getGetJson(this, paths + "?MemberId=" + share.getMemberID()+"&Key="+key+"&pageSize=15",loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getInoutHisUser(str);
                adapter = new InouthisAdapter(user, DMAinouthis.this);
                tatol=Integer.parseInt(user.getPageCount());
                now=2;
                list.setAdapter(adapter);
                loding.disShapeLoding();
            }
        });
    }

    private void refreshS(){
        HttpConnectionUtil.getGetJson(this, paths + "?MemberId=" + share.getMemberID()+"&Key="+key+"&PageIndex="+now+"&pageSize=15",null, new HttpConnectionUtil.OnJsonCall() {
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
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.inout_select:
                pop_right.showAsDropDown(select, 0, 5);
                break;
                case R.id.pop_select_gouwu:
                    selectItem("1");
                    pop_right.dismiss();
                    break;
                case R.id.pop_select_chongzhika:
                    selectItem("2");
                    pop_right.dismiss();
                    break;
                case R.id.pop_select_tixian:
                    selectItem("3");
                    pop_right.dismiss();
                    break;
                case R.id.pop_select_yongjin:
                    selectItem("4");
                    pop_right.dismiss();
                    break;
                case R.id.back:
                    JumpUtil.jump2finash(this);
                    break;

            }
    }

    private void selectItem(String key) {
        user=new InoutHisUser("0",new ArrayList<InoutHisItem>());
        adapter.setUser(user);
        adapter.notifyDataSetChanged();
        now=1;
        flage=false;
        this.key=key;
        getSJson(key);
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        if(now<=tatol){
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
