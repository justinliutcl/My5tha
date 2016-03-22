package InternetUser.Item;

/**
 * Created by baicai on 2016/3/8.
 */
public class TixianHisItem {
    private String Id;
    private String WithdrawCashType;
    private String Amount;
    private String OperateTime;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getWithdrawCashType() {
        return WithdrawCashType;
    }

    public void setWithdrawCashType(String withdrawCashType) {
        WithdrawCashType = withdrawCashType;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }

    public String getAudit() {
        return Audit;
    }

    public void setAudit(String audit) {
        this.Audit = audit;
    }

    public TixianHisItem() {

    }

    public TixianHisItem(String id, String withdrawCashType, String amount, String operateTime, String success) {

        Id = id;
        WithdrawCashType = withdrawCashType;
        Amount = amount;
        OperateTime = operateTime;
        this.Audit = success;
    }

    private String Audit;
}
