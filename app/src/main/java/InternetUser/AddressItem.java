package InternetUser;

/**
 * Created by baicai on 2016/3/20.
 */
public class AddressItem {
    private String Id;
    private String Name;
    private String Address;
    private String Phone;
    private String Zipcode;
    private boolean IsDefault;
    private String IdCard;
    private String Province;
    private String City;
    private String Area;
    private String CityCode;
    private String ProvinceCode;
    private String AreaCode;

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getProvinceCode() {
        return ProvinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        ProvinceCode = provinceCode;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public AddressItem(String id, String name, String address, String phone, String zipcode, boolean isDefault, String idCard, String province, String city, String area, String areaCode) {

        Id = id;
        Name = name;
        Address = address;
        Phone = phone;
        Zipcode = zipcode;
        IsDefault = isDefault;
        IdCard = idCard;
        Province = province;
        City = city;
        Area = area;
        AreaCode = areaCode;
    }

    public AddressItem() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public boolean isDefault() {
        return IsDefault;
    }

    public void setIsDefault(boolean isDefault) {
        IsDefault = isDefault;
    }

}
