package adapter.afrag_home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.transtion.my5th.AHomeActivity.WebActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.H5user;
import InternetUser.A_Home.TopImgItem;
import InternetUser.AllHost;
import fifthutil.FifUtil;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;
import httpConnection.HttpConnectionUtil;

/**
 * Created by baicai on 2016/3/25.
 */
public class RmhdAdapter extends BaseAdapter {
    private Context context;
    private List<TopImgItem> list;
    private ImageUtil imageUtil;
    public RmhdAdapter(Context context, List<TopImgItem> list) {
        this.context = context;
        this.list = list;
        this.imageUtil=new ImageUtil(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Viewholder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.adapter_hmhd, null);
            holder=new Viewholder();
            holder.goodimg= (ImageView) convertView.findViewById(R.id.adapter_rmhd);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        holder.goodimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=list.get(position).getUrl();
                String host=url.substring(0,3);
                if(host.equals("app")){
                         String murl=url.substring(4,url.length());
                        AllHost mhost= HttpConnectionUtil.getAllHost(murl);
                        H5user h5user=HttpConnectionUtil.getH5users(murl);
                        FifUtil.go2SomeThing(mhost.getCode(), context, h5user.getObjectId(), h5user.getProductType());
                    }
                else{
                    JumpUtil.jumpWithValue(context, WebActivity.class, new String[]{"address"}, new String[]{list.get(position).getUrl()}, true);
                }

            }
        });
        imageUtil.display4rmhd(holder.goodimg, list.get(position).getImgSrc());
        return convertView;
    }
    private class Viewholder{
        ImageView goodimg;
    }
}
