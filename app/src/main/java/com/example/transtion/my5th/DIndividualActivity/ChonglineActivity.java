package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.demo.PayDemoActivity;

import fifthutil.JumpUtil;

public class ChonglineActivity extends BaseActivity {
EditText money;
    LinearLayout alipay,wepay;
    Button commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongline);
        commit= (Button) findViewById(R.id.chongonline_commit);
    }

    @Override
    public void setListener() {
        commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chongonline_commit:
                JumpUtil.jump(this, PayDemoActivity.class,true);
                break;
        }
    }
}
