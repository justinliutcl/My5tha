package adapter.Individual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transtion.my5th.DIndividualActivity.Addresssave;
import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.AddressItem;
import fifthutil.JumpUtil;
import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/20.
 */
public class AddressAdapter extends BaseAdapter {
    List<AddressItem> list;
    Context context;
    LodingUtil lodingUtil;
    String deletePath= Path.HOST+Path.ip+Path.ADDRESSDELET_PATH;
    String setdefaultPath= Path.HOST+Path.ip+Path.ADDRESS_SETDEFAULT_PATH;
    OnadapterChangeCall callback;
    public interface OnadapterChangeCall{
        public void adapterChangeBack(int position);
    }
    public AddressAdapter(List<AddressItem> list, Context context, LodingUtil lodingUtil,OnadapterChangeCall callback) {
        this.list = list;
        this.context = context;
        this.lodingUtil=lodingUtil;
        this.callback=callback;
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
            convertView=View.inflate(context, R.layout.adapter_addressmanger, null);
            holder=new Viewholder();
            holder.address =(TextView) convertView.findViewById(R.id.adapter_addressmanger_address);
            holder.name =(TextView) convertView.findViewById(R.id.adapter_addressmanger_name);
            holder.phone =(TextView) convertView.findViewById(R.id.adapter_addressmanger_phone);
            holder.defaultaddress= (LinearLayout) convertView.findViewById(R.id.adapter_addressmanger_layout_defaultaddress);
            holder.edit= (LinearLayout) convertView.findViewById(R.id.adapter_addressmanger_layout_edit);
            holder.delete= (LinearLayout) convertView.findViewById(R.id.adapter_addressmanger_layout_delete);
            holder.imgdefault= (ImageView) convertView.findViewById(R.id.adapter_addressmanger_img_imgdefault);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getProvince()+list.get(position).getCity()+list.get(position).getArea()+list.get(position).getAddress());
        holder.phone.setText(list.get(position).getPhone());
        if(list.get(position).isDefault())
            holder.imgdefault.setImageResource(R.drawable.bg_radio_on3x);
        else
            holder.imgdefault.setImageResource(R.drawable.bg_radio3x);
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAddress(position);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delet(position);
            }
        });
        holder.defaultaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setdefault(position, holder.imgdefault);
            }
        });
        return convertView;
    }

    private void editAddress(int position){
        AddressItem item=list.get(position);
        JumpUtil.jumpWithValue(context, Addresssave.class,new String[]{"name","phone","zipcode","IdCard","Address","Province","City","Area","AreaCode","Id","ProvinceCode","CityCode","AreaCode"},
                new String[]{item.getName(),item.getPhone(),item.getZipcode(),item.getIdCard(),item.getAddress(),item.getProvince(),item.getCity(),item.getArea(),item.getAreaCode(),item.getId(),item.getProvinceCode(),item.getCityCode(),item.getAreaCode()},true);

    }

    private void setdefault(final int position, final ImageView img) {
        lodingUtil.showShapeLoding();
        HttpConnectionUtil.getJsonJsonwithDialog(context, setdefaultPath, new String[]{"memberId","Id"}, new String[]{ShareUtil.getInstanse(context).getMemberID(),list.get(position).getId()}, lodingUtil, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                lodingUtil.disShapeLoding();
               for(int i=0;i<list.size();i++){
                   list.get(i).setIsDefault(false);
                   if(i==position)
                       list.get(i).setIsDefault(true);
               }
//                img.setImageResource(R.drawable.bg_radio_on3x);
                callback.adapterChangeBack(position);
                Toast.makeText(context,"设置成功",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void delet(final int num) {
        lodingUtil.showShapeLoding();
        HttpConnectionUtil.getGetJson(context, deletePath + "?memberId=" + ShareUtil.getInstanse(context).getMemberID() + "&Id=" + list.get(num).getId(), lodingUtil, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                lodingUtil.disShapeLoding();
                list.remove(num);
                callback.adapterChangeBack(num);
            }
        });
    }

    private class Viewholder{
        TextView name, phone, address;
        LinearLayout defaultaddress,edit,delete;
        ImageView imgdefault;
    }
}
