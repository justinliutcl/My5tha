package adapter.order;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transtion.my5th.BShopcar.LogisticActivity;
import com.example.transtion.my5th.BShopcar.ShouyinActivity;
import com.example.transtion.my5th.DIndividualActivity.EvaluateActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.GoodsorderUser;
import InternetUser.order.GoodsorderItem;
import InternetUser.order.OrderDetailItem;
import InternetUser.shopcar.DingdanUser;
import InternetUser.shopcar.FendanUser;
import adapter.Individual.AddressAdapter;
import fifthutil.FifUtil;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;
import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/15.
 */
public class AllOrderWaitPayOrderAdapter extends BaseAdapter {
    Context context;
    GoodsorderUser user;
    ImageUtil imageUtil;
    AddressAdapter.OnadapterChangeCall callback;
    String path_delete= Path.HOST+Path.ip+Path.ORDERDETAIL_DELETE2RECYCLE_PATH;
    String path_besure= Path.HOST+Path.ip+Path.ORDERDETAIL_BESURE_PATH;
    LodingUtil lodingUtil;
    public AllOrderWaitPayOrderAdapter(GoodsorderUser user, Context context, LodingUtil lodingUtil, AddressAdapter.OnadapterChangeCall callback) {
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
        final Viewholder holder;
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
            holder.besure=(Button) convertView.findViewById(R.id.adapter_goodsorder_besure);

            holder.cheapmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//            convertView.setTag(holder);
//        }else{
//            holder=(Viewholder) convertView.getTag();
//        }
        LinearLayout orderlist=(LinearLayout) convertView.findViewById(R.id.adapter_goodsorder_layout);
        final List<GoodsorderItem> mlist=user.getList();


        String []t= FifUtil.getTime(mlist.get(position).getOperateTime());
        holder.time.setText(t[0]+" "+t[1]);
        holder.orderstatus.setText(mlist.get(position).getOrderStatusString()+"");
        holder.totalmoney.setText("￥"+mlist.get(position).getTotal());


        if(mlist.get(position).getIssueId().length()>1){
            holder.cheap.setVisibility(View.VISIBLE);
            holder.cheapmoney.setVisibility(View.VISIBLE);
            holder.cheapmoney.setText("￥"+mlist.get(position).getCouponMoney());
        }else{
            holder.cheap.setVisibility(View.GONE);
            holder.cheapmoney.setVisibility(View.GONE);
        }
        if(mlist.get(position).getOrderStatus().equals("0")){
            holder.logistic.setVisibility(View.GONE);
//            holder.payagain.setVisibility(View.GONE);
//            holder.showorder.setVisibility(View.GONE);
            holder.fastpay.setVisibility(View.VISIBLE);
            holder.besure.setVisibility(View.GONE);
        }else{
            holder.besure.setVisibility(View.GONE);
            if(mlist.get(position).getOrderStatus().equals("4")){
                holder.besure.setVisibility(View.VISIBLE);
            }
            holder.logistic.setVisibility(View.VISIBLE);
//            holder.payagain.setVisibility(View.VISIBLE);
//            holder.showorder.setVisibility(View.VISIBLE);
            holder.fastpay.setVisibility(View.GONE);

        }
        if(mlist.get(position).getOrderStatus().equals("99")||mlist.get(position).getOrderStatus().equals("6")){
            holder.delete.setVisibility(View.VISIBLE);
        }else{
            holder.delete.setVisibility(View.GONE);
        }
        holder.fastpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FendanUser  muser=new FendanUser();
                muser.setList(null);
                DingdanUser.getInstance().setFendanUser(muser);
                JumpUtil.jumpWithValue(context, ShouyinActivity.class,
                        new String[]{"money", "CouponIssueId", "title", "position", "id", "initType", "incomegwb", "incomeyongjin","Type"},
                        new String[]{mlist.get(position).getSurplusMoney() + "", "", mlist.get(position).getSubOrderList().get(0).getOrderDetailViewList().get(0).getTitle(), position + "", mlist.get(position).getOrderNumber(), "2", user.getBalance()+"", user.getCommission()+"",mlist.get(position).getSubOrderList().get(0).getOrderDetailViewList().get(0).isUseElectronic()+""}, true);

            }
        });
        holder.besure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lodingUtil.showShapeLoding();
                HttpConnectionUtil.getJsonJsonwithDialog(context, path_besure, new String[]{"MemberId", "OrderNumber"}, new String[]{ShareUtil.getInstanse(context).getMemberID(), user.getList().get(position).getOrderNumber()}, lodingUtil, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        lodingUtil.disShapeLoding();
                        holder.orderstatus.setText("待评价");
                        holder.besure.setText("已收货");
                        holder.besure.setEnabled(false);
                    }
                });
            }
        });
        holder.logistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpWithValue(context, LogisticActivity.class,new String[]{"orderNumber"}, new String[]{mlist.get(position).getOrderNumber()},true);
            }
        });

