package InternetUser.A_Home;

import java.util.List;

import InternetUser.O_other.OtherGoodUser;

/**
 * Created by 不爱白菜 on 2016/4/5.
 */
public class SelectClassUser {
    private List<SelectMesItem>Types;
    private List<SelectClassBrandsItem>Brands;
    private List<OtherGoodUser>List;
    private String PageCount;

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public java.util.List<SelectMesItem> getTypes() {
        return Types;
    }

    public void setTypes(java.util.List<SelectMesItem> types) {
        Types = types;
    }

    public java.util.List<SelectClassBrandsItem> getBrands() {
        return Brands;
    }

    public void setBrands(java.util.List<SelectClassBrandsItem> brands) {
        Brands = brands;
    }

    public java.util.List<OtherGoodUser> getList() {
        return List;
    }

    public void setList(java.util.List<OtherGoodUser> list) {
        List = list;
    }

    public SelectClassUser() {

    }

    public SelectClassUser(java.util.List<SelectMesItem> types, java.util.List<SelectClassBrandsItem> brands, java.util.List<OtherGoodUser> list) {

        Types = types;
        Brands = brands;
        List = list;
    }
}
