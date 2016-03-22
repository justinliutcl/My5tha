package InternetUser;

import java.util.List;

import InternetUser.Item.ChongItem;

/**
 * Created by baicai on 2016/3/6.
 */
public class Chonguser {
    private String PageCount;
    private List<ChongItem>list;

    public Chonguser() {
    }

    public String getPageCount() {

        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public List<ChongItem> getList() {
        return list;
    }

    public void setList(List<ChongItem> list) {
        this.list = list;
    }

    public Chonguser(String pageCount, List<ChongItem> list) {

        PageCount = pageCount;
        this.list = list;
    }
}
