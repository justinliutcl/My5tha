package com.example.transtion.my5th.DIndividualActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.transtion.my5th.BShopcar.GoodsorderActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import java.util.List;

import InternetUser.AddressItem;
import adapter.Individual.AddressAdapter;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;

public class AddressmanagerActivity extends BaseActivity {
    ListView list;
    Button add;
    AddressAdapter adapter;
    String path= Path.HOST+Path.ip+Path.ADDRESSLIST_PATH;
    List<AddressItem>mlist;
    boolean flage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressmanager);
        initView();
    }

    private void initView() {
        list= (ListView) findViewById(R.id.addressmanager_list);
        add= (Button) findViewById(R.id.addressmanager_add);
        flage=getIntent().getBooleanExtra("flage",false);

    }

    @Override
    protected void onResume() {
        super.onResume();
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
        if(flage){
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(AddressmanagerActivity.this, GoodsorderActivity.class);
                    intent.putExtra("name", mlist.get(position).getName());
                    intent.putExtra("phone", mlist.get(position).getPhone());
                    intent.putExtra("address", mlist.get(position).getAddress());
                    intent.putExtra("id", mlist.get(position).getId());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
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
