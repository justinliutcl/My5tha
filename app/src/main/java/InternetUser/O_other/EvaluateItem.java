package InternetUser.O_other;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/3/28.
 */
public class EvaluateItem {
    private String NickName;
    private String ThreadId;
    private String EvelateTime;
    private String HeadPortrait;
    private String ShoppingTime;
    private String StarValue;
    private String ProductId;
    private String ThreaText;

    public String getThreaText() {
        return ThreaText;
    }

    public void setThreaText(String threaText) {
        ThreaText = threaText;
    }

    private List<DetailItem> ImageList;

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getThreadId() {
        return ThreadId;
    }

    public void setThreadId(String threadId) {
        ThreadId = threadId;
    }

    public String getEvelateTime() {
        return EvelateTime;
    }

    public void setEvelateTime(String evelateTime) {
        EvelateTime = evelateTime;
    }

    public String getHeadPortrait() {
        return HeadPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        HeadPortrait = headPortrait;
    }

    public String getShoppingTime() {
        return ShoppingTime;
    }

    public void setShoppingTime(String shoppingTime) {
        ShoppingTime = shoppingTime;
    }

    public String getStarValue() {
        return StarValue;
    }

    public void setStarValue(String starValue) {
        StarValue = starValue;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public List<DetailItem> getImageList() {
        return ImageList;
    }

    public void setImageList(List<DetailItem> imageList) {
        ImageList = imageList;
    }

    public EvaluateItem() {

    }

    public EvaluateItem(String nickName, String threadId, String evelateTime, String headPortrait, String shoppingTime, String starValue, String productId, List<DetailItem> imageList) {

        NickName = nickName;
        ThreadId = threadId;
        EvelateTime = evelateTime;
        HeadPortrait = headPortrait;
        ShoppingTime = shoppingTime;
        StarValue = starValue;
        ProductId = productId;
        ImageList = imageList;
    }

    public EvaluateItem(String nickName, String threadId, String evelateTime, String headPortrait, String shoppingTime, String starValue, String productId, String threaText, List<DetailItem> imageList) {
        NickName = nickName;
        ThreadId = threadId;
        EvelateTime = evelateTime;
        HeadPortrait = headPortrait;
        ShoppingTime = shoppingTime;
        StarValue = starValue;
        ProductId = productId;
        ThreaText = threaText;
        ImageList = imageList;
    }
}
