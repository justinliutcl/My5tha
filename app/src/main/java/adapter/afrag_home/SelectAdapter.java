package adapter.afrag_home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.SelectTitleItem;
import customUI.GridViewForScrollView;

/**
 * Created by 不爱白菜 on 2016/4/4.
 */
public class SelectAdapter extends BaseAdapter{
    Context context;
    List<SelectTitleItem> list;

    public SelectAdapter(Context context, List<SelectTitleItem> list) {
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final Viewholder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.adapter_select, null);
            holder=new Viewholder();
            holder.title= (TextView) convertView.findViewById(R.id.adapter_select_title);
            holder.grid= (GridViewForScrollView) convertView.findViewById(R.id.adapter_select_grid);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getTypeName());
        SelectItemAdapter adapter=new SelectItemAdapter(context,list.get(position).getTypeShow(),list.get(position).getTypeId(),list.get(position).getTypeName());
        holder.grid.setAdapter(adapter);
        return convertView;
    }
    private class Viewholder{
       TextView title;
        GridViewForScrollView grid;
    }
}
