package com.example.transtion.my5th.BShopcar;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.DIndividualActivity.AddressmanagerActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.alipay.SelectcouponActivity;
import com.example.transtion.my5th.mActivity.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import InternetUser.AllHost;
import InternetUser.shopcar.DingdanUser;
import InternetUser.shopcar.FendanGoodItem;
import InternetUser.shopcar.FendanUser;
import InternetUser.shopcar.GoodsOrderUser;
import InternetUser.shopcar.ShopAddress;
import fifthutil.FifUtil;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class GoodsorderActivity extends BaseActivity {
    ImageUtil imageUtil;
    LinearLayout goods,coupon,address,addressmes,layout_couponmoney,layout_showcoupon;
    private static final int MYRESULT=1;
    private static final int MYADDRESULT=2;
    String goodsorderpath=Path.HOST+Path.ip+Path.GOODSORDERDETAIL_PATH;
    String path= Path.HOST+Path.ip+Path.SHOPCAR_COMMIT_PATH;
    String addresspath=Path.HOST+Path.ip+Path.FRIEIGHT4CHANGE_PATH;
    DingdanUser dingdanUser;
    FendanUser user;
    int position;
    TextView name,phone,mes,couponmoney,totalmoney,youhuimoney,taxtemp,freight,resultmoney,addressselect;
    double rmoney=0;
    Button commit;
    double tfright;
    String CouponIssueId;
    GoodsOrderUser goodsOrderUser;
    private double mTotalMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsorder);
        initView();
    }

    private void initView(){
        imageUtil=new ImageUtil(this);
        goods= (LinearLayout) findViewById(R.id.orderdetail_layout_goods);
        coupon= (LinearLayout) findViewById(R.id.goodsorder_layout_coupon);
        address= (LinearLayout) findViewById(R.id.goodsorder_layout_address);
        addressmes=(LinearLayout) findViewById(R.id.goodsorder_layout_addressmes);
        layout_couponmoney=(LinearLayout) findViewById(R.id.goodsorder_layout_couponmoney);
        layout_showcoupon=(LinearLayout) findViewById(R.id.goodsorder_layout_showcoupon);

        name= (TextView) findViewById(R.id.goodsorder_name);
        phone= (TextView) findViewById(R.id.goodsorder_phone);
        mes= (TextView) findViewById(R.id.goodsorder_address);
        couponmoney= (TextView) findViewById(R.id.goodsorder_coupon);
        totalmoney= (TextView) findViewById(R.id.goodsorder_totalmoney);
        youhuimoney= (TextView) findViewById(R.id.goodsorder_couponmoney);
        taxtemp= (TextView) findViewById(R.id.goodsorder_tariffmoney);
        freight= (TextView) findViewById(R.id.goodsorder_freightmoney);
        resultmoney= (TextView) findViewById(R.id.goodsorder_summoney);
        addressselect=(TextView) findViewById(R.id.goodsorder_selectaddress);

        commit= (Button) findViewById(R.id.goodsorder_commit);
        dingdanUser=DingdanUser.getInstance();
        user=dingdanUser.getFendanUser();
        position=getIntent().getIntExtra("position", 0);
        if(user.getAddress()!=null){
            addressmes.setVisibility(View.VISIBLE);
            addressselect.setVisibility(View.GONE);
            name.setText(user.getAddress().getName());
            phone.setText(user.getAddress().getMobel());
            mes.setText(user.getAddress().getAddress());
        }

        double tmoney=0;
         tfright=0;
        double ttaxTemp=0;
        CouponIssueId="";
        for(FendanGoodItem item:user.getList().get(position).getList()){
            tmoney+=item.getTotalPrice();
//            tfright+=item.getTotalFreight();
            ttaxTemp+=item.getTax()*item.getNumber();
        }
        totalmoney.setText("￥"+FifUtil.getPrice(tmoney));
        youhuimoney.setText("￥0.00");
        taxtemp.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        taxtemp.setText("￥"+FifUtil.getPrice(ttaxTemp));
        freight.setText("￥"+tfright);
//      rmoney=tmoney+ttaxTemp+tfright;
        rmoney=tmoney+tfright;
        resultmoney.setText("￥" + FifUtil.getPrice(rmoney));
        mTotalMoney=tmoney;
        setgoodLayout();
        getJson();
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, goodsorderpath + "?memberId=" + share.getMemberID() + "&cartIds=" + getString(), loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                goodsOrderUser=HttpConnectionUtil.getGoodsorderUsers(str);
                freight.setText("￥"+FifUtil.getPrice(goodsOrderUser.getFreigth()));
                resultmoney.setText("￥" + FifUtil.getPrice(rmoney+goodsOrderUser.getFreigth()));

                rmoney-=tfright;
                rmoney+= goodsOrderUser.getFreigth();
                tfright= goodsOrderUser.getFreigth();
                user.setGoodsOrderUser(goodsOrderUser);
                if(DingdanUser.getInstance().getFendanUser().getGoodsOrderUser().getCouponsView().isEmpty()){
                    layout_showcoupon.setVisibility(View.GONE);
                }
            }
        });
    }


    private void setgoodLayout(){
        List<FendanGoodItem> tlist=user.getList().get(position).getList();
        for(FendanGoodItem orderDetailItem:tlist){
            View v=View.inflate(this,R.layout.adapter_goodsorderlist,null);
            ImageView img= (ImageView) v.findViewById(R.id.linearlayout_orderlist_img);
            TextView title= (TextView) v.findViewById(R.id.linearlayout_orderlist_mes);
            TextView attr= (TextView) v.findViewById(R.id.linearlayout_orderlist_attr);
            TextView money= (TextView) v.findViewById(R.id.linearlayout_orderlist_money);
            TextView sum= (TextView) v.findViewById(R.id.linearlayout_orderlist_sum);

            String path=orderDetailItem.getImageSrc();
            imageUtil.display(img,path);
            attr.setText(orderDetailItem.getStandardDesc());
            money.setText("￥"+FifUtil.getPrice(orderDetailItem.getSellPrice()));
            sum.setText("×"+orderDetailItem.getNumber());
            title.setText(orderDetailItem.getTitle());
            goods.addView(v);
        }
    }



    @Override
    public void setListener() {
        coupon.setOnClickListener(this);
        address.setOnClickListener(this);
        commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.goodsorder_layout_coupon:
                    Intent intent=new Intent(this, SelectcouponActivity.class);

                    intent.putExtra("money",rmoney);
                    startActivityForResult(intent,MYRESULT);
                    this.overridePendingTransition(R.anim.push_in, R.anim.push_out);
                    break;
                case R.id.goodsorder_layout_address:
                    Intent intent2=new Intent(this, AddressmanagerActivity.class);
                    intent2.putExtra("flage",true);
                    startActivityForResult(intent2, MYADDRESULT);
                    this.overridePendingTransition(R.anim.push_in, R.anim.push_out);
                    break;
                case R.id.goodsorder_commit:
                    go2commit();
                    break;
            }
    }


    private void changeAddress(){
        String cartFreightIds="";
       List<FendanGoodItem>fenlist=user.getList().get(position).getList();
        for(int i=0;i<user.getList().get(position).getList().size();i++){
            cartFreightIds+=fenlist.get(i).getId()+":"+fenlist.get(i).getNumber()+",";
        }

        loding.showShapeLoding();
        HttpConnectionUtil.getJsonJsonwithDialog(this, addresspath, new String[]{"memberId","addressId","cartFreightIds"}, new String[]{share.getMemberID(),user.getAddress().getAddressId(),cartFreightIds}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                rmoney-=tfright;
                loding.disShapeLoding();
                AllHost allHost=HttpConnectionUtil.getAllHost(str);
                print(allHost.getData());
                String []a=allHost.getData().split(":");
                print(a[1].substring(0, a[1].length()-1));
                tfright=Double.parseDouble(a[1].substring(0,a[1].length()-1));
                freight.setText("￥"+FifUtil.getPrice(tfright));
                rmoney+=tfright;
                resultmoney.setText("￥" + FifUtil.getPrice(rmoney));
            }
        });
    }

    private String getString(){
        String cartFreightIds="";
        List<FendanGoodItem>fenlist=user.getList().get(position).getList();
        for(int i=0;i<user.getList().get(position).getList().size();i++){
            cartFreightIds+=fenlist.get(i).getId()+":"+fenlist.get(i).getNumber()+",";
        }
        return cartFreightIds;
    }


    private void go2commit() {

        loding.showShapeLoding();
        String CartIdNumbers="";
        for(int i=0;i<user.getList().get(position).getList().size();i++){
            if(i==user.getList().get(position).getList().size()-1){
                CartIdNumbers=CartIdNumbers+ user.getList().get(position).getList().get(i).getId()+":"+user.getList().get(position).getList().get(i).getNumber();
            }else {
                CartIdNumbers= CartIdNumbers+user.getList().get(position).getList().get(i).getId()+":"+user.getList().get(position).getList().get(i).getNumber()+",";
            }

        }
        HttpConnectionUtil.getJsonJsonwithDialog(this, path, new String[]{"memberId","CartIdNumbers","DeliveryId","Belong","OrderType","OrderWay","CouponIssueId","OrderFreight"},
                new String[]{share.getMemberID(),CartIdNumbers,user.getAddress().getAddressId(),user.getList().get(position).getList().get(0).getBelong(),user.getList().get(position).getList().get(0).getStandardType(),"2",CouponIssueId,tfright+""}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                String id="";
                boolean type=false;
                try {
                    JSONObject js=new JSONObject(str);
                    JSONObject date=js.getJSONObject("Data");
                    id=date.getString("OrderNumber");
                    type=date.getBoolean("Type");
                    double Balance=date.getDouble("Balance");
                    double Commission=date.getDouble("Commission");
                    user.setBalance(Balance);
                    user.setCommission(Commission);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               if( FendanActivity.instance!=null){
                   FendanActivity.instance.finish();
               }
                user.getList().get(position).getList().get(0).setTijiao(true);
                JumpUtil.jumpWithValue2finash(GoodsorderActivity.this, ShouyinActivity.class, true, new String[]{"money", "CouponIssueId", "title", "position","id","initType","Type"}, new String[]{rmoney + "", CouponIssueId, user.getList().get(position).getList().get(0).getTitle(), position + "",id,"1",type?"0":"1"});
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK && requestCode==MYRESULT){
            double couponMoney = data.getDoubleExtra("money",0);
            youhuimoney.setText("-￥"+ FifUtil.getPrice(couponMoney));
//            rmoney-=couponMoney;
            double mMoney=mTotalMoney+tfright-couponMoney;
            rmoney=mMoney;
            resultmoney.setText("￥"+FifUtil.getPrice(rmoney));
            CouponIssueId=data.getStringExtra("id");
            layout_couponmoney.setVisibility(View.VISIBLE);
        }
        if(resultCode==RESULT_OK && requestCode==MYADDRESULT){
            addressmes.setVisibility(View.VISIBLE);
            addressselect.setVisibility(View.GONE);
            name.setText(data.getStringExtra("name"));
            phone.setText(data.getStringExtra("phone"));
            mes.setText(data.getStringExtra("address"));
            if( user.getAddress()==null){
                user.setAddress(new ShopAddress(data.getStringExtra("id"),data.getStringExtra("name"),data.getStringExtra("address"),data.getStringExtra("phone")));
            }else{
                user.getAddress().setAddressId(data.getStringExtra("id"));
            }
            changeAddress();

        }
    }
}
