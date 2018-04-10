package InternetUser.shopcar;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/4/7.
 */
public class Shopcarlist {
    private double TotalPrice;
    private double TotalTax;
    private String TimeSpan;

    private List<ShopcarlistItem> CartViewList;

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public double getTotalTax() {
        return TotalTax;
    }

    public void setTotalTax(double totalTax) {
        TotalTax = totalTax;
    }

    public String getTimeSpan() {
        return TimeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        TimeSpan = timeSpan;
    }

    public List<ShopcarlistItem> getCartViewList() {
        return CartViewList;
    }

    public void setCartViewList(List<ShopcarlistItem> cartViewList) {
        CartViewList = cartViewList;
    }

    public Shopcarlist() {

    }

    public Shopcarlist(double totalPrice, double totalTax, String timeSpan, List<ShopcarlistItem> cartViewList) {

        TotalPrice = totalPrice;
        TotalTax = totalTax;
        TimeSpan = timeSpan;
        CartViewList = cartViewList;
    }
}
