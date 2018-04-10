package adapter.afrag_home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.SelectMesItem;

/**
 * Created by 不爱白菜 on 2016/4/4.
 */
public class SelectTypeItemAdapter extends BaseAdapter{
    Context context;
    List<SelectMesItem> list;
    public SelectTypeItemAdapter(Context context, List<SelectMesItem> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<SelectMesItem> list) {
        this.list = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Viewholder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.dialog_classselect_item, null);
            holder=new Viewholder();
            holder.title= (TextView) convertView.findViewById(R.id.dialog_classselect_item_mes);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getTypeName());
        if(list.get(position).isFlage()){
            holder.title.setBackgroundResource(R.drawable.smsbuttonback);
            holder.title.setTextColor(0xffffffff);
        }else{
            holder.title.setBackgroundResource(R.drawable.grayandwhite_oval);
            holder.title.setTextColor(context.getResources().getColor(R.color.individualblack));
        }
        return convertView;
    }
    private class Viewholder{
       TextView title;

    }
}
