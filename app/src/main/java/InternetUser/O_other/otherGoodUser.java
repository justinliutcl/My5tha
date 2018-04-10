package InternetUser.O_other;

/**
 * Created by baicai on 2016/3/27.
 */
public class OtherGoodUser {
    private String Title;
    private String StandardId;
    private String DetailType;
    private String Price;
    private String MarketPrice;
    private String Area;
    private String State;
    private String Flag;
    private String ImageUrl;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStandardId() {
        return StandardId;
    }

    public void setStandardId(String standardId) {
        StandardId = standardId;
    }

    public String getDetailType() {
        return DetailType;
    }

    public void setDetailType(String detailType) {
        DetailType = detailType;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getMarketPrice() {
        return MarketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        MarketPrice = marketPrice;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public OtherGoodUser() {

    }

    public OtherGoodUser(String title, String standardId, String detailType, String price, String marketPrice, String area, String state, String flag) {

        Title = title;
        StandardId = standardId;
        DetailType = detailType;
        Price = price;
        MarketPrice = marketPrice;
        Area = area;
        State = state;
        Flag = flag;
    }
}
