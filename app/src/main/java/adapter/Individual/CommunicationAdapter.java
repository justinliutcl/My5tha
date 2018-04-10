package adapter.Individual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import InternetUser.Communicationuser;
import fifthutil.FifUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class CommunicationAdapter extends BaseAdapter{
    Communicationuser user;
    Context context;

    public CommunicationAdapter(Communicationuser user, Context context) {
        this.user = user;
        this.context = context;
    }
    public void setUser(Communicationuser users){
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
            convertView=View.inflate(context, R.layout.adapter_gwbhistory, null);
            holder=new Viewholder();
            holder.time=(TextView) convertView.findViewById(R.id.adapter_gwb_time);
            holder.day=(TextView) convertView.findViewById(R.id.adapter_gwb_day);
            holder.his=(TextView) convertView.findViewById(R.id.adapter_gwb_his);
            holder.gwb=(TextView) convertView.findViewById(R.id.adapter_gwb_gwb);
            holder.type=(TextView) convertView.findViewById(R.id.adapter_gwb_type);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        String []t= FifUtil.getTime(user.getList().get(position).getOperaterTime());
        holder.time.setText(t[1]);
        holder.day.setText(t[0]);
        holder.his.setText(user.getList().get(position).getFinanceOperaType());
        holder.gwb.setText("+"+user.getList().get(position).getAmount());
        holder.type.setText("佣");
        return convertView;
    }
    private class Viewholder{
        TextView his,day,time,type,gwb;
    }
}
