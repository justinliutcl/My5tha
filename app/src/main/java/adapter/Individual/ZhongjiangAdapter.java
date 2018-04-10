package adapter.Individual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import InternetUser.IndividualFrag.Zhongjiang;
import fifthutil.FifUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class ZhongjiangAdapter extends BaseAdapter{
    Zhongjiang user;
    Context context;

    public ZhongjiangAdapter(Zhongjiang user, Context context) {
        this.user = user;
        this.context = context;
    }
    public void setUser(Zhongjiang users){
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
            convertView=View.inflate(context, R.layout.adapter_messageitem, null);
            holder=new Viewholder();
            holder.time=(TextView) convertView.findViewById(R.id.adapter_messagetime);
            holder.title=(TextView) convertView.findViewById(R.id.adapter_messagetitle);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        String []t= FifUtil.getTime(user.getList().get(position).getOperaterTime());
        holder.title.setText(user.getList().get(position).getTitle());
        holder.time.setText(t[0]+" "+t[1]);
        return convertView;
    }
    private class Viewholder{
        TextView title,time;
    }
}
