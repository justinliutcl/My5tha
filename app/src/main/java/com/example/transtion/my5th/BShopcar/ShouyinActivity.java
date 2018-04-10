package com.example.transtion.my5th.BShopcar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.DIndividualActivity.Order.DSAllActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.alipay.AliPayUse;
import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.wxapi.WePayUser;

import InternetUser.PayUser.WePayState;
import InternetUser.shopcar.DingdanUser;
import InternetUser.shopcar.FendanUser;
import customUI.TopbarView;
import fifthutil.FifUtil;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import togglebutton.ToggleButton;

public class ShouyinActivity extends BaseActivity {
    TextView gwbmoney,yongjinmoney,totalmoney;
    ImageView alipay,wechat;
    LinearLayout layout_alipay,layout_wechat,layout_pay;
    ToggleButton toggwb,togyongjin;
    Button commit;
    AlertDialog ad;
    double resultmoney;
    double yongjin,gwb,otherpay;
    FendanUser user;
    boolean gwbflage,yongjinflage;
    int paytype;
    String path= Path.HOST+Path.ip+Path.SHOPCAR_PINGTAIPAY_PATH;
    String couponId,title;
    AliPayUse pay;
    int position;
    String id;
    String initType;
    String incomegwb,incomeyongjin;
    TopbarView topbar;

