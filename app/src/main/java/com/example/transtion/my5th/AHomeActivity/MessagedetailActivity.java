package com.example.transtion.my5th.AHomeActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

public class MessagedetailActivity extends BaseActivity {
    TextView time,mes,other,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagedetail);
        initView();
    }

    private void initView() {
        time= (TextView) findViewById(R.id.messagedetail_time);
        mes= (TextView) findViewById(R.id.messagedetail_mes);
        other= (TextView) findViewById(R.id.messagedetail_other);
        delete= (TextView) findViewById(R.id.messagedetail_delete);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
