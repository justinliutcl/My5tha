package adapter.afrag_home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.SelectClassBrandsItem;
import InternetUser.A_Home.SelectMesItem;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by 不爱白菜 on 2016/4/6.
 */
public abstract class SelectResultItemRecycAdapter extends RecyclerView.Adapter<selectViewHolder>{
    List<SelectMesItem> horType;
    List<SelectClassBrandsItem>horbrand;
    Context context;

    public List<SelectClassBrandsItem> getHorbrand() {
        return horbrand;
    }

    public void setHorbrand(List<SelectClassBrandsItem> horbrand) {
        this.horbrand = horbrand;
    }

    public List<SelectMesItem> getHorType() {
        return horType;
    }

    public void setHorType(List<SelectMesItem> horType) {
        this.horType = horType;
    }

    protected abstract void onClickItem(View v, int position);
    public SelectResultItemRecycAdapter(List<SelectMesItem> horType, List<SelectClassBrandsItem> horbrand, Context context) {
        this.horType = horType;
        this.horbrand = horbrand;
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
        if(position<horType.size()){
            if(!horType.get(position).getTypeName().equals("全部"))
                holder.title.setText(horType.get(position).getTypeName());
        }else{
            if(!horbrand.get(position-horType.size()).getBrandName().equals("全部"))
                holder.title.setText(horbrand.get(position-horType.size()).getBrandName());
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return horType.size()+horbrand.size();
    }
}
class selectViewHolder extends ViewHolder{
    TextView title,delete;
    public selectViewHolder(View itemView) {
        super(itemView);
        title= (TextView) itemView.findViewById(R.id.adapter_selectresult_hor_text);
        delete= (TextView) itemView.findViewById(R.id.adapter_selectresult_hor_delete);
    }

}