package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.AHomeActivity.SelectActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.HostTitle;
import adapter.order.MyFragmentPagerAdapter;
import fifthutil.JumpUtil;
import fragment.A_home.HostFrag;
import fragment.A_home.OtherFrag;
import fragment.A_home.OtherFrag1;
import fragment.A_home.OtherFrag2;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;
import viewpagerindicator.PagerSlidingTabStrip;

/**
 * Created by baicai on 2016/2/22.
 */
public class AHomefrag extends Fragment  {
    View view;
    ViewPager pager;
    PagerSlidingTabStrip indica;
    MyFragmentPagerAdapter fragadapter;
    Fragment[] frag;
    Fragment[] frag2={new OtherFrag(),new OtherFrag1(),new OtherFrag2()};
    String[] titlename={"环球母婴","食品保健","美妆个护"};
    String []title;
    String path= Path.HOST+Path.ip+Path.A_HOME_TITLE;
    ShareUtil share;
    String oldJson;
    List<HostTitle>list;
    ImageView select,lingdang;
    TextView lingdang_oval;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.frag_ahome, null);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        pager= (ViewPager)view. findViewById(R.id.ahome_pager);
        indica= (PagerSlidingTabStrip) view.findViewById(R.id.ahome_indicator);
        select= (ImageView) view.findViewById(R.id.ahome_select);
        lingdang_oval= (TextView) view.findViewById(R.id.ahome_lingdangoval);
        lingdang=(ImageView) view.findViewById(R.id.ahome_lingdang);
        lingdang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(getActivity(), SelectActivity.class,true);
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(getActivity(), SelectActivity.class,true);
            }
        });
        share=ShareUtil.getInstanse(getActivity());
        setOldview();
        return view;
    }
    private void setOldview() {
        oldJson=share.getHostTitle();
        if(oldJson.length()>5){
            setView(oldJson);
        }

    }


    private void setView(String json){
        list=HttpConnectionUtil.getHostTitleList(json);
        title=new String[list.size()+1];
        frag=new Fragment[list.size()+1];
        title[0]="首页";
        frag[0]=new HostFrag();
        for(int i=0; i<list.size();i++){
            title[i+1]=titlename[i];
            frag[i+1]=frag2[i];

        }
//        title[list.size()+1]="二次元";
//        frag[list.size()+1]=new Twoelement();
        setpager();
    }


    public void setpager(){
        pager.setOffscreenPageLimit(1);
        fragadapter=new MyFragmentPagerAdapter(getChildFragmentManager(), frag,title);
        pager.setAdapter(fragadapter);
        indica.setIndicatorColor(getResources().getColor(R.color.main_color));
        indica.setViewPager(pager);
        pager.setCurrentItem(0);

    }
}