    //true不能用购物币
    String showtype;
    public static ShouyinActivity instance=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouyin);
        initView();
    }

    private void initView() {
        instance=this;
        topbar= (TopbarView) findViewById(R.id.topbar);
        gwbmoney= (TextView) findViewById(R.id.shouyin_gwbmoney);
        yongjinmoney= (TextView) findViewById(R.id.shouyin_yongjinmoney);
        totalmoney= (TextView) findViewById(R.id.shouyin_summoney);
        alipay= (ImageView) findViewById(R.id.shouyin_img_alipy);
        wechat= (ImageView) findViewById(R.id.shouyin_img_wepay);
        layout_alipay= (LinearLayout) findViewById(R.id.shouyin_layout_alipy);
        layout_wechat= (LinearLayout) findViewById(R.id.shouyin_layout_wepay);
        layout_pay= (LinearLayout) findViewById(R.id.shouyin_layout_pay);
        toggwb= (ToggleButton) findViewById(R.id.shouyin_gwbuse);
        togyongjin= (ToggleButton) findViewById(R.id.shouyin_yongjinuse);
        commit= (Button) findViewById(R.id.shouyin_commit);
        resultmoney=Double.parseDouble(getIntent().getStringExtra("money"));
        otherpay=resultmoney;
        yongjin=0;
        gwb=0;
        paytype=3;
        showtype=getIntent().getStringExtra("Type");
        if(showtype!=null) {
            if (showtype.equals("true")) {
                layout_pay.setVisibility(View.GONE);
            } else {
                layout_pay.setVisibility(View.VISIBLE);
            }
        }
        couponId=getIntent().getStringExtra("CouponIssueId");
        title=getIntent().getStringExtra("title");
        id=getIntent().getStringExtra("id");
        position=Integer.parseInt(getIntent().getStringExtra("position"));
        WePayState.getInstance().setPosition(position);
        totalmoney.setText("￥"+ FifUtil.getPrice(resultmoney));
        user=DingdanUser.getInstance().getFendanUser();
        if(user==null)
            user=new FendanUser();
        initType=getIntent().getStringExtra("initType");

        WePayState.getInstance().setInitType(initType);
        incomegwb=getIntent().getStringExtra("incomegwb");
        incomeyongjin=getIntent().getStringExtra("incomeyongjin");
        if(incomegwb!=null&&incomeyongjin!=null){
            if(incomegwb.equals("0"))
              user.setBalance(0);
            else
                user.setBalance(Double.parseDouble(incomegwb));
            if(incomeyongjin.equals("0"))
                user.setCommission(0);
            else
              user.setCommission(Double.parseDouble(incomeyongjin));
        }
        gwbflage=true;
        yongjinflage=true;

        setdialog();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(initType.equals("1")){
                int j=0;
                for (int i = 0; i < user.getList().size(); i++) {
                    if (!user.getList().get(i).getList().get(0).isFlage()) {
                        j++;
                        break;
                    }
                }
                if(j>1){
                    JumpUtil.jump2finish(ShouyinActivity.this, FendanActivity.class, false);
                }else{
                    JumpUtil.jump2finash(this);
                }
            }else{
                JumpUtil.jump2finash(this);
            }

            return true;
        }else
            return super.onKeyDown(keyCode, event);
    }

    private void setdialog() {
        AlertDialog.Builder ab=new AlertDialog.Builder(this, R.style.dialog);
        View view=View.inflate(this,R.layout.dialog_pay,null);
        ImageView close= (ImageView) view.findViewById(R.id.dialog_pay);
        final EditText password= (EditText) view.findViewById(R.id.dialog_pay_password);
        Button commit= (Button) view.findViewById(R.id.dialog_pay_commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pas=password.getText().toString();
                onlinepay(pas);
            }
        });
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String pas=password.getText().toString();
                onlinepay(pas);
                return true;
            }
        });
        close.setOnClickListener(this);
        ab.setView(view);
        ad=ab.create();
        ad.setCanceledOnTouchOutside(false);
        ad.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ad.getWindow().setWindowAnimations(R.style.dialog_updown_anim);
        ad.getWindow().setGravity(Gravity.CENTER);
    }


    public void onlinepay(String password){
        loding.showShapeLoding();
        HttpConnectionUtil.getJsonJsonwithDialog(this, path, new String[]{"memberId", "PayPwd", "orderNumber", "EToken", "Commission", "CouponId"},
                new String[]{share.getMemberID(), password, id, FifUtil.getPrice(gwb), FifUtil.getPrice(yongjin) , couponId}, loding, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        loding.disShapeLoding();
                        show("支付成功");

                        if (otherpay != 0) {
                            if (paytype == 1) {
                                pay = new AliPayUse(id, ShouyinActivity.this, title, otherpay, "购物", Path.ALIPAYPAY_PATH, new AliPayUse.OnPayCall() {
                                    @Override
                                    public void SuccessCallBack(String mes) {
                                        int aount=0;
                                        if (initType.equals("1")) {
                                            user.getList().get(position).getList().get(0).setFlage(true);
                                            for (int i = 0; i < user.getList().size(); i++) {
                                                if (!user.getList().get(i).getList().get(0).isFlage()) {
//                                                    JumpUtil.jump2finish(ShouyinActivity.this, FendanActivity.class, false);
                                                    aount++;
                                                    break;
                                                }
                                            }
                                            if(aount==0)
                                                JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayResultActivity.class, true, new String[]{"type"}, new String[]{"false"});
                                            else
                                                JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayResultActivity.class, true, new String[]{"type"}, new String[]{"true"});
                                        } else {
                                            if( DSAllActivity.instance!=null){
                                                DSAllActivity.instance.finish();
                                            }
                                            JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayResultActivity.class, true, new String[]{"type"}, new String[]{"false"});
                                        }

                                        show("支付成功");

                                    }

                                    @Override
                                    public void failCallBack(String mes) {
                                        int aount=0;
                                        if (initType.equals("1")) {
                                            user.getList().get(position).getList().get(0).setFlage(true);
                                            for (int i = 0; i < user.getList().size(); i++) {
                                                if (!user.getList().get(i).getList().get(0).isFlage()) {
//                                                    JumpUtil.jump2finish(ShouyinActivity.this, FendanActivity.class, false);
                                                    aount++;
                                                    break;
                                                }
                                            }
                                            if(aount==0)
                                                JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayfalseresultActivity.class, true, new String[]{"type"}, new String[]{"false"});
                                            else
                                                JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayfalseresultActivity.class, true, new String[]{"type"}, new String[]{"true"});
                                        } else {
                                            if( DSAllActivity.instance!=null){
                                                DSAllActivity.instance.finish();
                                            }
                                            JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayfalseresultActivity.class, true, new String[]{"type"}, new String[]{"false"});
                                        }
                                        show("支付失败");
                                    }
                                });
                                pay.pay();
                            }else{
                                WePayUser.wePay(ShouyinActivity.this,loding,id,otherpay);
                            }

                        } else {
                            int aount=0;
                            if (initType.equals("1")) {
                                user.getList().get(position).getList().get(0).setFlage(true);
                                for (int i = 0; i < user.getList().size(); i++) {
                                    if (!user.getList().get(i).getList().get(0).isFlage()) {
//                                        JumpUtil.jump2finish(ShouyinActivity.this, FendanActivity.class, false);
                                        aount++;
                                        break;
                                    }
                                }
                                if(aount==0)
                                    JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayResultActivity.class, true, new String[]{"type"}, new String[]{"false"});
                                else
                                    JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayResultActivity.class, true, new String[]{"type"}, new String[]{"true"});
                            }
                            else{
                                if( DSAllActivity.instance!=null){
                                    DSAllActivity.instance.finish();
                                }
                                JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayResultActivity.class, true, new String[]{"type"}, new String[]{"false"});
                            }
