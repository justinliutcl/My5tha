package InternetUser;

import java.text.DecimalFormat;

/**
 * Created by baicai on 2016/2/24.
 */
public class IndividualHost {
    private String MemberId;
    private String NickName;
    private String MemberType;
    private String EncryptMemberId;
    private String MemberAvatarImg;
    private int UnpaidNumber;
    private int UnDeliveryNumber;
    private int UnReceiveNumber;
    private int MyShareThreaNum;
    private int UnShareNumber;
    private double MyGwb;
    private double MyCommission;
    private String DreamFund;
    private String MyCouponsNum;
    private boolean IsSignIn;
    private String Continuous;
    private String tomorrowDrame;
    private boolean IsRefMember;

    public String getEncryptMemberId() {
        return EncryptMemberId;
    }

    public void setEncryptMemberId(String encryptMemberId) {
        EncryptMemberId = encryptMemberId;
    }

    public int getUnShareNumber() {
        return UnShareNumber;
    }

    public void setUnShareNumber(int unShareNumber) {
        UnShareNumber = unShareNumber;
    }

    public boolean isRefMember() {
        return IsRefMember;
    }

    public void setIsRefMember(boolean isRefMember) {
        IsRefMember = isRefMember;
    }

    DecimalFormat df = new DecimalFormat("#0.00");

    public IndividualHost() {
    }

    public IndividualHost(String memberId, String nickName, String memberType, String memberAvatarImg, int unpaidNumber, int unDeliveryNumber,
                          int unReceiveNumber, int myShareThreaNum, double myGwb, double myCommission, String dreamFund, String myCouponsNum,
                          boolean isSignIn, String continuous, String tomorrowDrame) {

        MemberId = memberId;
        NickName = nickName;
        MemberType = memberType;
        MemberAvatarImg = memberAvatarImg;
        UnpaidNumber = unpaidNumber;
        UnDeliveryNumber = unDeliveryNumber;
        UnReceiveNumber = unReceiveNumber;
        MyShareThreaNum = myShareThreaNum;
        MyGwb = myGwb;
        MyCommission = myCommission;
        DreamFund = dreamFund;
        MyCouponsNum = myCouponsNum;
        IsSignIn = isSignIn;
        Continuous = continuous;
        this.tomorrowDrame = tomorrowDrame;
    }

    public String getMemberId() {

        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getMemberType() {
        return MemberType;
    }

    public void setMemberType(String memberType) {
        MemberType = memberType;
    }

    public String getMemberAvatarImg() {
        return MemberAvatarImg;
    }

    public void setMemberAvatarImg(String memberAvatarImg) {
        MemberAvatarImg = memberAvatarImg;
    }

    public int getUnpaidNumber() {
        return UnpaidNumber;
    }

    public void setUnpaidNumber(int unpaidNumber) {
        UnpaidNumber = unpaidNumber;
    }

    public int getUnDeliveryNumber() {
        return UnDeliveryNumber;
    }

    public void setUnDeliveryNumber(int unDeliveryNumber) {
        UnDeliveryNumber = unDeliveryNumber;
    }

    public int getUnReceiveNumber() {
        return UnReceiveNumber;
    }

    public void setUnReceiveNumber(int unReceiveNumber) {
        UnReceiveNumber = unReceiveNumber;
    }

    public int getMyShareThreaNum() {
        return MyShareThreaNum;
    }

    public void setMyShareThreaNum(int myShareThreaNum) {
        MyShareThreaNum = myShareThreaNum;
    }

    public String getMyGwb() {
        return df.format(MyGwb);
    }

    public void setMyGwb(double myGwb) {
        MyGwb = myGwb;
    }

    public String getMyCommission() {
        return df.format(MyCommission);
    }

    public void setMyCommission(double myCommission) {
        MyCommission = myCommission;
    }

    public String getDreamFund() {
        return DreamFund;
    }

    public void setDreamFund(String dreamFund) {
        DreamFund = dreamFund;
    }

    public String getMyCouponsNum() {
        return MyCouponsNum;
    }

    public void setMyCouponsNum(String myCouponsNum) {
        MyCouponsNum = myCouponsNum;
    }

    public boolean isSignIn() {
        return IsSignIn;
    }

    public void setIsSignIn(boolean isSignIn) {
        IsSignIn = isSignIn;
    }

    public String getContinuous() {
        return Continuous;
    }

    public void setContinuous(String continuous) {
        Continuous = continuous;
    }

    public String getTomorrowDrame() {
        return tomorrowDrame;
    }

    public void setTomorrowDrame(String tomorrowDrame) {
        this.tomorrowDrame = tomorrowDrame;
    }
}
