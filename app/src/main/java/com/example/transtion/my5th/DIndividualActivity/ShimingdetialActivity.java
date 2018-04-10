package com.example.transtion.my5th.DIndividualActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import InternetUser.AllHost;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;
import fifthutil.PhotoSelectUtilsA;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import httpConnection.UploadUtil;

public class ShimingdetialActivity extends BaseActivity implements UploadUtil.OnUploadProcessListener{
    String id,name,idcard,frontimage,backimage,type;
    ImageView frontimg,backimg;
    Button commit;
    EditText et_name,et_idcard;
    ImageUtil imageUtil;
    PhotoSelectUtilsA fron,back;
    int selecttype;
    String path=Path.HOST + Path.ip + Path.REALNAME_IMGUPLOAD_PATH;
    String path2=Path.HOST + Path.ip + Path.REALNAME_HAVEIMGUPLOAD_PATH;
    int chance=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimingdetial);
        initView();
    }

    private void initView() {
        frontimg= (ImageView) findViewById(R.id.shimingdetial_iv_frontcard);
        backimg= (ImageView) findViewById(R.id.shimingdetial_iv_backcard);
        commit= (Button) findViewById(R.id.shimingdetial_commit);
        et_name= (EditText) findViewById(R.id.shimingdetial_et_name);
        et_idcard= (EditText) findViewById(R.id.shimingdetial_et_idcard);


        imageUtil=new ImageUtil(this);

        fron=new PhotoSelectUtilsA(this,this);
        fron.setimg(frontimg);
        back=new PhotoSelectUtilsA(this,this);
        back.setimg(backimg);

        type=getIntent().getStringExtra("type");
        if(type.equals("2"))
            {
            id=getIntent().getStringExtra("id");
            name=getIntent().getStringExtra("name");
            idcard=getIntent().getStringExtra("idcard");
            frontimage=getIntent().getStringExtra("FrontImage");
            backimage=getIntent().getStringExtra("BackImage");
            et_name.setText(name);
            et_idcard.setText(idcard);
            imageUtil.display(frontimg, frontimage);
            imageUtil.display(backimg, backimage);
        }


    }

    @Override
    public void setListener() {
        frontimg.setOnClickListener(this);
        backimg.setOnClickListener(this);
        commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.shimingdetial_iv_frontcard:
                    selecttype=1;
                    fron.showDialog();
                    break;
                case R.id.shimingdetial_iv_backcard:
                    selecttype=2;
                    back.showDialog();
                    break;
                case R.id.shimingdetial_commit:
                    if(type.equals("1")){
                        UploadUtil uploadUtil=UploadUtil.getInstance();
                        uploadUtil.setOnUploadProcessListener(ShimingdetialActivity.this);
                        Map<String, String> params = new HashMap<String, String>();
                        String memberId=share.getMemberID();
                        String RealName=et_name.getText().toString();
                        String IDCard=et_idcard.getText().toString();
                        params.put("memberId", memberId);
                        params.put("RealName", RealName);
                        params.put("IDCard", IDCard);

                        String fpath=fron.getImgpath();
                        String bpath=back.getImgpath();
                        if(fpath!=null&&bpath!=null){
                            loding.showloadingbutton();
                            uploadUtil.upmanyloadFile(new String[]{fpath,bpath}, new String[]{"Front","Back"},path , params, loding);
                        }
                        else
                            show("请添加身份证正反面照片");
                    }else{
                        if(chance==0)
                         pushRealName();
                        else if(chance==1){
                            UploadUtil uploadUtil=UploadUtil.getInstance();
                            uploadUtil.setOnUploadProcessListener(ShimingdetialActivity.this);
                            Map<String, String> params = new HashMap<String, String>();
                            String memberId=share.getMemberID();
                            String RealName=et_name.getText().toString();
                            String IDCard=et_idcard.getText().toString();
                            params.put("memberId", memberId);
                            params.put("RealName", RealName);
                            params.put("IDCard", IDCard);
                            params.put("Id", id);
                            String fpath=fron.getImgpath();
                            String bpath=back.getImgpath();
                            if(fpath==null){
                                params.put("FrontImage", frontimage);
                                loding.showloadingbutton();
                                uploadUtil.upmanyloadFile(new String[]{bpath}, new String[]{"Back"}, path, params, loding);
                            }else if(bpath==null){
                                params.put("BackImage", backimage);
                                loding.showloadingbutton();
                                uploadUtil.upmanyloadFile(new String[]{fpath}, new String[]{"Front"}, path, params, loding);
                            }else if(fpath!=null&&bpath!=null){
                                loding.showloadingbutton();
                                uploadUtil.upmanyloadFile(new String[]{fpath,bpath}, new String[]{"Front","Back"},path , params, loding);
                            }

                        }
                    }
                    break;
            }
    }

    public void commitChange(){

    }

    private void pushRealName() {
        loding.showloadingbutton();
        String memberId=share.getMemberID();
        String RealName=et_name.getText().toString();
        String IDCard=et_idcard.getText().toString();

        HttpConnectionUtil.getJsonJsonwithDialog(this, path2, new String[]{"MemberId","RealName","IDCard","Id","FrontImage","BackImage"}, new String[]{memberId,RealName,IDCard,id,frontimage,backimage}, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disloadingbutton("上传成功");
                Jump();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(type.equals("2")){
         chance=1;
       }
        if(selecttype==1){
            fron.forresult(requestCode,resultCode,data);
        }else if(selecttype==2){
            back.forresult(requestCode,resultCode,data);
        }

    }

    @Override
    public void onUploadDone(int responseCode, String message) {
        AllHost host= HttpConnectionUtil.getAllHost(message);
        if(host.isSuccess()){
            loding.disloadingbutton("上传成功");
            Jump();
        }else{
            loding.dismiss();
            Toast.makeText(this, host.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUploadProcess(int uploadSize) {

    }

    @Override
    public void initUpload(int fileSize) {

    }
    public void Jump(){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(Shimingshow.instance!=null)
                    Shimingshow.instance.finish();
                JumpUtil.jump2finash(ShimingdetialActivity.this);
            }
        }, 3700);
    }
}
