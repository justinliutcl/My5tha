package InternetUser.Item;

/**
 * Created by baicai on 2016/3/6.
 */
public class ChongItem {
    private String Amount;
    private String PayType;
    private String OperateTime;

    public ChongItem() {
    }

    public ChongItem(String amount, String payType, String operateTime) {

        Amount = amount;
        PayType = payType;
        OperateTime = operateTime;
    }

    public String getAmount() {

        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String payType) {
        PayType = payType;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }
}
