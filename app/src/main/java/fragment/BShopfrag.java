package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.transtion.my5th.BShopcar.FendanActivity;
import com.example.transtion.my5th.BShopcar.GoodsorderActivity;
import com.example.transtion.my5th.R;

import InternetUser.shopcar.DingdanUser;
import InternetUser.shopcar.FendanUser;
import InternetUser.shopcar.Shopcarlist;
import InternetUser.shopcar.ShopcarlistItem;
import adapter.shopcar_adapter.ShopcarAdapter;
import customUI.CountdownView;
import fifthutil.FifUtil;
import fifthutil.JumpUtil;
import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/2/22.
 */
public class BShopfrag extends Fragment implements View.OnClickListener{
    View view;
    ImageView selectall;
    TextView bottommes,moneysum;
    CountdownView time;
    Shopcarlist user;
    FendanUser fendanUser;
    String path= Path.HOST+Path.ip+Path.SHOPCARLIST_PATH;
    String fenpath=Path.HOST+Path.ip+Path.SHOPCAR_FENDAN_PATH;
    LodingUtil loding;
    ShopcarAdapter adapter;
    ListView mlist;
    LinearLayout commit;
    boolean flage=true;
    DingdanUser dingdanUser;
    LinearLayout shopcar_layout;
    double p;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.frag_bshop, null);
             ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        return view;
    }

    private void setListener() {
        commit.setOnClickListener(this);
        selectall.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        mclear();
        setListener();
    }

    private void initView() {

        selectall= (ImageView)view.findViewById(R.id.shopcar_up_select);
        bottommes= (TextView) view.findViewById(R.id.shopcar_up_mes);
        moneysum= (TextView) view.findViewById(R.id.shopcar_up_money);
        time= (CountdownView) view.findViewById(R.id.shopcar_time);
        mlist= (ListView) view.findViewById(R.id.shopcar_list);
        commit= (LinearLayout) view.findViewById(R.id.bshop_layout_commit);
        shopcar_layout= (LinearLayout) view.findViewById(R.id.shopcar_layout);
        loding=new LodingUtil(getActivity());
        dingdanUser=DingdanUser.getInstance();
//        mlist.setAdapter(null);
//        shopcar_layout.setVisibility(View.VISIBLE);
//        mlist.setVisibility(View.GONE);
        getJson();
    }

    private void getJson() {

        loding.showShapeLoding();
        HttpConnectionUtil.getGetshopcarJson(getActivity(), path + "?memberId=" + ShareUtil.getInstanse(getActivity()).getMemberID(), loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                user = HttpConnectionUtil.getShopcarlist(str);
                shopcar_layout.setVisibility(View.GONE);
                mlist.setVisibility(View.VISIBLE);
                selectall.setBackgroundResource(R.drawable.bg_radio_on3x);
                setView();
            }
        }, shopcar_layout,mlist);
    }


    private void fendanJson(String t) {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetshopJson(getActivity(), fenpath + "?memberId=" + ShareUtil.getInstanse(getActivity()).getMemberID()+"&Ids="+t, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                fendanUser=HttpConnectionUtil.getFendanUser(str);
                setFendanTotal(fendanUser);
                dingdanUser.setFendanUser(fendanUser);
                if(fendanUser.getIsDivide()){
                    JumpUtil.jumpWithValue(getActivity(), FendanActivity.class,new String[]{"moneysum"},new String[]{moneysum.getText().toString()},true);
                }else {
                    JumpUtil.jump(getActivity(), GoodsorderActivity.class,true);
                }
            }
        });
    }



    public void setFendanTotal(FendanUser fendanUser){
        for(int i=0;i<fendanUser.getList().size();i++){
            for(int j=0;j<fendanUser.getList().get(i).getList().size();j++){
                double p= fendanUser.getList().get(i).getList().get(j).getSellPrice();
                int num= fendanUser.getList().get(i).getList().get(j).getNumber();
                fendanUser.getList().get(i).getList().get(j).setTotalPrice(p*num);
            }
        }
    }

    private String getshopid(){
        String id="";
        if(adapter!=null) {
            for (ShopcarlistItem item : adapter.getList()) {
                if (item.isFlage()) {
                    id = id + item.getId() + ",";
                }
            }
        }
        return id;
    }


    private void setView() {
        bottommes.setText("(含关税￥" +FifUtil.getPrice( user.getTotalTax()) + "，不含运费)");
        time.start(Long.parseLong(user.getTimeSpan()) * 1000);
        moneysum.setText("￥" + FifUtil.getPrice(user.getTotalPrice()));
        adapter=new ShopcarAdapter(user.getCartViewList(),getActivity()) {
            @Override
            protected void ondeleteClickItem(View v, int position) {
                if(adapter.getList().isEmpty()){
                    shopcar_layout.setVisibility(View.VISIBLE);
                    mlist.setVisibility(View.GONE);
                    time.stop();
                    time.allShowZero();
                    bottommes.setText("(含关税￥0，不含运费)");

                }
//                double a=user.getCartViewList().get(position).getTax()*user.getCartViewList().get(position).getNumber();
//                bottommes.setText("(含关税￥" + FifUtil.getPrice(user.getTotalTax() - a) + "，不含运费)");
                p=0;
                for(ShopcarlistItem item: adapter.getList()){
                    if(item.isFlage()){
                        p+=item.getSellPrice()*item.getNumber();
                    }
                }
                moneysum.setText("￥" + FifUtil.getPrice(p));
            }

            @Override
            protected void onaddClickItem(View v, int position) {

                p=user.getTotalPrice()+adapter.getList().get(position).getSellPrice();
                user.setTotalPrice(user.getTotalPrice()+adapter.getList().get(position).getSellPrice());
                moneysum.setText("￥" + FifUtil.getPrice(p));
//                user.getCartViewList().get(position).setNumber(user.getCartViewList().get(position).getNumber() + 1);

                bottommes.setText("(含关税￥" + FifUtil.getPrice(user.getTotalTax() + user.getCartViewList().get(position).getTax()) + "，不含运费)");
                user.setTotalTax(user.getTotalTax() + user.getCartViewList().get(position).getTax());
            }

            @Override
            protected void onjianClickItem(View v, int position) {
                p=user.getTotalPrice()-adapter.getList().get(position).getSellPrice();
                user.setTotalPrice(user.getTotalPrice()-adapter.getList().get(position).getSellPrice());
                moneysum.setText("￥" + FifUtil.getPrice(p));
//                user.getCartViewList().get(position).setNumber( user.getCartViewList().get(position).getNumber()-1);
                bottommes.setText("(含关税￥" + FifUtil.getPrice(user.getTotalTax() - user.getCartViewList().get(position).getTax()) + "，不含运费)");
                user.setTotalTax(user.getTotalTax() - user.getCartViewList().get(position).getTax());
            }

            @Override
            protected void onselectClickItem(View v, int position) {
                selectall.setBackgroundResource(R.drawable.bg_radio3x);
                p=0;
                for(ShopcarlistItem item: adapter.getList()){
                   if(item.isFlage()){
                       p+=item.getSellPrice()*item.getNumber();
                   }
                }
                moneysum.setText("￥"+FifUtil.getPrice(p) );
            }
        };
        mlist.setAdapter(adapter);
    }
    public void mclear(){
        moneysum.setText("￥"+"0.00");
        bottommes.setText("(含关税￥" + "0.00" + "，不含运费)");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bshop_layout_commit:
                String t=getshopid();
                if(t.length()>3)
                    fendanJson(t);
                break;
            case R.id.shopcar_up_select:
                if(flage){
                    flage=false;
                    selectall.setBackgroundResource(R.drawable.bg_radio3x);
                    if(adapter!=null) {
                        for (ShopcarlistItem item : adapter.getList()) {
                            item.setFlage(flage);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    moneysum.setText("￥"+"0.00");
                }else{
                    flage=true;
                    selectall.setBackgroundResource(R.drawable.bg_radio_on3x);
                    p=0;
                    if(adapter!=null) {
                        for (ShopcarlistItem item : adapter.getList()) {
                            item.setFlage(true);
                            p += item.getSellPrice() * item.getNumber();
                        }
                        adapter.notifyDataSetChanged();
                    }
                    moneysum.setText("￥"+FifUtil.getPrice(p)+"");
                }

                break;
        }
    }
}
