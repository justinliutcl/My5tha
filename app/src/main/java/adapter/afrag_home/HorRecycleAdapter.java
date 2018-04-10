package adapter.afrag_home;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.JxduItem;
import fifthutil.ImageUtil;

/**
 * Created by baicai on 2016/3/23.
 */
public abstract class HorRecycleAdapter extends RecyclerView.Adapter<mViewHolder> {
    Context context;
    List<JxduItem>mlist;
    ImageUtil imageUtil;
    protected abstract void onClickItem(View v, int position);
    public HorRecycleAdapter(Context context, List<JxduItem> list) {
        this.mlist=list;
        this.context = context;
        imageUtil=new ImageUtil(context);
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.adapter_horgrid,null);
        mViewHolder vh=new mViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final mViewHolder holder, final int position) {
        if(position==mlist.size()){
            holder.money.setVisibility(View.GONE);
            holder.outmoney.setVisibility(View.GONE);
            holder.title.setVisibility(View.GONE);
            holder.tv.setVisibility(View.GONE);
            holder.showall.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem(holder.tv, position);
                }
            });
        }else{
            holder.money.setVisibility(View.VISIBLE);
            holder.outmoney.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.VISIBLE);
            holder.tv.setVisibility(View.VISIBLE);
            holder.showall.setVisibility(View.GONE);
            imageUtil.display(holder.tv,mlist.get(position).getImageUrl());
            holder.money.setText("￥" + mlist.get(position).getPrice());
            holder.outmoney.setText("￥"+mlist.get(position).getMarketPrice());
            holder.outmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.title.setText(mlist.get(position).getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem(holder.tv,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size()+1;
    }
}
 class mViewHolder extends RecyclerView.ViewHolder{
    ImageView tv;
    TextView title,money,outmoney;
     LinearLayout showall;
    public mViewHolder(View itemView) {
        super(itemView);
        tv= (ImageView) itemView.findViewById(R.id.adapter_horgrid_img);
        title= (TextView) itemView.findViewById(R.id.adapter_horgrid_title);
        money= (TextView) itemView.findViewById(R.id.adapter_horgrid_money);
        outmoney= (TextView) itemView.findViewById(R.id.adapter_horgrid_outmoney);
        showall= (LinearLayout) itemView.findViewById(R.id.adapter_horgrid_showall);
    }
}