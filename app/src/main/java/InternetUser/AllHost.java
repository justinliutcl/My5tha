package InternetUser;

/**
 * Created by baicai on 2016/2/24.
 */
public class AllHost {
    private boolean IsSuccess;
    private int Code;
    private String Message;
    private String Data;
    public AllHost(boolean isSuccess, int code, String message, String data) {
        IsSuccess = isSuccess;
        Code = code;
        Message = message;
        Data = data;
    }
    public boolean isSuccess() {
        return IsSuccess;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public AllHost(boolean isSuccess, int code, String message) {
        IsSuccess = isSuccess;
        Code = code;
        Message = message;
    }

    public AllHost() {

    }

    public int getCode() {

        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setIsSuccess(boolean isSuccess) {
        IsSuccess = isSuccess;
    }
}
