package InternetUser;

/**
 * Created by baicai on 2016/3/6.
 */
public class Communitionuser {
    private String Commission;
    private String Number;

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
