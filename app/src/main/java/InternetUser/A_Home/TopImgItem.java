package InternetUser.A_Home;

/**
 * Created by baicai on 2016/3/23.
 */
public class TopImgItem {
private String ImgSrc;
    private String Url;

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

    public TopImgItem() {

    }

    public TopImgItem(String imgSrc, String url) {

        ImgSrc = imgSrc;
        Url = url;
    }
}
