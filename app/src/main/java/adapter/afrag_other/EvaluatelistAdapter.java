package adapter.afrag_other;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.O_other.DetailItem;
import InternetUser.O_other.EvaluateItem;

/**
 * Created by baicai on 2016/3/4.
 */
public class EvaluatelistAdapter extends BaseAdapter{
    List<EvaluateItem>mlist;
    Context context;
    public EvaluatelistAdapter( List<EvaluateItem>mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }
    public void setmlist(List<EvaluateItem>mlist){
        this.mlist = mlist;
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.adapter_goodsevaluate, null);
            holder=new Viewholder();
            holder.phone = (TextView) convertView.findViewById(R.id.adapter_goodsevaluate_phone);
            holder.text = (TextView) convertView.findViewById(R.id.adapter_goodsevaluate_text);
            holder.starlayout= (LinearLayout) convertView.findViewById(R.id.adapter_goodsvaluate_layout_star);
            holder.grid = (GridView) convertView.findViewById(R.id.adapter_goodsevaluate_grid);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        holder.phone.setText(mlist.get(position).getNickName());
        holder.text.setText(mlist.get(position).getThreaText());
        int num= Integer.parseInt(mlist.get(position).getStarValue());
        for(int i=0;i<num;i++){
            holder.starlayout.getChildAt(i).setBackgroundResource(R.drawable.star);
        }
        if( mlist.get(position).getImageList()!=null){
            List<DetailItem>list= mlist.get(position).getImageList();
            OneImgAdapter adapter=new OneImgAdapter(list,context);
            holder.grid.setAdapter(adapter);
        }
        return convertView;
    }
    private class Viewholder{
        TextView phone, text;
        LinearLayout starlayout;
        GridView grid;
    }
}
