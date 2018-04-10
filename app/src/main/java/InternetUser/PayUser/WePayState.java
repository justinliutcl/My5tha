package InternetUser.PayUser;

/**
 * Created by 不爱白菜 on 2016/5/16.
 */
public class WePayState {
    private static WePayState wePayState;

    private  WePayState() {
    }
    public static WePayState getInstance(){
        if(wePayState==null)
            wePayState=new WePayState();
        return wePayState;
    }
    private int state;
    private int position;
    private String initType;

    public String getInitType() {
        return initType;
    }

    public void setInitType(String initType) {
        this.initType = initType;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
