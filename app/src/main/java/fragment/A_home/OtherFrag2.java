package fragment.A_home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.transtion.my5th.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import InternetUser.O_other.OtherUser;
import adapter.afrag_home.RmhdAdapter;
import adapter.afrag_other.OtherFragRecycleAdapter;
import adapter.afrag_other.OtwoGoodAdapter;
import customUI.GridViewForScrollView;
import customUI.ListViewForScrollView;
import customUI.PullToRefreshRecyclerView;
import customUI.WrapAdapter;
import fifthutil.ImageUtil;
import fifthutil.TopPager;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/23.
 */
public class OtherFrag2 extends Fragment {
    View view;
    String typecode="T00001";
    TopPager top=null;
    int now=2;
    int tatol;
    String path= Path.HOST+ Path.ip+ Path.O_OTHER_PATH;
    OtherUser user;
    OtwoGoodAdapter tadapter;
    RmhdAdapter rmhdAdapter;
    GridView tgrid;
    ListViewForScrollView blist;
    ImageView topimg;
    ImageUtil imageUtil;
    ShareUtil share;
    boolean flage=true;
    boolean reflash=true;
    String s;
    OtherFragRecycleAdapter adapter;
    WrapAdapter<RecyclerView.Adapter> wrapAdapter;
    RecyclerView mRecyclerView;
    View topview;
    View footview;
    PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    int pageSize=20;
    boolean isCompleteflage=true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_o_other_recyc, null);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        share = ShareUtil.getInstanse(getActivity());
        imageUtil = new ImageUtil(getActivity());

        final String ty = typecode;
        if (flage) {
            mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) view.findViewById(R.id.otherfrag_recyclerview);
            mRecyclerView = mPullToRefreshRecyclerView.getRefreshableView();
            topview = View.inflate(getActivity(), R.layout.fragment_o_other_item, null);
            tgrid = (GridViewForScrollView) topview.findViewById(R.id.otherfrag_tgrid);
            topimg = (ImageView) topview.findViewById(R.id.otherfrag_timg);

            footview = View.inflate(getActivity(), R.layout.fragment_o_other_item_footview, null);
            blist = (ListViewForScrollView) footview.findViewById(R.id.otherfrag_list);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            if (wrapAdapter != null)
                wrapAdapter.adjustSpanSize(mRecyclerView);
            getJson(ty);
            s = share.getSelectItem(Path.O_OTHER_PATH + typecode);
            if (s.length() > 3) {
                setView(s);
            }
        }
        setListener();
        return view;
    }

    private void setView(String str) {
        user = HttpConnectionUtil.getOtherUser(str);
        tatol = Integer.parseInt(user.getPageCount());
        tadapter = new OtwoGoodAdapter(user, getActivity());
        if(!user.getAdvertisementViewList().isEmpty())
            imageUtil.display4rmhd(topimg, user.getAdvertisementViewList().get(0).getImgSrc());
        tgrid.setAdapter(tadapter);
        flage = false;
        adapter = new OtherFragRecycleAdapter(user.getList(), getActivity());
        wrapAdapter = new WrapAdapter<RecyclerView.Adapter>(adapter);
        wrapAdapter.adjustSpanSize(mRecyclerView);
        mRecyclerView.setAdapter(wrapAdapter);
        wrapAdapter.addHeaderView(topview);

    }

    public void setListener() {
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

        mPullToRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() {
            @Override
            public void onRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                getJson2refresh();
            }
        });
    }
    public void onBottom() {
        Log.i("text-", "now=" + now + "----tatol=" + tatol);
        if (now <= tatol)
            getRefresh();
        else {
            if (reflash) {
//                        Toast.makeText(getActivity(), "已到最后一页", Toast.LENGTH_SHORT).show();
                rmhdAdapter = new RmhdAdapter(getActivity(), user.getHotActivityList());
                blist.setAdapter(rmhdAdapter);
                wrapAdapter.addFooterView(footview);
                reflash = false;
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        ImageUtil.clearlist();
    }
    private void getJson(String ty) {

        HttpConnectionUtil.getGetJson(getActivity(), path + "?MemberId=" + share.getMemberID() + "&typeCode=" + ty + "&pageSize=" + pageSize, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                if (!str.equals(s)) {
                    setView(str);
                    share.setSelectItem(Path.O_OTHER_PATH + typecode, str);
                }
            }
        });
    }
    private void getJson2refresh() {
        ImageUtil.clearlist();
        reflash=true;
        wrapAdapter.getFootersView().clear();
        HttpConnectionUtil.getGetJson(getActivity(), path + "?MemberId=" + share.getMemberID() + "&typeCode=" + typecode + "&pageSize=" + pageSize, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                user = HttpConnectionUtil.getOtherUser(str);
                tatol = Integer.parseInt(user.getPageCount());
                now = 2;
                flage = false;
                isCompleteflage = true;
                adapter.setMlist(user.getList());
                wrapAdapter.notifyDataSetChanged();
                mPullToRefreshRecyclerView.onRefreshComplete();
                wrapAdapter.getFootersView().clear();
            }
        });
    }
    private void getRefresh(){
        HttpConnectionUtil.getGetJson(getActivity(), path + "?MemberId=" + share.getMemberID() + "&typeCode=" + typecode + "&PageIndex=" + now + "&pageSize=" + pageSize, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                OtherUser users = HttpConnectionUtil.getOtherUser(str);
                user.getList().addAll(users.getList());
                now++;
                wrapAdapter.notifyDataSetChanged();
                isCompleteflage = true;
            }
        });
    }
}
