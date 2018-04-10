package InternetUser.shopcar;

/**
 * Created by 不爱白菜 on 2016/5/22.
 */
public class GoodsOrderCoupon {
    private String CouponsId;
    private String Description;
    private double FaceValue;
    private int Num;
    private int Consumption;
    private String EndTime;

    public GoodsOrderCoupon() {
    }

    public GoodsOrderCoupon(String couponsId, String description, int faceValue, int num, int consumption, String endTime) {
        CouponsId = couponsId;
        Description = description;
        FaceValue = faceValue;
        Num = num;
        Consumption = consumption;
        EndTime = endTime;
    }

    public String getCouponsId() {

        return CouponsId;
    }

    public void setCouponsId(String couponsId) {
        CouponsId = couponsId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getFaceValue() {
        return FaceValue;
    }

    public void setFaceValue(int faceValue) {
        FaceValue = faceValue;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public int getConsumption() {
        return Consumption;
    }

    public void setConsumption(int consumption) {
        Consumption = consumption;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
}
