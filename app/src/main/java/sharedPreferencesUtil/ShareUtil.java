package sharedPreferencesUtil;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by baicai on 2016/2/22.
 */
public class ShareUtil {
    private static SharedPreferences sp=null;
    private static ShareUtil shareUtil=null;
    public static ShareUtil getInstanse(Context context){
        if (shareUtil==null)
            shareUtil=new ShareUtil(context);
        return shareUtil;
    }
    private  ShareUtil(Context context){
        if (sp==null)
           sp=context.getSharedPreferences("fifth",Context.MODE_PRIVATE);
    }

    public void setValue(boolean flage,String name,String code){
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("flage",flage);
        editor.putString("name", name);
        editor.putString("code", code);
        editor.commit();
    }

    public boolean getFrag(){
        return sp.getBoolean("flage",false);
    }
    public String getName(){
        return sp.getString("name", "");
    }
    public String getCode(){
        return sp.getString("code", "");
    }

    public void setImg(String path){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("path", path);
        editor.commit();
    }
    public String getImgPath(){
        return sp.getString("path", null);
    }
    public void setImgUrl(String url){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("url", url);
        editor.commit();
    }

    public String getImgUrl(){
        return sp.getString("url", "");
    }

    public void setHomeJson(String json){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("home",json);
        editor.commit();
    }

    public String getHomeJson(){
        return sp.getString("home","");
    }

    public void setShopJson(String json){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("shop",json);
        editor.commit();
    }

    public String getShopJson(){
        return sp.getString("shop","");
    }

    public void setCommunityJson(String json){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("community",json);
        editor.commit();
    }

    public String getCommunityJson(){
        return sp.getString("community","");
    }

    public void setIndividualJson(String json){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("individual",json);
        editor.commit();
    }

    public String getIndividualJson(){
        return sp.getString("individual","");
    }

    public void setSetingJson(String json){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("seting",json);
        editor.commit();
    }

    public String getSetingJson(){
        return sp.getString("seting","");
    }

    public void setMemberID(String id){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("memberId",id);
        editor.commit();
    }

    public String getMemberID(){
        return sp.getString("memberId","");
    }

    public void clear(){
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        editor.commit();
    }
}
