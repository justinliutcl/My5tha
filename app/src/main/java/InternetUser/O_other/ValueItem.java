package InternetUser.O_other;

/**
 * Created by 不爱白菜 on 2016/3/28.
 */
public class ValueItem {
    private String Value;
    private String Id;
    private String Img;

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public ValueItem() {

    }

    public ValueItem(String value, String id) {
        Value = value;
        Id = id;
    }

    public ValueItem(String value) {

        Value = value;
    }
}
