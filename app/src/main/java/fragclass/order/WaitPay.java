package fragclass.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.transtion.my5th.DIndividualActivity.Order.OrderdetailsActivity;
import com.example.transtion.my5th.R;

import InternetUser.GoodsorderUser;
import adapter.Individual.AddressAdapter;
import adapter.order.WaitPayOrderAdapter;
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
    int now=2;
    int tatol;
    int orderType=0;
    String path= Path.HOST+Path.ip+Path.ALLORDER2_PATH;
    GoodsorderUser user;
    ShareUtil share=ShareUtil.getInstanse(getActivity());
    WaitPayOrderAdapter adapter;
    ProgressWheel progress;
    boolean reflash=true;
    public WaitPay(boolean flage) {
       this.flage = flage;
    }
    LinearLayout orderNone;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.frag_o_orderlist, null);
            share=ShareUtil.getInstanse(getActivity());
            refreshView= (PullToRefreshView) view.findViewById(R.id.myorder_list);
            list= (ListView) view.findViewById(R.id.myorder_listview);
            progress= (ProgressWheel) view.findViewById(R.id.progress);
            orderNone= (LinearLayout) view.findViewById(R.id.myorder_ordernone);
            refreshView.setEnablePullTorefresh(false);
            loding=new LodingUtil(getActivity());
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        setListener();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        if(flage){
            progress.setVisibility(View.VISIBLE);
            refreshView.setVisibility(View.GONE);
            getJson();
            flage=false;
        }

    }
    private void setListener() {
        refreshView.setOnFooterRefreshListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JumpUtil.jumpWithValue(getActivity(), OrderdetailsActivity.class, new String[]{"orderId","type","orderstate","Balance","Commission"}, new String[]{user.getList().get(position).getOrderNumber(),user.getList().get(position).getOrderStatusString(),user.getList().get(position).getOrderStatus(),user.getBalance()+"",user.getCommission()+""}, true);
            }
        });
    }

    private void getJson() {
        HttpConnectionUtil.getGetJsonWithProgressOnerror(getActivity(), path + "?MemberId=" + share.getMemberID() + "&orderType=" + orderType, progress, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                orderNone.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);
                refreshView.setVisibility(View.VISIBLE);
                user = HttpConnectionUtil.getGoodsorderUser(str);
                adapter = new WaitPayOrderAdapter(user, getActivity(), loding, new AddressAdapter.OnadapterChangeCall() {
                    @Override
                    public void adapterChangeBack(int position) {
                        adapter.notifyDataSetChanged();
                    }
                });
                tatol = Integer.parseInt(user.getPageCount());
                list.setAdapter(adapter);
                loding.disShapeLoding();
            }
        }, new HttpConnectionUtil.OnErrorJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                orderNone.setVisibility(View.VISIBLE);
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
