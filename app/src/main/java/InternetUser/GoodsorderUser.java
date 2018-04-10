package InternetUser;

import java.util.List;

import InternetUser.order.GoodsorderItem;

/**
 * Created by baicai on 2016/3/14.
 */
public class GoodsorderUser {
    private String PageCount;
    private double Balance;
    private double Commission;
    private List<GoodsorderItem>List;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public double getCommission() {
        return Commission;
    }

    public void setCommission(double commission) {
        Commission = commission;
    }

    public java.util.List<GoodsorderItem> getList() {
        return List;
    }

    public void setList(java.util.List<GoodsorderItem> list) {
        List = list;
    }

    public GoodsorderUser() {
    }

    public GoodsorderUser(String pageCount, double balance, double commission, java.util.List<GoodsorderItem> list) {

        PageCount = pageCount;
        Balance = balance;
        Commission = commission;
        List = list;
    }
}
