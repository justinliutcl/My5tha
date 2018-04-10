package InternetUser.order;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by baicai on 2016/3/14.
 */
public class GoodsorderItem {
    private double Commission;
    private String CouponMoney;
    private double Total;
    private String OperateTime;
    private String OrderStatus;
    private String OrderStatusString;
    private String IssueId;
    private String OrderNumber;
    private double SurplusMoney;
    private List<GoodsorderItem>SubOrderList;
    private List<OrderDetailItem> OrderDetailViewList;

    DecimalFormat df = new DecimalFormat("#0.00");

    public double getSurplusMoney() {
        return SurplusMoney;
    }

    public void setSurplusMoney(double surplusMoney) {
        SurplusMoney = surplusMoney;
    }

    public GoodsorderItem(double commission, String couponMoney, double total, String operateTime, String orderStatus, String orderStatusString, String issueId, String orderNumber, List<GoodsorderItem> subOrderList, List<OrderDetailItem> orderDetailViewList) {
        Commission = commission;
        CouponMoney = couponMoney;
        Total = total;
        OperateTime = operateTime;
        OrderStatus = orderStatus;
        OrderStatusString = orderStatusString;
        IssueId = issueId;
        OrderNumber = orderNumber;
        SubOrderList = subOrderList;
        OrderDetailViewList = orderDetailViewList;
    }

    public GoodsorderItem() {

    }

    public double getCommission() {

        return Commission;
    }

    public void setCommission(double commission) {
        Commission = commission;
    }

    public String getCouponMoney() {
        return CouponMoney;
    }

    public void setCouponMoney(String couponMoney) {
        CouponMoney = couponMoney;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getOrderStatusString() {
        return OrderStatusString;
    }

    public void setOrderStatusString(String orderStatusString) {
        OrderStatusString = orderStatusString;
    }

    public String getIssueId() {
        return IssueId;
    }

    public void setIssueId(String issueId) {
        IssueId = issueId;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public List<GoodsorderItem> getSubOrderList() {
        return SubOrderList;
    }

    public void setSubOrderList(List<GoodsorderItem> subOrderList) {
        SubOrderList = subOrderList;
    }

    public List<OrderDetailItem> getOrderDetailViewList() {
        return OrderDetailViewList;
    }

    public void setOrderDetailViewList(List<OrderDetailItem> orderDetailViewList) {
        OrderDetailViewList = orderDetailViewList;
    }
}
