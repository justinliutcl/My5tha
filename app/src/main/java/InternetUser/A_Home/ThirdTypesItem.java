package InternetUser.A_Home;

/**
 * Created by 不爱白菜 on 2016/4/15.
 */
public class ThirdTypesItem {
    private String TypeId;
    private String TypeName;
    private String Total;
    private boolean flage=false;

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

    public String getTypeId() {
        return TypeId;
    }

    public void setTypeId(String typeId) {
        TypeId = typeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public ThirdTypesItem() {

    }

    public ThirdTypesItem(String typeId, String typeName, String total,boolean flage) {

        TypeId = typeId;
        TypeName = typeName;
        Total = total;
        this.flage=flage;
    }
}
