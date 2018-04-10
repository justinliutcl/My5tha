package InternetUser.shopcar;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/5/22.
 */
public class GoodsOrderUser {
    private double Freigth;
    private List<GoodsOrderCoupon> CouponsView;

    public double getFreigth() {
        return Freigth;
    }

    public void setFreigth(double freigth) {
        Freigth = freigth;
    }

    public List<GoodsOrderCoupon> getCouponsView() {
        return CouponsView;
    }
    public void setCouponsView(List<GoodsOrderCoupon> couponsView) {
        CouponsView = couponsView;
    }

    public GoodsOrderUser() {

    }

    public GoodsOrderUser(double freigth, List<GoodsOrderCoupon> couponsView) {

        Freigth = freigth;
        CouponsView = couponsView;
    }
}
