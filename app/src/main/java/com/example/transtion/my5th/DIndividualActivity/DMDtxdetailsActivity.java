package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import InternetUser.Txdetails;
import fifthutil.FifUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class DMDtxdetailsActivity extends BaseActivity {
    TextView money,submittime,finishtime,shenhetype,txmoney,tdirection,paytime,paytype;
    ImageView finishimg,updown;
    LinearLayout details,content;
    String RecordId;
    String path= Path.HOST+Path.ip+Path.TIXIANDETAILS_PATH;
    Txdetails user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmdtxdetails);
        initview();
    }

    public void initview() {
        RecordId=getIntent().getStringExtra("RecordId");
        money= (TextView) findViewById(R.id.txdetails_money);
        submittime= (TextView) findViewById(R.id.txdetails_shenqingtime);
        finishtime= (TextView) findViewById(R.id.txdetails_finishtime);
        shenhetype= (TextView) findViewById(R.id.txdetails_shenhe);
        txmoney= (TextView) findViewById(R.id.txdetails_successmoney);
        tdirection= (TextView) findViewById(R.id.txdetails_direction);
        paytime= (TextView) findViewById(R.id.txdetails_paytime);
        paytype= (TextView) findViewById(R.id.txdetails_paydirection);
        finishimg= (ImageView) findViewById(R.id.txdetails_img_finishtime);
        updown= (ImageView) findViewById(R.id.txdetails_updown);
        details= (LinearLayout) findViewById(R.id.txdetails_layout_successdetails);
        content= (LinearLayout) findViewById(R.id.txdetails_layout_content);
        getJson();
    }

    private void getJson() {
        loding.showShapeLoding();
       HttpConnectionUtil.getGetJson(this, path + "?RecordId=" + RecordId,loding, new HttpConnectionUtil.OnJsonCall() {
           @Override
           public void JsonCallBack(String str) {
              user=HttpConnectionUtil.getTxdetailsUser(str);
               print(str);
               setView();
               loding.disShapeLoding();
           }
       });
    }

    @Override
    public void setListener() {
        details.setOnClickListener(this);
    }

    public void setView(){
        String[]t= FifUtil.getTime(user.getOperateTime());
        money.setText(user.getAmount());
        txmoney.setText("￥"+user.getAmount());
        submittime.setText(t[0]+"   "+t[1]);
        int i=Integer.parseInt(user.getAuditState());
        String type="审核中";
        switch (i){
            case 0:
                type="待审核";
                break;
            case 3:
                type="审核通过提现中";
                finishimg.setImageResource(R.drawable.radio_on);
                break;
            case 4:
                type="审核通过";
                finishimg.setImageResource(R.drawable.radio_on);
                break;
            case 5:
                type="审核提现失败";
                finishimg.setImageResource(R.drawable.radio_on);
                break;
            case 6:
                type="提现失败";
                finishimg.setImageResource(R.drawable.radio_on);
                break;
            default:
                break;
        }
        shenhetype.setText(type);
        if(i!=0){
            String[]s=FifUtil.getTime(user.getAuditTime());
            finishtime.setText(s[0]+"   "+s[1]);
        }else{
            finishtime.setText(t[0]+"   "+t[1]);
        }
        if(i==4){
            details.setVisibility(View.VISIBLE);
            String[]p=FifUtil.getTime(user.getAuditTime());
            tdirection.setText("提现至"+user.getWithdrawCashType());
            paytime.setText(p[0]+p[1]);
            paytype.setText("已将资金转移至"+user.getWithdrawCashType());

        }

    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.txdetails_layout_successdetails:

                    if(content.getVisibility()==View.GONE){
                        content.setVisibility(View.VISIBLE);
                        updown.setImageResource(R.drawable.button_down);
                    }else{
                        content.setVisibility(View.GONE);
                        updown.setImageResource(R.drawable.button_up);
                    }
                    break;
                default:
                    break;
            }
    }


}
