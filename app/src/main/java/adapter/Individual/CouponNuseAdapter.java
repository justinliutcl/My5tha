package adapter.Individual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.Item.CouponNuseItem;
import fifthutil.FifUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class CouponNuseAdapter extends BaseAdapter{
    List<CouponNuseItem> list;
    Context context;
    double money;
    public CouponNuseAdapter(List<CouponNuseItem> list, Context context,double money) {
        this.list = list;
        this.context = context;
        this.money=money;
    }
    public CouponNuseAdapter(List<CouponNuseItem> list, Context context) {
        this.list = list;
        this.context = context;
        money=0;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.adapter_coupon, null);
            holder=new Viewholder();
            holder.time=(TextView) convertView.findViewById(R.id.adapter_coupon_time);
            holder.money=(TextView) convertView.findViewById(R.id.adapter_coupon_money);
            holder.sum=(TextView) convertView.findViewById(R.id.adapter_coupon_sum);
            holder.mes=(TextView) convertView.findViewById(R.id.adapter_coupon_mes);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        String []t= FifUtil.getTime(list.get(position).getEndTime());
        holder.money.setText("￥"+list.get(position).getFaceValue());
        holder.time.setText("过期时间："+t[0]+" "+t[1]);
        holder.sum.setText(list.get(position).getNum()+"张优惠劵");
        holder.mes.setText("俱乐部会员优惠劵-"+list.get(position).getDescription());
        return convertView;
    }
    private class Viewholder{
        TextView money,sum,time,mes;
    }
}
