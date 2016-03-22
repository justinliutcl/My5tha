package customUI.Loding;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transtion.my5th.R;

/**
 * 加载中Dialog
 * 
 * @author lexyhp
 */
public class LoadingDialog extends AlertDialog {

	private TextView tips_loading_msg;
	private int layoutResId=0;
	private View view;
	private String message = null;
	private LoadingButton mDefaultLButton;
	private Context context;
	/**
	 * 构造方法
	 * 
	 * @param context
	 *            上下文
	 * @param layoutResId
	 *            要传入的dialog布局文件的id
	 */
	public LoadingDialog(Context context, int layoutResId) {
		super(context);
		this.layoutResId = layoutResId;
		this.context=context;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		this.setContentView(layoutResId);
		mDefaultLButton= (LoadingButton) findViewById(R.id.loadView);

	}
	public void disloadingbutton(final String str, final LoadingDialog dialog){
		mDefaultLButton.setTargetProgress(360);
		mDefaultLButton.setCallback(new LoadingButton.Callback() {
			@Override
			public void complete() {
				dialog.setCanceledOnTouchOutside(true);
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}
				}, 400);
			}
		});
	}
	public LoadingButton getloadingButton(){
		return mDefaultLButton;
	}
}
