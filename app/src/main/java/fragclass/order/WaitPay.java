package fragclass.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.transtion.my5th.DIndividualActivity.OrderdetailsActivity;
import com.example.transtion.my5th.R;

import InternetUser.GoodsorderUser;
import adapter.order.AllOrderAdapter;
import customUI.Loding.ProgressWheel;
import customUI.PullToRefreshView;
import fifthutil.JumpUtil;
import fifthutil.LodingUtil;
import fragclass.BaseFragment;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/15.
 */
public class WaitPay extends BaseFragment implements PullToRefreshView.OnFooterRefreshListener {
    View view;
    PullToRefreshView refreshView;
    ListView list;
    public LodingUtil loding;
    int now=1;
    int tatol;
    int orderType=0;
    String path= Path.HOST+Path.ip+Path.ALLORDER_PATH;
    GoodsorderUser user;
    ShareUtil share=ShareUtil.getInstanse(getActivity());
    AllOrderAdapter adapter;
    ProgressWheel progress;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.frag_o_orderlist, null);
            share=ShareUtil.getInstanse(getActivity());
            refreshView= (PullToRefreshView) view.findViewById(R.id.myorder_list);
            list= (ListView) view.findViewById(R.id.myorder_listview);
            progress= (ProgressWheel) view.findViewById(R.id.progress);
            refreshView.setEnablePullTorefresh(false);
            loding=new LodingUtil(getActivity());
            getJson();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        setListener();
        return view;
    }
    private void setListener() {
        refreshView.setOnFooterRefreshListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JumpUtil.jumpWithValue(getActivity(), OrderdetailsActivity.class, new String[]{"orderId"}, new String[]{user.getList().get(position).getOrderNumber()}, true);
            }
        });
    }

    private void getJson() {
        HttpConnectionUtil.getGetJsonWithProgress(getActivity(), path + "?MemberId=" + share.getMemberID() + "&orderType=" + orderType, progress, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                progress.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
                user = HttpConnectionUtil.getGoodsorderUser(str);
                adapter = new AllOrderAdapter(user, getActivity());
                list.setAdapter(adapter);
                loding.disShapeLoding();
            }
        });
    }

    private void refresh(){
        progress.setVisibility(View.GONE);
        HttpConnectionUtil.getGetJson(getActivity(), path + "?MemberId=" + share.getMemberID()+"&orderType="+orderType+"&PageIndex="+now,null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                GoodsorderUser users = HttpConnectionUtil.getGoodsorderUser(str);
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
            Toast.makeText(getActivity(), "已到最后一页", Toast.LENGTH_SHORT).show();
            refreshView.onFooterRefreshComplete();
        }
    }

    @Override
    protected void lazyLoad() {
    }
}
