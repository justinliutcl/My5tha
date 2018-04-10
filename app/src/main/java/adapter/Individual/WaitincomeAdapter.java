package adapter.Individual;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.IndividualUser.WaitincomeItem;
import fifthutil.FifUtil;

/**
 * Created by 不爱白菜 on 2016/6/26.
 */
public class WaitincomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<WaitincomeItem>  mlist;
    Context context;

    public WaitincomeAdapter(List<WaitincomeItem> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }


    public List<WaitincomeItem> getMlist() {
        return mlist;
    }

    public void setMlist(List<WaitincomeItem> mlist) {
        this.mlist = mlist;
    }

    @Override
    public CoustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CoustomViewHolder holderconvertView=new CoustomViewHolder( LayoutInflater.from(
                context).inflate(R.layout.adapter_waitincome_item, parent,
                false));
        return holderconvertView;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder Viewholder, final int position) {
        final CoustomViewHolder holder= (CoustomViewHolder) Viewholder;
        holder.name.setText(mlist.get(position).getOpNickName());
        holder.type.setText(mlist.get(position).getFinanceOperaTypeName());
        holder.money.setText("+" + FifUtil.getPrice(mlist.get(position).getAmount()));
       String []t= FifUtil.getTime(mlist.get(position).getOperaterTime());
        holder.time.setText(t[0]);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    class CoustomViewHolder extends RecyclerView.ViewHolder{
        TextView name,type,money,time;

        public CoustomViewHolder(View convertView) {
            super(convertView);
            name= (TextView) convertView.findViewById(R.id.adapter_waitincome_name);
            type= (TextView) convertView.findViewById(R.id.adapter_waitincome_type);
            money= (TextView) convertView.findViewById(R.id.adapter_waitincome_money);
            time= (TextView) convertView.findViewById(R.id.adapter_waitincome_time);
        }
    }
}
