package httpConnection;

/**
 * Created by baicai on 2016/2/24.
 */
public interface Path {
    String HOST="http://";
//    String HOST="https://";
    //线上
//    String ip="api.5tha.com/";
//    String ALIPAYCHONG_PATH="https://m.5tha.com/Pay/RechargeAlipayNotify";
//    String ALIPAYPAY_PATH="https://m.5tha.com/Pay/AlipayNotify";
//    String WEPAY_PATH="https://api.5tha.com/v1/Pay/WxPay";

    //外网测试
    String ip="apitest.5tha.com/";
    String ALIPAYCHONG_PATH="https://wx.5tha.com/Pay/RechargeAlipayNotify";
    String ALIPAYPAY_PATH="https://wx.5tha.com/Pay/AlipayNotify";
    String WEPAY_PATH="https://apitest.5tha.com/v1/Pay/WxPay";

    //版本号
    String VERSION="3.03";

    String UPDATA_PATH="v1/Basic/LineEdition?edtionType=2";
    String INDIVIDUAL_PATH="v1/Member/Index?memberid=";
    String SIGN_PATH="v1/Member/SignIn";
    String PHOTOUP_PATH="v1/Member/Avatar";
    String RegistPHONE_PATH="v1/Register/SendCode?Loginname=";
    String REGIST_PATH="v1/Register/Register";
    String LOGIN_PATH="v1/login/login";
    String THRID_LOGIN_PATH="v1/Login/ThirdLogin";
    String TRAVEL_PATH="v1/Finance/DreamFundList";
    String COMMUNITION_PATH="v1/finance/CommissionIndex?memberid=";
    String GWB_DUIHUAN_PATH="v1/Finance/ExchangeList";
    String GWB_CHANGZHI_PATH="v1/Finance/RechargeRecord";
    String INCOME_PATH="v1/finance/CommissionInComeList";
    String WAITINCOME_PATH="v1/Member/PreCommissionRecordList";
    String TIXIANHIS_PATH="v1/Finance/CommissionWithdrawList";
    String TIXIANDETAILS_PATH="v1/finance/CommissionWithdrawDetail";
    String REFERRER_PATH="v1/finance/GetReferrerList";
    String TIXIAN_PATH="v1/finance/SetWithdrawCash";
    String GIFTCARD_PATH="v1/finance/Exchange";
    String INOUTHIS_PATH="v1/finance/GetTransactionRecord";
    String INOUT_SELECT_PATH="v1/finance/search";
    String COUPONNUSE_PATH="v1/finance/CouponList";
    String COUPONHUSE_PATH="v1/finance/UsedCouponList";
    String COUPONPASTUSE_PATH="v1/finance/OldCouponList";
    String ALLORDER_PATH="v1_1/order/getorders";
    String ALLORDER2_PATH="v1/order/getorders";
    String CHILDRENORDER_PATH="v1/order/getsuborders";
    String SETING_PATH="v1/member/PersonalSetting";
    String SMS_PATH="v1/member/SendMsg?phone=";
    String CHANGE2OLDPHONE="v1/member/VerifyMsg";
    String CHANGE2NEWPHONE="v1/member/ModifyPhone";
    String BANDPHONE_PATH="v1/Register/ThirdLoginPost";
    String CHANGE2NAME_PATH="v1/member/ModifyUserName";
    String SETSEX_PATH="v1/member/ModifySex";
    String CHANGE_CODE_PATH="v1/member/ModifyPwd";
    String CHANGE_FORGETCODE_PATH="v1/member/ModifyPass";
    String ADDRESSLIST_PATH="v1/Address/List";
    String ADDRESSDELET_PATH="v1/Address/Del";
    String ADDRESS_SETDEFAULT_PATH="v1/Address/SetDefault";
    String ADDRESS_SAVE_PATH="v1/Address/SetAddress";
    String POPULAZE_PATH="v1/member/Registration";
    String COLLECT_PATH="v1/Member/MyCollectionList";
    String COLLECT_DELETE_PATH="v1/Member/CancelCollection";
    String REALNAME_PATH="v1/Member/MemberReal";
    String REALNAME_IMGUPLOAD_PATH="v1/Member/MemberRealPost";
    String REALNAME_HAVEIMGUPLOAD_PATH="v1/Member/MemberRealModifyPost";


    String ORDERDETAIL_PATH="v1/order/orderdetail";
    String ORDERDETAIL_DELETE2RECYCLE_PATH ="v1/order/DeleteToRecycle";
    String ORDERDETAIL_BESURE_PATH ="v1/order/ConfirmOrder";

    String ORDERDELETE_PATH="v1/order/deleteorder";
    String ORDERBACK_PATH="v1/order/OrderBack";
    String ORDERSELECT_PATH="v1/order/OrderSearch";



    String ADVERTISMENT_PATH="v1/Home/StartAppAd";
    String A_HOME_TITLE="v1/Home/FirstType";
    String HOME_INDEX_PATH="v1/Home/Index";

    String O_OTHER_PATH="v1/Home/FirstTypeIndex";

    String TEXT_PATH="v1_1/Product/Detail";
    String COLLECTTEXT_PATH="v1/Product/CollectionDetail";
    String TEXT_EVALUTE_PATH="v1/Product/EvaluteList";
    String TEXT_COLLECT_PATH="v1/Product/Collect";
    String TEXT_ADDSHOPCAR_PATH="v1/Cart/AddCart";
    String TEXT_PINPAI_PATH="v1/Product/Brand";
    String TEXT_STARDID_PATH="v1/Product/GetStandard";


    String EVALUATE_PHOTOT_PATH="v1/Order/Avatar";
    String EVALUATE_COMMIT_PATH="v1/order/Evaluate";

    String HOSTSELECT_ITEM_PATH="v1/Home/Types";

    String CLASSSELECT_PATH="v1/Search/SearchResult";

    String SHOPCARLIST_PATH="v1/Cart/Cart";
    String SHOPCAR_REMOVEGOODS_PATH="v1/Cart/RemoveCart";
    String SHOPCAR_NUMCHANGE_PATH="v1/Cart/ModifyCartNumber";
    String SHOPCAR_FENDAN_PATH="v1/cart/OrderDivides";
    String SHOPCAR_COMMIT_PATH="v1/Order/SetOrder";
    String SHOPCAR_PINGTAIPAY_PATH="v1/Pay/OwnPay";
    String LOGISTIC_DETAILS_PATH="v1/order/ViewLogistics";
    String FRIEIGHT4CHANGE_PATH="v1/Cart/GetNewOrderFreight";
    String GOODSORDERDETAIL_PATH="v1/Cart/GetCartDivideDetail";


    String BANDERDETAILS_DETAILS_PATH="v1/home/SecondTypeIndex";
    String BANDERDETAILS_SELECT_PATH="v1/home/SecondTypeSearch";


    String TUIJIAN_PATH="v1/Register/Recommend";



    String CAINIXIHUAN="v1/Product/ProductRecommend";

    String ONLINENUM_PATH="v1/Pay/RechargeNumber";

    String Zhognjiang_PATH="v1/Finance/RaffleRecordList";

}
