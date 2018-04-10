package com.example.transtion.my5th.mActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transtion.my5th.AHomeActivity.WebActivity;
import com.example.transtion.my5th.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

import java.util.List;

import InternetUser.A_Home.AdvertismentUser;
import InternetUser.A_Home.H5user;
import InternetUser.A_Home.HostTitle;
import InternetUser.AllHost;
import fifthutil.FifUtil;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;
import fifthutil.TransationShareUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class WelcomeActivity extends BasenoscrollActivity {

    String typecode;

    String path= Path.HOST+Path.ip+Path.A_HOME_TITLE;
    String path2= Path.HOST+Path.ip+Path.HOME_INDEX_PATH;
    String advpath= Path.HOST+Path.ip+Path.ADVERTISMENT_PATH;

    boolean title=false;
    boolean host=false;
    boolean show=false;
    boolean flage=true;

    int i=3;
    TextView time;
    ImageUtil imageUtil;
    ImageView imageView;
    AdvertismentUser user;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                if(i>0){
                    i--;
                    time.setText("跳过 "+i);

                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        time= (TextView) findViewById(R.id.welcome_text);
        imageView= (ImageView) findViewById(R.id.welcome_adavertisment);

        imageUtil=new ImageUtil(this);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flage=false;
                gomain();
            }
        });
        getJson();
        getJson2();
        getadvJson();
    }
    @Override
    public void setListener() {

    }

    private void getJson() {
        HttpConnectionUtil.getGetJson4Welcome(this, path, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {

                if (!share.getHostTitle().equals(str)) {
                    share.setHostTitle(str);
                    List<HostTitle> list = HttpConnectionUtil.getHostTitleList(str);
                    for (int i = 0; i < list.size(); i++) {
                        share.setSelectItem(list.get(i).getTypeCode(), "");
                    }
                }
                title = true;
//                gomain();
                isshow(false);
            }
        });
    }

    private void getJson2() {
        HttpConnectionUtil.getGetJson(this, path2 + "?memberId=" + share.getMemberID(), null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                if (!share.getfirstHost().equals(str)) {
                    share.setfirstHost(str);
                }

                host = true;
                isshow(false);
//                gomain();
            }
        });

    }

    private void getadvJson() {
        HttpConnectionUtil.getGetJsonhaveError(this, advpath, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {

                user = HttpConnectionUtil.getAdvertismentUser(str);
                show = true;
//                gomain();
                isshow(false);
            }
        }, new HttpConnectionUtil.OnErrorJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                isshow(true);
            }
        });
    }

    private void sumtime(){
        time.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i > 0) {
                    i--;
                    time.setText("跳过 " + i);
                    handler.postDelayed(this, 1000);
                } else {
                    if(flage) {
                        flage=false;
                        gomain();
                    }
                }
            }
        }, 1000);
    }

    private void isshow(boolean showerr){
//        if(show) {
//            if (user.isShow()) {
//                bitmapUtils.display(imageView, user.getSrc(), new CustomBitmapLoadCallBack());
//            }else{
//                if(show&&title&&host)
//                      gomain();
//            }
//        }else
            if(show&&title&&host) {
            if(user.isShow()) {
                imageUtil.display(imageView, user.getSrc());
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        flage = false;
                        gomain();
                        if (user.getJumpValue().length() > 5) {
                            String url = user.getJumpValue();
                            String host = url.substring(0, 3);
                            if (host.equals("app")) {
                                String murl = url.substring(4, url.length());
                                AllHost mhost = HttpConnectionUtil.getAllHost(murl);
                                H5user h5user = HttpConnectionUtil.getH5users(murl);
                                FifUtil.go2SomeThing(mhost.getCode(), WelcomeActivity.this, h5user.getObjectId(), h5user.getProductType());
                            } else {
                                JumpUtil.jumpWithValue(WelcomeActivity.this, WebActivity.class, new String[]{"address"}, new String[]{user.getJumpValue()}, true);
                            }
                        }
                    }
                });
                sumtime();
            }
            else
                gomain();
        }
            else if(showerr){
                flage=false;
                 gomain();
        }

    }

    private void gomain() {
        if(title && host){
            if(TransationShareUtil.getInstanse(WelcomeActivity.this).getFrag())
                JumpUtil.jump2finish(this,MainActivity.class,true);
            else{
                JumpUtil.jump2finish(this, TransationActivity.class, true);
            }
        }
    }



    @Override
    public void onClick(View v) {

    }
    public class CustomBitmapLoadCallBack extends
            DefaultBitmapLoadCallBack<ImageView> {

        @Override
        public void onLoading(ImageView container, String uri,
                              BitmapDisplayConfig config, long total, long current) {
        }

        @Override
        public void onLoadCompleted(ImageView container, String uri,
                                    Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
            // super.onLoadCompleted(container, uri, bitmap, config, from);
            if(user.isShow()) {
                if (title && host) {
                    fadeInDisplay(container, bitmap);
                    sumtime();
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            flage=false;
                            gomain();
                            if(user.getJumpValue().length()>5){
                                String url=user.getJumpValue();
                                String host=url.substring(0,3);
                                if(host.equals("app")){
                                    String murl=url.substring(4,url.length());
                                    AllHost mhost= HttpConnectionUtil.getAllHost(murl);
                                    H5user h5user=HttpConnectionUtil.getH5users(murl);
                                    FifUtil.go2SomeThing(mhost.getCode(), WelcomeActivity.this, h5user.getObjectId(), h5user.getProductType());
                                }
                                else{
                                    JumpUtil.jumpWithValue(WelcomeActivity.this, WebActivity.class, new String[]{"address"}, new String[]{user.getJumpValue()}, true);
                                }
                            }
                        }
                    });
                }
            }else{
                gomain();
            }

        }

        @Override
        public void onLoadFailed(ImageView container, String uri,
                                 Drawable drawable) {
            if(TransationShareUtil.getInstanse(WelcomeActivity.this).getFrag())
                JumpUtil.jump2finish(WelcomeActivity.this,MainActivity.class,true);
            else{
                JumpUtil.jump2finish(WelcomeActivity.this, TransationActivity.class, true);
            }

        }
    }
    private void fadeInDisplay(ImageView imageView, Bitmap bitmap) {//目前流行的渐变效果
        final TransitionDrawable transitionDrawable = new TransitionDrawable(
                new Drawable[] { TRANSPARENT_DRAWABLE,
                        new BitmapDrawable(imageView.getResources(), bitmap) });
        imageView.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(500);
    }
    private static final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(android.R.color.transparent);
}

