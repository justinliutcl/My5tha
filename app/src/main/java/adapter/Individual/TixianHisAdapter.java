package adapter.Individual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import InternetUser.TixianHIsUser;
import fifthutil.FifUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class TixianHisAdapter extends BaseAdapter{
    TixianHIsUser user;
    Context context;

    public TixianHisAdapter(TixianHIsUser user, Context context) {
        this.user = user;
        this.context = context;
    }
    public void setUser(TixianHIsUser users){
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
            convertView=View.inflate(context, R.layout.adapter_txhistory, null);
            holder=new Viewholder();
            holder.time=(TextView) convertView.findViewById(R.id.adapter_this_time);
            holder.point=(TextView) convertView.findViewById(R.id.adapter_this_money);
            holder.name=(TextView) convertView.findViewById(R.id.adapter_this_type);
            holder.success=(TextView) convertView.findViewById(R.id.adapter_this_result);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        String []t= FifUtil.getTime(user.getList().get(position).getOperateTime());
        holder.name.setText(user.getList().get(position).getWithdrawCashType());
        holder.time.setText(t[0]+" "+t[1]);
        holder.point.setText(user.getList().get(position).getAmount());
        holder.success.setText(user.getList().get(position).getAudit());
        return convertView;
    }
    private class Viewholder{
        TextView name,time,point,success;
    }
}
