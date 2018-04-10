package com.example.transtion.my5th.DIndividualActivity.MyWallet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.alipay.AliPayUse;
import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.wxapi.WePayUser;

import InternetUser.AllHost;
import InternetUser.PayUser.WePayState;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class ChonglineActivity extends BaseActivity {
    EditText money;
    LinearLayout alipay,wepay;
    Button commit;
    int type=1;
    ImageView iv_alipay,iv_wepay;
    AliPayUse pay;
    String path=Path.HOST+Path.ip+Path.ONLINENUM_PATH;
    public static ChonglineActivity instace=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongline);
        instace=this;
        commit= (Button) findViewById(R.id.chongonline_commit);
        money= (EditText) findViewById(R.id.chongonline_money);
        alipay= (LinearLayout) findViewById(R.id.chongonline_layout_alipy);
        wepay= (LinearLayout) findViewById(R.id.chongonline_layout_wepay);
        iv_alipay= (ImageView) findViewById(R.id.chongonline_img_alipy);
        iv_wepay= (ImageView) findViewById(R.id.chongonline_img_wepay);
    }

    @Override
    public void setListener() {
        commit.setOnClickListener(this);
        alipay.setOnClickListener(this);
        wepay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chongonline_commit:
                String mmoney=money.getText().toString();
                if(mmoney.length()<1){
                    show("请输入金额");
                }else {
                    if (Double.parseDouble(mmoney) > 0) {
                        if (type == 1) {
                            getOnlineNum(mmoney);
                        } else {
                            wePay(mmoney);
                        }
                    } else {
                        show("每次充值必须大于0");
                    }
                }
                break;
            case R.id.chongonline_layout_alipy:
                type=1;
                iv_alipay.setBackgroundResource(R.drawable.bg_radio_on3x);
                iv_wepay.setBackgroundResource(R.drawable.bg_radio3x);
                break;
            case R.id.chongonline_layout_wepay:
                type=2;
                iv_alipay.setBackgroundResource(R.drawable.bg_radio3x);
                iv_wepay.setBackgroundResource(R.drawable.bg_radio_on3x);
                break;
        }
    }

    public void getOnlineNum(final String onlinemoney){
        loding.showShapeLoding();
        HttpConnectionUtil.getJsonJsonwithDialog(this, path, new String[]{"MemberId", "PayAmountTemp"}, new String[]{share.getMemberID(), onlinemoney}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                AllHost host = HttpConnectionUtil.getAllHost(str);
                alipay(host.getData(), onlinemoney);
            }
        });
    }


    public void alipay(String num,String mmoney){
        pay=new AliPayUse(num, ChonglineActivity.this, "第五大街在线充值", Double.parseDouble(mmoney), "充值", Path.ALIPAYCHONG_PATH, new AliPayUse.OnPayCall() {
            @Override
            public void SuccessCallBack(String mes) {
                DWdqbActivity.instance.finish();
                JumpUtil.jump2finash(ChonglineActivity.this);

                show("充值成功");
            }

            @Override
            public void failCallBack(String mes) {
                show("充值失败");
            }
        });
        pay.pay();
    }

    public void wePay(String mmoney){
       WePayState.getInstance().setState(0);
        WePayUser.wePayonline(this, loding, Double.parseDouble(mmoney));
    }


}
