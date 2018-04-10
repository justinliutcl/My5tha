package InternetUser.shopcar;

/**
 * Created by 不爱白菜 on 2016/4/8.
 */
public class DingdanUser {
    private static DingdanUser user;
    private static  FendanUser fendanUser;
    private DingdanUser(){}
    public static DingdanUser getInstance(){
        if(user==null)
            user=new DingdanUser();
        return user;
    }

    public FendanUser getFendanUser() {
        return fendanUser;
    }

    public void setFendanUser(FendanUser fendanUser) {
        this.fendanUser = fendanUser;
    }
}
