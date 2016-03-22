package InternetUser.Item;

/**
 * Created by baicai on 2016/3/7.
 */
public class DuiItem {
    private String CardType;
    private String FaceValue;
    private String OperateTime;

    public DuiItem() {
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getFaceValue() {
        return FaceValue;
    }

    public void setFaceValue(String faceValue) {
        FaceValue = faceValue;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }

    public DuiItem(String cardType, String faceValue, String operateTime) {

        CardType = cardType;
        FaceValue = faceValue;
        OperateTime = operateTime;
    }
}
