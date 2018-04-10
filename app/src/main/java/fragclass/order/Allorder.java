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
import adapter.order.AllOrderWaitPayOrderAdapter;
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
public class Allorder extends BaseFragment implements PullToRefreshView.OnFooterRefreshListener {
    View view;
    PullToRefreshView refreshView;
    ListView list;
    public LodingUtil loding;
    int now=2;
    int tatol;
    String path= Path.HOST+Path.ip+Path.ALLORDER_PATH;
    GoodsorderUser user;
    ShareUtil share=ShareUtil.getInstanse(getActivity());
    AllOrderWaitPayOrderAdapter adapter;
    int i=0;
    ProgressWheel progress;
    boolean reflash=true;
    public Allorder(boolean flage) {
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
            orderNone= (LinearLayout) view.findViewById(R.id.myorder_ordernone);
            progress= (ProgressWheel) view.findViewById(R.id.progress);
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
    private void setListener() {
        refreshView.setOnFooterRefreshListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String orderid;
                orderid = user.getList().get(position).getOrderNumber();
//                if (user.getList().get(position).getOrderStatus().equals("0")) {
//                    orderid = user.getList().get(position).getOrderNumber();
//                } else {
//                    orderid = user.getList().get(position).getSubOrderList().get(0).getOrderNumber();
//                }
                JumpUtil.jumpWithValue(getActivity(), OrderdetailsActivity.class, new String[]{"orderId", "type", "orderstate"}, new String[]{orderid, user.getList().get(position).getOrderStatusString(), user.getList().get(position).getOrderStatus()}, true);
            }
        });
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


    private void getJson() {
        HttpConnectionUtil.getGetJsonWithProgressOnerror(getActivity(), path + "?MemberId=" + share.getMemberID(), progress, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                orderNone.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);
                refreshView.setVisibility(View.VISIBLE);
                user = HttpConnectionUtil.getGoodsorderUser(str);
                tatol = Integer.parseInt(user.getPageCount());
//                List<GoodsorderItem> newlist = user.getList();
//                List<GoodsorderItem> removenewlist = user.getList();
//                List<GoodsorderItem> removenew2list=new ArrayList<GoodsorderItem>() ;
//                for (int i = 0; i < user.getList().size(); i++) {
//                    if (user.getList().get(i).getOrderStatus().equals("1")) {
//                        for (int j = 0; j < user.getList().get(i).getSubOrderList().size(); j++) {
//                            if (j == 0) {
//                                newlist.remove(i);
//                            }
//                            List<GoodsorderItem> nlist = removenewlist.get(i).getSubOrderList();
//                            GoodsorderItem g1 = removenewlist.get(i).getSubOrderList().get(j);
//                            List<GoodsorderItem> a = new ArrayList<GoodsorderItem>();
//                            a.add(g1);
//                            g1.setSubOrderList(a);
////                            removenewlist.get(i).getSubOrderList().subList()
////                            removenewlist.get(i).getSubOrderList().get(0).setSubOrderList(nlist.get(j).getSubOrderList());
//                            removenew2list.add(g1);
//                        }
//                    }
//                }
//                newlist.addAll(removenew2list);
//                user.setList(newlist);
                adapter = new AllOrderWaitPayOrderAdapter(user, getActivity(), loding, new AddressAdapter.OnadapterChangeCall() {
                    @Override
                    public void adapterChangeBack(int position) {
                        adapter.notifyDataSetChanged();
                    }
                });
                list.setAdapter(adapter);

            }
        }, new HttpConnectionUtil.OnErrorJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                orderNone.setVisibility(View.VISIBLE);
            }
        });
    }

    private void refresh(){
        HttpConnectionUtil.getGetJson(getActivity(), path + "?MemberId=" + share.getMemberID() + "&PageIndex=" + now, null, new HttpConnectionUtil.OnJsonCall() {
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
