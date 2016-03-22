package InternetUser;

import java.util.List;

import InternetUser.Item.CouponPastItem;

/**
 * Created by baicai on 2016/3/14.
 */
public class CouponPastuser {
    private String PageCount;
    private List<CouponPastItem>list;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public List<CouponPastItem> getList() {
        return list;
    }

    public void setList(List<CouponPastItem> list) {
        this.list = list;
    }

    public CouponPastuser() {

    }

    public CouponPastuser(String pageCount, List<CouponPastItem> list) {

        PageCount = pageCount;
        this.list = list;
    }
}
