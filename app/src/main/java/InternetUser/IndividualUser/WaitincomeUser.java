package InternetUser.IndividualUser;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/6/28.
 */
public class WaitincomeUser {
    private List<WaitincomeItem> List;
    private String PageCount;

    public java.util.List<WaitincomeItem> getList() {
        return List;
    }

    public void setList(java.util.List<WaitincomeItem> list) {
        List = list;
    }

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public WaitincomeUser() {

    }

    public WaitincomeUser(java.util.List<WaitincomeItem> list, String pageCount) {

        List = list;
        PageCount = pageCount;
    }
}
