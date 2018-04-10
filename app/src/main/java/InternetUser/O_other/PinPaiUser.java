package InternetUser.O_other;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/3/30.
 */
public class PinPaiUser {
    private List<OtherGoodUser> List;
    private String BrandName;
    private String BrandLogo;
    private String Description;
    private String BrandImage;
    private String PageCount;
    private String StateFlag;

    public String getStateFlag() {
        return StateFlag;
    }

    public void setStateFlag(String stateFlag) {
        StateFlag = stateFlag;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public PinPaiUser(List<OtherGoodUser> list, String brandName, String brandLogo, String description, String brandImage, String pageCount) {

        this.List = list;
        BrandName = brandName;
        BrandLogo = brandLogo;
        Description = description;
        BrandImage = brandImage;
        PageCount = pageCount;
    }

    public String getPageCount() {
        return PageCount;
    }

    public PinPaiUser() {
    }


    public List<OtherGoodUser> getList() {
        return List;
    }

    public void setList(List<OtherGoodUser> list) {
        this.List = list;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getBrandLogo() {
        return BrandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        BrandLogo = brandLogo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getBrandImage() {
        return BrandImage;
    }

    public void setBrandImage(String brandImage) {
        BrandImage = brandImage;
    }
}
