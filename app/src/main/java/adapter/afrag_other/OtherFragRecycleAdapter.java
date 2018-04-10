package adapter.afrag_other;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.AHomeActivity.TextActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.O_other.OtherGoodUser;
import fifthutil.ImageUtil;

/**
 * Created by 不爱白菜 on 2016/6/26.
 */
public class OtherFragRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<OtherGoodUser> mlist;
    Context context;
    ImageUtil imageUtil;
    boolean flage=true;

    public OtherFragRecycleAdapter(List<OtherGoodUser> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
        imageUtil=new ImageUtil(context);
    }

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

    public List<OtherGoodUser> getMlist() {
        return mlist;
    }

    public void setMlist(List<OtherGoodUser> mlist) {
        this.mlist = mlist;
    }

    @Override
    public CoustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CoustomViewHolder holderconvertView=new CoustomViewHolder(View.inflate(context, R.layout.adapter_o_gridgoods, null));
        return holderconvertView;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder Viewholder, final int position) {
        final CoustomViewHolder holder= (CoustomViewHolder) Viewholder;
        holder.name.setText(mlist.get(position).getTitle());
        holder.type.setText(mlist.get(position).getState() + "直供 " + mlist.get(position).getArea() + "闪电发货");
        holder.money.setText(mlist.get(position).getPrice());
        holder.outmoney.setText("￥" + mlist.get(position).getMarketPrice());
        switch (mlist.get(position).getDetailType()){
            case "2":
                holder.goodtype.setText("极速保税");
                holder.goodtype.setBackgroundColor(0xffa321dd);
                break;
            case "3":
                holder.goodtype.setText("匠心独寻");
                holder.goodtype.setBackgroundColor(0xfff32d61);
                break;
            case "5":
                holder.goodtype.setText("极速保税");
                holder.goodtype.setBackgroundColor(0xffa321dd);
                break;
            case "7":
                holder.goodtype.setText("匠心独寻");
                holder.goodtype.setBackgroundColor(0xfff32d61);
                break;
            default:
                holder.goodtype.setText("匠心独寻");
                holder.goodtype.setBackgroundColor(0xfff32d61);
                break;
        }
        if(flage) {
            imageUtil.display40grid(holder.img, mlist.get(position).getImageUrl());
            imageUtil.displaywithoutanim(holder.cityimg, mlist.get(position).getFlag());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextActivity.launch(context, holder.img, mlist.get(position).getImageUrl(), mlist.get(position).getStandardId(), mlist.get(position).getDetailType(), "objectId");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    class CoustomViewHolder extends RecyclerView.ViewHolder{
        TextView name,type,money,outmoney,goodtype;
        ImageView img,cityimg;

        public CoustomViewHolder(View convertView) {
            super(convertView);
            name= (TextView) convertView.findViewById(R.id.adapter_0_gridgoods_title);
            type= (TextView) convertView.findViewById(R.id.adapter_0_gridgoods_type);
            money= (TextView) convertView.findViewById(R.id.adapter_0_gridgoods_money);
            outmoney= (TextView) convertView.findViewById(R.id.adapter_0_gridgoods_outmoney);
            goodtype= (TextView) convertView.findViewById(R.id.adapter_0_gridgoods_ident);
            img= (ImageView) convertView.findViewById(R.id.adapter_0_gridgoods_godimg);
            cityimg= (ImageView) convertView.findViewById(R.id.adapter_0_gridgoods_cityimg);
            outmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
