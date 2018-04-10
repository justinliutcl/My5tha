package InternetUser.order;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/4/22.
 */
public class LogistDetails {
    private String OperatingCompany;
    private String OperaNumber;
    private String OperationTime;
    private List<LogistDetailsItem>OrderRecordSummaryList;

    public String getOperatingCompany() {
        return OperatingCompany;
    }

    public void setOperatingCompany(String operatingCompany) {
        OperatingCompany = operatingCompany;
    }

    public String getOperaNumber() {
        return OperaNumber;
    }

    public void setOperaNumber(String operaNumber) {
        OperaNumber = operaNumber;
    }

    public String getOperationTime() {
        return OperationTime;
    }

    public void setOperationTime(String operationTime) {
        OperationTime = operationTime;
    }

    public List<LogistDetailsItem> getOrderRecordSummaryList() {
        return OrderRecordSummaryList;
    }

    public void setOrderRecordSummaryList(List<LogistDetailsItem> orderRecordSummaryList) {
        OrderRecordSummaryList = orderRecordSummaryList;
    }

    public LogistDetails() {

    }

    public LogistDetails(String operatingCompany, String operaNumber, String operationTime, List<LogistDetailsItem> orderRecordSummaryList) {

        OperatingCompany = operatingCompany;
        OperaNumber = operaNumber;
        OperationTime = operationTime;
        OrderRecordSummaryList = orderRecordSummaryList;
    }
}
