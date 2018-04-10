package com.example.transtion.my5th.DIndividualActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import fifthutil.EvaluateSelectUtilA;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class EvaluateActivity extends BaseActivity {
    TextView goodtitle;
    LinearLayout starlinear;
    EditText evaluate;
    ImageView add1,add2,add3,add4,add5,addimg1,addimg2,addimg3,addimg4,addimg5,goodimg,star1,star2,star3,star4,star5;
    Button commit;
    ImageUtil imageUtil;
    String ordernum,productId;
    EvaluateSelectUtilA evaluateSelectUtilA;
    String sum="5";
    String path= Path.HOST+Path.ip+Path.EVALUATE_COMMIT_PATH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        initView();
    }

    private void initView() {
        goodtitle= (TextView) findViewById(R.id.evaluate_goodtitle);
        starlinear= (LinearLayout) findViewById(R.id.evaluate_layout_star);
        evaluate= (EditText) findViewById(R.id.evaluate_evaluates);
        commit= (Button) findViewById(R.id.evaluate_commit);
        add1= (ImageView) findViewById(R.id.evaluate_jia1);
        add2= (ImageView) findViewById(R.id.evaluate_jia2);
        add3= (ImageView) findViewById(R.id.evaluate_jia3);
        add4= (ImageView) findViewById(R.id.evaluate_jia4);
        add5= (ImageView) findViewById(R.id.evaluate_jia5);
        addimg1= (ImageView) findViewById(R.id.evaluate_jiaimg1);
        addimg2= (ImageView) findViewById(R.id.evaluate_jiaimg2);
        addimg3= (ImageView) findViewById(R.id.evaluate_jiaimg3);
        addimg4= (ImageView) findViewById(R.id.evaluate_jiaimg4);
        addimg5= (ImageView) findViewById(R.id.evaluate_jiaimg5);
        goodimg= (ImageView) findViewById(R.id.evaluate_goodimg);
        star1= (ImageView) findViewById(R.id.evaluate_star1);
        star2= (ImageView) findViewById(R.id.evaluate_star2);
        star3= (ImageView) findViewById(R.id.evaluate_star3);
        star4= (ImageView) findViewById(R.id.evaluate_star4);
        star5= (ImageView) findViewById(R.id.evaluate_star5);

        evaluateSelectUtilA=new EvaluateSelectUtilA(this,this);
        imageUtil=new ImageUtil(this);
        Intent intent=getIntent();
        imageUtil.display(goodimg,intent.getStringExtra("path"));
        goodtitle.setText(intent.getStringExtra("title"));
        ordernum=intent.getStringExtra("ordernum");
        productId=intent.getStringExtra("productId");
    }

    private void setStar(int num){
        for(int i=0;i<5;i++){
                if(i<num){
                    starlinear.getChildAt(i).setBackgroundResource(R.drawable.star);
                }else{
                    starlinear.getChildAt(i).setBackgroundResource(R.drawable.star_no);
                }
        }
    }
    @Override
    public void setListener() {
        add1.setOnClickListener(this);
        add2.setOnClickListener(this);
        add3.setOnClickListener(this);
        add4.setOnClickListener(this);
        add5.setOnClickListener(this);
        star1.setOnClickListener(this);
        star2.setOnClickListener(this);
        star3.setOnClickListener(this);
        star4.setOnClickListener(this);
        star5.setOnClickListener(this);
        commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.evaluate_star1:
                setStar(1);
                sum="1";
                break;
            case R.id.evaluate_star2:
                setStar(2);
                sum="2";
                break;
            case R.id.evaluate_star3:
                setStar(3);
                sum="3";
                break;
            case R.id.evaluate_star4:
                setStar(4);
                sum="4";
                break;
            case R.id.evaluate_star5:
                setStar(5);
                sum="5";
                break;
            case R.id.evaluate_jia1:
                evaluateSelectUtilA.setimg(add1, addimg1);
                evaluateSelectUtilA.showDialog();
                break;
            case R.id.evaluate_jia2:
                evaluateSelectUtilA.setimg(add2,addimg2);
                evaluateSelectUtilA.showDialog();
                break;
            case R.id.evaluate_jia3:
                evaluateSelectUtilA.setimg(add3,addimg3);
                evaluateSelectUtilA.showDialog();
                break;
            case R.id.evaluate_jia4:
                evaluateSelectUtilA.setimg(add4,addimg4);
                evaluateSelectUtilA.showDialog();
                break;
            case R.id.evaluate_jia5:
                evaluateSelectUtilA.setimg(add5,addimg5);
                evaluateSelectUtilA.showDialog();
                break;
            case R.id.evaluate_commit:
                if(evaluateSelectUtilA.isFlage()){
                    commit();
                }else{
                    show("还在上传图片请稍后");
                }
                break;
        }
    }

    private void commit() {
        loding.showShapeLoding();
        String mes=evaluate.getText().toString();
        if(mes.length()<10){
            show("请输入至少十字的评论");
            loding.disShapeLoding();
        }else {
            String a = evaluateSelectUtilA.getImgurl();
            HttpConnectionUtil.getJsonJsonwithDialog(this, path, new String[]{"memberId", "ordernumber", "productId", "Title", "StarValue", "ImageUrl", "Reply", "ThreadText"},
                    new String[]{share.getMemberID(), ordernum, productId, goodtitle.getText().toString(), sum, evaluateSelectUtilA.getImgurl(), evaluate.getText().toString(), evaluate.getText().toString()}, loding, new HttpConnectionUtil.OnJsonCall() {
                        @Override
                        public void JsonCallBack(String str) {
                            show("评价成功");
                            loding.disShapeLoding();
                            JumpUtil.jump2finash(EvaluateActivity.this);
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        evaluateSelectUtilA.forresult(requestCode, resultCode, data);
    }
}
