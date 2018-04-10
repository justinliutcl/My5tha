package adapter.afrag_other;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.ThirdTypesItem;

/**
 * Created by 不爱白菜 on 2016/4/6.
 */
public abstract class SelectResultItemRecycAdapter extends RecyclerView.Adapter<selectViewHolder>{
    List<ThirdTypesItem> horType;
    Context context;

    public List<ThirdTypesItem> getHorType() {
        return horType;
    }

    public void setHorType(List<ThirdTypesItem> horType) {
        this.horType = horType;
    }

    protected abstract void onClickItem(View v, int position);
    public SelectResultItemRecycAdapter( Context context,List<ThirdTypesItem> horType) {
        this.horType = horType;
        this.context = context;
    }

    @Override
    public selectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.adapter_selectresult_hor,null);
        selectViewHolder v=new selectViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(selectViewHolder holder, final int position) {
        holder.title.setText(horType.get(position).getTypeName());
        if(horType.get(position).isFlage()){
            holder.layout.setBackgroundResource(R.drawable.smsbuttonback);
            holder.title.setTextColor(0xffffffff);
        }else{
            holder.layout.setBackgroundColor(0xffffffff);
            holder.title.setTextColor(context.getResources().getColor(R.color.individualblack));
        }
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return horType.size();
    }
}
class selectViewHolder extends RecyclerView.ViewHolder {
    TextView title,delete;
    LinearLayout layout;
    public selectViewHolder(View itemView) {
        super(itemView);
        title= (TextView) itemView.findViewById(R.id.adapter_selectresult_hor_text);
        delete= (TextView) itemView.findViewById(R.id.adapter_selectresult_hor_delete);
        layout= (LinearLayout) itemView.findViewById(R.id.adapter_selectresult_hor_layout);
        delete.setVisibility(View.GONE);
    }

}