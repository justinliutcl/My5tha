package InternetUser.A_Home;

/**
 * Created by 不爱白菜 on 2016/5/27.
 */
public class H5user {
    private String ObjectId;
    private String productType;

    public H5user() {
    }

    public H5user(String objectId, String productType) {
        ObjectId = objectId;
        this.productType = productType;
    }

    public String getObjectId() {

        return ObjectId;
    }

    public void setObjectId(String objectId) {
        ObjectId = objectId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
