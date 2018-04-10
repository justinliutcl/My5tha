package InternetUser;

/**
 * Created by baicai on 2016/3/6.
 */
public class Communitionuser {
    private String Commission;
    private String Number;
    private String EncryptMemberId;
    private boolean IsRefMember;

    public boolean getIsRefMember() {
        return IsRefMember;
    }

    public void setIsRefMember(boolean isRefMember) {
        IsRefMember = isRefMember;
    }

    public String getEncryptMemberId() {
        return EncryptMemberId;
    }

    public void setEncryptMemberId(String encryptMemberId) {
        EncryptMemberId = encryptMemberId;
    }

    public String getCommission() {
        return Commission;
    }

    public void setCommission(String commission) {
        Commission = commission;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public Communitionuser() {

    }

    public Communitionuser(String commission, String number) {

        Commission = commission;
        Number = number;
    }
}
