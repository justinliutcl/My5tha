package InternetUser.shopcar;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/5/22.
 */
public class CouponsView {
    private int 可用数量;
    private List<GoodsOrderCoupon>发放列表;

    public CouponsView() {
    }

    public CouponsView(int 可用数量, List<GoodsOrderCoupon> 发放列表) {

        this.可用数量 = 可用数量;
        this.发放列表 = 发放列表;
    }

    public int get可用数量() {

        return 可用数量;
    }

    public void set可用数量(int 可用数量) {
        this.可用数量 = 可用数量;
    }

    public List<GoodsOrderCoupon> get发放列表() {
        return 发放列表;
    }

    public void set发放列表(List<GoodsOrderCoupon> 发放列表) {
        this.发放列表 = 发放列表;
    }
}
