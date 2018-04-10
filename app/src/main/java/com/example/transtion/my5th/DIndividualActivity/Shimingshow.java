package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import InternetUser.IndividualFrag.Realname;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class Shimingshow extends BaseActivity {
    public static Shimingshow instance = null;
    String path= Path.HOST+Path.ip+Path.REALNAME_PATH;
    ImageView img;
    LinearLayout layout_mes;
    TextView name,idcard,type;
    Button commit;
    Realname user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimingshow);
        initView();
    }

    private void initView() {
        instance=this;
        img= (ImageView) findViewById(R.id.shimingshow_iv_nonereal);
        layout_mes= (LinearLayout) findViewById(R.id.shimingshow_layout_mes);
        name= (TextView) findViewById(R.id.shimingshow_tv_name);
        idcard= (TextView) findViewById(R.id.shimingshow_tv_idcardnum);
        type= (TextView) findViewById(R.id.shimingshow_tv_nonereal);
        commit= (Button) findViewById(R.id.shimingshow_commit);
        commit.setEnabled(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getJson();
    }

    @Override
    public void setListener() {
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpWithValue(Shimingshow.this,ShimingdetialActivity.class,new String[]{"type"},new String[]{"1"},true);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    public void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?memberId=" + share.getMemberID(), loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user= HttpConnectionUtil.getRealname(str);
                loding.dismiss();
                setView();
                commit.setEnabled(true);
            }
        });
    }

    private void setView() {
        if(user.getId()!=null){
            switch (user.getAuditStatus()){
                case "0":
                    type.setText("审核中");
                    img.setBackgroundResource(R.drawable.realname_audit);
                    commit.setBackgroundResource(R.color.main_gry);
                    commit.setClickable(false);
                    commit.setEnabled(false);
                    break;
                case "1":
                    type.setText("认证成功");
                    commit.setVisibility(View.GONE);
                    layout_mes.setVisibility(View.VISIBLE);
                    img.setBackgroundResource(R.drawable.realname_auditsucces);
                    name.setText(user.getRealName());
                    idcard.setText(user.getIDCard());
                    break;
                case "2":
                    type.setText("审核失败");
                    img.setBackgroundResource(R.drawable.realname_auditflase);
                    commit.setBackgroundResource(R.drawable.buttonback);
                    commit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            JumpUtil.jumpWithValue(Shimingshow.this, ShimingdetialActivity.class, new String[]{"type", "id", "name", "idcard", "FrontImage", "BackImage"}, new String[]{"2", user.getId(), user.getRealName(), user.getIDCard(), user.getFrontImage(), user.getBackImage()}, true);
                        }
                    });
                    break;
            }
        }
    }
}
