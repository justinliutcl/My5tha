package fragclass;

import android.support.v4.app.Fragment;

/**
 * Created by baicai on 2016/3/16.
 */
public abstract class BaseFragment extends Fragment{
    protected abstract void lazyLoad();
    protected boolean isVisible;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }
    /**
     * 不可见
     */
    protected void onInvisible() {


    }
}
