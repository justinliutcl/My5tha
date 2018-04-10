package InternetUser.A_Home;

/**
 * Created by baicai on 2016/3/24.
 */
public class QqtmUser {
    private String EventDetailId;
    private boolean IsEventShow;
    private long timeSecond;
    private int ShowHour;
    private String Title;
    private String SellPrice;
    private String ReferenceRrice;
    private String PlaceOfProductImg;
    private String ProductImg;
    private String Count;
    private String Belong;
    private String DetailType;


    public int getShowHour() {
        return ShowHour;
    }

    public void setShowHour(int showHour) {
        ShowHour = showHour;
    }

    public String getDetailType() {
        return DetailType;
    }

    public void setDetailType(String detailType) {
        DetailType = detailType;
    }

    public String getEventDetailId() {
        return EventDetailId;
    }

    public void setEventDetailId(String eventDetailId) {
        EventDetailId = eventDetailId;
    }

    public boolean isEventShow() {
        return IsEventShow;
    }

    public void setIsEventShow(boolean isEventShow) {
        IsEventShow = isEventShow;
    }

    public long getTimeSecond() {
        return timeSecond;
    }

    public void setTimeSecond(long timeSecond) {
        this.timeSecond = timeSecond;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(String sellPrice) {
        SellPrice = sellPrice;
    }

    public String getReferenceRrice() {
        return ReferenceRrice;
    }

    public void setReferenceRrice(String referenceRrice) {
        ReferenceRrice = referenceRrice;
    }

    public String getPlaceOfProductImg() {
        return PlaceOfProductImg;
    }

    public void setPlaceOfProductImg(String placeOfProductImg) {
        PlaceOfProductImg = placeOfProductImg;
    }

    public String getProductImg() {
        return ProductImg;
    }

    public void setProductImg(String productImg) {
        ProductImg = productImg;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getBelong() {
        return Belong;
    }

    public void setBelong(String belong) {
        Belong = belong;
    }

    public String getPlaceOfProduct() {
        return PlaceOfProduct;
    }

    public void setPlaceOfProduct(String placeOfProduct) {
        PlaceOfProduct = placeOfProduct;
    }

    public QqtmUser(String eventDetailId, boolean isEventShow, long timeSecond, String title, String sellPrice, String referenceRrice, String placeOfProductImg, String productImg, String count, String belong, String placeOfProduct) {

        EventDetailId = eventDetailId;
        IsEventShow = isEventShow;
        this.timeSecond = timeSecond;
        Title = title;
        SellPrice = sellPrice;
        ReferenceRrice = referenceRrice;
        PlaceOfProductImg = placeOfProductImg;
        ProductImg = productImg;
        Count = count;
        Belong = belong;
        PlaceOfProduct = placeOfProduct;
    }

    public QqtmUser() {

    }

    private String PlaceOfProduct
            ;

}
