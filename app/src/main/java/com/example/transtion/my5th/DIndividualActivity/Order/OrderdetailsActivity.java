package com.example.transtion.my5th.DIndividualActivity.Order;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.BShopcar.LogisticActivity;
import com.example.transtion.my5th.BShopcar.ShouyinActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import java.util.List;

import InternetUser.order.GoodsDetailUser;
import InternetUser.order.LogisticsItem;
import InternetUser.order.OrderDetailItem;
import fifthutil.FifUtil;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class OrderdetailsActivity extends BaseActivity {
    String path= Path.HOST+Path.ip+Path.ORDERDETAIL_PATH;
    TextView tradetype,ordernum,logistictype,logisttime,name,phone,address,goodstype,paymoney,totalmoney,feight,couponmoney,shouldmoney,ordertime,ordercomment;
    Button delete,logist,buyagain;
    LinearLayout goods,pay,coupon,layout_logist,layout_paytype;
    GoodsDetailUser user;
    String orderId,type,orderstate;
    ImageUtil imageUtil;
    String path_delete=Path.HOST+Path.ip+Path.ORDERDETAIL_DELETE2RECYCLE_PATH;
    String path_buy;
    double shouldpay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        initView();
    }

    private void initView() {
        orderId=getIntent().getStringExtra("orderId");
        type=getIntent().getStringExtra("type");
        orderstate=getIntent().getStringExtra("orderstate");

        tradetype= (TextView) findViewById(R.id.orderdetail_tradetype);
        ordernum= (TextView) findViewById(R.id.orderdetail_ordernum);
        logistictype= (TextView) findViewById(R.id.orderdetail_logistictype);
        logisttime= (TextView) findViewById(R.id.orderdetail_logistictime);
        name= (TextView) findViewById(R.id.orderdetail_name);
        phone= (TextView) findViewById(R.id.orderdetail_phone);
        address= (TextView) findViewById(R.id.orderdetail_address);
        goodstype= (TextView) findViewById(R.id.orderdetail_goodstype);
        paymoney= (TextView) findViewById(R.id.orderdetail_paymoney);
        totalmoney= (TextView) findViewById(R.id.orderdetail_totalmoney);
        feight= (TextView) findViewById(R.id.orderdetail_freight);
        couponmoney= (TextView) findViewById(R.id.orderdetail_couponmoney);
        shouldmoney= (TextView) findViewById(R.id.orderdetail_shouldpay);
        ordertime= (TextView) findViewById(R.id.orderdetail_ordertime);
        ordercomment= (TextView) findViewById(R.id.orderdetail_goodstypecomment);

        delete= (Button) findViewById(R.id.orderdetail_delete);
        logist= (Button) findViewById(R.id.orderdetail_seelogistic);
        buyagain= (Button) findViewById(R.id.orderdetail_bugagain);
        goods= (LinearLayout) findViewById(R.id.orderdetail_layout_goods);
        layout_logist= (LinearLayout) findViewById(R.id.orderdetail_layout_logist);
        pay= (LinearLayout) findViewById(R.id.orderdetail_layout_pay);
        layout_paytype= (LinearLayout) findViewById(R.id.orderdetail_layout_paytype);
        coupon= (LinearLayout) findViewById(R.id.orderdetail_layout_coupon);
        if(orderstate.equals("0")){
            logist.setVisibility(View.GONE);
            buyagain.setVisibility(View.VISIBLE);
            buyagain.setText("前去付款");
        }
        int orderstateInt=Integer.parseInt(orderstate);
        if(orderstateInt<=5)
            delete.setVisibility(View.GONE);
        imageUtil=new ImageUtil(this);
        getJson();

    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?MemberId=" + share.getMemberID() + "&orderNumber=" + orderId, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getGoodsDetailUser(str);
                Log.i("orderdetil",str);
                loding.disShapeLoding();
                if(user!=null)
                     setView();
            }
        });
    }

    private void setView() {
        tradetype.setText(type);

        ordernum.setText(user.getOrderNumber());
        List<LogisticsItem> orderlist=user.getOrderRecordSummaryList();
        if(!orderlist.isEmpty()) {
            logistictype.setText("物流信息：" + orderlist.get(orderlist.size() - 1).getOperationContext());
            String[]t= FifUtil.getTime(orderlist.get(orderlist.size() - 1).getOperationTime());
            logisttime.setText(t[0]+" "+t[1]);
        }else{
            layout_logist.setVisibility(View.GONE);
        }


        name.setText(user.getDeliveryName());
        phone.setText(user.getMobile());
        address.setText(user.getAddress());
        double havePay=user.getElectronicToken()+user.getCommission()+user.getThirdPayAmount();
        paymoney.setText("￥"+FifUtil.getPrice(havePay));
        totalmoney.setText("￥"+FifUtil.getPrice(user.getTotal()-user.getFreight()+user.getCouponMoney()));
        feight.setText(FifUtil.getPrice(user.getFreight()));
        if(user.getCouponMoney()==0){
            coupon.setVisibility(View.GONE);
        }
        couponmoney.setText("-￥"+FifUtil.getPrice(user.getCouponMoney()));
        if(orderstate.equals("0")){
            shouldpay=user.getTotal()-havePay;
            shouldmoney.setText("￥"+FifUtil.getPrice(shouldpay));
        }else{
            shouldmoney.setText("￥"+FifUtil.getPrice(user.getTotal()));
        }

        String[]t2= FifUtil.getTime(user.getOperateTime());
        ordertime.setText(t2[0]+" "+t2[1]);
        goodstype.setText("一般贸易产品");
        switch (user.getList().get(0).getBelongType()){
            case "1":
                goodstype.setText("极速保税");
                break;
            case "2":
                goodstype.setText("极速保税");
                break;
            default:
                goodstype.setText("匠心独寻");
                break;
        }

        setgoodLayout();
        setpaymoneyLayout();
    }

    private void setgoodLayout(){
        List<OrderDetailItem>tlist=user.getList();
        if(tlist.size()<2)
            ordercomment.setVisibility(View.GONE);
        for(OrderDetailItem orderDetailItem:tlist){
            View v=View.inflate(this,R.layout.adapter_orderlist,null);
            ImageView img= (ImageView) v.findViewById(R.id.linearlayout_orderlist_img);
            TextView title= (TextView) v.findViewById(R.id.linearlayout_orderlist_mes);
            TextView attr= (TextView) v.findViewById(R.id.linearlayout_orderlist_attr);
            TextView money= (TextView) v.findViewById(R.id.linearlayout_orderlist_money);
            TextView sum= (TextView) v.findViewById(R.id.linearlayout_orderlist_sum);
            String path=orderDetailItem.getImageSrc();
            imageUtil.display(img,path);
            if(orderDetailItem.getOrderTatilAttributeList().size()!=0){
                for(int i=0;i<orderDetailItem.getOrderTatilAttributeList().size();i++){
                    if(i==0){
                        attr.setText(orderDetailItem.getOrderTatilAttributeList().get(i).getValue());
                    }else{
                        attr.append("|"+(orderDetailItem.getOrderTatilAttributeList().get(i).getValue()));
                    }
                }
            }
            money.setText("￥"+orderDetailItem.getPrice());
            sum.setText("×"+orderDetailItem.getNumber());
            title.setText(orderDetailItem.getTitle());
            goods.addView(v);
        }
    }
    private void setpaymoneyLayout(){
        int i=0;
        if(user.getElectronicToken()!=0){
            View v=View.inflate(this,R.layout.adapter_payline,null);
            TextView paytype= (TextView) v.findViewById(R.id.payline_type);
            TextView paymoney= (TextView) v.findViewById(R.id.payline_money);
            TextView paytime= (TextView) v.findViewById(R.id.payline_time);
            String[]t= FifUtil.getTime(user.getOperateTime());
            paytime.setText(t[0]+" "+t[1]);
            paymoney.setText(FifUtil.getPrice(user.getElectronicToken()));
            paytype.setText("购物币支付:");
            pay.addView(v);
            i++;
        }
        if(user.getCommission()!=0){
            View v=View.inflate(this,R.layout.adapter_payline,null);
            TextView paytype= (TextView) v.findViewById(R.id.payline_type);
            TextView paymoney= (TextView) v.findViewById(R.id.payline_money);
            TextView paytime= (TextView) v.findViewById(R.id.payline_time);
            String[]t= FifUtil.getTime(user.getOperateTime());
            paytime.setText(t[0]+" "+t[1]);
            paymoney.setText(FifUtil.getPrice(user.getCommission()));
            paytype.setText("佣金支付:");
            pay.addView(v);
            i++;
        }
        if(user.getThirdPayAmount()!=0){
            View v=View.inflate(this,R.layout.adapter_payline,null);
            TextView paytype= (TextView) v.findViewById(R.id.payline_type);
            TextView paymoney= (TextView) v.findViewById(R.id.payline_money);
            TextView paytime= (TextView) v.findViewById(R.id.payline_time);
            String[]t= FifUtil.getTime(user.getOperateTime());
            paytime.setText(t[0]+" "+t[1]);
            paymoney.setText(FifUtil.getPrice(user.getThirdPayAmount()));
            paytype.setText(user.getThirdPayType()+":");
            pay.addView(v);
            i++;
        }
        if(i<1){
            layout_paytype.setVisibility(View.GONE);
        }
//        layout_paytype.setVisibility(View.GONE);
    }

    @Override
    public void setListener() {
        logist.setOnClickListener(this);
        delete.setOnClickListener(this);
        buyagain.setOnClickListener(this);
    }

    private void delete(){
        loding.showShapeLoding();
        HttpConnectionUtil.getJsonJsonwithDialog(this, path_delete, new String[]{"MemberId","Ordernumber"}, new String[]{share.getMemberID(),orderId}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                show("删除成功");
                JumpUtil.jump2finash(OrderdetailsActivity.this);
            }
        });
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.orderdetail_delete:
                    delete();
                    break;
                case R.id.orderdetail_seelogistic:
                    JumpUtil.jumpWithValue(this, LogisticActivity.class,new String[]{"orderNumber"},new String[]{orderId},true);
                    break;
                case R.id.orderdetail_bugagain:

                    JumpUtil.jumpWithValue2finash(this, ShouyinActivity.class, true, new String[]{"money", "CouponIssueId", "title", "position","id","initType","incomegwb","incomeyongjin"}, new String[]{shouldpay+"", "", user.getList().get(0).getTitle(), 0 + "",user.getOrderNumber(),"2",getIntent().getStringExtra("Balance"),getIntent().getStringExtra("Commission")});
                    break;
            }
    }
}
