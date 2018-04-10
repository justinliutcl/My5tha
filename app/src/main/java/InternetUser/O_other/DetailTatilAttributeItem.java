package InternetUser.O_other;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/3/28.
 */
public class DetailTatilAttributeItem {
    private String Name;
    private List<ValueItem>Value;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<ValueItem> getValue() {
        return Value;
    }

    public void setValue(List<ValueItem> value) {
        Value = value;
    }

    public DetailTatilAttributeItem() {

    }

    public DetailTatilAttributeItem(String name, List<ValueItem> value) {

        Name = name;
        Value = value;
    }
}
