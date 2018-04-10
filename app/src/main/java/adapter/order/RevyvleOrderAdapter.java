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
import android.widget.Toast;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.GoodsorderUser;
import InternetUser.order.GoodsorderItem;
import InternetUser.order.OrderDetailItem;
import adapter.Individual.AddressAdapter;
import fifthutil.FifUtil;
import fifthutil.ImageUtil;
import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/15.
 */
public class RevyvleOrderAdapter extends BaseAdapter {
    Context context;
    GoodsorderUser user;
    ImageUtil imageUtil;
    AddressAdapter.OnadapterChangeCall callback;
    String path_delete= Path.HOST+Path.ip+Path.ORDERDELETE_PATH;
    String path_back=Path.HOST+Path.ip+Path.ORDERBACK_PATH;
    LodingUtil lodingUtil;
    public RevyvleOrderAdapter(GoodsorderUser user, Context context, LodingUtil lodingUtil, AddressAdapter.OnadapterChangeCall callback) {
        this.context = context;
        this.user = user;
        this.callback=callback;
        this.lodingUtil=lodingUtil;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder holder;
//        if(convertView==null){
            convertView=View.inflate(context, R.layout.adapter_goodsorderrecycle, null);
            holder=new Viewholder();
            holder.time=(TextView) convertView.findViewById(R.id.adapter_goodsorder_time);
            holder.orderstatus=(TextView) convertView.findViewById(R.id.adapter_goodsorder_status);
            holder.cheap=(TextView) convertView.findViewById(R.id.adapter_goodsorder_YOUHUI);
            holder.cheapmoney=(TextView) convertView.findViewById(R.id.adapter_goodsorder_yhmoney);
            holder.totalmoney=(TextView) convertView.findViewById(R.id.adapter_goodsorder_totalprice);
            holder.delete=(Button) convertView.findViewById(R.id.adapter_goodsorder_delete);
            holder.greturn =(Button) convertView.findViewById(R.id.adapter_goodsorder_return);

            holder.cheapmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            convertView.setTag(holder);
//        }else{
//            holder=(Viewholder) convertView.getTag();
//        }
        LinearLayout orderlist=(LinearLayout) convertView.findViewById(R.id.adapter_goodsorder_layout);
        List<GoodsorderItem> mlist=user.getList();


        String []t= FifUtil.getTime(mlist.get(position).getOperateTime());
        holder.time.setText(t[0]+" "+t[1]);
        holder.orderstatus.setText(mlist.get(position).getOrderStatusString()+"");
        holder.totalmoney.setText("￥"+mlist.get(position).getTotal());
        if(!mlist.get(position).getCouponMoney().equals("0")){
            holder.cheap.setVisibility(View.VISIBLE);
            holder.cheapmoney.setVisibility(View.VISIBLE);
            holder.cheapmoney.setText("￥" + mlist.get(position).getCouponMoney());
        }else{
            holder.cheap.setVisibility(View.GONE);
            holder.cheapmoney.setVisibility(View.GONE);
        }

//        for(int i=0;i<user.getList().get(position).getSubOrderList().size();i++) {
//            List<OrderDetailItem> tlist=user.getList().get(position).getSubOrderList().get(i).getOrderDetailViewList();
//            for (OrderDetailItem orderDetailItem : tlist) {
//                View v = View.inflate(context, R.layout.adapter_orderlist, null);
//                ImageView img = (ImageView) v.findViewById(R.id.linearlayout_orderlist_img);
//                TextView title = (TextView) v.findViewById(R.id.linearlayout_orderlist_mes);
//                TextView attr = (TextView) v.findViewById(R.id.linearlayout_orderlist_attr);
//                TextView money = (TextView) v.findViewById(R.id.linearlayout_orderlist_money);
//                TextView sum = (TextView) v.findViewById(R.id.linearlayout_orderlist_sum);
//                String path = orderDetailItem.getImageSrc();
//                imageUtil.display(img, path);
////            img.setTag(path);
////            BitmapUtil.setImage(img);
////            bitutil.display(img,path);
//                if (orderDetailItem.getOrderTatilAttributeList().size() != 0) {
//                    for (int  j= 0; j < orderDetailItem.getOrderTatilAttributeList().size(); j++) {
//                        if (j == 0) {
//                            attr.setText(orderDetailItem.getOrderTatilAttributeList().get(j).getValue());
//                        } else {
//                            attr.append("|" + (orderDetailItem.getOrderTatilAttributeList().get(j).getValue()));
//                        }
//
//                    }
//                }
//
//                money.setText("￥" + orderDetailItem.getPrice());
//                sum.setText("×" + orderDetailItem.getNumber());
//                title.setText(orderDetailItem.getTitle());
//                orderlist.addView(v);
//            }
//        }









        if(mlist.get(position).getOrderStatus().equals("0") || mlist.get(position).getOrderStatus().equals("99")) {
            if(user.getList().get(position).getSubOrderList()!=null) {
                for (int i = 0; i < user.getList().get(position).getSubOrderList().size(); i++) {
                    final List<OrderDetailItem> tlist = user.getList().get(position).getSubOrderList().get(i).getOrderDetailViewList();
                    for (final OrderDetailItem orderDetailItem : tlist) {
                        View v = View.inflate(context, R.layout.adapter_orderlist, null);
                        ImageView img = (ImageView) v.findViewById(R.id.linearlayout_orderlist_img);
                        TextView title = (TextView) v.findViewById(R.id.linearlayout_orderlist_mes);
                        TextView attr = (TextView) v.findViewById(R.id.linearlayout_orderlist_attr);
                        TextView money = (TextView) v.findViewById(R.id.linearlayout_orderlist_money);
                        TextView sum = (TextView) v.findViewById(R.id.linearlayout_orderlist_sum);
                        String path = orderDetailItem.getImageSrc();
                        imageUtil.display(img, path);
                        if (orderDetailItem.getOrderTatilAttributeList().size() != 0) {
                            for (int j = 0; j < orderDetailItem.getOrderTatilAttributeList().size(); j++) {
                                if (j == 0) {
                                    attr.setText(orderDetailItem.getOrderTatilAttributeList().get(j).getValue());
                                } else {
                                    attr.append("|" + (orderDetailItem.getOrderTatilAttributeList().get(j).getValue()));
                                }

                            }
                        }
                        money.setText("￥" + orderDetailItem.getPrice());
                        sum.setText("×" + orderDetailItem.getNumber());
                        title.setText(orderDetailItem.getTitle());
                        orderlist.addView(v);
                    }
                }
            }
        }else{
            final List<OrderDetailItem> ttlist = user.getList().get(position).getOrderDetailViewList();
            for (final OrderDetailItem orderDetailItem : ttlist) {
                View v = View.inflate(context, R.layout.adapter_orderlist, null);
                ImageView img = (ImageView) v.findViewById(R.id.linearlayout_orderlist_img);
                TextView title = (TextView) v.findViewById(R.id.linearlayout_orderlist_mes);
                TextView attr = (TextView) v.findViewById(R.id.linearlayout_orderlist_attr);
                TextView money = (TextView) v.findViewById(R.id.linearlayout_orderlist_money);
                TextView sum = (TextView) v.findViewById(R.id.linearlayout_orderlist_sum);
                String path = orderDetailItem.getImageSrc();
                imageUtil.display(img, path);
//            img.setTag(path);
//            BitmapUtil.setImage(img);
//            bitutil.display(img,path);
                if (orderDetailItem.getOrderTatilAttributeList().size() != 0) {
                    for (int i = 0; i < orderDetailItem.getOrderTatilAttributeList().size(); i++) {
                        if (i == 0) {
                            attr.setText(orderDetailItem.getOrderTatilAttributeList().get(i).getValue());
                        } else {
                            attr.append("|" + (orderDetailItem.getOrderTatilAttributeList().get(i).getValue()));
                        }

                    }
                }

                money.setText("￥" + orderDetailItem.getPrice());
                sum.setText("×" + orderDetailItem.getNumber());
                title.setText(orderDetailItem.getTitle());
                orderlist.addView(v);
            }
        }


















        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
            }
        });
        holder.greturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(position);
            }
        });
        return convertView;
    }
    private void delete(final int position){
        lodingUtil.showShapeLoding();
        HttpConnectionUtil.getJsonJsonwithDialog(context, path_delete, new String[]{"MemberId", "Ordernumber"}, new String[]{ShareUtil.getInstanse(context).getMemberID(), user.getList().get(position).getOrderNumber()}, lodingUtil, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                lodingUtil.disShapeLoding();
                user.getList().remove(position);
                Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                callback.adapterChangeBack(position);
            }
        });
    }
    private void back(final int position){
        lodingUtil.showShapeLoding();
        HttpConnectionUtil.getJsonJsonwithDialog(context, path_back, new String[]{"MemberId", "Ordernumber"}, new String[]{ShareUtil.getInstanse(context).getMemberID(), user.getList().get(position).getOrderNumber()}, lodingUtil, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                lodingUtil.disShapeLoding();
                user.getList().remove(position);
                Toast.makeText(context,"还原成功",Toast.LENGTH_SHORT).show();
                callback.adapterChangeBack(position);
            }
        });
    }
    private class Viewholder{
        TextView time,orderstatus,cheap,cheapmoney,totalmoney;
        Button delete, greturn;
        LinearLayout orderlist;

    }
}
