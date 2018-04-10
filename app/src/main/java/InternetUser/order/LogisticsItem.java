package InternetUser.order;

/**
 * Created by baicai on 2016/3/22.
 */
public class LogisticsItem {
    private String OperationTime;
    private String OperationContext;

    public String getOperationTime() {
        return OperationTime;
    }

    public void setOperationTime(String operationTime) {
        OperationTime = operationTime;
    }

    public String getOperationContext() {
        return OperationContext;
    }

    public void setOperationContext(String operationContext) {
        OperationContext = operationContext;
    }

    public LogisticsItem() {

    }

    public LogisticsItem(String operationTime, String operationContext) {

        OperationTime = operationTime;
        OperationContext = operationContext;
    }
}
