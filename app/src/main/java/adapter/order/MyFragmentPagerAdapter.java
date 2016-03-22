package adapter.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	Fragment frag[];
	
	private String[] titles;
	
	public MyFragmentPagerAdapter(FragmentManager fm,Fragment frag[], String[] titles) {
		
		super(fm);
	
		this.frag = frag;
		this.titles=titles;
	}
	
 
	@Override
	public CharSequence getPageTitle(int position) {
	 
		return   this.titles[position];
	}


	@Override
	public int getCount() {
		return frag.length;
	}
	
	@Override
	public Fragment getItem(int arg0) {
		return frag[arg0];
	}
	
	
	
	
}
