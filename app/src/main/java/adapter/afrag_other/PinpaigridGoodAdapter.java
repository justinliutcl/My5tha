package adapter.afrag_other;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.AHomeActivity.TextActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.O_other.OtherGoodUser;
import fifthutil.ImageUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class PinpaigridGoodAdapter extends BaseAdapter{
    List<OtherGoodUser>mlist;
    Context context;
    ImageUtil imageUtil;
    public PinpaigridGoodAdapter(List<OtherGoodUser> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
        imageUtil=new ImageUtil(context);
    }
    public void setmlist(List<OtherGoodUser>mlist){
        this.mlist=mlist;
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Viewholder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.adapter_o_gridgoods, null);
            holder=new Viewholder();
            holder.name= (TextView) convertView.findViewById(R.id.adapter_0_gridgoods_title);
            holder.type= (TextView) convertView.findViewById(R.id.adapter_0_gridgoods_type);
            holder.money= (TextView) convertView.findViewById(R.id.adapter_0_gridgoods_money);
            holder.outmoney= (TextView) convertView.findViewById(R.id.adapter_0_gridgoods_outmoney);
            holder.goodtype= (TextView) convertView.findViewById(R.id.adapter_0_gridgoods_ident);
            holder.img= (ImageView) convertView.findViewById(R.id.adapter_0_gridgoods_godimg);
            holder.cityimg= (ImageView) convertView.findViewById(R.id.adapter_0_gridgoods_cityimg);
            holder.outmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        holder.name.setText(mlist.get(position).getTitle());
        holder.type.setVisibility(View.GONE);
        holder.cityimg.setVisibility(View.GONE);
        holder.money.setText(mlist.get(position).getPrice());
        holder.outmoney.setText("￥"+mlist.get(position).getMarketPrice());
        holder.goodtype.setText("极速保税");
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
        imageUtil.displaybandwithoutresume(holder.img, mlist.get(position).getImageUrl());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextActivity.launch(context, holder.img, mlist.get(position).getImageUrl(), mlist.get(position).getStandardId(), mlist.get(position).getDetailType(),"objectId");
            }
        });
        return convertView;
    }
    private class Viewholder{
        TextView name,type,money,outmoney,goodtype;
        ImageView img,cityimg;
    }
}
