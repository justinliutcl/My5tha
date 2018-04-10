package fragment.A_home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.SelectItem;
import InternetUser.A_Home.SelectTitleItem;
import adapter.afrag_home.SelectAdapter;
import customUI.Loding.ProgressWheel;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by 不爱白菜 on 2016/4/4.
 */
public class SelectFrag extends Fragment {
    View view;
    String id;
    ShareUtil share;
    ProgressWheel progress;
    String path= Path.HOST+Path.ip+Path.HOSTSELECT_ITEM_PATH;
    SelectItem user;
    ListView list;
    List<SelectTitleItem> mlist;
    SelectAdapter adapter;
    public SelectFrag() {
    }

    public SelectFrag(String id) {
        this.id = id;
    }
    LinearLayout orderNone;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_s_chose, null);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        share=ShareUtil.getInstanse(getActivity());
        list= (ListView) view.findViewById(R.id.frag_chose_list);
        progress= (ProgressWheel) view.findViewById(R.id.progress);
        orderNone= (LinearLayout) view.findViewById(R.id.myorder_ordernone);
        if(share.getSelectItem(id).length()>5)
            setOldView(share.getSelectItem(id));
        getJson();
        return view;
    }

    private void getJson() {
        HttpConnectionUtil.getGetJsonWithProgressOnerror(getActivity(), path + "?TypeCode=" + id, progress, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                orderNone.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
                if (str != share.getSelectItem(id)) {
                    share.setSelectItem(id, str);
                    setOldView(str);
                }
            }
        }, new HttpConnectionUtil.OnErrorJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                orderNone.setVisibility(View.VISIBLE);
            }
        });
    }

    public void setOldView(String json){
        progress.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        mlist= HttpConnectionUtil.getSelectItem(json);
        adapter=new SelectAdapter(getActivity(),mlist);
        list.setAdapter(adapter);
    }
}
