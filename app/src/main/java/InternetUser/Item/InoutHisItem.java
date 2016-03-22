package InternetUser.Item;

/**
 * Created by baicai on 2016/3/12.
 */
public class InoutHisItem {
    private String OperateTime;
    private String FinanceOperaType;
    private String Amount;
    private String Description;

    public InoutHisItem() {
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }

    public String getFinanceOperaType() {
        return FinanceOperaType;
    }

    public void setFinanceOperaType(String financeOperaType) {
        FinanceOperaType = financeOperaType;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public InoutHisItem(String operaTimeName, String financeOperaTypeName, String amount, String description) {
        OperateTime = operaTimeName;
        FinanceOperaType = financeOperaTypeName;
        Amount = amount;
        Description = description;
    }
}
