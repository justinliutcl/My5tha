package InternetUser.O_other;

import java.util.List;

import InternetUser.A_Home.TopImgItem;

/**
 * Created by baicai on 2016/3/27.
 */
public class OtherUser {
    private List<TopImgItem> AdvertisementViewList;
    private List<OtherGoodUser>List;
    private List<AdverImg> SecondProductTypeList;
    private List<TopImgItem> HotActivityList;

    public java.util.List<AdverImg> getSecondProductTypeList() {
        return SecondProductTypeList;
    }

    public void setSecondProductTypeList(java.util.List<AdverImg> secondProductTypeList) {
        SecondProductTypeList = secondProductTypeList;
    }

    public java.util.List<TopImgItem> getHotActivityList() {
        return HotActivityList;
    }

    public void setHotActivityList(java.util.List<TopImgItem> hotActivityList) {
        HotActivityList = hotActivityList;
    }

    private String PageCount;

    public java.util.List<TopImgItem> getAdvertisementViewList() {
        return AdvertisementViewList;
    }

    public void setAdvertisementViewList(java.util.List<TopImgItem> advertisementViewList) {
        AdvertisementViewList = advertisementViewList;
    }

    public java.util.List<OtherGoodUser> getList() {
        return List;
    }

    public void setList(java.util.List<OtherGoodUser> list) {
        List = list;
    }

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public OtherUser() {

    }

    public OtherUser(java.util.List<TopImgItem> advertisementViewList, java.util.List<OtherGoodUser> list, String pageCount) {

        AdvertisementViewList = advertisementViewList;
        List = list;
        PageCount = pageCount;
    }
}
