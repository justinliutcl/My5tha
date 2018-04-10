package adapter.afrag_home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.SelectClassBrandsItem;
import fifthutil.ImageUtil;

/**
 * Created by 不爱白菜 on 2016/4/4.
 */
public class SelectBrandItemAdapter extends BaseAdapter{
    Context context;
    List<SelectClassBrandsItem> list;
    ImageUtil imageUtil;
    public SelectBrandItemAdapter(Context context, List<SelectClassBrandsItem> list) {
        this.context = context;
        this.list = list;
        imageUtil=new ImageUtil(context);
    }

    public void setList(List<SelectClassBrandsItem> list) {
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
            holder.img= (ImageView) convertView.findViewById(R.id.dialog_classselect_item_img);

            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        if(position==0){
            holder.img.setVisibility(View.GONE);
            holder.title.setText(list.get(position).getBrandName());
            if(list.get(position).isFlage()){
                holder.title.setBackgroundResource(R.drawable.smsbuttonback);
                holder.title.setTextColor(0xffffffff);
            }else{
                holder.title.setBackgroundResource(R.drawable.grayandwhite_oval);
                holder.title.setTextColor(context.getResources().getColor(R.color.individualblack));
            }
        }else{
            holder.img.setVisibility(View.VISIBLE);
            imageUtil.displaybandwithoutresume(holder.img, list.get(position).getBrandLogo());
            if(list.get(position).isFlage()){
                holder.title.setBackgroundResource(R.drawable.bg_btn_norm_on);
            }else{
                holder.title.setBackgroundResource(R.drawable.bg_btn_norm);
            }
        }

        return convertView;
    }
    private class Viewholder{
       TextView title;
        ImageView img;
    }
}
