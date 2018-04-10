package adapter.Individual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transtion.my5th.AHomeActivity.TextActivity;
import com.example.transtion.my5th.R;

import InternetUser.CollectUser;
import fifthutil.ImageUtil;
import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/4.
 */
public abstract class CollectuseAdapter extends BaseAdapter{
    CollectUser user;
    Context context;
    LodingUtil lodingUtil;
    String path= Path.HOST+Path.ip+Path.COLLECT_DELETE_PATH;
    ImageUtil imageUtil;
    public CollectuseAdapter(CollectUser user, Context context, LodingUtil lodingUtil) {
        this.user = user;
        this.context = context;
        this.lodingUtil=lodingUtil;
        imageUtil=new ImageUtil(context);
    }
    protected abstract void ondeleteClickItem(View v, int position);
    public void setUser(CollectUser users){
        user=users;
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
        if(convertView==null){
            convertView=View.inflate(context, R.layout.adapter_collect, null);
            holder=new Viewholder();
            holder.title =(TextView) convertView.findViewById(R.id.adapter_collect_title);
            holder.longtitle =(TextView) convertView.findViewById(R.id.adapter_collect_longtitle);
            holder.money =(TextView) convertView.findViewById(R.id.adapter_collect_money);
            holder.img= (ImageView) convertView.findViewById(R.id.adapter_horgrid_img);
            holder.delete= (ImageView) convertView.findViewById(R.id.adapter_collect_delete);
            convertView.setTag(holder);
        }else{
            holder=(Viewholder) convertView.getTag();
        }

        holder.title.setText(user.getList().get(position).getTitle());
        holder.longtitle.setText(user.getList().get(position).getLongTitle());
        holder.money.setText("￥"+user.getList().get(position).getSellPrice());

//        Log.i("fifth", user.getList().get(position).getSrc());
//        BitmapUtil.setImage(holder.img);
        imageUtil.display(holder.img,user.getList().get(position).getSrc());
        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                lodingUtil.showShapeLoding();
                HttpConnectionUtil.getJsonJsonwithDialog(context, path, new String[]{"MemberId", "ObjId"}, new String[]{ShareUtil.getInstanse(context).getMemberID(), user.getList().get(position).getObjId()}, lodingUtil, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        lodingUtil.disShapeLoding();
                        user.getList().remove(position);
                        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                        ondeleteClickItem(v,position);
                    }
                });
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextActivity.launch(context, holder.img, user.getList().get(position).getSrc(), user.getList().get(position).getObjId(), user.getList().get(position).getBelong(),"productId");
            }
        });

        return convertView;
    }
    private class Viewholder{
        TextView title, longtitle, money;
        ImageView img,delete;

    }
}
