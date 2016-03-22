package fifthutil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import java.io.InputStream;

public class OptsBitmapUtil {
public static Bitmap calculatorBitmap(String path,ImageView img){
	Options opts=new Options();
	opts.inJustDecodeBounds=true;
	BitmapFactory.decodeFile(path, opts);
	int mapwidth=opts.outWidth;
	LayoutParams para=img.getLayoutParams();
	int imgwidth=para.width;
	int sma=Math.round(mapwidth/imgwidth);
	opts.inJustDecodeBounds=false;
	opts.inSampleSize=sma;
	return BitmapFactory.decodeFile(path, opts);
}
public static Bitmap calculatorStreamBitmap(InputStream path,ImageView img){
	Options opts=new Options();
	opts.inJustDecodeBounds=true;
	BitmapFactory.decodeStream(path,null,opts);
	int mapwidth=opts.outWidth;
	LayoutParams para=img.getLayoutParams();
	int imgwidth=para.width;
	int sma=Math.round(mapwidth/imgwidth);
	opts.inJustDecodeBounds=false;
	opts.inSampleSize=sma;
	return BitmapFactory.decodeStream(path,null,opts);
}
}
