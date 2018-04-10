package InternetUser.O_other;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/3/29.
 */
public class EvaluateUser {
    private String PageCount;
    private List<EvaluateItem> EvaluateList;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public List<EvaluateItem> getEvaluateList() {
        return EvaluateList;
    }

    public void setEvaluateList(List<EvaluateItem> evaluateList) {
        EvaluateList = evaluateList;
    }

    public EvaluateUser() {

    }

    public EvaluateUser(String pageCount, List<EvaluateItem> evaluateList) {

        PageCount = pageCount;
        EvaluateList = evaluateList;
    }
}
