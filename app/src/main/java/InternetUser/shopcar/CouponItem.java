package InternetUser.shopcar;

/**
 * Created by 不爱白菜 on 2016/4/8.
 */
public class CouponItem {
    private String CouponsId;
    private String Description;
    private String EndTime;
    private String Num;
    private String FaceValue;

    public CouponItem() {
    }

    public CouponItem(String couponsId, String description, String endTime, String num, String faceValue) {

        CouponsId = couponsId;
        Description = description;
        EndTime = endTime;
        Num = num;
        FaceValue = faceValue;
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

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getFaceValue() {
        return FaceValue;
    }

    public void setFaceValue(String faceValue) {
        FaceValue = faceValue;
    }
}
