package com.example.transtion.my5th.Setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import InternetUser.SettingUser;
import customUI.MyImageView;
import fifthutil.FifUtil;
import fifthutil.JumpUtil;
import fifthutil.OptsBitmapUtil;
import fifthutil.PhotoSelectUtilA;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class DSetingActivity extends BaseActivity {
    LinearLayout layout_img,layout_name,layout_sex,layout_class,layout_phone,layout_signcode,layout_paycode,layout_bindwichat,layout_bindqq,layout_bindwibo;
    TextView name,sex,level,phone,paycode,bindwichat,bindqq,bindwibo;
    MyImageView img;
    PhotoSelectUtilA photoUtil;
    String oldJson;
    SettingUser user;
    AlertDialog ad;
    String selectitem[]={"男","女","保密","取消"};
    String path= Path.HOST+Path.ip+Path.SETING_PATH;
    String sexPath=Path.HOST+Path.ip+Path.SETSEX_PATH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dseting);
        intoview();
    }

    private void intoview() {
        layout_img= (LinearLayout) findViewById(R.id.seting_layout_img);
        layout_name= (LinearLayout) findViewById(R.id.seting_layout_name);
        layout_sex= (LinearLayout) findViewById(R.id.seting_layout_sex);
        layout_class= (LinearLayout) findViewById(R.id.seting_layout_class);
        layout_phone= (LinearLayout) findViewById(R.id.seting_layout_phone);
        layout_signcode= (LinearLayout) findViewById(R.id.seting_layout_signpassword);
        layout_paycode= (LinearLayout) findViewById(R.id.seting_layout_paypassword);
        layout_bindwichat= (LinearLayout) findViewById(R.id.seting_layout_bindwechat);
        layout_bindqq= (LinearLayout) findViewById(R.id.seting_layout_bindqq);
        layout_bindwibo= (LinearLayout) findViewById(R.id.seting_layout_bindwibo);

        img= (MyImageView) findViewById(R.id.seting_img);

        name= (TextView) findViewById(R.id.seting_name);
        sex= (TextView) findViewById(R.id.seting_sex);
        level= (TextView) findViewById(R.id.seting_class);
        phone= (TextView) findViewById(R.id.seting_phone);
        paycode= (TextView) findViewById(R.id.seting_paypassword);
        bindwichat= (TextView) findViewById(R.id.seting_wichat);
        bindqq= (TextView) findViewById(R.id.seting_qq);
        bindwibo= (TextView) findViewById(R.id.seting_wichat);

        photoUtil=new PhotoSelectUtilA(this,this);
        setDialog();
        setOldview();
        getJson();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getJson();
    }

    private void getJson() {
            HttpConnectionUtil.getGetJson(this, path + "?memberId=" + ShareUtil.getInstanse(this).getMemberID(), loding, new HttpConnectionUtil.OnJsonCall() {
                @Override
                public void JsonCallBack(String str) {
                    share.setSetingJson(str);
                    if (str != oldJson){
                        setView(str);
                        print(str);
                    }
                    loding.disShapeLoding();
                }
            });
    }

    private void setOldview() {
        String path=share.getImgPath();
        if(path!=null)
            img.setImageBitmap(OptsBitmapUtil.calculatorBitmap(path, img));
        oldJson=share.getSetingJson();
        if(oldJson.length()>10){
            setView(oldJson);
            print(oldJson);
        }else{
            loding.showShapeLoding();
        }
    }

    @Override
    public void setListener() {
        layout_img.setOnClickListener(this);
        layout_name.setOnClickListener(this);
        layout_sex.setOnClickListener(this);
        layout_class.setOnClickListener(this);
        layout_phone.setOnClickListener(this);
        layout_signcode.setOnClickListener(this);
        layout_paycode.setOnClickListener(this);
        layout_bindwichat.setOnClickListener(this);
        layout_bindqq.setOnClickListener(this);
        layout_bindwibo.setOnClickListener(this);

    }

    private void setView(String json){
        user=HttpConnectionUtil.getSetingUser(json);

        name.setText(user.getNickName());
        sex.setText(user.getSex());
        level.setText(user.getLevel());
        phone.setText(FifUtil.getSecretPhone(user.getPhone()));
        if(user.getIsSetPayPwd())
            paycode.setText("未设置");
        else
            paycode.setText("修改");
        setBind(bindwibo, user.getIsOpenWb());
        setBind(bindwichat, user.getIsOpenWx());
        setBind(bindqq, user.getIsOpenQ());

    }

    public void setBind(TextView text,boolean flage){
        if(flage)
            text.setVisibility(View.GONE);
        else
            text.setVisibility(View.VISIBLE);
    }
    public void setSex(String num, final String ss){
        loding.showShapeLoding();
        HttpConnectionUtil.getJsonJsonwithDialog(this, sexPath, new String[]{"MemberId","Sex"}, new String[]{share.getMemberID(),num}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                sex.setText(ss);
                show("设置成功");
            }
        });
    }
    private void setDialog() {
        AlertDialog.Builder ab=new AlertDialog.Builder(this, R.style.dialog);
        ab.setItems(selectitem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Sex;
                switch (which) {
                    case 0:
                        Sex="1";
                        setSex(Sex,selectitem[0]);
                        break;
                    case 1:
                        Sex="0";
                        setSex(Sex,selectitem[1]);
                        break;
                    case 2:
                        Sex="2";
                        setSex(Sex,selectitem[2]);
                        break;
                    default:
                        break;
                }

            }
        });

        ad=ab.create();
        ad.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ad.getWindow().setWindowAnimations(R.style.dialog_updown_anim);
        ad.getWindow().setGravity(Gravity.BOTTOM);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.seting_layout_img:
                    photoUtil.setimg(img);
                    photoUtil.showDialog();
                    break;
                case R.id.seting_layout_name:
                    JumpUtil.jumpWithValue(this, DSnameActivity.class, new String[]{"name"}, new String[]{user.getNickName()}, true);
                    break;
                case R.id.seting_layout_sex:
                    ad.show();
                    break;
                case R.id.seting_layout_phone:
                    JumpUtil.jumpWithValue(this,ChangehoneActivity.class,new String[]{"phone"},new String[]{user.getPhone()},true);
                    break;
                case R.id.seting_layout_signpassword:
                    JumpUtil.jump(this,ChangeLoginActivity.class,true);
                    break;
                case R.id.seting_layout_paypassword:
                    JumpUtil.jump(this,SetpaycodeActivity.class,true);
                    break;
                case R.id.seting_layout_bindwechat:

                    break;
                case R.id.seting_layout_bindqq:

                    break;
                case R.id.seting_layout_bindwibo:

                    break;

            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoUtil.forresult(requestCode, resultCode, data);
    }
}
