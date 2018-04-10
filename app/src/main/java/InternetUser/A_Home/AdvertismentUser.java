package InternetUser.A_Home;

/**
 * Created by 不爱白菜 on 2016/6/29.
 */
public class AdvertismentUser {
    private String Src;
    private String JumpValue;
    private String Title;
    private boolean IsShow;

    public AdvertismentUser() {
    }

    public AdvertismentUser(String src, String jumpValue, String title, boolean isShow) {

        Src = src;
        JumpValue = jumpValue;
        Title = title;
        IsShow = isShow;
    }

    public String getSrc() {

        return Src;
    }

    public void setSrc(String src) {
        Src = src;
    }

    public String getJumpValue() {
        return JumpValue;
    }

    public void setJumpValue(String jumpValue) {
        JumpValue = jumpValue;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public boolean isShow() {
        return IsShow;
    }

    public void setIsShow(boolean isShow) {
        IsShow = isShow;
    }
}
