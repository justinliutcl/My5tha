package adapter.afrag_home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.List;

import InternetUser.A_Home.HostTitle;
import fifthutil.ImageUtil;
import fifthutil.OptsBitmapUtil;
import fifthutil.cache.BitmapUtil;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by 不爱白菜 on 2016/4/4.
 */
public class SelectleftItemAdapter extends BaseAdapter{
    Context context;
    List<HostTitle> list;
    ImageUtil imageUtil;
    public SelectleftItemAdapter(Context context, List<HostTitle> list) {
        this.context = context;
        this.list = list;
        imageUtil=new ImageUtil(context);
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
            convertView=View.inflate(context, R.layout.adapter_select_leftitem, null);
            holder=new Viewholder();
            holder.title= (TextView) convertView.findViewById(R.id.adapter_select_leftitem_text);
            holder.img= (ImageView) convertView.findViewById(R.id.adapter_select_leftitem_img);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }
        if(position==0){
            convertView.setBackgroundColor(0xffffffff);
            holder.title.setTextColor(0xffffc000);
//          imageUtil.displaywithoutanim(holder.img,list.get(position).getImageLight());
            String path= ShareUtil.getInstanse(context).getSelectItem(list.get(position).getImageLight());
            if(path.length()>1)
                holder.img.setImageBitmap(OptsBitmapUtil.calculatorBitmap(path, holder.img));
            else
                getimg(holder.img,list.get(position).getImageLight(),list.get(position).getTypeName()+"light");
        }else{
//            imageUtil.displaywithoutanim(holder.img,list.get(position).getImageGray());
            String path2=ShareUtil.getInstanse(context).getSelectItem(list.get(position).getImageGray());
            if(path2.length()>1)
                holder.img.setImageBitmap(OptsBitmapUtil.calculatorBitmap(path2, holder.img));
            else
                getimg(holder.img,list.get(position).getImageGray(),list.get(position).getTypeName()+"gray");
        }
        holder.title.setText(list.get(position).getTypeName());

        return convertView;
    }
    private void getimg(ImageView userImg,String url,String name){
        BitmapUtil bitmapUtil=new BitmapUtil(context);
        userImg.setTag(url);
        bitmapUtil.setSelectImg(userImg, name);
    }
    private class Viewholder{
       TextView title;
        ImageView img;
    }
}
