package com.example.transtion.my5th.AHomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import InternetUser.A_Home.H5user;
import InternetUser.AllHost;
import fifthutil.FifUtil;
import httpConnection.HttpConnectionUtil;

public class Web4memberActivity extends BaseActivity {

    String path;
    WebView web;
    Intent intent;
    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web4member);
        web= (WebView) findViewById(R.id.webshow_web);
        bar = (ProgressBar)findViewById(R.id.myProgressBar);
        setpro();
        getValue();
    }

    public void setpro(){
        web.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });

    }

    private void getValue() {
        intent=getIntent();
        path=intent.getStringExtra("address");
        print(path);
        web.getSettings().setJavaScriptEnabled(true);
//        path="https://cdn.5tha.com/test1.html";
        path+=share.getMemberID();
        web.loadUrl(path);

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("web----", url);
                String host=url.substring(0, 3);
                String murl=url.substring(4,url.length());
                if(host.equals("app")){
                    murl.replaceAll("%22", "\"");
                    murl.replaceAll("%20", " ");
                    Log.i("web----", url);
                    AllHost mhost= HttpConnectionUtil.getAllHost(murl);
                    H5user h5user=HttpConnectionUtil.getH5users(murl);
                    if(h5user!=null){
                        FifUtil.go2SomeThing(mhost.getCode(), Web4memberActivity.this, h5user.getObjectId(), h5user.getProductType());
                    }else{
                        Toast.makeText(Web4memberActivity.this, "浏览器版本过低为保证您的信息安全请更新系统", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }else{

                    String panduanurl=url.toLowerCase();
                    int i=panduanurl.indexOf("memberid=");
                    if(i<(panduanurl.length()-13)){

                    }else{
                        url+=share.getMemberID();
                    }
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);
                }


            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && web.canGoBack()){
            web.goBack();
            return true;
        }else{
            this.finish();
            this.overridePendingTransition(R.anim.back_in, R.anim.back_out);
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