//                            if (user.getList() != null) {
//                                JumpUtil.jump2finash(ShouyinActivity.this);
//                            }
                            ad.dismiss();

                        }
                    }
                });
    }
    @Override
    public void setListener() {
        topbar.getleftbar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        commit.setOnClickListener(this);
        layout_alipay.setOnClickListener(this);
        layout_wechat.setOnClickListener(this);
        toggwb.setOnClickListener(this);
        togyongjin.setOnClickListener(this);
//        toggwb.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
//            @Override
//            public void onToggle(boolean on) {
//                    if(on){
//                        if(otherpay>user.getBalance()){
//                            gwbmoney.setText("选择支付"+user.getBalance());
//                            gwb=user.getBalance();
//                            otherpay-=user.getBalance();
//
//                        }else{
//                            gwbmoney.setText("选择支付"+otherpay);
//                            gwb=otherpay;
//                            otherpay=0;
//                        }
//                    }else{
//                        gwbmoney.setText("");
//                        otherpay+=gwb;
//                    }
//                totalmoney.setText("￥"+ FifUtil.getPrice(otherpay));
//            }
//        });
//
//        togyongjin.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
//            @Override
//            public void onToggle(boolean on) {
//                if(on){
//                    if(otherpay>user.getCommission()){
//                        yongjinmoney.setText("选择支付"+user.getCommission());
//                        yongjin=user.getCommission();
//                        otherpay-=user.getCommission();
//
//                    }else{
//                        yongjinmoney.setText("选择支付"+otherpay);
//                        yongjin=otherpay;
//                        otherpay=0;
//                    }
//                }else{
//                    yongjinmoney.setText("");
//                    otherpay+=yongjin;
//                }
//                totalmoney.setText("￥"+ FifUtil.getPrice(otherpay));
//            }
//        });
    }
    private void shouyingwb(){
        if(gwbflage){
            toggwb.setToggleOn();
            gwbflage=false;
            if(otherpay>user.getBalance()){
                gwbmoney.setText("选择支付"+FifUtil.getPrice(user.getBalance()));
                gwb=user.getBalance();
                otherpay-=user.getBalance();
                paytype=3;
            }else{
                gwbmoney.setText("选择支付"+FifUtil.getPrice(otherpay));
                gwb=otherpay;
                otherpay=0;
                alipay.setBackgroundResource(R.drawable.bg_radio3x);
                wechat.setBackgroundResource(R.drawable.bg_radio3x);
                paytype=3;
            }
        }else{
            toggwb.setToggleOff();
            gwbflage=true;
            gwbmoney.setText(" ");
            otherpay+=gwb;
            gwb=0;
        }
//        totalmoney.setText("￥"+ FifUtil.getPrice(otherpay));
    }

    private void shouyinyong(){
        if(yongjinflage){
            togyongjin.setToggleOn();
            yongjinflage=false;
            if(otherpay>user.getCommission()){
                yongjinmoney.setText("选择支付"+FifUtil.getPrice(user.getCommission()));
                yongjin=user.getCommission();
                otherpay-=user.getCommission();
                paytype=3;
            }else{

                yongjinmoney.setText("选择支付"+FifUtil.getPrice(otherpay));
                yongjin=otherpay;
                otherpay=0;
                alipay.setBackgroundResource(R.drawable.bg_radio3x);
                wechat.setBackgroundResource(R.drawable.bg_radio3x);
                paytype=3;
            }
        }else{
            togyongjin.setToggleOff();
            yongjinflage=true;
            yongjinmoney.setText(" ");
            otherpay+=yongjin;
            yongjin=0;
        }
//        totalmoney.setText("￥" + FifUtil.getPrice(otherpay));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_pay:
                ad.dismiss();
                break;
            case R.id.shouyin_gwbuse:
                shouyingwb();
                break;
            case R.id.shouyin_yongjinuse:
                shouyinyong();
                break;
            case R.id.shouyin_layout_alipy:
                if(otherpay>0){
                    alipay.setBackgroundResource(R.drawable.bg_radio_on3x);
                    wechat.setBackgroundResource(R.drawable.bg_radio3x);
                    paytype=1;
                }else{
                    show("无需支付宝支付");
                }
                break;
            case R.id.shouyin_layout_wepay:
                if(otherpay>0){
                    alipay.setBackgroundResource(R.drawable.bg_radio3x);
                    wechat.setBackgroundResource(R.drawable.bg_radio_on3x);
                    paytype=2;
                }else{
                    show("无需微信支付");
                }
                break;
            case R.id.shouyin_commit:

                if(otherpay>0.001&&paytype==3) {
                    show("亲，还不够支付呦，请添加支付宝或微信支付");
                }else{
                    if (yongjin != 0 || gwb != 0) {
                        ad.show();
                    } else {
                        if (paytype == 1) {
                            pay = new AliPayUse(id, ShouyinActivity.this, title, otherpay, "购物", Path.ALIPAYPAY_PATH, new AliPayUse.OnPayCall() {
                                @Override
                                public void SuccessCallBack(String mes) {
                                    if (initType.equals("1")) {
                                        int aount=0;
                                        user.getList().get(position).getList().get(0).setFlage(true);
                                        for (int i = 0; i < user.getList().size(); i++) {
                                            if (!user.getList().get(i).getList().get(0).isFlage()) {
//                                                JumpUtil.jump2finish(ShouyinActivity.this, FendanActivity.class, false);
                                                aount++;
                                                break;
                                            }
                                        }
                                        if(aount==0)
                                            JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayResultActivity.class, true, new String[]{"type"}, new String[]{"false"});
                                        else
                                            JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayResultActivity.class, true, new String[]{"type"}, new String[]{"true"});
                                    } else {
                                        if( DSAllActivity.instance!=null){
                                            DSAllActivity.instance.finish();
                                        }
                                        JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayResultActivity.class, true, new String[]{"type"}, new String[]{"false"});
                                    }
                                    show("支付成功");
                                }

                                @Override
                                public void failCallBack(String mes) {
                                    int aount=0;
                                    if (initType.equals("1")) {
                                        user.getList().get(position).getList().get(0).setFlage(true);
                                        for (int i = 0; i < user.getList().size(); i++) {
                                            if (!user.getList().get(i).getList().get(0).isFlage()) {
//                                                    JumpUtil.jump2finish(ShouyinActivity.this, FendanActivity.class, false);
                                                aount++;
                                                break;
                                            }
                                        }
                                        if(aount==0)
                                            JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayfalseresultActivity.class, true, new String[]{"type"}, new String[]{"false"});
                                        else
                                            JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayfalseresultActivity.class, true, new String[]{"type"}, new String[]{"true"});
                                    } else {
                                        if( DSAllActivity.instance!=null){
                                            DSAllActivity.instance.finish();
                                        }
                                        JumpUtil.jumpWithValue2finash(ShouyinActivity.this, PayfalseresultActivity.class, true, new String[]{"type"}, new String[]{"false"});
                                    }
                                    show("支付失败");
                                }
                            });
                            pay.pay();
                        } else if (paytype == 2) {
//                            startActivity(new Intent(this, PayActivity.class));
                            WePayUser.wePay(this,loding,id,otherpay);
                        }

                    }
                }
                break;
        }
    }
}
