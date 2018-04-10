package adapter.Individual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import InternetUser.Duiuser;
import fifthutil.FifUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class DuiAdapter extends BaseAdapter{
    Duiuser user;
    Context context;

    public DuiAdapter(Duiuser user, Context context) {
        this.user = user;
        this.context = context;
    }
    public void setUser(Duiuser users){
        user=users;
    }
    @Override
    public int getCount() {
        return user.getDlist().size();
    }

    @Override
    public Object getItem(int position) {
        return user.getDlist().get(position);
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
        String []t= FifUtil.getTime(user.getDlist().get(position).getOperateTime());
        holder.time.setText(t[1]);
        holder.day.setText(t[0]);
        holder.his.setText(user.getDlist().get(position).getCardType());
        holder.gwb.setText("+"+user.getDlist().get(position).getFaceValue());
        holder.type.setText("兑");
        return convertView;
    }
    private class Viewholder{
        TextView his,day,time,type,gwb;
    }
}
