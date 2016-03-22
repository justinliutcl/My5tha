package InternetUser.order;

/**
 * Created by baicai on 2016/3/14.
 */
public class OrderTatilAttrItem {
    private String Name;
    private String Value;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public OrderTatilAttrItem(String name, String value) {

        Name = name;
        Value = value;
    }

    public OrderTatilAttrItem() {

    }
}
