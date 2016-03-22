package com.example.transtion.my5th.DIndividualActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.AddressItem;
import adapter.AddressAdapter;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class AddressmanagerActivity extends BaseActivity {
    ListView list;
    Button add;
    AddressAdapter adapter;
    String path= Path.HOST+Path.ip+Path.ADDRESSLIST_PATH;
    List<AddressItem>mlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressmanager);
        initView();
    }

    private void initView() {
        list= (ListView) findViewById(R.id.addressmanager_list);
        add= (Button) findViewById(R.id.addressmanager_add);
        getJson();
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(this, path + "?memberId=" + share.getMemberID(), loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disShapeLoding();
                mlist=HttpConnectionUtil.getAddressItemList(str);
                adapter=new AddressAdapter(mlist, AddressmanagerActivity.this, loding, new AddressAdapter.OnadapterChangeCall() {
                    @Override
                    public void adapterChangeBack(int position) {
                        adapter.notifyDataSetChanged();
                    }
                });
                list.setAdapter(adapter);
            }
        });
    }


    @Override
    public void setListener() {
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addressmanager_add:
                JumpUtil.jump(this,Addresssave.class,true);
                break;
        }
    }


}
