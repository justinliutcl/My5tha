package InternetUser;

import java.util.List;

import InternetUser.Item.CommunicationItem;

/**
 * Created by baicai on 2016/3/7.
 */
public class Communicationuser {
    private String PageCount;
    private List<CommunicationItem>list;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public List<CommunicationItem> getList() {
        return list;
    }

    public void setList(List<CommunicationItem> list) {
        this.list = list;
    }

    public Communicationuser() {

    }

    public Communicationuser(String pageCount, List<CommunicationItem> list) {

        PageCount = pageCount;
        this.list = list;
    }
}
