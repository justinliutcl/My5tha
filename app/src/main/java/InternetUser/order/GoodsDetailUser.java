package InternetUser.order;

import java.util.List;

/**
 * Created by baicai on 2016/3/21.
 */
public class GoodsDetailUser {
    private String DeliveryName;
    private String Mobile;
    private String Address;
    private String OrderStatus;
    private String OrderNumber;
    private String OperateTime;
    private String OperatingCompany;
    private double Commission;
    private double ElectronicToken;
    private double Freight;
    private double CouponMoney;
    private double Total;
    private List<OrderDetailItem> list;

    public List<OrderDetailItem> getList() {
        return list;
    }

    public void setList(List<OrderDetailItem> list) {
        this.list = list;
    }

    public String getDeliveryName() {
        return DeliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        DeliveryName = deliveryName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }

    public String getOperatingCompany() {
        return OperatingCompany;
    }

    public void setOperatingCompany(String operatingCompany) {
        OperatingCompany = operatingCompany;
    }

    public double getCommission() {
        return Commission;
    }

    public void setCommission(double commission) {
        Commission = commission;
    }

    public double getElectronicToken() {
        return ElectronicToken;
    }

    public void setElectronicToken(double electronicToken) {
        ElectronicToken = electronicToken;
    }

    public double getFreight() {
        return Freight;
    }

    public void setFreight(double freight) {
        Freight = freight;
    }

    public double getCouponMoney() {
        return CouponMoney;
    }

    public void setCouponMoney(double couponMoney) {
        CouponMoney = couponMoney;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public GoodsDetailUser() {

    }

    public GoodsDetailUser(String deliveryName, String mobile, String address, String orderStatus, String orderNumber, String operateTime, String operatingCompany, double commission, double electronicToken, double freight, double couponMoney, double total) {

        DeliveryName = deliveryName;
        Mobile = mobile;
        Address = address;
        OrderStatus = orderStatus;
        OrderNumber = orderNumber;
        OperateTime = operateTime;
        OperatingCompany = operatingCompany;
        Commission = commission;
        ElectronicToken = electronicToken;
        Freight = freight;
        CouponMoney = couponMoney;
        Total = total;
    }
}
