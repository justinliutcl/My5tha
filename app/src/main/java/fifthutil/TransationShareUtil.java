package fifthutil;

import android.content.Context;
import android.content.SharedPreferences;

import httpConnection.Path;

/**
 * Created by 不爱白菜 on 2016/5/28.
 */
public class TransationShareUtil {
    private static SharedPreferences sp=null;
    private static TransationShareUtil shareUtil=null;
    public static TransationShareUtil getInstanse(Context context){
        if (shareUtil==null)
            shareUtil=new TransationShareUtil(context);
        return shareUtil;
    }
    private  TransationShareUtil(Context context){
        if (sp==null)
            sp=context.getSharedPreferences("fifthwelcome",Context.MODE_PRIVATE);
    }
    public void setValue(boolean flage){
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("flage",flage);
        editor.commit();
    }

    public boolean getFrag(){
        return sp.getBoolean("flage",false);
    }

    public void setVersion(String version){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("version", version);
        editor.commit();
    }

    public String getversion(){
        return sp.getString("version", Path.VERSION);
    }

}
