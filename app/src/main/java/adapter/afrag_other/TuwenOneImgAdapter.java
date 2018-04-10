package adapter.afrag_other;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.O_other.DetailItem;
import fifthutil.ImageUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class TuwenOneImgAdapter extends BaseAdapter{
    List<DetailItem> list;
    Context context;
    ImageUtil imageUtil;
    public TuwenOneImgAdapter(List<DetailItem> list, Context context) {
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
            convertView=View.inflate(context, R.layout.adapter_detial_tuwen, null);
            holder=new Viewholder();
            holder.img= (ImageView) convertView.findViewById(R.id.adapter_detial_img);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }

        imageUtil.display(holder.img,list.get(position).getImg());
        return convertView;
    }
    private class Viewholder{
        ImageView img;
    }
}
