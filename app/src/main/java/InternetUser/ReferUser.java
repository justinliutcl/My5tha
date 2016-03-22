package InternetUser;

import java.util.List;

import InternetUser.Item.ReferItem;

/**
 * Created by baicai on 2016/3/10.
 */
public class ReferUser {
    private String PageCount;
    private List<ReferItem>list;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public List<ReferItem> getList() {
        return list;
    }

    public void setList(List<ReferItem> list) {
        this.list = list;
    }

    public ReferUser() {

    }

    public ReferUser(String pageCount, List<ReferItem> list) {

        PageCount = pageCount;
        this.list = list;
    }
}
