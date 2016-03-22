package fifthutil;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

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

    public static String getSecretPhone(String phone){
        String head=phone.substring(0,3);
        String end=phone.substring(7,11);
        return head+"****"+end;
    }

    public static String getPrice(double price){
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(price);
    }
}
