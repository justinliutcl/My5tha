package InternetUser;

import java.util.List;

import InternetUser.Item.CouponHitem;

/**
 * Created by baicai on 2016/3/14.
 */
public class CouponHuser {
    private String PageCount;
    private List<CouponHitem>list;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public List<CouponHitem> getList() {
        return list;
    }

    public void setList(List<CouponHitem> list) {
        this.list = list;
    }

    public CouponHuser() {

    }

    public CouponHuser(String pageCount, List<CouponHitem> list) {

        PageCount = pageCount;
        this.list = list;
    }
}
