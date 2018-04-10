package adapter.afrag_home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.AHomeActivity.SelectresultActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.SelectMesItem;
import fifthutil.JumpUtil;

/**
 * Created by 不爱白菜 on 2016/4/4.
 */
public class SelectItemAdapter extends BaseAdapter{
    Context context;
    List<SelectMesItem>list;
    String typeID;
    String name;
    public SelectItemAdapter(Context context, List<SelectMesItem> list,String typeID,String name) {
        this.context = context;
        this.list = list;
        this.typeID = typeID;
        this.name = name;
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
            convertView=View.inflate(context, R.layout.adapter_select_item, null);
            holder=new Viewholder();
            holder.title= (TextView) convertView.findViewById(R.id.adapter_select_item_text);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getTypeName());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpWithValue(context, SelectresultActivity.class,new String[]{"parentTypeCode","brandId","name","position","titlename"},new String[]{typeID,list.get(position).getTypeId(),list.get(position).getTypeName(),position+"",name},true);
            }
        });
        return convertView;
    }
    private class Viewholder{
       TextView title;
    }
}
