package InternetUser;

import java.util.List;

import InternetUser.Item.DuiItem;

/**
 * Created by baicai on 2016/3/6.
 */
public class Duiuser {
    private String PageCount;
    private List<DuiItem>dlist;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public List<DuiItem> getDlist() {
        return dlist;
    }

    public void setDlist(List<DuiItem> dlist) {
        this.dlist = dlist;
    }

    public Duiuser(String pageCount, List<DuiItem> dlist) {

        PageCount = pageCount;
        this.dlist = dlist;
    }

    public Duiuser() {
    }

    public Duiuser(String pageCount) {

        PageCount = pageCount;
    }
}
