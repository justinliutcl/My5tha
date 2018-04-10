package adapter.order;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.order.LogistDetailsItem;
import fifthutil.FifUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class LogistDetailsAdapter extends BaseAdapter{
    List<LogistDetailsItem> list;
    Context context;

    public LogistDetailsAdapter(List<LogistDetailsItem> list, Context context) {
        this.list = list;
        this.context = context;
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
            convertView=View.inflate(context, R.layout.adapter_logistic, null);
            holder=new Viewholder();
            holder.time=(TextView) convertView.findViewById(R.id.adapter_logistic_time);
            holder.small=(TextView) convertView.findViewById(R.id.adapter_logistic_smalloval);
            holder.big=(TextView) convertView.findViewById(R.id.adapter_logistic_bigoval);
            holder.title=(TextView) convertView.findViewById(R.id.adapter_logistic_title);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        String []t= FifUtil.getTime(list.get(position).getOperationTime());
        holder.time.setText(t[0]+"  "+t[1]);
        holder.title.setText(list.get(position).getOperationContext());
        return convertView;
    }
    private class Viewholder{
        TextView small,big,title,time;
    }
}
