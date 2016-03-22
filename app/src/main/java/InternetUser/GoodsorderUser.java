package InternetUser;

import java.util.List;

import InternetUser.order.GoodsorderItem;

/**
 * Created by baicai on 2016/3/14.
 */
public class GoodsorderUser {
    private String PageCount;
    private List<GoodsorderItem>list;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public List<GoodsorderItem> getList() {
        return list;
    }

    public void setList(List<GoodsorderItem> list) {
        this.list = list;
    }

    public GoodsorderUser() {

    }

    public GoodsorderUser(String pageCount, List<GoodsorderItem> list) {

        PageCount = pageCount;
        this.list = list;
    }
}
