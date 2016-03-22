package InternetUser;

import java.util.List;

import InternetUser.Item.CollectItem;

/**
 * Created by baicai on 2016/3/21.
 */
public class CollectUser {
    private String PageCount;
    private List<CollectItem> list;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public List<CollectItem> getList() {
        return list;
    }

    public void setList(List<CollectItem> list) {
        this.list = list;
    }

    public CollectUser() {

    }

    public CollectUser(String pageCount, List<CollectItem> list) {

        PageCount = pageCount;
        this.list = list;
    }
}
