package fragclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.Item.CouponNuseItem;
import adapter.CouponNuseAdapter;
import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/13.
 */
public class Cnuse extends Fragment {
    View view;
    List<CouponNuseItem>list;
    ListView mlist;
    String path= Path.HOST+Path.ip+Path.COUPONNUSE_PATH;
    CouponNuseAdapter adapter;
    ShareUtil share;
    LodingUtil loading;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.frag_c_coupon_nuse, null);
            mlist= (ListView) view.findViewById(R.id.coupon_listview);
            share=ShareUtil.getInstanse(getActivity());
            loading=new LodingUtil(getActivity());
            getJson();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        mlist= (ListView) view.findViewById(R.id.coupon_listview);
        return view;
    }

    private void getJson() {
        loading.showShapeLoding();
        HttpConnectionUtil.getGetJson(getActivity(), path + "?MemberId=" + share.getMemberID(), loading, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                list= HttpConnectionUtil.getNuserList(str);
                adapter=new CouponNuseAdapter(list,getActivity());
                mlist.setAdapter(adapter);
                loading.disShapeLoding();
            }
        });
    }


}
