package fifthutil;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.transtion.my5th.R;

import java.lang.reflect.Field;

/**
 * Created by baicai on 2016/3/23.
 */
public class TopPager implements ViewPager.OnPageChangeListener{


    private ImageView[] tips;

    private ImageView[] mImageViews;

    private Context context;

    private ViewPager viewPager;
    int i;

    ImageUtil imageUtil;
    MyAdapter adapter;
    ImageView[] s;
    int now;
    OnTopPageCall onTopPageCall;
    public interface OnTopPageCall{
        public void CallBack(int now);
    }
    public TopPager(Context context, ViewGroup group,ImageView[] size, final ViewPager viewPager) {
        this.context=context;
        tips = new ImageView[size.length];
        s=size;
        imageUtil=new ImageUtil(context);
//        viewPager.setVisibility(View.GONE);
        for(int i=0; i<tips.length; i++){
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams lay=new LinearLayout.LayoutParams(10,10);
            lay.setMargins(9, 0, 0, 0);
            imageView.setLayoutParams(lay);
            tips[i] = imageView;
            if(i == 0){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            group.addView(imageView);
        }

        mImageViews = new ImageView[size.length];
        for(int i=0; i<mImageViews.length; i++){
            ImageView imageView = new ImageView(context);
            mImageViews[i] = imageView;
//            imageView.setBackgroundResource(imgIdArray[i]);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        adapter=new MyAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem((mImageViews.length) * 100);
        now=(mImageViews.length) * 100;
        ViewPagerScroller sc=new ViewPagerScroller(context);
        sc.initViewPagerScroll(viewPager);
        viewPager.setPageTransformer(true, new AccordionTransformer());
        viewPager.setOnPageChangeListener(this);

        final Handler hand=new Handler();
        hand.postDelayed(new Runnable() {

            @Override
            public void run() {
                now++;
                viewPager.setCurrentItem(now,true);
                hand.postDelayed(this,3000);
            }
        }, 3000);
//        hand.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                viewPager.setVisibility(View.VISIBLE);
//            }
//        }, 2000);

      this.viewPager=viewPager;
    }

   public void setPageOnclick(final OnTopPageCall onTopPageCall){
       this.onTopPageCall=onTopPageCall;
   }


    public void setImg(ImageView[] mImage){
        mImageViews=mImage;
        adapter.notifyDataSetChanged();

    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViews[position % mImageViews.length]);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onTopPageCall!=null)
                        onTopPageCall.CallBack(now% mImageViews.length);
                }
            });
            imageUtil.display4top(imageView,s[position% mImageViews.length].getTag().toString());
            container.addView(imageView, 0);
            return imageView;
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
       if(now!=arg0)
           now=arg0;
    }

    @Override
    public void onPageSelected(int arg0) {
        setImageBackground(arg0 % mImageViews.length);
    }

    /**
     * ����ѡ�е�tip�ı���
     * @param selectItems
     */
    private void setImageBackground(int selectItems){
        for(int i=0; i<tips.length; i++){
            if(i == selectItems){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    public class ViewPagerScroller extends Scroller {
        private int mScrollDuration = 1000;             // 滑动速度

        /**
         * 设置速度速度
         * @param duration
         */
        public void setScrollDuration(int duration){
            this.mScrollDuration = duration;
        }

        public ViewPagerScroller(Context context) {
            super(context);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }


        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mScrollDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mScrollDuration);
        }



        public void initViewPagerScroll(ViewPager viewPager) {
            try {
                Field mScroller = ViewPager.class.getDeclaredField("mScroller");
                mScroller.setAccessible(true);
                mScroller.set(viewPager, this);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
