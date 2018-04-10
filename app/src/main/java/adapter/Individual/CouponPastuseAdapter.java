package adapter.Individual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import InternetUser.CouponPastuser;
import fifthutil.FifUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class CouponPastuseAdapter extends BaseAdapter{
    CouponPastuser user;
    Context context;

    public CouponPastuseAdapter(CouponPastuser user, Context context) {
        this.user = user;
        this.context = context;
    }
    public void setUser(CouponPastuser users){
        user=users;
    }
    @Override
    public int getCount() {
        return user.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return user.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.adapter_coupon_out, null);
            holder=new Viewholder();
            holder.time=(TextView) convertView.findViewById(R.id.adapter_couponout_time);
            holder.money=(TextView) convertView.findViewById(R.id.adapter_couponout_money);
            holder.moneyuse=(TextView) convertView.findViewById(R.id.adapter_couponout_moneyuse);
            holder.mes=(TextView) convertView.findViewById(R.id.adapter_couponout_mes);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        String []t= FifUtil.getTime(user.getList().get(position).getEndTime());
        holder.money.setText(user.getList().get(position).getFaceValue());
        holder.time.setText("使用时间："+t[0]+" "+t[1]);
        holder.moneyuse.setText("满"+user.getList().get(position).getConsumption()+"元");
        holder.mes.setText("俱乐部会员优惠劵-"+user.getList().get(position).getDescription());
        return convertView;
    }
    private class Viewholder{
        TextView money,moneyuse,mes,time;
    }
}
