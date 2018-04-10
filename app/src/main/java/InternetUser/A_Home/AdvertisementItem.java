package InternetUser.A_Home;

/**
 * Created by 不爱白菜 on 2016/4/14.
 */
public class AdvertisementItem {
    private String ImgSrc;
    private String Url;

    public AdvertisementItem() {
    }

    public AdvertisementItem(String imgSrc, String url) {

        ImgSrc = imgSrc;
        Url = url;
    }

    public String getImgSrc() {

        return ImgSrc;
    }

    public void setImgSrc(String imgSrc) {
        ImgSrc = imgSrc;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
