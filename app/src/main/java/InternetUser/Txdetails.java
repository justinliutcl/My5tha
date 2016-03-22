package InternetUser;

/**
 * Created by baicai on 2016/3/9.
 */
public class Txdetails {
    private String Id;
    private String WithdrawCashType;
    private String Amount;
    private String Audit;
    private String AuditState;
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

    public String getAudit() {
        return Audit;
    }

    public void setAudit(String audit) {
        Audit = audit;
    }

    public String getAuditState() {
        return AuditState;
    }

    public void setAuditState(String auditState) {
        AuditState = auditState;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }

    public String getAuditTime() {
        return AuditTime;
    }

    public void setAuditTime(String auditTime) {
        AuditTime = auditTime;
    }

    public String getPayTime() {
        return PayTime;
    }

    public void setPayTime(String payTime) {
        PayTime = payTime;
    }

    public Txdetails() {

    }

    public Txdetails(String id, String withdrawCashType, String amount, String audit, String auditState, String operateTime, String auditTime, String payTime) {

        Id = id;
        WithdrawCashType = withdrawCashType;
        Amount = amount;
        Audit = audit;
        AuditState = auditState;
        OperateTime = operateTime;
        AuditTime = auditTime;
        PayTime = payTime;
    }

    private String AuditTime;
    private String PayTime;
}
