package fifthutil;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

/**
 * Created by baicai on 2016/3/1.
 */
public class SMSUtil {
    private static String path= Path.HOST+ Path.ip+ Path.RegistPHONE_PATH;
    private static String path2= Path.HOST+ Path.ip+ Path.SMS_PATH;
    private  String phoneNum;
    private Button btn;
    private Context context;
    private int i=60;
    private boolean istext=false;
    private LodingUtil loading;
    private String p="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0-9]|17[0-9])\\d{8}$";
    public SMSUtil() {
    }
   private  Handler hand=new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==1){
                if(i==0){
                    i=60;
                    btn.setText("获取验证码");
                    btn.setEnabled(true);
                    istext=true;
                }else{
                    i--;
                    btn.setText("发送中"+"("+i+")");
                    if(istext){
                        i=0;
                    }
                    hand.sendEmptyMessageDelayed(1, 1000);
                }
            }

        };
    };
    public SMSUtil(Button btnn, Context contextt, final EditText phone) {
        this.btn = btnn;
        this.context = contextt;
        loading=new LodingUtil(contextt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum=phone.getText().toString();
               if (panduan()){
                   loading.showShapeLoding();
                   btn.setEnabled(false);
                   btn.setText("发送中" + "(" + i + ")");
                   hand.sendEmptyMessage(1);
                   HttpConnectionUtil.getGetJson(context, path + phoneNum, loading,new HttpConnectionUtil.OnJsonCall() {
                       @Override
                       public void JsonCallBack(String str) {
                           loading.disShapeLoding();
                           Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
//                           AllHost host=HttpConnectionUtil.getAllHost(str);
//                           if(!host.isSuccess()){
//                               istext=true;
//                               Toast.makeText(context,host.getMessage(),Toast.LENGTH_SHORT).show();
//                           }else{
//                               Toast.makeText(context,host.getMessage(),Toast.LENGTH_SHORT).show();
//                               istext=true;
//                           }
                       }
                   });
               }
            }
        });
    }

    public SMSUtil(Button btnn, Context contextt, final String phone,String t) {
        this.btn = btnn;
        this.context = contextt;
        loading=new LodingUtil(contextt);
        final String type=t;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum=phone;
                if (panduan()){
                    loading.showShapeLoding();
                    btn.setEnabled(false);
                    btn.setText("发送中" + "(" + i + ")");
                    hand.sendEmptyMessage(1);
                    HttpConnectionUtil.getGetJson(context, path2 + phoneNum+"&passwordType="+type, loading,new HttpConnectionUtil.OnJsonCall() {
                        @Override
                        public void JsonCallBack(String str) {
                            loading.disShapeLoding();
                            Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
//                           AllHost host=HttpConnectionUtil.getAllHost(str);
//                           if(!host.isSuccess()){
//                               istext=true;
//                               Toast.makeText(context,host.getMessage(),Toast.LENGTH_SHORT).show();
//                           }else{
//                               Toast.makeText(context,host.getMessage(),Toast.LENGTH_SHORT).show();
//                               istext=true;
//                           }
                        }
                    });
                }
            }
        });
    }
    public SMSUtil(Button btnn, Context contextt, final EditText phone,String t) {
        this.btn = btnn;
        this.context = contextt;
        loading=new LodingUtil(contextt);
        final String type=t;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum=phone.getText().toString();
                if (panduan()){
                    loading.showShapeLoding();
                    btn.setEnabled(false);
                    btn.setText("发送中" + "(" + i + ")");
                    hand.sendEmptyMessage(1);
                    HttpConnectionUtil.getGetJson(context, path2 + phoneNum+"&passwordType="+type, loading,new HttpConnectionUtil.OnJsonCall() {
                        @Override
                        public void JsonCallBack(String str) {
                            loading.disShapeLoding();
                            Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
//                           AllHost host=HttpConnectionUtil.getAllHost(str);
//                           if(!host.isSuccess()){
//                               istext=true;
//                               Toast.makeText(context,host.getMessage(),Toast.LENGTH_SHORT).show();
//                           }else{
//                               Toast.makeText(context,host.getMessage(),Toast.LENGTH_SHORT).show();
//                               istext=true;
//                           }
                        }
                    });
                }
            }
        });
    }
    public boolean panduan(){
        if(!phoneNum.matches(p)){
            Toast.makeText(context,"请输入正确手机号",Toast.LENGTH_SHORT).show();
            return false;
        }else
            return true;
    }

}
