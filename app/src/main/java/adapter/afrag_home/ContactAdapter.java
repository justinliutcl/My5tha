package adapter.afrag_home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import java.util.ArrayList;

import fifthutil.ContactInfo;

public class ContactAdapter extends BaseAdapter implements SectionIndexer {

	private LayoutInflater mInflater;
	ArrayList<ContactInfo> itemList;

	public ContactAdapter(Context context,ArrayList<ContactInfo> itemList) {
		mInflater = LayoutInflater.from(context);
		this.itemList = itemList;
	}

	public ArrayList<ContactInfo> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<ContactInfo> itemList) {
		this.itemList = itemList;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return itemList.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;

		convertView = mInflater.inflate(R.layout.contact_list, null);
		holder = new ViewHolder();
		holder.mname = (TextView) convertView.findViewById(R.id.mname);
		holder.msisdn = (TextView) convertView.findViewById(R.id.msisdn);
		holder.check = (CheckBox) convertView.findViewById(R.id.check);
		holder.tvTitle = (TextView) convertView.findViewById(R.id.catalog);
		convertView.setTag(holder);
		holder.mname.setText(itemList.get(position).getContactName());
		holder.msisdn.setText("手机号: " + itemList.get(position).getUserNumber());
		holder.check.setChecked(itemList.get(position).getIsChecked());
		//根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);
		//如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if (position == getPositionForSection(section)) {
			holder.tvTitle.setVisibility(View.VISIBLE);
			holder.tvTitle.setText(itemList.get(position).getSortLetters());
		}else {
			holder.tvTitle.setVisibility(View.GONE);
		}


		return convertView;
	}

	@Override
	public Object[] getSections() {
		return null;
	}

	@Override
	public int getPositionForSection(int sectionIndex) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = itemList.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == sectionIndex) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public int getSectionForPosition(int position) {
		return itemList.get(position).getSortLetters().charAt(0);
	}

	class ViewHolder {
		TextView mname;
		TextView msisdn;
		CheckBox check;
		TextView tvTitle;
	}

	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
		// 正则表达式，判断首字母是否是英文字母
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}
}