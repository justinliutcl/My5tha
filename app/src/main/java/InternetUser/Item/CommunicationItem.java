package InternetUser.Item;

/**
 * Created by baicai on 2016/3/7.
 */
public class CommunicationItem {
    private String Id;
    private String NickName;
    private String FinanceOperaType;
    private String OperaterTime;
    private String Amount;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getFinanceOperaType() {
        return FinanceOperaType;
    }

    public void setFinanceOperaType(String financeOperaType) {
        FinanceOperaType = financeOperaType;
    }

    public String getOperaterTime() {
        return OperaterTime;
    }

    public void setOperaterTime(String operaterTime) {
        OperaterTime = operaterTime;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public CommunicationItem() {

    }

    public CommunicationItem(String id, String nickName, String financeOperaType, String operaterTime, String amount) {

        Id = id;
        NickName = nickName;
        FinanceOperaType = financeOperaType;
        OperaterTime = operaterTime;
        Amount = amount;
    }
}

