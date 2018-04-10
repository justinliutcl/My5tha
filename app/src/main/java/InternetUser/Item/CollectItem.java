package InternetUser.Item;

import fifthutil.FifUtil;

/**
 * Created by baicai on 2016/3/21.
 */
public class CollectItem {
    private String Src;
    private String Title;
    private String ObjId;
    private double SellPrice;
    private String LongTitle;
    private String Belong;
    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSrc() {
        return Src;
    }

    public void setSrc(String src) {
        Src = src;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getObjId() {
        return ObjId;
    }

    public void setObjId(String objId) {
        ObjId = objId;
    }

    public String getSellPrice() {
        return FifUtil.getPrice(SellPrice);
    }

    public void setSellPrice(double sellPrice) {
        SellPrice = sellPrice;
    }

    public String getBelong() {
        return Belong;
    }

    public void setBelong(String belong) {
        Belong = belong;
    }

    public String getLongTitle() {
        return LongTitle;
    }

    public void setLongTitle(String longTitle) {
        LongTitle = longTitle;
    }

    public CollectItem() {

    }

    public CollectItem(String src, String title, String objId, double sellPrice, String belong, String productId) {

        Src = src;
        Title = title;
        ObjId = objId;
        SellPrice = sellPrice;
        Belong = belong;
        LongTitle = productId;
    }
}
