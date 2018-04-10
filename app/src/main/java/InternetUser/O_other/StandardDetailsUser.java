package InternetUser.O_other;

/**
 * Created by 不爱白菜 on 2016/5/19.
 */
public class StandardDetailsUser {
    private String StandardId;
    private String ValueId;

    public StandardDetailsUser(String standardId, String valueId) {
        StandardId = standardId;
        ValueId = valueId;
    }

    public StandardDetailsUser() {
    }

    public String getStandardId() {

        return StandardId;
    }

    public void setStandardId(String standardId) {
        StandardId = standardId;
    }

    public String getValueId() {
        return ValueId;
    }

    public void setValueId(String valueId) {
        ValueId = valueId;
    }
}
