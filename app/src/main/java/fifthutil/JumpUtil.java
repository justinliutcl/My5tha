package fifthutil;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.transtion.my5th.R;

public class JumpUtil {
	public static void jump(Context context,Class clazz,boolean flage){
		Intent intent=new Intent(context,clazz);
		context.startActivity(intent);
		if(flage){
			((Activity)context).overridePendingTransition(R.anim.push_in, R.anim.push_out);
		}else{
			((Activity)context).overridePendingTransition(R.anim.back_in,R.anim.back_out);
		}
		
	}
	public static void jump2finish(Context context,Class clazz,boolean flage){
		Intent intent=new Intent(context,clazz);
		context.startActivity(intent);
		((Activity)context).finish();
		if(flage){
			((Activity)context).overridePendingTransition(R.anim.push_in, R.anim.push_out);
		}else{
			((Activity)context).overridePendingTransition(R.anim.back_in,R.anim.back_out);
		}
	}
	public static void jumpWithValue(Context context,Class clazz,String[]key,String[]value,boolean flage){
		Intent intent=new Intent(context,clazz);
		for(int i=0;i<key.length;i++){
			intent.putExtra(key[i], value[i]);
		}
		context.startActivity(intent);
		if(flage){
			((Activity)context).overridePendingTransition(R.anim.push_in, R.anim.push_out);
		}else{
			((Activity)context).overridePendingTransition(R.anim.back_in,R.anim.back_out);
		}
		
	}
	public static void jumpWithValue2finash(Context context,Class clazz,boolean flage,String[]key,String[]value){
		Intent intent=new Intent(context,clazz);
		for(int i=0;i<key.length;i++){
			intent.putExtra(key[i], value[i]);
		}
		context.startActivity(intent);
		((Activity)context).finish();
		if(flage){
			((Activity)context).overridePendingTransition(R.anim.push_in, R.anim.push_out);
		}else{
			((Activity)context).overridePendingTransition(R.anim.back_in,R.anim.back_out);
		}
		
	}
	public static void jump2finash(Context context){
		((Activity)context).finish();
		((Activity)context).overridePendingTransition(R.anim.back_in, R.anim.back_out);
	}
//	public static void jump2down(Context contextboolean flage){
//
//		if(flage){
//			((Activity)context).overridePendingTransition(R.anim.dowm_in,R.anim.down_out);
//		}else{
//			((Activity)context).overridePendingTransition(R.anim.down_out,R.anim.dowm_in);
//		}
//
//	}
	public static void jump2hdown(Context context,Class clazz,boolean flage){
		((Activity)context).finish();
		Intent intent=new Intent(context,clazz);
		context.startActivity(intent);
		if(flage){
			((Activity)context).overridePendingTransition(R.anim.dowm_in,R.anim.down_out);
		}else{
			((Activity)context).overridePendingTransition(R.anim.up_in,R.anim.up_out);
		}

	}
}
