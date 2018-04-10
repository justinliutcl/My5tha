package customUI;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by 不爱白菜 on 2016/4/19.
 */
public class CustomScrollView extends ScrollView {

    OnBorderListener onBorderListener;
    Context context;
    boolean flage=true;

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setonBorderListener(OnBorderListener onBorderListener){
        this.onBorderListener=onBorderListener;
    }
    public static interface OnBorderListener {

        /**
         * Called when scroll to bottom
         */
        public void onBottom();
    }
    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        doOnBorderListener();
    }
    private void doOnBorderListener() {
        View contentView = getChildAt(0);
//        int a=getScrollY() + getHeight();
//        print(contentView.getMeasuredHeight()+"-----"+a);
        if (contentView != null && contentView.getMeasuredHeight()-2000 <= getScrollY() + getHeight()) {

            if (onBorderListener != null) {
                if(flage){
                    onBorderListener.onBottom();
                    flage=false;
                }

            }
        }
    }
    public void print(String str){
        Log.i("fifth", str);
    }
}
