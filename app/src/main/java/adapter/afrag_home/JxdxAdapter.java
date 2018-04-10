package adapter.afrag_home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.transtion.my5th.AHomeActivity.PinpaiActivity;
import com.example.transtion.my5th.AHomeActivity.TextActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.JxdxUser;
import fifthutil.ImageUtil;

/**
 * Created by baicai on 2016/3/25.
 */
public class JxdxAdapter extends BaseAdapter {
    private Context context;
    private List<JxdxUser> list;
    private ImageUtil imageUtil;
    private HorRecycleAdapter adapterr;
    public JxdxAdapter(Context context, List<JxdxUser> list) {
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
            convertView=View.inflate(context, R.layout.adapter_jxdx, null);
            holder=new Viewholder();
            holder.topimg = (ImageView) convertView.findViewById(R.id.adapter_jxdx_topimg);
            holder.recycle = (RecyclerView) convertView.findViewById(R.id.adapter_jxdx_recycle);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }

        imageUtil.display(holder.topimg, list.get(position).getImgSrc());
        holder.topimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PinpaiActivity.launch(context,v,list.get(position).getImgSrc(),list.get(position).getBrandId());
            }
        });
        holder.recycle.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        adapterr=new HorRecycleAdapter(context,list.get(position).getBrandProducts()) {
            @Override
            protected void onClickItem(View v, int p) {
                if(p==list.get(position).getBrandProducts().size()){
                    PinpaiActivity.launch(context, holder.topimg,list.get(position).getImgSrc(),list.get(position).getBrandId());
                }else{
                    TextActivity.launch(context, v, mlist.get(p).getImageUrl(),mlist.get(p).getStandardId(),mlist.get(p).getDetailType(),"objectId");
                }

            }
        };
        holder.recycle.setAdapter(adapterr);
        return convertView;
    }
    private class Viewholder{
        ImageView topimg;
        RecyclerView recycle;
    }
}
