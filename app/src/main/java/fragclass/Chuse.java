package fragclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.transtion.my5th.R;

import InternetUser.CouponHuser;
import adapter.Individual.CouponHuseAdapter;
import customUI.PullToRefreshView;
import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/13.
 */
public class Chuse extends Fragment implements PullToRefreshView.OnFooterRefreshListener{
    View view;
    PullToRefreshView refreshView;
    ListView list;
    public LodingUtil loding;
    int now=2;
    int tatol;
    String path= Path.HOST+Path.ip+Path.COUPONHUSE_PATH;
    CouponHuser user;
    ShareUtil share=ShareUtil.getInstanse(getActivity());
    CouponHuseAdapter adapter;
    boolean reflash=true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.frag_c_coupon, null);
            share=ShareUtil.getInstanse(getActivity());
            refreshView= (PullToRefreshView) view.findViewById(R.id.coupon_list);
            list= (ListView) view.findViewById(R.id.coupon_listview);
            refreshView.setEnablePullTorefresh(false);
            loding=new LodingUtil(getActivity());
            getJson();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        refreshView= (PullToRefreshView) view.findViewById(R.id.coupon_list);
        list= (ListView) view.findViewById(R.id.coupon_listview);
        refreshView.setEnablePullTorefresh(false);
        setListener();
        return view;
    }
    private void setListener() {
        refreshView.setOnFooterRefreshListener(this);
    }

    private void getJson() {
//        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(getActivity(), path + "?MemberId=" + share.getMemberID(), loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getCouponHuser(str);
                adapter = new CouponHuseAdapter(user, getActivity());
                list.setAdapter(adapter);
                loding.disShapeLoding();
                Log.i("chuse",str);
            }
        });
    }

    private void refresh(){
        HttpConnectionUtil.getGetJson(getActivity(), path + "?MemberId=" + share.getMemberID()+"&PageIndex="+now,null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                CouponHuser users = HttpConnectionUtil.getCouponHuser(str);
                user.getList().addAll(users.getList());
                adapter.setUser(user);
                adapter.notifyDataSetChanged();
                refreshView.onFooterRefreshComplete();
                now++;
            }
        });
    }
    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        if(now<=tatol)
            refresh();
        else{
            if(reflash){
                Toast.makeText(getActivity(), "已到最后一页", Toast.LENGTH_SHORT).show();
                refreshView.onFooterRefreshComplete();
                refreshView.getmFooterView().setVisibility(View.INVISIBLE);
            }

        }
    }
}
