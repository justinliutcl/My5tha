package fifthutil;

import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by baicai on 2016/3/4.
 */
public class UpProgress {
    private ProgressBar bar;
    private int total;
    private int have;
    private int speed=2000;
    private int i=0;
    private TextView text;
    private Handler hand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                i+=(int)(speed/1000);
                bar.setProgress(i);
                text.setText(i+"");
                if(i<have){
                    hand.sendEmptyMessageDelayed(1,1);

                }else{
                    bar.setProgress(have);
                    text.setText(have+"");
                }
            }
        }
    };
    public UpProgress(ProgressBar bar, int total, int have,TextView text) {
        this.bar = bar;
        this.total = total;
        this.have = have;
        this.text = text;
    }
    public void go(){
        bar.setMax(total);
        if(have!=0)
            hand.sendEmptyMessageDelayed(1,1);
        else
            text.setText(0+"");
    }
}
