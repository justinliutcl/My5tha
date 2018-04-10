package InternetUser.order;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by baicai on 2016/3/14.
 */
public class OrderDetailItem {
    private String Title;
    private double Price;
    private String BelongType;
    private int Number;
    private boolean IsEvluate;
    private List<OrderTatilAttrItem> OrderTatilAttributeList;
    private String ImageSrc;
    private String productId;
    private String StrandardDescription;
//    private boolean Display;
    private boolean IsUseElectronic;
    DecimalFormat df = new DecimalFormat("#0.00");

    public boolean isUseElectronic() {
        return IsUseElectronic;
    }

    public void setIsUseElectronic(boolean isUseElectronic) {
        IsUseElectronic = isUseElectronic;
    }



    public OrderDetailItem() {
    }

    public String getTitle() {

        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getBelongType() {
        return BelongType;
    }

    public void setBelongType(String belongType) {
        BelongType = belongType;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public boolean isEvluate() {
        return IsEvluate;
    }

    public void setIsEvluate(boolean isEvluate) {
        IsEvluate = isEvluate;
    }

    public List<OrderTatilAttrItem> getOrderTatilAttributeList() {
        return OrderTatilAttributeList;
    }

    public void setOrderTatilAttributeList(List<OrderTatilAttrItem> orderTatilAttributeList) {
        OrderTatilAttributeList = orderTatilAttributeList;
    }

    public String getImageSrc() {
        return ImageSrc;
    }

    public void setImageSrc(String imageSrc) {
        ImageSrc = imageSrc;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStrandardDescription() {
        return StrandardDescription;
    }

    public void setStrandardDescription(String strandardDescription) {
        StrandardDescription = strandardDescription;
    }

    public OrderDetailItem(String title, double price, String belongType, int number, boolean isEvluate, List<OrderTatilAttrItem> orderTatilAttributeList, String imageSrc, String productId, String strandardDescription) {
        Title = title;
        Price = price;
        BelongType = belongType;
        Number = number;
        IsEvluate = isEvluate;
        OrderTatilAttributeList = orderTatilAttributeList;
        ImageSrc = imageSrc;
        this.productId = productId;
        StrandardDescription = strandardDescription;
    }
}
