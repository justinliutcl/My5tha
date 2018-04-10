package InternetUser.A_Home;

import java.util.List;

/**
 * Created by baicai on 2016/3/25.
 */
public class JxdxUser {
    private String ImgSrc;
    private String BrandId;
    private List<JxduItem>BrandProducts;

    public String getImgSrc() {
        return ImgSrc;
    }

    public void setImgSrc(String imgSrc) {
        ImgSrc = imgSrc;
    }

    public String getBrandId() {
        return BrandId;
    }

    public void setBrandId(String brandId) {
        BrandId = brandId;
    }

    public List<JxduItem> getBrandProducts() {
        return BrandProducts;
    }

    public void setBrandProducts(List<JxduItem> brandProducts) {
        BrandProducts = brandProducts;
    }

    public JxdxUser() {

    }

    public JxdxUser(String imgSrc, String brandId, List<JxduItem> brandProducts) {

        ImgSrc = imgSrc;
        BrandId = brandId;
        BrandProducts = brandProducts;
    }
}
