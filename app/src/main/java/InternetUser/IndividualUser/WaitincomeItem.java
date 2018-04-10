package InternetUser.IndividualUser;

/**
 * Created by 不爱白菜 on 2016/6/28.
 */
public class WaitincomeItem {
    private String OperaterTime;
    private String FinanceOperaTypeName;
    private double Amount;
    private String OpNickName;

    public WaitincomeItem() {
    }

    public WaitincomeItem(String operaterTime, String financeOperaTypeName, double amount, String opNickName) {

        OperaterTime = operaterTime;
        FinanceOperaTypeName = financeOperaTypeName;
        Amount = amount;
        OpNickName = opNickName;
    }

    public String getOperaterTime() {

        return OperaterTime;
    }

    public void setOperaterTime(String operaterTime) {
        OperaterTime = operaterTime;
    }

    public String getFinanceOperaTypeName() {
        return FinanceOperaTypeName;
    }

    public void setFinanceOperaTypeName(String financeOperaTypeName) {
        FinanceOperaTypeName = financeOperaTypeName;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getOpNickName() {
        return OpNickName;
    }

    public void setOpNickName(String opNickName) {
        OpNickName = opNickName;
    }
}
