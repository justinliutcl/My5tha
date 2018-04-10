package InternetUser.A_Home;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/4/4.
 */
public class SelectItem {
    List<SelectTitleItem>Data;

    public List<SelectTitleItem> getData() {
        return Data;
    }

    public void setData(List<SelectTitleItem> data) {
        Data = data;
    }

    public SelectItem() {

    }

    public SelectItem(List<SelectTitleItem> data) {

        Data = data;
    }
}
