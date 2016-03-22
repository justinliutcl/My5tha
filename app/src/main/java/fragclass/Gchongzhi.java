package fragclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.transtion.my5th.R;

import InternetUser.Chonguser;
import adapter.ChongAdapter;
import customUI.PullToRefreshView;
import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/3.
 */
public class Gchongzhi extends Fragment implements PullToRefreshView.OnFooterRefreshListener{
    View view;
    public LodingUtil loding;
    String path= Path.HOST+Path.ip+Path.GWB_CHANGZHI_PATH;
    PullToRefreshView refreshView;
    ListView list;
    int now=1;
    int tatol;
    Chonguser user;
    ShareUtil share;
    ChongAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.frag_g_chongzhi, null);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        share=ShareUtil.getInstanse(getActivity());
        refreshView= (PullToRefreshView) view.findViewById(R.id.chongzhi_list);
        list= (ListView) view.findViewById(R.id.chongzhi_listview);
        refreshView.setEnablePullTorefresh(false);
        loding=new LodingUtil(getActivity());
        setListener();
        getJson();
        return view;
    }

    private void setListener() {
        refreshView.setOnFooterRefreshListener(this);
    }

    private void getJson() {
        loding.showShapeLoding();
            HttpConnectionUtil.getGetJson(getActivity(), path+ "?MemberId=" + share.getMemberID(),loding, new HttpConnectionUtil.OnJsonCall() {
                @Override
                public void JsonCallBack(String str) {
                    user=HttpConnectionUtil.getChongUser(str);
                    adapter=new ChongAdapter(user,getActivity());
                    list.setAdapter(adapter);
                    loding.disShapeLoding();
                }
            });
    }

    private void refresh(){
        HttpConnectionUtil.getGetJson(getActivity(), path + "?MemberId=" + share.getMemberID()+"&PageIndex="+now,null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                Chonguser users = HttpConnectionUtil.getChongUser(str);
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
        if(now<tatol)
            refresh();
        else{
            Toast.makeText(getActivity(),"已到最后一页",Toast.LENGTH_SHORT).show();
            refreshView.onFooterRefreshComplete();
        }
    }
}
