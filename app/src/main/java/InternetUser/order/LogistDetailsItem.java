package InternetUser.order;

/**
 * Created by 不爱白菜 on 2016/4/22.
 */
public class LogistDetailsItem {
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

    public LogistDetailsItem() {

    }

    public LogistDetailsItem(String operationTime, String operationContext) {

        OperationTime = operationTime;
        OperationContext = operationContext;
    }
}
