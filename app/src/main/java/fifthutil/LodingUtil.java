package fifthutil;

import android.content.Context;
import android.view.View;

import com.example.transtion.my5th.R;

import customUI.Loding.LoadingDialog;

/**
 * Created by baicai on 2016/3/10.
 */
public class LodingUtil {
//   private ShapeLoadingDialog shapeLoadingDialog;
    private LoadingDialog dialog;

    private View mDialogContentView;
    private static LodingUtil loding;

    public LodingUtil( Context context) {
        this.context = context;
//        shapeLoadingDialog=new ShapeLoadingDialog(context);
//        shapeLoadingDialog.setLoadingText("loading...");

    }
//    public ShapeLoadingDialog getShapeLoadingDialog() {
//        return shapeLoadingDialog;
//    }

//    public void setShapeLoadingDialog(ShapeLoadingDialog shapeLoadingDialog) {
//        this.shapeLoadingDialog = shapeLoadingDialog;
//    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;

    public void showShapeLoding(){
//        shapeLoadingDialog.setCanceledOnTouchOutside(false);
//        shapeLoadingDialog.show();
        dialog = new LoadingDialog(context, R.layout.dialog_loading);
//        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

//        AlertDialog.Builder ab=new AlertDialog.Builder(context, R.style.dialog);
//        mDialogContentView= LayoutInflater.from(context).inflate(R.layout.dialog_loading,null);
//        ab.setView(mDialogContentView);
//        mDialog=ab.create();
////        mDialog.setContentView(mDialogContentView);
//        mDialog.setCanceledOnTouchOutside(false);
//        mDialog.show();

    }
    public void disShapeLoding(){
        if(dialog!=null && dialog.isShowing())
            dialog.dismiss();
    }

    public void showloadingbutton(){
//        AlertDialog.Builder ab=new AlertDialog.Builder(context, R.style.dialog);

//        ab.setView(mDialogContentView);
//        mDialog=ab.create();
//        mDialog.setContentView(mDialogContentView);
        dialog=new LoadingDialog(context,R.layout.dialog_loadingbutton);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getloadingButton().show();
//        mDialog.setCanceledOnTouchOutside(false);
//        mDialog.show();
//        mDefaultLButton.show();
    }
    public void disloadingbutton(final String str){
        dialog.disloadingbutton(str, dialog);
    }
    public void dismiss(){
//        shapeLoadingDialog.dismiss();
        if(dialog!=null && dialog.isShowing())
            dialog.dismiss();
    }

}
