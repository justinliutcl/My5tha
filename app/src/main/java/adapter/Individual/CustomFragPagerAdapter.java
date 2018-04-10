package adapter.Individual;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

//import com.viewpagerindicator.IconPagerAdapter;

public class CustomFragPagerAdapter extends FragmentPagerAdapter {
	Fragment frag[];

	public CustomFragPagerAdapter(FragmentManager fm,Fragment frag[]) {
		super(fm);
		this.frag=frag;
	}

	@Override
	public Fragment getItem(int arg0) {
		return frag[arg0];
	}

	@Override
	public int getCount() {
		return frag.length;
	}

//	@Override
//	public int getIconResId(int index) {
//
//		return 0;
//	}

}
