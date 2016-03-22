package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import InternetUser.ReferUser;
import fifthutil.FifUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class ReferAdapter extends BaseAdapter{
    ReferUser user;
    Context context;

    public ReferAdapter(ReferUser user, Context context) {
        this.user = user;
        this.context = context;
    }
    public void setUser(ReferUser users){
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
            convertView=View.inflate(context, R.layout.adapter_refer, null);
            holder=new Viewholder();
            holder.time=(TextView) convertView.findViewById(R.id.adapter_refer_time);
            holder.name=(TextView) convertView.findViewById(R.id.adapter_refer_phone);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        String []t= FifUtil.getTime(user.getList().get(position).getOperateTime());
        holder.time.setText(t[0]+" "+t[1]);
        holder.name.setText(user.getList().get(position).getPhone());
        return convertView;
    }
    private class Viewholder{
        TextView name,time,point;
    }
}
