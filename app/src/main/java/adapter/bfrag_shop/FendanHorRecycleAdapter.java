package adapter.bfrag_shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.shopcar.FendanGoodItem;
import fifthutil.ImageUtil;

/**
 * Created by 不爱白菜 on 2016/4/13.
 */
public class FendanHorRecycleAdapter extends RecyclerView.Adapter<FendanViewHolder> {
    List<FendanGoodItem>list;
    Context context;
    ImageUtil imageUtil;

    public FendanHorRecycleAdapter(Context context, List<FendanGoodItem> list) {
        this.context = context;
        this.list = list;
        imageUtil=new ImageUtil(context);
    }

    @Override
    public FendanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.adapter_fendan_horgrid,null);
        FendanViewHolder vh=new FendanViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(FendanViewHolder holder, int position) {
        holder.title.setText("x"+list.get(position).getNumber());
        imageUtil.displaywithoutanim(holder.tv,list.get(position).getImageSrc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
 class FendanViewHolder extends RecyclerView.ViewHolder{
    ImageView tv;
    TextView title;
    public FendanViewHolder(View itemView) {
        super(itemView);
        tv= (ImageView) itemView.findViewById(R.id.adapter_fendan_horgrid_img);
        title= (TextView) itemView.findViewById(R.id.adapter_fendan_horgrid_text);
    }
}