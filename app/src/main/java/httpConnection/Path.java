package httpConnection;

/**
 * Created by baicai on 2016/2/24.
 */
public interface Path {
    String HOST="http://";
//    String ip="1.192.127.27:7777/v1/";
    String ip="api.5tha.com/v1/";
    String INDIVIDUAL_PATH="Member/Index?memberid=";
    String SIGN_PATH="Member/SignIn";
    String PHOTOUP_PATH="Member/Avatar";
    String RegistPHONE_PATH="Register/SendCode?Loginname=";
    String REGIST_PATH="Register/Register";
    String LOGIN_PATH="login/login";
    String TRAVEL_PATH="Finance/DreamFundList";
    String COMMUNITION_PATH="finance/CommissionIndex?memberid=";
    String GWB_DUIHUAN_PATH="Finance/ExchangeList";
    String GWB_CHANGZHI_PATH="Finance/RechargeRecord";
    String INCOME_PATH="finance/CommissionInComeList";
    String TIXIANHIS_PATH="Finance/CommissionWithdrawList";
    String TIXIANDETAILS_PATH="finance/CommissionWithdrawDetail";
    String REFERRER_PATH="finance/GetReferrerList";
    String TIXIAN_PATH="finance/SetWithdrawCash";
    String GIFTCARD_PATH="finance/Exchange";
    String INOUTHIS_PATH="finance/GetTransactionRecord";
    String INOUT_SELECT_PATH="finance/search";
    String COUPONNUSE_PATH="finance/CouponList";
    String COUPONHUSE_PATH="finance/UsedCouponList";
    String COUPONPASTUSE_PATH="finance/OldCouponList";
    String ALLORDER_PATH="order/getorders";
    String SETING_PATH="member/PersonalSetting";
    String SMS_PATH="member/SendMsg?phone=";
    String CHANGE2OLDPHONE="member/VerifyMsg";
    String CHANGE2NEWPHONE="member/ModifyPhone";
    String SETSEX_PATH="member/ModifySex";
    String CHANGE_CODE_PATH="member/ModifyPwd";
    String ADDRESSLIST_PATH="Address/List";
    String ADDRESSDELET_PATH="Address/Del";
    String ADDRESS_SETDEFAULT_PATH="Address/SetDefault";
    String ADDRESS_SAVE_PATH="Address/SetAddress";
    String POPULAZE_PATH="member/Registration";
    String COLLECT_PATH="Member/MyCollectionList";
    String COLLECT_DELETE_PATH="Member/CancelCollection";
    String ORDERDETAIL_PATH="order/orderdetail";
}
