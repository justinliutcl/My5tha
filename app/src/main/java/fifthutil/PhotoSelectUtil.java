package fifthutil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.transtion.my5th.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import InternetUser.AllHost;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import httpConnection.UploadUtil;
import sharedPreferencesUtil.ShareUtil;

public class PhotoSelectUtil implements UploadUtil.OnUploadProcessListener{
	String selectitem[]={"打开相机","打开图库","取消"};
	AlertDialog ad;
	private static final int SELECT_BY_PHOTO=1;
	private static final int SELECT_BY_STORE=2;
	private static final int SELECT_BY_caijian=3;
	private static final String INTENT_key="photo_path";
	LodingUtil loading;
	Uri photoUri;
	String photopath;
	Context context;
	ImageView img;
	Fragment frag;
	ShareUtil share;
	Bitmap bmap;
	public PhotoSelectUtil(Context context,Fragment frag){
		this.context=context;
		this.frag=frag;
		setDialog();
	}
	public void setimg(ImageView imag){
		img=imag;
	}
	public void showDialog(){
		ad.show();
	}
	
	public void dismisDialog(){
		ad.dismiss();
	}
	
	public void forresult(int requestCode, int resultCode, Intent data){
		if(resultCode==Activity.RESULT_OK){
			if(requestCode==SELECT_BY_caijian){
				go2caijian(data);
			}else{
				doinPhoto(requestCode,data);
			}
		}
	}
	
	private void setDialog() {
		AlertDialog.Builder ab=new AlertDialog.Builder(context, R.style.dialog);
		ab.setItems(selectitem, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					openPhoto();
					break;
				case 1:
					openImageStore();
					break;
				case 2:
					break;
				default:
					break;
				}
			}
		});

		ad=ab.create();
		ad.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		ad.getWindow().setWindowAnimations(R.style.dialog_updown_anim);
		ad.getWindow().setGravity(Gravity.BOTTOM);
	}
	public void openPhoto(){
		String sdPath=Environment.getExternalStorageState();
		if(sdPath.equals(Environment.MEDIA_MOUNTED)){
			Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			photoUri=Uri.fromFile(getUribyPhoto());
			photopath=photoUri.getPath();
			intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
			frag.startActivityForResult(intent, SELECT_BY_PHOTO);
			
		}else{
			Toast.makeText(context, "请检查sd卡", Toast.LENGTH_SHORT).show();
		}
		 
	}
	//得到拍照的地址
	public File getUribyPhoto(){
		File root=new File(Environment.getExternalStorageDirectory(), "csd");
		if(!root.exists())
			root.mkdirs();
		String filename=new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date())+".jpg";
		File uri=new File(root, filename);
		return uri;
	}

	public void openImageStore(){
		Intent intent=new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		frag.startActivityForResult(intent, SELECT_BY_STORE);
	}
	
	private void go2caijian(Intent data) {
        // 拿到剪切数据  
        bmap = data.getParcelableExtra("data");
        // 显示剪切的图像  

         
        share=ShareUtil.getInstanse(context);
        // 图像保存到文件中  
        FileOutputStream foutput = null;
        ByteArrayOutputStream byteout=new ByteArrayOutputStream();
        try {
            foutput = new FileOutputStream(new File(photopath));
            bmap.compress(Bitmap.CompressFormat.PNG, 100, foutput);
            bmap.compress(Bitmap.CompressFormat.PNG, 100, byteout);
//            byte[] bytes=byteout.toByteArray();
//
//            byte[] encode=Base64.encode(bytes, Base64.DEFAULT);
//            String encodeString = new String(encode);
//            String phone=share.getName();
			UploadUtil uploadUtil=UploadUtil.getInstance();
			uploadUtil.setOnUploadProcessListener(this);
			String fileKey = "pic";
			Map<String, String> params = new HashMap<String, String>();
			String memberId=share.getMemberID();
			if(memberId.length()>3){
				params.put("memberId", memberId);
			}
			else {
				params.put("memberId", "100000002003");
			}
			loading=new LodingUtil(context);
			loading.showloadingbutton();
			uploadUtil.uploadFile(photopath, fileKey, Path.HOST+Path.ip+Path.PHOTOUP_PATH, params,loading);

//            HttpconnectUtil.getPostJson(context, Path.TEACHER_UPIMG_PATH, new String[]{"phone","img","type"}, new String[]{phone,encodeString,type}, new OnJsonCall() {
//
//				@Override
//				public void JsonCallBack(String str) {
//					Userlogin user=HttpconnectUtil.getLoginMes(str);
//					if(user.getStatus().equals("1")){
//						Toast.makeText(context, "上传成功", 0).show();
//						SharedPreferencesUtil.getInstances(context).setImg(photopath);
//					}else{
//						Toast.makeText(context, user.getMsg(), 0).show();
//					}
//				}
//			});


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            if(null != foutput){
                try {
                    foutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	
}
	
	
	private void doincaijian(String path) {
		Intent intent = new Intent();  
	    
	    intent.setAction("com.android.camera.action.CROP");  
	    intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");// mUri是已经选择的图片Uri  
	    intent.putExtra("crop", "true");  
	    intent.putExtra("aspectX", 1);// 裁剪框比例  
	    intent.putExtra("aspectY", 1);  
	    intent.putExtra("outputX", 150);// 输出图片大小  
	    intent.putExtra("outputY", 150);  
	    intent.putExtra("return-data", true);  
	      
	    frag.startActivityForResult(intent, SELECT_BY_caijian); 
	}
	
	private void doinPhoto(int requestCode,Intent data) {
		if(requestCode==SELECT_BY_STORE){
			if(data==null){
				Toast.makeText(context, "请选择正确的图片", Toast.LENGTH_SHORT).show();
			}
			photoUri=data.getData();
			if(photoUri==null){
				Toast.makeText(context, "路径出错", Toast.LENGTH_SHORT).show();
			}
			ContentResolver cr=context.getContentResolver();
			Cursor cursor=cr.query(photoUri, null, null, null, null);
			cursor.moveToFirst();
			photopath=cursor.getString(1);
		}
		doincaijian(photopath);
	}

	@Override
	public void onUploadDone(int responseCode, String message) {
		AllHost host= HttpConnectionUtil.getAllHost(message);
		if(host.isSuccess()){
						loading.disloadingbutton("上传成功");
					img.setImageBitmap(bmap);
					ShareUtil.getInstanse(context).setImg(photopath);
					}else{
						loading.dismiss();
						Toast.makeText(context, host.getMessage(), Toast.LENGTH_SHORT).show();
					}
	}

	@Override
	public void onUploadProcess(int uploadSize) {

	}

	@Override
	public void initUpload(int fileSize) {

	}
}
