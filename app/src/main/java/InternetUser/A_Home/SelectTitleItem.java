package InternetUser.A_Home;

import java.util.List;

/**
 * Created by 不爱白菜 on 2016/4/4.
 */
public class SelectTitleItem {
    private String TypeName;
    private List<SelectMesItem>TypeShow;
   private String TypeId;

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

    public List<SelectMesItem> getTypeShow() {
        return TypeShow;
    }

    public void setTypeShow(List<SelectMesItem> typeShow) {
        TypeShow = typeShow;
    }

    public SelectTitleItem(String typeName, List<SelectMesItem> typeShow) {

        TypeName = typeName;
        TypeShow = typeShow;
    }

    public SelectTitleItem() {

    }
}
