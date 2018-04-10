package InternetUser.A_Home;

/**
 * Created by 不爱白菜 on 2016/4/5.
 */
public class SelectClassBrandsItem {
    private String BrandName;
    private String BrandId;
    private String BrandLogo;

    public String getBrandLogo() {
        return BrandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        BrandLogo = brandLogo;
    }

    private boolean flage=false;

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

    public String getBrandId() {
        return BrandId;
    }

    public void setBrandId(String brandId) {
        BrandId = brandId;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public SelectClassBrandsItem() {

    }

    public SelectClassBrandsItem(String Id,String brandName,boolean f) {
        flage=f;
        BrandName = brandName;
        BrandId=Id;
    }
}
