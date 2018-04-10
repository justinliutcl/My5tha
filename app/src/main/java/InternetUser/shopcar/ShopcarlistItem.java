package InternetUser.shopcar;

/**
 * Created by 不爱白菜 on 2016/4/7.
 */
public class ShopcarlistItem {
    private String Id;
    private double TaxTemp;
    private double SellPrice;
    private int Number;
    private int Storage;
    private String DeatilId;
    private String ImageSrc;
    private String EventDetailId;
    private String StandardType;
    private String TotalPrice;
    private String TotalFreight;
    private String Pay;
    private String Operatetime;
    private boolean flage=true;
    private String StandardDesc;
    private String Title;
    private int LimitNum;
    private String ProductId;
    private String Belong;
    private String StandardId;
    private double Tax;

    public double getTax() {
        return Tax;
    }

    public void setTax(double tax) {
        Tax = tax;
    }

    public String getStandardId() {
        return StandardId;
    }

    public void setStandardId(String standardId) {
        StandardId = standardId;
    }

    public String getBelong() {
        return Belong;
    }

    public void setBelong(String belong) {
        Belong = belong;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public int getLimitNum() {
        return LimitNum;
    }

    public void setLimitNum(int limitNum) {
        LimitNum = limitNum;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public double getTaxTemp() {
        return TaxTemp;
    }

    public void setTaxTemp(double taxTemp) {
        TaxTemp = taxTemp;
    }

    public double getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(double sellPrice) {
        SellPrice = sellPrice;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public int getStorage() {
        return Storage;
    }

    public void setStorage(int storage) {
        Storage = storage;
    }

    public String getDeatilId() {
        return DeatilId;
    }

    public void setDeatilId(String deatilId) {
        DeatilId = deatilId;
    }

    public String getImageSrc() {
        return ImageSrc;
    }

    public void setImageSrc(String imageSrc) {
        ImageSrc = imageSrc;
    }

    public String getEventDetailId() {
        return EventDetailId;
    }

    public void setEventDetailId(String eventDetailId) {
        EventDetailId = eventDetailId;
    }

    public String getStandardType() {
        return StandardType;
    }

    public void setStandardType(String standardType) {
        StandardType = standardType;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getTotalFreight() {
        return TotalFreight;
    }

    public void setTotalFreight(String totalFreight) {
        TotalFreight = totalFreight;
    }

    public String getPay() {
        return Pay;
    }

    public void setPay(String pay) {
        Pay = pay;
    }

    public String getOperatetime() {
        return Operatetime;
    }

    public void setOperatetime(String operatetime) {
        Operatetime = operatetime;
    }

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

    public String getStandardDesc() {
        return StandardDesc;
    }

    public void setStandardDesc(String standardDesc) {
        StandardDesc = standardDesc;
    }

    public ShopcarlistItem() {

    }

    public ShopcarlistItem(String id, double taxTemp, double sellPrice, int number, int storage, String deatilId, String imageSrc, String eventDetailId, String standardType, String totalPrice, String totalFreight, String pay, String operatetime, boolean flage, String standardDesc) {

        Id = id;
        TaxTemp = taxTemp;
        SellPrice = sellPrice;
        Number = number;
        Storage = storage;
        DeatilId = deatilId;
        ImageSrc = imageSrc;
        EventDetailId = eventDetailId;
        StandardType = standardType;
        TotalPrice = totalPrice;
        TotalFreight = totalFreight;
        Pay = pay;
        Operatetime = operatetime;
        this.flage = flage;
        StandardDesc = standardDesc;
    }
}
