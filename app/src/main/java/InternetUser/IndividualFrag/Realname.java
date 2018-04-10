package InternetUser.IndividualFrag;

/**
 * Created by 不爱白菜 on 2016/5/6.
 */
public class Realname {
    private String Id;
    private String MemberId;
    private String RealName;
    private String IDCard;
    private String CardType;
    private String FrontImage;
    private String BackImage;
    private String AuditStatus;
    private String Remark;
    private String Mobile;

    public Realname() {
    }

    public Realname(String id, String memberId, String realName, String IDCard, String cardType, String frontImage, String backImage, String auditStatus, String remark, String mobile) {

        Id = id;
        MemberId = memberId;
        RealName = realName;
        this.IDCard = IDCard;
        CardType = cardType;
        FrontImage = frontImage;
        BackImage = backImage;
        AuditStatus = auditStatus;
        Remark = remark;
        Mobile = mobile;
    }

    public String getId() {

        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getFrontImage() {
        return FrontImage;
    }

    public void setFrontImage(String frontImage) {
        FrontImage = frontImage;
    }

    public String getBackImage() {
        return BackImage;
    }

    public void setBackImage(String backImage) {
        BackImage = backImage;
    }

    public String getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        AuditStatus = auditStatus;
    }


    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
