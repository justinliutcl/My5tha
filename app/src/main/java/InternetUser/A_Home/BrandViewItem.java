package InternetUser.A_Home;

/**
 * Created by 不爱白菜 on 2016/4/14.
 */
public class BrandViewItem {
    private String BrandLogo;
    private String BrandId;

    public String getBrandLogo() {
        return BrandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        BrandLogo = brandLogo;
    }

    public String getBrandId() {
        return BrandId;
    }

    public void setBrandId(String brandId) {
        BrandId = brandId;
    }

    public BrandViewItem() {

    }

    public BrandViewItem(String brandLogo, String brandId) {

        BrandLogo = brandLogo;
        BrandId = brandId;
    }
}
