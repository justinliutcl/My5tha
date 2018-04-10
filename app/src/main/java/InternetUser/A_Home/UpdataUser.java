package InternetUser.A_Home;

/**
 * Created by 不爱白菜 on 2016/5/29.
 */
public class UpdataUser {
    private String Id;
    private double SerialNumber;
    private String OperateTime;
    private String Summary;
    private String OperateId;
    private String Type;
    private String Title;
    private String Url;
    private String Content;
    private String TotalNumber;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public double getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(double serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getOperateId() {
        return OperateId;
    }

    public void setOperateId(String operateId) {
        OperateId = operateId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTotalNumber() {
        return TotalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        TotalNumber = totalNumber;
    }

    public UpdataUser() {

    }

    public UpdataUser(String id, double serialNumber, String operateTime, String summary, String operateId, String type, String title, String url, String content, String totalNumber) {

        Id = id;
        SerialNumber = serialNumber;
        OperateTime = operateTime;
        Summary = summary;
        OperateId = operateId;
        Type = type;
        Title = title;
        Url = url;
        Content = content;
        TotalNumber = totalNumber;
    }
}
