package fragclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.transtion.my5th.R;

import InternetUser.IndividualFrag.Zhongjiang;
import adapter.Individual.ZhongjiangAdapter;
import customUI.PullToRefreshView;
import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by 不爱白菜 on 2016/7/21.
 */
public class Choujiang extends Fragment  implements PullToRefreshView.OnFooterRefreshListener {
    View view;
    ListView list;
    Zhongjiang user;
    ZhongjiangAdapter adapter;
    PullToRefreshView refreshView;
    int now=2;
    int tatol;
    boolean reflash=true;
    String type[]={"1","2"};
    String mtype;
    String path= Path.HOST+Path.ip+ Path.Zhognjiang_PATH;
    LodingUtil loding;
    ShareUtil share;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.activity_message, null);

        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        loding=new LodingUtil(getActivity());
        share=ShareUtil.getInstanse(getActivity());
        refreshView= (PullToRefreshView)view.findViewById(R.id.myorder_list);
        list= (ListView) view.findViewById(R.id.message_list);
        refreshView.setEnablePullTorefresh(false);
        mtype=type[0];
        getJson();
        setListener();
        return view;
    }
    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(getActivity(), path + "?memberId=" + share.getMemberID() + "&type=" + mtype, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getZhongjiang(str);
                adapter = new ZhongjiangAdapter(user, getActivity());
                list.setAdapter(adapter);
                tatol = Integer.parseInt(user.getPageCount());
                loding.disShapeLoding();
            }
        });
    }
    private void refresh(){
        HttpConnectionUtil.getGetJson(getActivity(), path + "?memberId=" + share.getMemberID() + "&type=" + mtype + "&PageIndex=" + now, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                Zhongjiang users = HttpConnectionUtil.getZhongjiang(str);
                user.getList().addAll(users.getList());
                adapter.setUser(user);
                adapter.notifyDataSetChanged();
                refreshView.onFooterRefreshComplete();
                now++;
            }
        });
    }
    public void setListener() {
        refreshView.setOnFooterRefreshListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
                reflash=false;
            }
        }
    }
}
