package adapter.afrag_other;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.O_other.PropertyValueItem;
import fifthutil.ImageUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class CanshuAdapter extends BaseAdapter{
    List<PropertyValueItem> list;
    Context context;
    ImageUtil imageUtil;
    public CanshuAdapter(List<PropertyValueItem> list, Context context) {
        this.list = list;
        this.context = context;
        imageUtil=new ImageUtil(context);
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
            convertView=View.inflate(context, R.layout.adapter_detial_canshu, null);
            holder=new Viewholder();
            holder.name= (TextView) convertView.findViewById(R.id.adapter_detial_canshu_name);
            holder.mes= (TextView) convertView.findViewById(R.id.adapter_detial_canshu_mes);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getName()+":");
        holder.mes.setText(list.get(position).getValue());
        return convertView;
    }
    private class Viewholder{
        TextView name,mes;
    }
}
