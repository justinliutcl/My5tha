package adapter.afrag_other;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.transtion.my5th.AHomeActivity.PinpaiActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.BrandViewItem;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;

/**
 * Created by baicai on 2016/3/23.
 */
public  class HorTwoRecycleAdapter extends RecyclerView.Adapter<TwoViewHolder> {
    Context context;
    List<BrandViewItem>list;
    ImageUtil imageUtil;

    public List<BrandViewItem> getList() {
        return list;
    }

    public void setList(List<BrandViewItem> list) {
        this.list = list;
    }

    public HorTwoRecycleAdapter(Context context, List<BrandViewItem> list) {
        this.list=list;
        this.context = context;
        imageUtil=new ImageUtil(context);
    }

    @Override
    public TwoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.adapter_brandhorgrid,null);
       TwoViewHolder vh=new TwoViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final TwoViewHolder holder, final int position) {
        imageUtil.display(holder.title,list.get(position).getBrandLogo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpWithValue(context,PinpaiActivity.class,new String[]{"resId","brandId"},new String[]{"1",list.get(position).getBrandId()},true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class TwoViewHolder extends RecyclerView.ViewHolder {
    ImageView title;

    public TwoViewHolder(View itemView) {
        super(itemView);
        title = (ImageView) itemView.findViewById(R.id.adapter_brandhorgrid_img);
    }
}