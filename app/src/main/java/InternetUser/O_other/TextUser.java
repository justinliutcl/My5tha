package InternetUser.O_other;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/3/28.
 */
public class TextUser {
    private String MarketPrice;
    private String SellPrice;
    private String StockNumber;
    private String LimitNumber;
    private String ProductName;
    private String State;
    private String Flag;
    private String EventNumber;
    private String ProductId;
    private String StandardId;
    private boolean IsCollection;
    private String ShippingAddr;
    private long timeSecond;
    private String Brand;
    private String Area;
    private String DetailType;
    private String BlongTo;
    private String DetailId;
    private int CartNumber;
    private String BrandId;
    private boolean StandardStatus;

    public boolean isStandardStatus() {
        return StandardStatus;
    }

    public void setStandardStatus(boolean standardStatus) {
        StandardStatus = standardStatus;
    }

    public String getBrandId() {
        return BrandId;
    }

    public void setBrandId(String brandId) {
        BrandId = brandId;
    }

    public int getCartNumber() {
        return CartNumber;
    }

    public void setCartNumber(int cartNumber) {
        CartNumber = cartNumber;
    }

    public String getBlongTo() {
        return BlongTo;
    }

    public String getDetailId() {
        return DetailId;
    }

    public void setDetailId(String detailId) {
        DetailId = detailId;
    }

    public void setBlongTo(String blongTo) {
        BlongTo = blongTo;
    }

    private boolean IsEventProduct;

    public boolean isEventProduct() {
        return IsEventProduct;
    }

    public void setIsEventProduct(boolean isEventProduct) {
        IsEventProduct = isEventProduct;
    }

    public String getDetailType() {
        return DetailType;
    }

    public void setDetailType(String detailType) {
        DetailType = detailType;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public long getTimeSecond() {
        return timeSecond;
    }

    public void setTimeSecond(long timeSecond) {
        this.timeSecond = timeSecond;
    }

    private List<DetailItem>Detail;
    private List<EvaluateItem>EvaluateList;
    private List<DetailItem>standardImagesList;
    private List<DetailTatilAttributeItem> DetailTatilAttributeList;
    private List<PropertyValueItem>PropertyValueList;
    private List<StandardDetailsUser>StandardDetails;

    public List<StandardDetailsUser> getStandardDetails() {
        return StandardDetails;
    }

    public void setStandardDetails(List<StandardDetailsUser> standardDetails) {
        StandardDetails = standardDetails;
    }

    public String getMarketPrice() {
        return MarketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        MarketPrice = marketPrice;
    }

    public String getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(String sellPrice) {
        SellPrice = sellPrice;
    }

    public String getStockNumber() {
        return StockNumber;
    }

    public void setStockNumber(String stockNumber) {
        StockNumber = stockNumber;
    }

    public String getLimitNumber() {
        return LimitNumber;
    }

    public void setLimitNumber(String limitNumber) {
        LimitNumber = limitNumber;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
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

    public String getEventNumber() {
        return EventNumber;
    }

    public void setEventNumber(String eventNumber) {
        EventNumber = eventNumber;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getStandardId() {
        return StandardId;
    }

    public void setStandardId(String standardId) {
        StandardId = standardId;
    }

    public boolean IsCollection() {
        return IsCollection;
    }

    public void setIsCollection(boolean isCollection) {
        IsCollection = isCollection;
    }

    public String getShippingAddr() {
        return ShippingAddr;
    }

    public void setShippingAddr(String shippingAddr) {
        ShippingAddr = shippingAddr;
    }

    public List<DetailItem> getDetail() {
        return Detail;
    }

    public void setDetail(List<DetailItem> detail) {
        Detail = detail;
    }

    public List<EvaluateItem> getEvaluateList() {
        return EvaluateList;
    }

    public void setEvaluateList(List<EvaluateItem> evaluateList) {
        EvaluateList = evaluateList;
    }

    public List<DetailItem> getStandardImagesList() {
        return standardImagesList;
    }

    public void setStandardImagesList(List<DetailItem> standardImagesList) {
        this.standardImagesList = standardImagesList;
    }

    public List<DetailTatilAttributeItem> getDetailTatilAttributeList() {
        return DetailTatilAttributeList;
    }

    public void setDetailTatilAttributeList(List<DetailTatilAttributeItem> detailTatilAttributeList) {
        DetailTatilAttributeList = detailTatilAttributeList;
    }

    public List<PropertyValueItem> getPropertyValueList() {
        return PropertyValueList;
    }

    public void setPropertyValueList(List<PropertyValueItem> propertyValueList) {
        PropertyValueList = propertyValueList;
    }

    public TextUser() {

    }

    public TextUser(String marketPrice, String sellPrice, String stockNumber, String limitNumber, String productName, String state, String flag, String eventNumber, String productId, String standardId, boolean isCollection, String shippingAddr, List<DetailItem> detail, List<EvaluateItem> evaluateList, List<DetailItem> standardImagesList, List<DetailTatilAttributeItem> detailTatilAttributeList, List propertyValueList) {

        MarketPrice = marketPrice;
        SellPrice = sellPrice;
        StockNumber = stockNumber;
        LimitNumber = limitNumber;
        ProductName = productName;
        State = state;
        Flag = flag;
        EventNumber = eventNumber;
        ProductId = productId;
        StandardId = standardId;
        IsCollection = isCollection;
        ShippingAddr = shippingAddr;
        Detail = detail;
        EvaluateList = evaluateList;
        this.standardImagesList = standardImagesList;
        DetailTatilAttributeList = detailTatilAttributeList;
        PropertyValueList = propertyValueList;
    }
}
