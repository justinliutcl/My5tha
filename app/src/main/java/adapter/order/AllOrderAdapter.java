package adapter.order;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.GoodsorderUser;
import InternetUser.order.GoodsorderItem;
import InternetUser.order.OrderDetailItem;
import fifthutil.FifUtil;
import fifthutil.ImageUtil;

/**
 * Created by baicai on 2016/3/15.
 */
public class AllOrderAdapter extends BaseAdapter {
    Context context;
    GoodsorderUser user;
    ImageUtil imageUtil;
    public AllOrderAdapter(GoodsorderUser user,Context context) {
        this.context = context;
        this.user = user;
        imageUtil=new ImageUtil(context);
    }
    public void setUser(GoodsorderUser user){
        this.user=user;
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
//        if(convertView==null){
            convertView=View.inflate(context, R.layout.adapter_goodsorder, null);
            holder=new Viewholder();
            holder.time=(TextView) convertView.findViewById(R.id.adapter_goodsorder_time);
            holder.orderstatus=(TextView) convertView.findViewById(R.id.adapter_goodsorder_status);
            holder.cheap=(TextView) convertView.findViewById(R.id.adapter_goodsorder_YOUHUI);
            holder.cheapmoney=(TextView) convertView.findViewById(R.id.adapter_goodsorder_yhmoney);
            holder.totalmoney=(TextView) convertView.findViewById(R.id.adapter_goodsorder_totalprice);
            holder.delete=(Button) convertView.findViewById(R.id.adapter_goodsorder_delete);
            holder.logistic=(Button) convertView.findViewById(R.id.adapter_goodsorder_logistics);
            holder.payagain=(Button) convertView.findViewById(R.id.adapter_goodsorder_shopagain);
            holder.showorder=(Button) convertView.findViewById(R.id.adapter_goodsorder_seeorder);
            holder.fastpay=(Button) convertView.findViewById(R.id.adapter_goodsorder_fastpay);

            holder.cheapmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            convertView.setTag(holder);
//        }else{
//            holder=(Viewholder) convertView.getTag();
//        }
        LinearLayout orderlist=(LinearLayout) convertView.findViewById(R.id.adapter_goodsorder_layout);
        List<GoodsorderItem> mlist=user.getList();
        List<OrderDetailItem> tlist=user.getList().get(position).getList();

        String []t= FifUtil.getTime(mlist.get(position).getOperateTime());
        holder.time.setText(t[0]+" "+t[1]);
        holder.orderstatus.setText(mlist.get(position).getOrderStatusString()+"");
        holder.totalmoney.setText("￥"+mlist.get(position).getTotal());
        if(mlist.get(position).getIssueId()!=null){
            holder.cheap.setVisibility(View.VISIBLE);
            holder.cheapmoney.setVisibility(View.VISIBLE);
            holder.cheapmoney.setText("￥"+mlist.get(position).getCouponMoney());
        }else{
            holder.cheap.setVisibility(View.GONE);
            holder.cheapmoney.setVisibility(View.GONE);
        }
        if(mlist.get(position).getOrderStatus().equals("0")){
            holder.logistic.setVisibility(View.GONE);
            holder.payagain.setVisibility(View.GONE);
            holder.showorder.setVisibility(View.GONE);
            holder.fastpay.setVisibility(View.VISIBLE);
        }else{
            holder.logistic.setVisibility(View.VISIBLE);
            holder.payagain.setVisibility(View.VISIBLE);
            holder.showorder.setVisibility(View.VISIBLE);
            holder.fastpay.setVisibility(View.GONE);
        }

        for(OrderDetailItem orderDetailItem:tlist){
            View v=View.inflate(context,R.layout.adapter_orderlist,null);
            ImageView img= (ImageView) v.findViewById(R.id.linearlayout_orderlist_img);
            TextView title= (TextView) v.findViewById(R.id.linearlayout_orderlist_mes);
            TextView attr= (TextView) v.findViewById(R.id.linearlayout_orderlist_attr);
            TextView money= (TextView) v.findViewById(R.id.linearlayout_orderlist_money);
            TextView sum= (TextView) v.findViewById(R.id.linearlayout_orderlist_sum);
            String path=orderDetailItem.getImageSrc();
            imageUtil.display(img,path);
//            img.setTag(path);
//            BitmapUtil.setImage(img);
//            bitutil.display(img,path);
            if(orderDetailItem.getList().size()!=0){
                for(int i=0;i<orderDetailItem.getList().size();i++){
                    if(i==0){
                        attr.setText(orderDetailItem.getList().get(i).getValue());
                    }else{
                        attr.append("|"+(orderDetailItem.getList().get(i).getValue()));
                    }

                }
            }

            money.setText("￥"+orderDetailItem.getPrice());
            sum.setText("×"+orderDetailItem.getNumber());
            title.setText(orderDetailItem.getTitle());
            orderlist.addView(v);
        }


        return convertView;
    }
    private class Viewholder{
        TextView time,orderstatus,cheap,cheapmoney,totalmoney;
        Button delete,logistic,payagain,showorder,fastpay;
        LinearLayout orderlist;

    }
}
