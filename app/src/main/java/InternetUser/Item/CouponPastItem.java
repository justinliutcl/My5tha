package InternetUser.Item;

/**
 * Created by baicai on 2016/3/14.
 */
public class CouponPastItem {
    private String EndTime;
    private String Description;
    private String FaceValue;
    private String Consumption;

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public CouponPastItem(String endTime, String description, String faceValue, String consumption) {

        EndTime = endTime;
        Description = description;
        FaceValue = faceValue;
        Consumption = consumption;
    }

    public CouponPastItem() {

    }
}
