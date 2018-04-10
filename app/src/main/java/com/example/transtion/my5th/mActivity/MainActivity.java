package com.example.transtion.my5th.mActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transtion.my5th.AHomeActivity.WebActivity;
import com.example.transtion.my5th.AHomeActivity.WebActivity.maincall;
import com.example.transtion.my5th.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import InternetUser.A_Home.UpdataUser;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;
import fifthutil.TransationShareUtil;
import fragment.AHomefrag;
import fragment.BShopfrag;
import fragment.CCommunityfrag;
import fragment.DIndividualfrag;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

public class MainActivity extends FragmentActivity implements maincall{
    boolean flage=true;
    ShareUtil share;
    FragmentTabHost tabHost;
    Handler hand=new Handler(){
        public void handleMessage(android.os.Message msg) {
            flage=true;
        };
    };
    public static MainActivity instance = null;
    File file;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String path= Path.HOST+Path.ip+Path.UPDATA_PATH;
//    double version;
    UpdataUser user;
    AlertDialog ad;
    ProgressBar bar;
    boolean jumpflage=false;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initview();
    }
    public void initview() {
//        version=getVersionCode(this);
        share=ShareUtil.getInstanse(this);
        instance=this;
        tabHost=(FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(MainActivity.this, getSupportFragmentManager(), R.id.main_view);
        tabHost.addTab(setSpec(tabHost.newTabSpec("首页"), R.drawable.a_shouye), AHomefrag.class, null);
        tabHost.addTab(setSpec(tabHost.newTabSpec("购物袋"), R.drawable.b_shop), BShopfrag.class, null);
        tabHost.addTab(setSpec(tabHost.newTabSpec("推荐"), R.drawable.c_communition), CCommunityfrag.class, null);
        tabHost.addTab(setSpec(tabHost.newTabSpec("我的"), R.drawable.d_individual), DIndividualfrag.class, null);
        tabHost.getTabWidget().setDividerDrawable(null);
        if(share.getMemberID().length()<2){
            tabHost.getTabWidget().getChildTabViewAt(3).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(share.getMemberID().length()<2)
                        JumpUtil.jump2hdown(MainActivity.this, SignActivity.class, true);
                    else {
//                        fragmentManager=getSupportFragmentManager();
//                        fragmentTransaction=fragmentManager.beginTransaction();
//                        DIndividualfrag frag=new DIndividualfrag();
//                        fragmentTransaction.replace(R.id.main_view, frag);
//                        fragmentTransaction.commit();
                        tabHost.setCurrentTab(3);
                    }
                }
            });
            tabHost.getTabWidget().getChildTabViewAt(1).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(share.getMemberID().length()<2)
                        JumpUtil.jump2hdown(MainActivity.this, SignActivity.class, true);
                    else {
//                        fragmentManager=getSupportFragmentManager();
//                        fragmentTransaction=fragmentManager.beginTransaction();
//                        BShopfrag frag=new BShopfrag();
//                        fragmentTransaction.replace(R.id.main_view, frag);
//                        fragmentTransaction.commit();
                        tabHost.setCurrentTab(1);
                    }
                }
            });
        }
        getJson();
        WebActivity.setM(this);
    }

    private void setDialog() {
            AlertDialog.Builder ab=new AlertDialog.Builder(this, R.style.dialog);
            View view=View.inflate(this,R.layout.dialog_updata,null);
            TextView version= (TextView) view.findViewById(R.id.dialog_updata_version);
             TextView content= (TextView) view.findViewById(R.id.dialog_updata_content);
             final Button commit= (Button) view.findViewById(R.id.dialog_updata_commit);
        final Button after= (Button) view.findViewById(R.id.dialog_updata_after);
         bar= (ProgressBar) view.findViewById(R.id.dialog_updata_prograssbar);
        version.setText("最新版本："+user.getTitle());
        String temp = user.getSummary().replace("\\n", "\n");
        content.setText(temp);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downFile(user.getUrl());
                commit.setText("正在下载请稍后");
                commit.setClickable(false);
                after.setClickable(false);
            }
        });
        after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransationShareUtil.getInstanse(MainActivity.this).setVersion(user.getSerialNumber()+"");
                ad.dismiss();
            }
        });
            ab.setView(view);
            ad=ab.create();
            ad.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            ad.getWindow().setWindowAnimations(R.style.dialog_updown_anim);
            ad.getWindow().setGravity(Gravity.CENTER);
        ad.show();
    }

    private void getJson() {
        HttpConnectionUtil.getGetJson(this, path, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                Log.i("main",str);
                user=HttpConnectionUtil.getUpdataUser(str);
                String version=TransationShareUtil.getInstanse(MainActivity.this).getversion();
                double a=Double.parseDouble(version);
                if(user.getSerialNumber()>a){
                    setDialog();
                }
            }
        });
    }


    public static int getVersionCode(Context context)//获取版本号(内部识别号)
    {
        try {
            PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            Log.i("5tha--",pi.versionCode+"=============="+pi.versionName);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageUtil.clearlist();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImageUtil.clearlist();
        jumpflage=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(jumpflage) {
            if (count == 1 || count == 3) {
                if (share.getMemberID().length() < 2) {
                    JumpUtil.jump2hdown(MainActivity.this, SignActivity.class, true);
                } else {
                    tabHost.setCurrentTab(count);
                }
            } else {
                tabHost.setCurrentTab(count);
            }
        }
    }

    public TabHost.TabSpec setSpec(TabHost.TabSpec tabSpec,int res){
        View view=View.inflate(MainActivity.this, R.layout.tabhost_item, null);
        TextView text= (TextView) view.findViewById(R.id.tab_text);
        ImageView img=(ImageView) view.findViewById(R.id.tab_img);
        img.setBackgroundResource(res);
        text.setText(tabSpec.getTag().toString());
        return tabSpec.setIndicator(view);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return true;
        }else
            return super.onKeyDown(keyCode, event);
    }

    public void exit(){
        if(flage){
            flage=false;
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            hand.sendEmptyMessageDelayed(0, 2000);
        }else{
            finish();
        }
    }

    private File downFile(final String httpUrl) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL(httpUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    FileOutputStream fileOutputStream = null;
                    InputStream inputStream;
                    if (connection.getResponseCode() == 200) {
                        inputStream = connection.getInputStream();

                        if (inputStream != null) {
                            file = getFile(httpUrl);
                            sendmes(3,file.length());
                            fileOutputStream = new FileOutputStream(file);
                           int a= inputStream.available();
                            byte[] buffer = new byte[1024];
                            int length = 0;
                            int count=0;
                            while ((length = inputStream.read(buffer)) != -1) {
                                fileOutputStream.write(buffer, 0, length);
                                count+=length;
                                sendmes(2, count);
                            }
                            fileOutputStream.close();
                            fileOutputStream.flush();
                        }
                        inputStream.close();
                    }

                    System.out.println("已经下载完成");
                    // 往handler发送一条消息 更改button的text属性
//                    Message message = handler.obtainMessage();
//                    message.what = 1;
//                    handler.sendMessage(message);
                    sendmes(1,0);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return file;
    }

    private void sendmes(int whatnum,long sum){
        Message message = handler.obtainMessage();
        message.what = whatnum;
        message.obj=sum;
        handler.sendMessage(message);
    }

    private File getFile(String url) {
        File files = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), getFilePath(url));
        return files;
    }

    private String getFilePath(String url) {
        return url.substring(url.lastIndexOf("/"), url.length());
    }

    private void installApk() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    ad.dismiss();
                   Toast.makeText(MainActivity.this,"下载完成",Toast.LENGTH_SHORT).show();
                    installApk();
                    break;
                case 2:
                    long count= (long) msg.obj;
//                    Toast.makeText(MainActivity.this,"已下载"+count,Toast.LENGTH_SHORT).show();
                    bar.setProgress((int)count);
                    break;
                case 3:
                    long sum= (long) msg.obj;
//                    Toast.makeText(MainActivity.this,"总大小"+sum,Toast.LENGTH_SHORT).show();
                    bar.setMax((int)sum);
                    bar.setProgress(0);
                    break;
            }
        }

    };

    @Override
    public void mcall(int i) {
        count=i;
        jumpflage=true;
    }
}
