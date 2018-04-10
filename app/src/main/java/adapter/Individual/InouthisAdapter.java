package adapter.Individual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import InternetUser.InoutHisUser;
import fifthutil.FifUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public class InouthisAdapter extends BaseAdapter{
    InoutHisUser user;
    Context context;

    public InouthisAdapter(InoutHisUser user, Context context) {
        this.user = user;
        this.context = context;
    }
    public void setUser(InoutHisUser users){
        user=users;
    }
    @Override
    public int getCount() {
        return user.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return user.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.adapter_gwbhistory, null);
            holder=new Viewholder();
            holder.time=(TextView) convertView.findViewById(R.id.adapter_gwb_time);
            holder.day=(TextView) convertView.findViewById(R.id.adapter_gwb_day);
            holder.his=(TextView) convertView.findViewById(R.id.adapter_gwb_his);
            holder.gwb=(TextView) convertView.findViewById(R.id.adapter_gwb_gwb);
            holder.type=(TextView) convertView.findViewById(R.id.adapter_gwb_type);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        String []t= FifUtil.getTime(user.getList().get(position).getOperateTime());
        holder.time.setText(t[1]);
        holder.day.setText(t[0]);
        holder.his.setText(user.getList().get(position).getDescription());
        double p=Double.parseDouble(user.getList().get(position).getAmount());

        String text="";
        switch (user.getList().get(position).getFinanceOperaType()){
            case "2":
                text="充";
                break;
            case "12":
                text="充";
                break;
            case "13":
                text="充";
                break;
            case "14":
                text="充";
                break;
            case "1":
                text="购";
                break;
            case "4":
                text="购";
                break;
            case "5":
                text="购";
                break;
            case "21":
                text="购";
                break;
            case "16":
                text="提";
                break;
            case "3":
                text="兑";
                break;
            case "90":
                text="退";
                break;
            case "91":
                text="退";
                break;
            case "11":
                text="扣";
                break;
            case "15":
                text="扣";
                break;

            default:
                text="充";
                break;
        }
        if(p>=0){
            holder.gwb.setText("+"+FifUtil.getPrice(p));
        }else{
            holder.gwb.setText(FifUtil.getPrice(p));
        }

        holder.type.setText(text);
        return convertView;
    }
    private class Viewholder{
        TextView his,day,time,type,gwb;
    }
}
