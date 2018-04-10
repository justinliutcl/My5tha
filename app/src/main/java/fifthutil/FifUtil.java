package fifthutil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.transtion.my5th.AHomeActivity.BanderdetailsDActivity;
import com.example.transtion.my5th.AHomeActivity.MessageActivity;
import com.example.transtion.my5th.AHomeActivity.PinpaiActivity;
import com.example.transtion.my5th.AHomeActivity.TextActivity;
import com.example.transtion.my5th.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/9.
 */
public class FifUtil {
    public static ShareUtil share;
    public static String[] getTime(String time){
        String []t=time.split("T");
       t[1]=t[1].substring(0,8);
        return t;
    }
    public static void saveMyBitmap(String bitName,Bitmap mBitmap,Context context){
        File root=new File(Environment.getExternalStorageDirectory(), "5tha");
        if(root==null)
            root.mkdirs();
        share=ShareUtil.getInstanse(context);
//        File f = new File(root.getPath(), bitName + ".png");
        File f = new File(root.getPath(), "5thaUserPhoneImage.png");
        try {
            f.createNewFile();
        } catch (IOException e) {
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            ShareUtil.getInstanse(context).setImgUrl(bitName);
            share.setImg(f.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String saveHBBitmap(Bitmap mBitmap,Context context){
        File root=new File(Environment.getExternalStorageDirectory(), "5tha");
        if(root==null)
            root.mkdirs();
        share=ShareUtil.getInstanse(context);
//        File f = new File(root.getPath(), bitName + ".png");
        File f = new File(root.getPath(), "hb.png");
        try {
            f.createNewFile();
        } catch (IOException e) {
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            return f.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void saveSelectBitmap(String bitName,Bitmap mBitmap,Context context,String name){
        File root=new File(Environment.getExternalStorageDirectory(), "5tha");
        if(root==null)
            root.mkdirs();
//        File f = new File(root.getPath(), bitName + ".png");
        File f = new File(root.getPath(), name+".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            ShareUtil.getInstanse(context).setImgUrl(bitName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String getSecretPhone(String phone){
        String head=phone.substring(0,3);
        String end=phone.substring(7,11);
        return head+"****"+end;
    }

    public static String getPrice(double price){
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(price);
    }

    public static void go2SomeThing(int code,Context context,String id,String type){
        switch (code){
            case 1:
                JumpUtil.jumpWithValue(context, TextActivity.class,new String[]{"resId","objid","type","texttype"},new String[]{"h5",id,type,"objectId"},true);
                break;
            case 2:

                break;
            case 3:
                JumpUtil.jumpWithValue(context, BanderdetailsDActivity.class,new String[]{"typecode"},new String[]{id},true);
                break;
            case 4:

                break;
            case 5:
                JumpUtil.jumpWithValue(context,PinpaiActivity.class,new String[]{"resId","brandId"},new String[]{"1",id},true);
                break;
            case 6:
                JumpUtil.jumpWithValue(context, TextActivity.class,new String[]{"resId","objid","type","texttype"},new String[]{"h5",id,type,"objectId"},true);
                break;
            case 7:
                showShare(context,id);
                break;
            case 8:
                JumpUtil.jump(context, MessageActivity.class,true);
                break;

        }
    }

    public static void showShare(Context mcontext,String code){
        Bitmap bitmap= BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.pic_hb2);
        String  address= FifUtil.saveHBBitmap(bitmap,mcontext);
        ShareSDK.initSDK(mcontext);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("开场狂欢，新人送礼");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("进口特卖，正品低价，海外直采，第五大街为您奉上新人大礼包，小主请笑纳!");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(address);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(code);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        oks.setTitleUrl(code);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(mcontext.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(mcontext);
    }


}
