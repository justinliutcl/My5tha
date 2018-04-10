package InternetUser.A_Home;

import java.util.List;

import InternetUser.O_other.OtherGoodUser;

/**
 * Created by 不爱白菜 on 2016/4/14.
 */
public class BanderDetailsUser {
    private AdvertisementItem AdvertisementView;
    private String PageCount;
    private List<BrandViewItem>BrandViewList;
    private List<OtherGoodUser>List;
    private List<ThirdTypesItem>ThirdTypes;

    public java.util.List<ThirdTypesItem> getThirdTypes() {
        return ThirdTypes;
    }

    public void setThirdTypes(java.util.List<ThirdTypesItem> thirdTypes) {
        ThirdTypes = thirdTypes;
    }

    public AdvertisementItem getAdvertisementView() {
        return AdvertisementView;
    }

    public void setAdvertisementView(AdvertisementItem advertisementView) {
        AdvertisementView = advertisementView;
    }

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public java.util.List<BrandViewItem> getBrandViewList() {
        return BrandViewList;
    }

    public void setBrandViewList(java.util.List<BrandViewItem> brandViewList) {
        BrandViewList = brandViewList;
    }

    public java.util.List<OtherGoodUser> getList() {
        return List;
    }

    public void setList(java.util.List<OtherGoodUser> list) {
        List = list;
    }

    public BanderDetailsUser() {

    }

    public BanderDetailsUser(AdvertisementItem advertisementView, String pageCount, java.util.List<BrandViewItem> brandViewList, java.util.List<OtherGoodUser> list) {

        AdvertisementView = advertisementView;
        PageCount = pageCount;
        BrandViewList = brandViewList;
        List = list;
    }
}
