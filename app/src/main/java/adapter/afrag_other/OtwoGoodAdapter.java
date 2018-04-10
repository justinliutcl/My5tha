package adapter.afrag_other;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.AHomeActivity.BanderdetailsDActivity;
import com.example.transtion.my5th.R;

import InternetUser.O_other.OtherUser;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class OtwoGoodAdapter extends BaseAdapter{
    OtherUser user;
    Context context;
    ImageUtil imageUtil;
    public OtwoGoodAdapter(OtherUser user, Context context) {
        this.user = user;
        this.context = context;
        imageUtil=new ImageUtil(context);
    }
    public void setUser(OtherUser users){
        user=users;
    }
    @Override
    public int getCount() {
        return user.getSecondProductTypeList().size();
    }

    @Override
    public Object getItem(int position) {
        return user.getSecondProductTypeList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.adapter_o_twogoods, null);
            holder=new Viewholder();
            holder.name=(TextView) convertView.findViewById(R.id.adapter_o_twogoods_text);
            holder.img= (ImageView) convertView.findViewById(R.id.adapter_o_twogoods_img);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        imageUtil.display(holder.img,user.getSecondProductTypeList().get(position).getImageUrl());
        holder.name.setText(user.getSecondProductTypeList().get(position).getTypeName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpWithValue(context, BanderdetailsDActivity.class,new String[]{"typecode"},new String[]{user.getSecondProductTypeList().get(position).getTypeId()},true);
            }
        });
        return convertView;
    }
    private class Viewholder{
        TextView name;
        ImageView img;
    }
}
