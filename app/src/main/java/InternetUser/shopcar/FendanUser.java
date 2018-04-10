package InternetUser.shopcar;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/4/8.
 */
public class FendanUser {
    private List<FendanItem>List;
    ShopAddress Address;
    private double Commission;
    private double Balance;
    private boolean IsDivide;
    private GoodsOrderUser goodsOrderUser;

    public GoodsOrderUser getGoodsOrderUser() {
        return goodsOrderUser;
    }

    public void setGoodsOrderUser(GoodsOrderUser goodsOrderUser) {
        this.goodsOrderUser = goodsOrderUser;
    }

    public java.util.List<FendanItem> getList() {
        return List;
    }

    public void setList(java.util.List<FendanItem> list) {
        List = list;
    }

    public ShopAddress getAddress() {
        return Address;
    }

    public void setAddress(ShopAddress address) {
        Address = address;
    }


    public double getCommission() {
        return Commission;
    }

    public void setCommission(double commission) {
        Commission = commission;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }


    public boolean getIsDivide() {
        return IsDivide;
    }

    public void setIsDivide(boolean isDivide) {
        IsDivide = isDivide;
    }


    public FendanUser() {

    }

    public FendanUser(java.util.List<FendanItem> list, ShopAddress address, double commission, double balance, boolean isDivide) {
        List = list;
        Address = address;
        Commission = commission;
        Balance = balance;
        IsDivide = isDivide;
    }
}
