package adapter.shopcar_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.AHomeActivity.TextActivity;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.shopcar.ShopcarlistItem;
import fifthutil.FifUtil;
import fifthutil.ImageUtil;
import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public abstract class ShopcarAdapter extends BaseAdapter{
    List<ShopcarlistItem> list;
    Context context;
    LodingUtil lodingUtil;
    ImageUtil imageUtil;
    private String removepath= Path.HOST+Path.ip+Path.SHOPCAR_REMOVEGOODS_PATH;
    private String changepath= Path.HOST+Path.ip+Path.SHOPCAR_NUMCHANGE_PATH;
    public List<ShopcarlistItem> getList() {
        return list;
    }

    public void setList(List<ShopcarlistItem> list) {
        this.list = list;
    }

    public ShopcarAdapter(List<ShopcarlistItem> list, Context context) {
        this.list = list;
        this.context = context;
        lodingUtil=new LodingUtil(context);
        imageUtil=new ImageUtil(context);
    }
    protected abstract void ondeleteClickItem(View v, int position);
    protected abstract void onaddClickItem(View v, int position);
    protected abstract void onjianClickItem(View v, int position);
    protected abstract void onselectClickItem(View v, int position);
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
            convertView=View.inflate(context, R.layout.adapter_shopcar, null);
            holder=new Viewholder();
            holder.name=(TextView) convertView.findViewById(R.id.adaptershopcar_title);
            holder.mes=(TextView) convertView.findViewById(R.id.adaptershopcar_mes);
            holder.money=(TextView) convertView.findViewById(R.id.adaptershopcar_price);
            holder.sum=(TextView) convertView.findViewById(R.id.adaptershopcar_num);
            holder.select= (ImageView) convertView.findViewById(R.id.adaptershopcar_select);
            holder.delete= (ImageView) convertView.findViewById(R.id.adaptershopcar_delete);
            holder.jian= (ImageView) convertView.findViewById(R.id.adaptershopcar_jian);
            holder.jia= (ImageView) convertView.findViewById(R.id.adaptershopcar_jia);
            holder.img= (ImageView) convertView.findViewById(R.id.adaptershopcar_img);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        double price=list.get(position).getSellPrice();
        final int num=list.get(position).getNumber();

        final int totalnum;
        final String objId;
        if(list.get(position).getEventDetailId()==null){
            totalnum=list.get(position).getStorage();
            objId=list.get(position).getStandardId();
        }else{
            if(list.get(position).getEventDetailId().length()<2){
                totalnum=list.get(position).getStorage();
                objId=list.get(position).getStandardId();
            }else{
                totalnum=list.get(position).getLimitNum();
                objId=list.get(position).getEventDetailId();
            }
        }
        holder.name.setText(list.get(position).getTitle());
        holder.mes.setText(list.get(position).getStandardDesc());
        double p=price*num;
        holder.money.setText("￥"+FifUtil.getPrice(p));
        holder.sum.setText(num+"");
        if(list.get(position).isFlage()){
            holder.select.setBackgroundResource(R.drawable.bg_radio_on3x);
        }else{
            holder.select.setBackgroundResource(R.drawable.bg_radio3x);
        }
        if(num<2){
            holder.jian.setBackgroundResource(R.drawable.jianfang);
            holder.jian.setEnabled(false);
        }else{
            holder.jian.setBackgroundResource(R.drawable.jianfang_no);
            holder.jian.setEnabled(true);
        }

        if(num>=totalnum){
            holder.jia.setBackgroundResource(R.drawable.jiafang);
            holder.jia.setEnabled(false);
        }else{
            holder.jia.setBackgroundResource(R.drawable.jiafang_no);
            holder.jia.setEnabled(true);
        }
        holder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                lodingUtil.showShapeLoding();
                HttpConnectionUtil.getJsonJsonwithDialog(context, changepath, new String[]{"memberId", "Id","Number"}, new String[]{ShareUtil.getInstanse(context).getMemberID(), list.get(position).getId(),list.get(position).getNumber()-1+""}, lodingUtil, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        lodingUtil.dismiss();
                        list.get(position).setNumber(list.get(position).getNumber() - 1);
                        double p=list.get(position).getNumber()*list.get(position).getSellPrice();
                        holder.money.setText("￥"+ FifUtil.getPrice(p));
                        holder.sum.setText(list.get(position).getNumber()+"");
                        if(list.get(position).getNumber()<2){
                            holder.jian.setBackgroundResource(R.drawable.jianfang);
                            holder.jian.setEnabled(false);
                            holder.jia.setBackgroundResource(R.drawable.jiafang_no);
                            holder.jia.setEnabled(true);
                        }else{
                            holder.jian.setBackgroundResource(R.drawable.jianfang_no);
                            holder.jian.setEnabled(true);
                            holder.jia.setBackgroundResource(R.drawable.jiafang_no);
                            holder.jia.setEnabled(true);
                        }
                        notifyDataSetChanged();
                        onjianClickItem(v, position);
                    }
                });

            }
        });
        holder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                lodingUtil.showShapeLoding();
                int n=list.get(position).getNumber()+1;
                String a=n+"";
                HttpConnectionUtil.getJsonJsonwithDialog(context, changepath, new String[]{"memberId", "Id","Number"}, new String[]{ShareUtil.getInstanse(context).getMemberID(),list.get(position).getId(),a }, lodingUtil, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        lodingUtil.disShapeLoding();
                        list.get(position).setNumber(list.get(position).getNumber() + 1);
                        double p=list.get(position).getNumber()*list.get(position).getSellPrice();
                        holder.money.setText("￥" + FifUtil.getPrice(p));
                        holder.sum.setText(list.get(position).getNumber() + "");
                        if(list.get(position).getNumber()>=totalnum){
                            holder.jia.setBackgroundResource(R.drawable.jiafang);
                            holder.jia.setEnabled(false);
                            holder.jian.setBackgroundResource(R.drawable.jianfang_no);
                            holder.jian.setEnabled(true);
                        }else{
                            holder.jia.setBackgroundResource(R.drawable.jiafang_no);
                            holder.jia.setEnabled(true);
                            holder.jian.setBackgroundResource(R.drawable.jianfang_no);
                            holder.jian.setEnabled(true);
                        }
                        notifyDataSetChanged();
                        onaddClickItem(v, position);
                    }
                });
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                lodingUtil.showShapeLoding();
                HttpConnectionUtil.getJsonJsonwithDialog(context, removepath, new String[]{"memberId", "Id"}, new String[]{ShareUtil.getInstanse(context).getMemberID(), list.get(position).getId()}, lodingUtil, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        lodingUtil.disShapeLoding();
                        list.remove(position);
                        notifyDataSetChanged();
                        ondeleteClickItem(v, position);
                    }
                });
            }
        });
        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).isFlage()) {
                    list.get(position).setFlage(false);
                    holder.select.setBackgroundResource(R.drawable.bg_radio3x);

                } else {
                    list.get(position).setFlage(true);
                    holder.select.setBackgroundResource(R.drawable.bg_radio_on3x);
                }
                onselectClickItem(v, position);
            }
        });
        imageUtil.displaywithoutanim(holder.img, list.get(position).getImageSrc());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextActivity.launch(context, holder.img, list.get(position).getImageSrc(),objId, list.get(position).getStandardType(),"objectId");
            }
        });
        return convertView;
    }
    private class Viewholder{
        TextView name,mes,money,sum;
        ImageView select,delete,jian,jia,img;
    }
}
