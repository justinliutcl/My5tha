package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.transtion.my5th.R;

import InternetUser.A_Home.SelectClassUser;
import adapter.afrag_other.OtherFragRecycleAdapter;
import customUI.ListViewForScrollView;
import customUI.WrapAdapter;
import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/2/22.
 */
public class CCommunityfrag extends Fragment {
    View view;
    int now=2;
    int tatol;
    boolean reflash=true;
    String path= Path.HOST+Path.ip+Path.CAINIXIHUAN;
    ShareUtil share;
    LodingUtil loding;
    OtherFragRecycleAdapter gadapter;
    SelectClassUser user;
    boolean flage=true;
    WrapAdapter<RecyclerView.Adapter> wrapAdapter;
    RecyclerView mRecyclerView;
    View footview;
    ListViewForScrollView blist;
    boolean isCompleteflage=true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.frag_ccommunity, null);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        if(flage){
            initView();
        }

        return view;
    }

    private void initView() {
        share=ShareUtil.getInstanse(getActivity());
        loding=new LodingUtil(getActivity());
        mRecyclerView= (RecyclerView) view.findViewById(R.id.otherfrag_recyclerview);
        footview=View.inflate(getActivity(), R.layout.fragment_o_other_item_footview, null);
        blist= (ListViewForScrollView) footview.findViewById(R.id.otherfrag_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        if(wrapAdapter!=null)
            wrapAdapter.adjustSpanSize(mRecyclerView);
        getJson();
        setListener();
    }

    private void setListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (isCompleteflage) {
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int nowshow = manager.findLastVisibleItemPosition();
                    int total = manager.getItemCount();
                    if (nowshow > (total - 15)) {
                        isCompleteflage = false;

                        onBottom();
                    }
                }
            }
        });
    }

    public void onBottom() {
        if (now <= tatol)
            getRefresh();
        else {
            if (reflash) {
//                        Toast.makeText(getActivity(), "已到最后一页", Toast.LENGTH_SHORT).show();
                wrapAdapter.addFooterView(footview);
                reflash = false;
            }
        }
    }

    private void getJson() {
        loding.showShapeLoding();
        HttpConnectionUtil.getGetJson(getActivity(), path + "?MemberId=" + share.getMemberID(), loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getSelectClassUser(str);
                gadapter=new OtherFragRecycleAdapter(user.getList(),getActivity());
                wrapAdapter = new WrapAdapter<RecyclerView.Adapter>(gadapter);
                wrapAdapter.adjustSpanSize(mRecyclerView);
                mRecyclerView.setAdapter(wrapAdapter);
                tatol = Integer.parseInt(user.getPageCount());
                loding.disShapeLoding();
                flage=false;
            }
        });

    }


    private void getRefresh() {
        HttpConnectionUtil.getGetJson(getActivity(), path + "?memberId=" + share.getMemberID() + "&PageIndex=" + now, loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                SelectClassUser muser = HttpConnectionUtil.getSelectClassUser(str);
                user.getList().addAll(muser.getList());
                now++;
                wrapAdapter.notifyDataSetChanged();
                isCompleteflage = true;
            }
        });

    }
}
