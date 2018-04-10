package com.example.transtion.my5th.mActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import fifthutil.JumpUtil;
import fifthutil.LodingUtil;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/2/22.
 */
public abstract class BaseActivity extends SwipeBackActivity implements android.view.View.OnClickListener{

    public LodingUtil loding;
    public ShareUtil share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        loding=new LodingUtil(this);
        share=ShareUtil.getInstanse(this);

    }
    public abstract void setListener();

    @Override
    protected void onStart() {
        super.onStart();
        setListener();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            JumpUtil.jump2finash(this);
            return true;
        }else
            return super.onKeyDown(keyCode, event);
    }

    public void show(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
    public void print(String str){
        Log.i("fifth", str);
    }
}
