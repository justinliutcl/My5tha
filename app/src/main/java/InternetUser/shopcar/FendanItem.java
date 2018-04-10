package InternetUser.shopcar;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/5/21.
 */
public class FendanItem {
    private List<FendanGoodItem>List;
    private String Name;
    private String TimeSpan;

    public FendanItem(java.util.List<FendanGoodItem> list, String name, String timeSpan) {
        List = list;
        Name = name;
        TimeSpan = timeSpan;
    }

    public FendanItem() {

    }

    public java.util.List<FendanGoodItem> getList() {

        return List;
    }

    public void setList(java.util.List<FendanGoodItem> list) {
        List = list;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTimeSpan() {
        return TimeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        TimeSpan = timeSpan;
    }
}
