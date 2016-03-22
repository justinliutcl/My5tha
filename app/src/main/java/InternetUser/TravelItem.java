package InternetUser;

/**
 * Created by baicai on 2016/3/4.
 */
public class TravelItem {
    private String FundValue;
    private String GainType;
    private String OperateTime;

    public String getFundValue() {
        return FundValue;
    }

    public void setFundValue(String fundValue) {
        FundValue = fundValue;
    }

    public String getGainType() {
        return GainType;
    }

    public void setGainType(String gainType) {
        GainType = gainType;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }

    public TravelItem() {

    }

    public TravelItem(String fundValue, String gainType, String operateTime) {
        FundValue = fundValue;
        GainType = gainType;
        OperateTime = operateTime;
    }
}