//        if(mlist.get(position).getOrderStatus().equals("1")){
//            holder.cheap.setVisibility(View.VISIBLE);
//            holder.cheapmoney.setVisibility(View.VISIBLE);
//        }else{
//            holder.cheap.setVisibility(View.GONE);
//            holder.cheapmoney.setVisibility(View.GONE);
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
                        Button btn = (Button) v.findViewById(R.id.linearlayout_orderlist_seeorder);
                        if (mlist.get(position).getOrderStatus().equals("5")) {
                            btn.setVisibility(View.VISIBLE);
                        } else {
                            btn.setVisibility(View.GONE);
                        }
                        if (orderDetailItem.isEvluate()) {
                            btn.setText("已评价");
                            btn.setEnabled(false);
                        } else {
                            btn.setText("我要晒单");
                            btn.setEnabled(true);
                        }
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                JumpUtil.jumpWithValue(context, EvaluateActivity.class, new String[]{"title", "path", "ordernum", "productId"}, new String[]{orderDetailItem.getTitle(), orderDetailItem.getImageSrc(), mlist.get(position).getOrderNumber(), orderDetailItem.getProductId()}, true);
                            }
                        });
                        String path = orderDetailItem.getImageSrc();
                        Log.i("text---",path);
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
                Button btn = (Button) v.findViewById(R.id.linearlayout_orderlist_seeorder);
                if (mlist.get(position).getOrderStatus().equals("5")) {
                    btn.setVisibility(View.VISIBLE);
                } else {
                    btn.setVisibility(View.GONE);
                }
                if (orderDetailItem.isEvluate()) {
                    btn.setText("已评价");
                    btn.setEnabled(false);
                } else {
                    btn.setText("我要晒单");
                    btn.setEnabled(true);
                }
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JumpUtil.jumpWithValue(context, EvaluateActivity.class, new String[]{"title", "path", "ordernum", "productId"}, new String[]{orderDetailItem.getTitle(), orderDetailItem.getImageSrc(), mlist.get(position).getOrderNumber(), orderDetailItem.getProductId()}, true);
                    }
                });
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

        return convertView;
    }
    private void delete(final int position){
        lodingUtil.showShapeLoding();
        HttpConnectionUtil.getJsonJsonwithDialog(context, path_delete, new String[]{"MemberId", "Ordernumber"}, new String[]{ShareUtil.getInstanse(context).getMemberID(), user.getList().get(position).getOrderNumber()}, lodingUtil, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                lodingUtil.disShapeLoding();
                Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                user.getList().remove(position);
                notifyDataSetChanged();
                callback.adapterChangeBack(position);
            }
        });
    }
    private class Viewholder{
        TextView time,orderstatus,cheap,cheapmoney,totalmoney;
        Button delete,logistic,payagain,showorder,fastpay,besure;
        LinearLayout orderlist;

    }
}
