package InternetUser;

import java.util.List;

/**
 * Created by baicai on 2016/3/4.
 */
public class TravelUser {
    private String DreamFund;
    private String RecordCount;
    private String PageCount;
    private List<TravelItem>list;

    public List<TravelItem> getList() {
        return list;
    }

    public void setList(List<TravelItem> list) {
        this.list = list;
    }

    public TravelUser(String dreamFund, String recordCount, String pageCount, List<TravelItem> list) {

        DreamFund = dreamFund;
        RecordCount = recordCount;
        PageCount = pageCount;
        this.list = list;
    }

    public String getDreamFund() {
        return DreamFund;
    }

    public void setDreamFund(String dreamFund) {
        DreamFund = dreamFund;
    }

    public String getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(String recordCount) {
        RecordCount = recordCount;
    }

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public TravelUser() {
    }

    public TravelUser(String dreamFund, String recordCount, String pageCount) {

        DreamFund = dreamFund;
        RecordCount = recordCount;
        PageCount = pageCount;
    }



}
