package InternetUser.Item;

/**
 * Created by baicai on 2016/3/10.
 */
public class ReferItem {
    private String Phone;
    private String OperateTime;

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }

    public ReferItem() {

    }

    public ReferItem(String phone, String operateTime) {

        Phone = phone;
        OperateTime = operateTime;
    }
}
