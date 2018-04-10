package adapter.afrag_home;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.AHomeActivity.TextActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.QqtmUser;
import customUI.CountdownView;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;

/**
 * Created by baicai on 2016/3/25.
 */
public class QqtmAdapter extends BaseAdapter {
    private Context context;
    private List<QqtmUser> list;
    private ImageUtil imageUtil;
    public QqtmAdapter(Context context, List<QqtmUser> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Viewholder holder;
        if(convertView==null){
            imageUtil=new ImageUtil(context);
            convertView=View.inflate(context, R.layout.adapter_qqtm, null);
            holder=new Viewholder();
            holder.timetype =(TextView) convertView.findViewById(R.id.adapter_qqtm_timetype);
            holder.time =(CountdownView) convertView.findViewById(R.id.adapter_qqtm_time);
            holder.title =(TextView) convertView.findViewById(R.id.adapter_qqtm_name);
            holder.money =(TextView) convertView.findViewById(R.id.adapter_qqtm_money);
            holder.oldmoney =(TextView) convertView.findViewById(R.id.adapter_qqtm_outmoney);
            holder.goodimg= (ImageView) convertView.findViewById(R.id.adapter_horgrid_img);
            holder.cityimg= (ImageView) convertView.findViewById(R.id.adapter_qqtm_cityimg);
            holder.oldmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }

//        if(list.get(position).isEventShow()){
//            convertView.setVisibility(View.VISIBLE);
//        }else{
//            convertView.setVisibility(View.GONE);
//        }
            if(list.get(position).getTimeSecond()>0){
                holder.timetype.setText("距离开始还有:");
                holder.time.start(list.get(position).getTimeSecond()*1000);
                long d=list.get(position).getTimeSecond()/86400;
//                if(d>1){
//                    holder.day.setVisibility(View.VISIBLE);
//                    int i=(int)Math.floor((double)d);
//                    holder.day.setText(i+"天");
//                }else{
//                    holder.day.setVisibility(View.GONE);
//                }
            }else if(list.get(position).getTimeSecond()<0){
                holder.timetype.setText("距离结束还有:");
                holder.time.start(Math.abs(list.get(position).getTimeSecond())*1000);
                long d=Math.abs(list.get(position).getTimeSecond())/86400;
//                if(d>1){
//                    holder.day.setVisibility(View.VISIBLE);
//                    int i=(int)Math.floor((double)d);
//                    holder.day.setText(i+"天");
//                }else{
//                    holder.day.setVisibility(View.GONE);
//                }
            }else if(list.get(position).getTimeSecond()==0){
                holder.timetype.setText("已结束");
                holder.time.setVisibility(View.GONE);
//                holder.day.setVisibility(View.GONE);
                holder.title.setTextColor(context.getResources().getColor(R.color.main_sgry));
                holder.money.setTextColor(context.getResources().getColor(R.color.main_sgry));
            }
        imageUtil.display4qqtm(holder.goodimg, list.get(position).getProductImg());
        imageUtil.displaywithoutanim(holder.cityimg, list.get(position).getPlaceOfProductImg());
        holder.oldmoney.setText("￥" + list.get(position).getReferenceRrice());
        holder.money.setText(list.get(position).getSellPrice());
        holder.title.setText(list.get(position).getTitle());
        holder.time.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                if(list.get(position).getTimeSecond()>0){
                    holder.time.start(list.get(position).getShowHour()*3600000);
                }else{
                    holder.timetype.setText("已结束");
                    holder.time.setVisibility(View.GONE);
                    holder.title.setTextColor(context.getResources().getColor(R.color.main_sgry));
                    holder.money.setTextColor(context.getResources().getColor(R.color.main_sgry));
                }
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpWithValue(context, TextActivity.class, new String[]{"resId", "objid", "type", "texttype"}, new String[]{"h5", list.get(position).getEventDetailId(), list.get(position).getDetailType(), "objectId"}, true);
//                Text4qqtmActivity.launch(context, holder.cityimg, "https:"+list.get(position).getProductImgs(), list.get(position).getEventDetailId(), list.get(position).getDetailType(), "objectId",list.get(position).getPlaceOfProductImg());
            }
        });
        return convertView;
    }
    private class Viewholder{
        TextView timetype, title,money,oldmoney;
        CountdownView time;
        ImageView goodimg,cityimg;
    }
}
