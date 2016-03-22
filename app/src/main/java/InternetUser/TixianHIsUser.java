package InternetUser;

import java.util.List;

import InternetUser.Item.TixianHisItem;

/**
 * Created by baicai on 2016/3/8.
 */
public class TixianHIsUser {
    private String PageCount;
    private List<TixianHisItem>list;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public List<TixianHisItem> getList() {
        return list;
    }

    public void setList(List<TixianHisItem> list) {
        this.list = list;
    }

    public TixianHIsUser() {

    }

    public TixianHIsUser(String pageCount, List<TixianHisItem> list) {

        PageCount = pageCount;
        this.list = list;
    }
}
