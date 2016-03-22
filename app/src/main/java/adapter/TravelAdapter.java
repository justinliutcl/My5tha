package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import InternetUser.TravelUser;
import fifthutil.FifUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class TravelAdapter extends BaseAdapter{
    TravelUser user;
    Context context;

    public TravelAdapter(TravelUser user, Context context) {
        this.user = user;
        this.context = context;
    }
    public void setUser(TravelUser users){
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
            convertView=View.inflate(context, R.layout.adapter_travel, null);
            holder=new Viewholder();
            holder.time=(TextView) convertView.findViewById(R.id.adapter_travel_time);
            holder.point=(TextView) convertView.findViewById(R.id.adapter_travel_point);
            holder.name=(TextView) convertView.findViewById(R.id.adapter_travel_name);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        String []t= FifUtil.getTime(user.getList().get(position).getOperateTime());
        holder.name.setText(user.getList().get(position).getGainType());
        holder.time.setText(t[0]+" "+t[1]);
        holder.point.setText(user.getList().get(position).getFundValue());
        return convertView;
    }
    private class Viewholder{
        TextView name,time,point;
    }
}
