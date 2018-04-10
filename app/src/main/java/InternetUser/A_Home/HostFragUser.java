package InternetUser.A_Home;

import java.util.List;

/**
 * Created by baicai on 2016/3/24.
 */
public class HostFragUser {
    private List<TopImgItem>AdvertisementViewList;
    private List<QqtmUser>PeriodViewList;
    private List<TopImgItem>HotActivityList;
    private List<JxdxUser>BrandViewList;

    public HostFragUser(List<TopImgItem> advertisementViewList, List<QqtmUser> periodViewList, List<TopImgItem> hotActivityList, List<JxdxUser> brandViewList) {
        AdvertisementViewList = advertisementViewList;
        PeriodViewList = periodViewList;
        HotActivityList = hotActivityList;
        BrandViewList = brandViewList;
    }

    public List<JxdxUser> getBrandViewList() {
        return BrandViewList;
    }

    public void setBrandViewList(List<JxdxUser> brandViewList) {
        BrandViewList = brandViewList;
    }

    public List<TopImgItem> getAdvertisementViewList() {
        return AdvertisementViewList;
    }

    public void setAdvertisementViewList(List<TopImgItem> advertisementViewList) {
        AdvertisementViewList = advertisementViewList;
    }

    public List<QqtmUser> getPeriodViewList() {
        return PeriodViewList;
    }

    public void setPeriodViewList(List<QqtmUser> periodViewList) {
        PeriodViewList = periodViewList;
    }

    public List<TopImgItem> getHotActivityList() {
        return HotActivityList;
    }

    public void setHotActivityList(List<TopImgItem> hotActivityList) {
        HotActivityList = hotActivityList;
    }


    public HostFragUser() {

    }
}
