package InternetUser.order;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by baicai on 2016/3/14.
 */
public class GoodsorderItem {
    private double CouponMoney;
    private double Total;
    private String OperateTime;
    private String OrderStatus;
    private String OrderStatusString;
    private String IssueId;
    private String OrderNumber;

    public void setIssueId(String issueId) {
        IssueId = issueId;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getIssueId() {
        return IssueId;
    }

    public GoodsorderItem(double couponMoney, double total, String operateTime, String orderStatus, String orderStatusString, String issueId, List<OrderDetailItem> list) {

        CouponMoney = couponMoney;
        Total = total;
        OperateTime = operateTime;
        OrderStatus = orderStatus;
        OrderStatusString = orderStatusString;
        IssueId = issueId;
        this.df = df;
        this.list = list;
    }

    DecimalFormat df = new DecimalFormat("#0.00");

    public String getOrderStatusString() {

        return OrderStatusString;
    }

    public void setOrderStatusString(String orderStatusString) {
        OrderStatusString = orderStatusString;
    }

    private List<OrderDetailItem> list;


    public String getCouponMoney() {
        return df.format(CouponMoney);
    }

    public void setCouponMoney(double couponMoney) {
        CouponMoney = couponMoney;
    }

    public String getTotal() {
        return df.format(Total);
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

    public List<OrderDetailItem> getList() {
        return list;
    }

    public void setList(List<OrderDetailItem> list) {
        this.list = list;
    }


    public GoodsorderItem() {

    }
}
