package customUI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.transtion.my5th.R;

import fifthutil.JumpUtil;

public class TopbarView extends RelativeLayout{
Context context;
float leftsize;
float leftHsize;
Drawable leftback;
String midtext;
float titlesize;
ImageView left;
TextView title;
	public TopbarView(Context context) {
		super(context);
	}


	public TopbarView(final Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.TopbarView);
		
		leftsize=ta.getDimension(R.styleable.TopbarView_left_size,0);
		leftHsize=ta.getDimension(R.styleable.TopbarView_left_hsize, 0);
		leftback=ta.getDrawable(R.styleable.TopbarView_left_back);
		midtext=ta.getString(R.styleable.TopbarView_medtitle);
		titlesize=ta.getDimension(R.styleable.TopbarView_title_size, 0);
		ta.recycle();
		
		left=new ImageView(context);
		left.setImageResource(R.drawable.back3x);
		left.setPadding(0,0,(int)leftsize,0);
		LayoutParams leftparams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,(int)leftHsize);
		leftparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
		leftparams.addRule(RelativeLayout.CENTER_VERTICAL);
		leftparams.setMargins(0, 0, 0, 0);
		addView(left, leftparams);
		
		title=new TextView(context);
		title.setTextSize(TypedValue.COMPLEX_UNIT_PX,titlesize);
		title.setText(midtext);
		title.setTextColor(0xff444444);
		LayoutParams centitle=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		centitle.addRule(RelativeLayout.CENTER_IN_PARENT);
		addView(title,centitle);
		left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				JumpUtil.jump2finash(context);
			}
		});
		
	}
	public void setleftListener(final Context context){
		left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				JumpUtil.jump2finish(context, MainActivity.class,false);
				
			}
		});
	}
	public ImageView getleftbar(){
		return left;
	}
	public void setTitle(String text){
		title.setText(text);
	}
}
