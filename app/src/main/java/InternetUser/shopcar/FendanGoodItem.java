package InternetUser.shopcar;

/**
 * Created by 不爱白菜 on 2016/4/8.
 */
public class FendanGoodItem {
    private String Id;
    private double Tax;
    private double SellPrice;
    private int Number;
    private int Storage;
    private double TotalPrice;
    private String ImageSrc;
    private String StandardType;
    private String Operatetime;
    private String StandardDesc;
    private String Title;
    private String Belong;
    private String ProductId;
    private String StandardId;
    private boolean flage=false;
    private boolean tijiao=false;

    public double getTax() {
        return Tax;
    }

    public void setTax(double tax) {
        Tax = tax;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getStandardId() {
        return StandardId;
    }

    public void setStandardId(String standardId) {
        StandardId = standardId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public boolean isTijiao() {
        return tijiao;
    }

    public void setTijiao(boolean tijiao) {
        this.tijiao = tijiao;
    }

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

    public String getBelong() {
        return Belong;
    }

    public void setBelong(String belong) {
        Belong = belong;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public double getTaxTemp() {
        return Tax;
    }

    public void setTaxTemp(double taxTemp) {
        Tax = taxTemp;
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

    public String getImageSrc() {
        return ImageSrc;
    }

    public void setImageSrc(String imageSrc) {
        ImageSrc = imageSrc;
    }

    public String getStandardType() {
        return StandardType;
    }

    public void setStandardType(String standardType) {
        StandardType = standardType;
    }


    public String getOperatetime() {
        return Operatetime;
    }

    public void setOperatetime(String operatetime) {
        Operatetime = operatetime;
    }

    public String getStandardDesc() {
        return StandardDesc;
    }

    public void setStandardDesc(String standardDesc) {
        StandardDesc = standardDesc;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public FendanGoodItem() {

    }

    public FendanGoodItem(String id, double sellPrice, int number, int storage, String imageSrc, String standardType,  String operatetime, String standardDesc, String title) {

        Id = id;
        SellPrice = sellPrice;
        Number = number;
        Storage = storage;
        ImageSrc = imageSrc;
        StandardType = standardType;
        Operatetime = operatetime;
        StandardDesc = standardDesc;
        Title = title;
    }
}
