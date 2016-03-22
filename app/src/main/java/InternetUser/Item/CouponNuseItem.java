package InternetUser.Item;

/**
 * Created by baicai on 2016/3/14.
 */
public class CouponNuseItem {
    private String EndTime;
    private String CouponsId;
    private String Description;
    private String Num;
    private String FaceValue;

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
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

    public CouponNuseItem() {

    }

    public CouponNuseItem(String endTime, String couponsId, String description, String num, String faceValue) {

        EndTime = endTime;
        CouponsId = couponsId;
        Description = description;
        Num = num;
        FaceValue = faceValue;
    }
}
