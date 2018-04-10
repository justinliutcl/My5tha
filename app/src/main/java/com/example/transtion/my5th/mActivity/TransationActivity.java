package com.example.transtion.my5th.mActivity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import fifthutil.JumpUtil;
import fifthutil.TransationShareUtil;

public class TransationActivity extends BasenoscrollActivity {
    ViewPager pager;
    LinearLayout dou;
    CustomPagerAdapter CustomAdapter;
    int position=0;
    public static final int[] image={R.drawable.transation1,R.drawable.transation2,
            R.drawable.transation3};
    TextView welcome_go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transation);
        intoview();
        setListener();
        addDou();
        setAdapter();
        dou.getChildAt(0).setBackgroundResource(R.drawable.page_now);
    }
    public void intoview(){
        dou=(LinearLayout) findViewById(R.id.welcome_doulinear);
        pager=(ViewPager) findViewById(R.id.welcome_pager);
        welcome_go= (TextView) findViewById(R.id.welcome_go);
    }

    public void addDou(){
        for(int i=0;i<image.length;i++){
            ImageView img=new ImageView(TransationActivity.this);
            LinearLayout.LayoutParams ll=new LinearLayout.LayoutParams(15, 15);
            ll.setMargins(10, 0, 0, 0);
            img.setBackgroundResource(R.drawable.page);
            img.setLayoutParams(ll);
            dou.addView(img);
        }
    }
    public void setAdapter(){
        CustomAdapter=new CustomPagerAdapter();
        pager.setAdapter(CustomAdapter);
    }
    @Override
    public void setListener() {
        setPagerListener();
        welcome_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransationShareUtil.getInstanse(TransationActivity.this).setValue(true);
                JumpUtil.jump2finish(TransationActivity.this, MainActivity.class, true);
            }
        });
    }
    public void setPagerListener(){
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                dou.getChildAt(arg0).setBackgroundResource(R.drawable.page_now);
                dou.getChildAt(position).setBackgroundResource(R.drawable.page);
                if(arg0==2){
                    welcome_go.setVisibility(View.VISIBLE);
                }else{
                    welcome_go.setVisibility(View.GONE);
                }
                position = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }


    @Override
    public void onClick(View v) {

    }

    class CustomPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView img=new ImageView(TransationActivity.this);
//            if (position < image.length)

            switch (position){
                case 0:
                    img.setBackgroundResource(R.drawable.transation1);
                    break;
                case 1:
                    img.setBackgroundResource(R.drawable.transation2);
                    break;
                case 2:
                    img.setBackgroundResource(R.drawable.transation3);
                    break;
                default:
                    img.setBackgroundResource(R.drawable.transation3);
                    break;
            }
            container.addView(img);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView img=new ImageView(TransationActivity.this);
            img.setImageResource(image[position]);
            container.removeView(img);
        }

    }
}
