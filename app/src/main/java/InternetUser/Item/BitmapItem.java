package InternetUser.Item;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by 不爱白菜 on 2016/6/21.
 */
public class BitmapItem {
    ImageView img;
    Bitmap bitmap;

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public BitmapItem() {
    }

    public BitmapItem(ImageView img, Bitmap bitmap) {
        this.img = img;
        this.bitmap = bitmap;
    }
}
