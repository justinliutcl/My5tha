package com.example.transtion.my5th;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import fifthutil.LodingUtil;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/2/22.
 */
public abstract class BaseActivity extends Activity implements android.view.View.OnClickListener{

    public LodingUtil loding;
    public ShareUtil share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loding=new LodingUtil(this);
        share=ShareUtil.getInstanse(this);

    }
    public abstract void setListener();

    @Override
    protected void onStart() {
        super.onStart();
        setListener();
    }

    public void show(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
    public void print(String str){
        Log.i("fifth", str);
    }
}
