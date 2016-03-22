package InternetUser;

/**
 * Created by baicai on 2016/2/24.
 */
public class LoginUser {
    private boolean IsSuccess;
    private int Code;
    private String Message;
    private int Data;

    public int getData() {
        return Data;
    }

    public void setData(int data) {
        Data = data;
    }

    public boolean isSuccess() {
        return IsSuccess;
    }

    public LoginUser(boolean isSuccess, int code, String message,int data) {
        IsSuccess = isSuccess;
        Code = code;
        Message = message;
        Data=data;
    }

    public LoginUser() {

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
