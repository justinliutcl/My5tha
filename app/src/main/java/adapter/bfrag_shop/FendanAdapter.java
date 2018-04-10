package adapter.bfrag_shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.BShopcar.GoodsorderActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.shopcar.FendanGoodItem;
import InternetUser.shopcar.FendanItem;
import fifthutil.FifUtil;
import fifthutil.ImageUtil;

/**
 * Created by baicai on 2016/3/25.
 */
public class FendanAdapter extends BaseAdapter {
    private Context context;
    private List<FendanItem> list;
    private FendanHorRecycleAdapter adapterr;
    private boolean bao=true;
    private boolean zun=true;
    ImageUtil imageUtil;
    public FendanAdapter(Context context, List<FendanItem> list) {
        this.context = context;
        this.list = list;
        imageUtil=new ImageUtil(this.context);
    }

    public List<FendanItem> getList() {
        return list;
    }

    public void setList(List<FendanItem> list) {
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
            convertView=View.inflate(context, R.layout.adapter_fendan, null);
            holder=new Viewholder();
            holder.commit = (Button) convertView.findViewById(R.id.adapter_fendan_buy);
            holder.title = (TextView) convertView.findViewById(R.id.adapter_fendan_title);
            holder.titletype = (TextView) convertView.findViewById(R.id.adapter_fendan_titletype);
            holder.money = (TextView) convertView.findViewById(R.id.adapter_fendan_money);
            holder.type = (TextView) convertView.findViewById(R.id.adapter_fendan_type);
            holder.recycle = (LinearLayout) convertView.findViewById(R.id.adapter_fendan_recycle);
            holder.fendan_layout= (LinearLayout) convertView.findViewById(R.id.adapter_fendan_layout);
        if(list.get(position).getName().equals("normalList")) {
            if (zun) {
                holder.title.setText("以下商品为第五大街尊享商品。请结算");
                holder.titletype.setText("尊");
                holder.titletype.setBackgroundResource(R.drawable.icon_zun);
                holder.fendan_layout.setVisibility(View.VISIBLE);
                zun=false;
                double p=0;
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getName().equals("normalList")) {
                        for (int j = 0; j < list.get(i).getList().size(); j++) {
                            p += list.get(i).getList().get(j).getTotalPrice();
                        }
                    }
                }
                holder.type.setText("￥"+ FifUtil.getPrice(p));
            }else{
                holder.fendan_layout.setVisibility(View.GONE);
            }
        }
        else{
            if(list.get(position).getName().equals("abroadList")) {
                if (bao) {
                    holder.title.setText("以下商品由第五大街保税仓发货。请结算");
                    holder.titletype.setText("保");
                    holder.titletype.setBackgroundResource(R.drawable.icon_bao);
                    holder.fendan_layout.setVisibility(View.VISIBLE);
                    bao=false;
                    double p=0;
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).getName().equals("abroadList")) {
                            for (int j = 0; j < list.get(i).getList().size(); j++) {
                                p += list.get(i).getList().get(j).getTotalPrice();
                            }
                        }
                    }
                    holder.type.setText("￥"+ FifUtil.getPrice(p));


                }else{
                    holder.fendan_layout.setVisibility(View.GONE);
                }
            }
        }




        if(list.get(position).getList().get(0).isFlage()){

            holder.commit.setBackgroundResource(R.drawable.grayandwhite);
            holder.commit.setText("已结算");
            holder.commit.setTextColor(context.getResources().getColor(R.color.main_sgry));
            holder.commit.setEnabled(false);
        }else{
            if(list.get(position).getList().get(0).isTijiao()){
                holder.commit.setBackgroundResource(R.drawable.grayandwhite);
                holder.commit.setEnabled(false);
                holder.commit.setText("已提交");
                holder.commit.setTextColor(context.getResources().getColor(R.color.main_sgry));
            }else {
                holder.commit.setBackgroundResource(R.drawable.buttonback);
                holder.commit.setEnabled(true);
                holder.commit.setText("结算订单");
                holder.commit.setTextColor(0xffffffff);
            }

        }
        double tmoney=0;
        for(FendanGoodItem item:list.get(position).getList()){
            tmoney+=item.getTotalPrice();
        }

        holder.money.setText("￥"+FifUtil.getPrice(tmoney));
//        holder.recycle.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
//        adapterr=new FendanHorRecycleAdapter(context,list.get(position).getList());
//        holder.recycle.setAdapter(adapterr);



        List<FendanGoodItem> tlist=list.get(position).getList();
        for(FendanGoodItem orderDetailItem:tlist){
            View v=View.inflate(context,R.layout.adapter_goodsorderlist,null);
            ImageView img= (ImageView) v.findViewById(R.id.linearlayout_orderlist_img);
            TextView title= (TextView) v.findViewById(R.id.linearlayout_orderlist_mes);
            TextView attr= (TextView) v.findViewById(R.id.linearlayout_orderlist_attr);
            TextView money= (TextView) v.findViewById(R.id.linearlayout_orderlist_money);
            TextView sum= (TextView) v.findViewById(R.id.linearlayout_orderlist_sum);

            String path=orderDetailItem.getImageSrc();
            imageUtil.display(img,path);
            attr.setText(orderDetailItem.getStandardDesc());
            money.setText("￥"+FifUtil.getPrice(orderDetailItem.getSellPrice()));
            sum.setText("×"+orderDetailItem.getNumber());
            title.setText(orderDetailItem.getTitle());
            holder.recycle.addView(v);
        }






        holder.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsorderActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.push_in, R.anim.push_out);

            }
        });
        return convertView;
    }
    private class Viewholder{
        TextView title,titletype,type,money;
        Button commit;
        LinearLayout recycle;
        LinearLayout fendan_layout;
    }
}
