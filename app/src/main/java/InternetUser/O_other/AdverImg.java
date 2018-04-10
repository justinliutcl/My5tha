package InternetUser.O_other;

/**
 * Created by baicai on 2016/3/27.
 */
public class AdverImg {
    private String TypeName;
    private String Total;
    private String TypeId;
    private String ImageUrl;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
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

    public String getTypeId() {
        return TypeId;
    }

    public void setTypeId(String typeId) {
        TypeId = typeId;
    }

    public AdverImg(String typeName, String total, String typeId) {

        TypeName = typeName;
        Total = total;
        TypeId = typeId;
    }

    public AdverImg() {

    }
}
