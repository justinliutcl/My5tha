package InternetUser.shopcar;

/**
 * Created by 不爱白菜 on 2016/4/8.
 */
public class ShopAddress {
    private String AddressId;
    private String Name;
    private String Address;
    private String Mobel;

    public ShopAddress(String addressId, String name, String address, String mobel) {
        AddressId = addressId;
        Name = name;
        Address = address;
        Mobel = mobel;
    }

    public ShopAddress() {

    }

    public String getAddressId() {
        return AddressId;
    }

    public void setAddressId(String addressId) {
        AddressId = addressId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobel() {
        return Mobel;
    }

    public void setMobel(String mobel) {
        Mobel = mobel;
    }
}
