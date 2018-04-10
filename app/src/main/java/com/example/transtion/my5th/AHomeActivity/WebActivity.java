package com.example.transtion.my5th.AHomeActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;
import com.example.transtion.my5th.mActivity.MainActivity;
import com.example.transtion.my5th.mActivity.SignActivity;

import java.util.List;

import InternetUser.A_Home.H5user;
import InternetUser.A_Home.HostTitle;
import InternetUser.AllHost;
import fifthutil.FifUtil;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;

public class WebActivity extends BaseActivity {
    String path;
    WebView web;
    Intent intent;
     ProgressBar bar;
    ImageView back,more;
    PopupWindow mPopupWindow;
    public interface maincall{
        void mcall(int i);
    }
    public static maincall m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_web);
        web= (WebView) findViewById(R.id.webshow_web);
         bar = (ProgressBar)findViewById(R.id.myProgressBar);
        back= (ImageView) findViewById(R.id.back);
        more= (ImageView) findViewById(R.id.register_tologin);
        intitview();
        setpro();
        getValue();
    }

    public static void setM(maincall mm){
        m=mm;
    }

    private void intitview() {
        back.setOnClickListener(this);
        more.setOnClickListener(this);

        View view= LayoutInflater.from(this).inflate(R.layout.dialog_more,null);
        LinearLayout go2home= (LinearLayout) view.findViewById(R.id.dialog_more_2home);
        LinearLayout go2shop= (LinearLayout) view.findViewById(R.id.dialog_more_2shop);
        LinearLayout go2individual= (LinearLayout) view.findViewById(R.id.dialog_more_2individual);
        go2home.setOnClickListener(this);
        go2shop.setOnClickListener(this);
        go2individual.setOnClickListener(this);

        mPopupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopupWindow.setFocusable(true);
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

        String panduanurl=path.toLowerCase();
        if(panduanurl.contains("memberid=")) {
            int j = panduanurl.indexOf("memberid=");
            if (j < (panduanurl.length() - 13)) {

            } else {
                if(share.getMemberID().length()>3){
                    path += share.getMemberID();
                }else{
                  String str= share.getHostTitle();
                   String s =share.getfirstHost();
                    share.clear();
                    List<HostTitle> list= HttpConnectionUtil.getHostTitleList(str);
                    for(int i=0; i<list.size();i++){
                        share.setSelectItem(list.get(i).getTypeCode(),"");
                    }
                    share.setHostTitle(str);
                    share.setfirstHost(s);
                    MainActivity.instance.finish();
                    JumpUtil.jump2hdown(this, SignActivity.class, true);
                }

            }
        }

        web.loadUrl(path);

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("web----",url);

                String host=url.substring(0, 3);
                String murl=url.substring(4,url.length());
                if(host.equals("app")){
                    murl.replaceAll("%22", "\"");
                    murl.replaceAll("%20", " ");
                    Log.i("web----", url);
                    AllHost mhost= HttpConnectionUtil.getAllHost(murl);
                    H5user h5user=HttpConnectionUtil.getH5users(murl);
                    if(h5user!=null){
                        FifUtil.go2SomeThing(mhost.getCode(), WebActivity.this, h5user.getObjectId(), h5user.getProductType());
                    }else{
                        Toast.makeText(WebActivity.this,"浏览器版本过低为保证您的信息安全请更新系统",Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }else{

                    String panduanurl=url.toLowerCase();
                    if(panduanurl.contains("memberid=")) {
                        int j = panduanurl.indexOf("memberid=");
                        if (j < (panduanurl.length() - 13)) {

                        } else {
                            if(share.getMemberID().length()>3){
                                url += share.getMemberID();
                            }else{
                                String str= share.getHostTitle();
                                String s =share.getfirstHost();
                                share.clear();
                                List<HostTitle> list= HttpConnectionUtil.getHostTitleList(str);
                                for(int i=0; i<list.size();i++){
                                    share.setSelectItem(list.get(i).getTypeCode(),"");
                                }
                                share.setHostTitle(str);
                                share.setfirstHost(s);
                                MainActivity.instance.finish();
                                JumpUtil.jump2hdown(WebActivity.this, SignActivity.class, true);
                            }


                        }
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
        switch (v.getId()){
            case R.id.back:
                if(web.canGoBack())
                    web.goBack();
                else {
                    this.finish();
                    this.overridePendingTransition(R.anim.back_in, R.anim.back_out);
                }
                break;
            case R.id.register_tologin:
                    mPopupWindow.showAsDropDown(more,0,2);
                break;
            case R.id.dialog_more_2home:
                if(m!=null)
                    m.mcall(0);
                JumpUtil.jump2finash(this);
                break;
            case R.id.dialog_more_2shop:
                if(m!=null)
                    m.mcall(1);
                JumpUtil.jump2finash(this);
                break;
            case R.id.dialog_more_2individual:
                if(m!=null)
                    m.mcall(3);
                JumpUtil.jump2finash(this);
                break;
        }
    }
}
