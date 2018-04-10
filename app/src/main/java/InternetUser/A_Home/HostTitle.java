package InternetUser.A_Home;

/**
 * Created by baicai on 2016/3/23.
 */
public class HostTitle {
    private String TypeName;
    private String TypeCode;
    private String ImageGray;
    private String ImageLight;

    public String getImageGray() {
        return ImageGray;
    }

    public void setImageGray(String imageGray) {
        ImageGray = imageGray;
    }

    public String getImageLight() {
        return ImageLight;
    }

    public void setImageLight(String imageLight) {
        ImageLight = imageLight;
    }

    public HostTitle() {
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(String typeCode) {
        TypeCode = typeCode;
    }

    public HostTitle(String typeName, String t00001) {
        TypeName = typeName;
        TypeCode = t00001;
    }
}
