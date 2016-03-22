package InternetUser.Item;

/**
 * Created by baicai on 2016/3/14.
 */
public class CouponHitem {
    private String Description;
    private String OperateTime;
    private String FaceValue;
    private String Consumption;
    private String OrderNumber;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }

    public String getFaceValue() {
        return FaceValue;
    }

    public void setFaceValue(String faceValue) {
        FaceValue = faceValue;
    }

    public String getConsumption() {
        return Consumption;
    }

    public void setConsumption(String consumption) {
        Consumption = consumption;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public CouponHitem() {

    }

    public CouponHitem(String description, String operateTime, String faceValue, String consumption, String orderNumber) {

        Description = description;
        OperateTime = operateTime;
        FaceValue = faceValue;
        Consumption = consumption;
        OrderNumber = orderNumber;
    }
}
