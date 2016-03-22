package InternetUser;

import java.util.List;

import InternetUser.Item.InoutHisItem;

/**
 * Created by baicai on 2016/3/12.
 */
public class InoutHisUser {
    private String PageCount;
    private List<InoutHisItem>list;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public List<InoutHisItem> getList() {
        return list;
    }

    public void setList(List<InoutHisItem> list) {
        this.list = list;
    }

    public InoutHisUser() {

    }

    public InoutHisUser(String pageCount, List<InoutHisItem> list) {

        PageCount = pageCount;
        this.list = list;
    }
}
