package InternetUser.order;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by baicai on 2016/3/14.
 */
public class OrderDetailItem {
    private String Title;
    private double Price;
    private int Number;
    private List<OrderTatilAttrItem> list;
    private String ImageSrc;
    DecimalFormat df = new DecimalFormat("#0.00");
    public OrderDetailItem(String title, double price, int number, String imageSrc, List<OrderTatilAttrItem> list) {
        Title = title;
        Price = price;
        Number = number;
        ImageSrc = imageSrc;
        this.list = list;
    }


    public String getImageSrc() {
        return ImageSrc;
    }

    public void setImageSrc(String imageSrc) {
        ImageSrc = imageSrc;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPrice() {
        return df.format(Price);
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public List<OrderTatilAttrItem> getList() {
        return list;
    }

    public void setList(List<OrderTatilAttrItem> list) {
        this.list = list;
    }

    public OrderDetailItem() {

    }

}
