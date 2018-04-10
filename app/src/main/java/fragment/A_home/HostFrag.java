package fragment.A_home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.transtion.my5th.AHomeActivity.WebActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.H5user;
import InternetUser.A_Home.HostFragUser;
import InternetUser.AllHost;
import adapter.afrag_home.JxdxAdapter;
import adapter.afrag_home.QqtmAdapter;
import adapter.afrag_home.RmhdAdapter;
import customUI.ListViewForScrollView;
import fifthutil.FifUtil;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;
import fifthutil.TopPager;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/23.
 */
public class HostFrag extends Fragment {
    private String path= Path.HOST+Path.ip+Path.HOME_INDEX_PATH;
    View view;
    ListViewForScrollView qqtmlist,jxdulist,rmhdlist;
    List<String> mDatas;
    LinearLayout qqtm;

    QqtmAdapter qqtmAdapter;
    JxdxAdapter jxdxAdapter;
    RmhdAdapter rmhdAdapter;
    private ViewPager viewPager;

    private ImageView[] tips;

    private ImageView[] mImageViews;
    ViewGroup group;

    int i;
    private int[] imgIdArray ;
    TopPager top=null;

    private ShareUtil share;
    private HostFragUser user;
    ImageUtil imageUtil;
    boolean flage=true;
    String str;
    ScrollView scrollView;
    boolean reflash=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_h_host, null);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        group = (ViewGroup)view.findViewById(R.id.viewGroup);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        qqtmlist= (ListViewForScrollView) view.findViewById(R.id.fragment_h_host_qqtmlist);
        jxdulist= (ListViewForScrollView) view.findViewById(R.id.fragment_h_host_jxdxlist);
        rmhdlist= (ListViewForScrollView) view.findViewById(R.id.fragment_h_host_rmhdlist);
        scrollView= (ScrollView) view.findViewById(R.id.host_scrollview);
        qqtm= (LinearLayout) view.findViewById(R.id.fragment_h_host_layout_qqtm);
        share=ShareUtil.getInstanse(getActivity());
        imageUtil=new ImageUtil(getActivity());

        if(flage){
            if(share.getfirstHost().length()>5){
                str=share.getfirstHost();
                setView(str);
            }
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(reflash)
            gettimeJson();
        ImageUtil.clearlist();
    }

    @Override
    public void onPause() {
        super.onPause();
        reflash=true;
    }

    private void getJson() {
        HttpConnectionUtil.getGetJson(getActivity(), path + "?memberId=" + share.getMemberID(), null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                setView(str);

            }
        });
    }

    private void gettimeJson() {
        HttpConnectionUtil.getGetJson(getActivity(), path + "?memberId=" + share.getMemberID(), null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                settimeView(str);

            }
        });
    }

    private void settimeView(String str){
        flage=false;
        user= HttpConnectionUtil.getHostFragUser(str);
        if(user.getPeriodViewList().isEmpty())
            qqtm.setVisibility(View.GONE);
        else
            qqtm.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                qqtmAdapter = new QqtmAdapter(getActivity(), user.getPeriodViewList());
                qqtmlist.setAdapter(qqtmAdapter);

            }
        }, 50);


    }

    private void setView(String str){
        flage=false;
        user= HttpConnectionUtil.getHostFragUser(str);
        ImageView []img=new ImageView[user.getAdvertisementViewList().size()];
        for(int i=0;i<user.getAdvertisementViewList().size();i++){
            ImageView imageView = new ImageView(getActivity());
            img[i] = imageView;
            img[i].setScaleType(ImageView.ScaleType.FIT_XY);
            img[i].setTag(user.getAdvertisementViewList().get(i).getImgSrc());

//                    imageUtil.display(img[i], user.getAdvertisementViewList().get(i).getImgSrc());
        }
        if(top==null){
            top= new TopPager(getActivity(),group,img,viewPager);
            top.setPageOnclick(new TopPager.OnTopPageCall() {
                @Override
                public void CallBack(int now) {
                    String url=user.getAdvertisementViewList().get(now).getUrl();
                    String host=url.substring(0, 3);
                    String murl=url.substring(4,url.length());
                    if(host.equals("app")){
                        AllHost mhost= HttpConnectionUtil.getAllHost(murl);
                        H5user h5user=HttpConnectionUtil.getH5users(murl);
                        FifUtil.go2SomeThing(mhost.getCode(),getActivity(),h5user.getObjectId(),h5user.getProductType());
                    }else{
                        JumpUtil.jumpWithValue(getActivity(), WebActivity.class, new String[]{"address"}, new String[]{url},true);
                    }

                }
            });
        }
        if(user.getPeriodViewList().isEmpty())
            qqtm.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                qqtmAdapter = new QqtmAdapter(getActivity(), user.getPeriodViewList());
                qqtmlist.setAdapter(qqtmAdapter);
                jxdxAdapter = new JxdxAdapter(getActivity(), user.getBrandViewList());
                jxdulist.setAdapter(jxdxAdapter);
                rmhdAdapter = new RmhdAdapter(getActivity(), user.getHotActivityList());
                rmhdlist.setAdapter(rmhdAdapter);

            }
        }, 50);


    }

}
