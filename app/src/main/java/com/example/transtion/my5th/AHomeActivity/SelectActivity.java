package com.example.transtion.my5th.AHomeActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.transtion.my5th.R;
import com.example.transtion.my5th.mActivity.BaseActivity;

import java.util.List;

import InternetUser.A_Home.HostTitle;
import adapter.afrag_home.SelectleftItemAdapter;
import fifthutil.ImageUtil;
import fifthutil.OptsBitmapUtil;
import fifthutil.cache.BitmapUtil;
import fragment.A_home.SelectFrag;
import httpConnection.HttpConnectionUtil;

public class SelectActivity extends BaseActivity {
    List<HostTitle> list;
    ListView lift;
    SelectFrag frag;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SelectleftItemAdapter adapter;
    ImageUtil imageUtil;
    int postnow=0;
    int now=0;
    int[]imgnow={};
    int[]imgpost={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        initView();
    }

    private void initView() {
        String json=share.getHostTitle();
        lift= (ListView) findViewById(R.id.select_lift);
        list= HttpConnectionUtil.getHostTitleList(json);
        adapter=new SelectleftItemAdapter(this,list);
        lift.setAdapter(adapter);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        frag=new SelectFrag(list.get(0).getTypeCode());
        fragmentTransaction.add(R.id.select_right, frag);
        fragmentTransaction.commit();
        imageUtil=new ImageUtil(this);
    }

    public void chose(String id){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        frag=new SelectFrag(id);
        fragmentTransaction.replace(R.id.select_right, frag);
        fragmentTransaction.commit();
    }
    private void getimg(ImageView userImg,String url,String name){
        BitmapUtil bitmapUtil=new BitmapUtil(this);
        userImg.setTag(url);
        bitmapUtil.setSelectImg(userImg, name);
    }
    @Override
    public void setListener() {
        lift.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                now=position;
                if(now!=postnow){
                    view.setBackgroundColor(0xffffffff);
                    LinearLayout l= (LinearLayout) view;
                    ImageView img_now= (ImageView) l.getChildAt(0);

                    String path=share.getSelectItem(list.get(now).getImageLight());
                    if(path.length()>1)
                        img_now.setImageBitmap(OptsBitmapUtil.calculatorBitmap(path, img_now));
                    else
                        getimg(img_now,list.get(now).getImageLight(),list.get(now).getTypeName()+"light");
//                    imageUtil.displaywithoutanim(img_now, list.get(now).getImageLight());
                    ((TextView)l.getChildAt(1)).setTextColor(0xffffc000);
                    LinearLayout l2= (LinearLayout)lift.getChildAt(postnow);
                    ImageView img_post= (ImageView) l2.getChildAt(0);

                    String path2=share.getSelectItem(list.get(postnow).getImageGray());
                    if(path2.length()>1)
                        img_post.setImageBitmap(OptsBitmapUtil.calculatorBitmap(path2, img_post));
                    else
                        getimg(img_post,list.get(postnow).getImageGray(),list.get(postnow).getTypeName()+"gray");

//                    imageUtil.displaywithoutanim(img_post,list.get(postnow).getImageGray());
                    l2.setBackgroundColor(0xfff5f5f5);
                    ((TextView)l2.getChildAt(1)).setTextColor(0xffb2b2b2);
                    chose(list.get(position).getTypeCode());

                    postnow=now;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
