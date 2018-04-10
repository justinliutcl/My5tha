package InternetUser.O_other;

/**
 * Created by 不爱白菜 on 2016/3/28.
 */
public class PropertyValueItem {
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

    public PropertyValueItem() {

    }

    public PropertyValueItem(String name, String value) {

        Name = name;
        Value = value;
    }
}
