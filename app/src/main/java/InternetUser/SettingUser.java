package InternetUser;

/**
 * Created by baicai on 2016/3/17.
 */
public class SettingUser {
    private String NickName;
    private String Sex;
    private String Level;
    private String Phone;
    private boolean IsSetPayPwd;
    private boolean IsOpenQ;
    private boolean IsOpenWx;
    private boolean IsOpenWb;

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public boolean getIsSetPayPwd() {
        return IsSetPayPwd;
    }

    public void setIsSetPayPwd(boolean isSetPayPwd) {
        IsSetPayPwd = isSetPayPwd;
    }

    public boolean getIsOpenQ() {
        return IsOpenQ;
    }

    public void setIsOpenQ(boolean isOpenQ) {
        IsOpenQ = isOpenQ;
    }

    public boolean getIsOpenWx() {
        return IsOpenWx;
    }

    public void setIsOpenWx(boolean isOpenWx) {
        IsOpenWx = isOpenWx;
    }

    public boolean getIsOpenWb() {
        return IsOpenWb;
    }

    public void setIsOpenWb(boolean isOpenWb) {
        IsOpenWb = isOpenWb;
    }

    public SettingUser() {

    }

    public SettingUser(String nickName, String sex, String level, String phone, boolean isSetPayPwd, boolean isOpenQ, boolean isOpenWx, boolean isOpenWb) {

        NickName = nickName;
        Sex = sex;
        Level = level;
        Phone = phone;
        IsSetPayPwd = isSetPayPwd;
        IsOpenQ = isOpenQ;
        IsOpenWx = isOpenWx;
        IsOpenWb = isOpenWb;
    }
}
