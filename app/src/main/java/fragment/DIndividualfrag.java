package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.DIndividualActivity.AddressmanagerActivity;
import com.example.transtion.my5th.DIndividualActivity.CollectActivity;
import com.example.transtion.my5th.DIndividualActivity.DMAcoupon;
import com.example.transtion.my5th.DIndividualActivity.DMAgodActivity;
import com.example.transtion.my5th.DIndividualActivity.DMAgwbActivity;
import com.example.transtion.my5th.DIndividualActivity.DMAtravelActivity;
import com.example.transtion.my5th.DIndividualActivity.DSAllActivity;
import com.example.transtion.my5th.DIndividualActivity.PopulazeActivity;
import com.example.transtion.my5th.Setting.DSetingActivity;
import com.example.transtion.my5th.DIndividualActivity.DWdqbActivity;
import com.example.transtion.my5th.R;
import com.example.transtion.my5th.Setting.MysetingActivity;
import com.example.transtion.my5th.SignActivity;

import InternetUser.IndividualHost;
import InternetUser.LoginUser;
import customUI.MyImageView;
import fifthutil.ImageUtil;
import fifthutil.JumpUtil;
import fifthutil.LodingUtil;
import fifthutil.OptsBitmapUtil;
import fifthutil.PhotoSelectUtil;
import fifthutil.cache.BitmapUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/2/22.
 */
public class DIndividualfrag extends Fragment implements View.OnClickListener{
    MyImageView userImg;

    ImageView myseting;

    TextView name,type,csiday,travelMoney,signday,signdayleft,signdayright,shopmoney,commission,travelMoneySum,coupon,shoukuanSum,fahuoSum,shouhuoSum,pingjiaSum;

    Button exit;

    LinearLayout layout_set, layout_address, layout_popularized, layout_collect, layout_sign,layout_signmes, layout_signmesnor,
            layout_shopmoney,layout_commission,layout_travelmoneySum,layout_coupon,layout_wdqb,layout_allorder,layout_title;

    FrameLayout layout_shoukuan,layout_fahuo,layout_shouhuo,layout_pingjia;

    IndividualHost host;

    ShareUtil share;

    String memberid;
    String oldJson;

    LodingUtil loading;

    PhotoSelectUtil photoUtil;

    String individualHostPath=Path.HOST+Path.ip+Path.INDIVIDUAL_PATH;
    String individualSign=Path.HOST+Path.ip+Path.SIGN_PATH;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_dindividual,null);

        userImg= (MyImageView) view.findViewById(R.id.individual_userimg);

        myseting= (ImageView) view.findViewById(R.id.individual_myseting);

        name= (TextView) view.findViewById(R.id.individual_name);
        type= (TextView) view.findViewById(R.id.individual_type);
        csiday= (TextView) view.findViewById(R.id.individual_CSIday);
        travelMoney= (TextView) view.findViewById(R.id.individual_travelmoney);
        signday= (TextView) view.findViewById(R.id.individual_signday);
        signdayleft= (TextView) view.findViewById(R.id.individual_signdayleft);
        signdayright= (TextView) view.findViewById(R.id.individual_signdayright);
        shopmoney= (TextView) view.findViewById(R.id.individual_shopmoney);
        commission= (TextView) view.findViewById(R.id.individual_commission);
        travelMoneySum= (TextView) view.findViewById(R.id.individual_travelmoneysum);
        coupon= (TextView) view.findViewById(R.id.individual_coupon);

        shoukuanSum= (TextView) view.findViewById(R.id.individual_shoukuannum);
        fahuoSum= (TextView) view.findViewById(R.id.individual_fahuonum);
        shouhuoSum= (TextView) view.findViewById(R.id.individual_shouhuonum);
        pingjiaSum= (TextView) view.findViewById(R.id.individual_pingjianum);

        exit= (Button) view.findViewById(R.id.individual_exit);

        layout_signmes= (LinearLayout) view.findViewById(R.id.individual_layout_signmes);
        layout_signmesnor = (LinearLayout) view.findViewById(R.id.individual_layout_signmesnor);

        layout_set = (LinearLayout) view.findViewById(R.id.individual_layout_set);
        layout_address = (LinearLayout) view.findViewById(R.id.individual_layout_address);
        layout_popularized = (LinearLayout) view.findViewById(R.id.individual_layout_popularize);
        layout_collect = (LinearLayout) view.findViewById(R.id.individual_layout_collect);
        layout_title = (LinearLayout) view.findViewById(R.id.individual_layout_title);

        layout_sign= (LinearLayout) view.findViewById(R.id.individual_layout_sign);

        layout_shopmoney= (LinearLayout) view.findViewById(R.id.individual_layout_shopmoney);
        layout_commission= (LinearLayout) view.findViewById(R.id.individual_layout_commission);
        layout_travelmoneySum= (LinearLayout) view.findViewById(R.id.individual_layout_travelmoneysum);
        layout_coupon= (LinearLayout) view.findViewById(R.id.individual_layout_coupon);
        layout_wdqb= (LinearLayout) view.findViewById(R.id.individual_wdqb);
        layout_allorder= (LinearLayout) view.findViewById(R.id.individual_layout_allorder);

        layout_shoukuan= (FrameLayout) view.findViewById(R.id.individual_layout_shoukuannum);
        layout_fahuo= (FrameLayout) view.findViewById(R.id.individual_layout_fahuonum);
        layout_shouhuo= (FrameLayout) view.findViewById(R.id.individual_layout_shouhuonum);
        layout_pingjia= (FrameLayout) view.findViewById(R.id.individual_layout_pingjianum);

        share=ShareUtil.getInstanse(getActivity());
        memberid=share.getMemberID();

        photoUtil=new PhotoSelectUtil(getActivity(),this);

        shoukuanSum.setVisibility(View.GONE);
        fahuoSum.setVisibility(View.GONE);
        shouhuoSum.setVisibility(View.GONE);
        pingjiaSum.setVisibility(View.GONE);
        loading=new LodingUtil(getActivity());
        setListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setOldview();
        getJson();
    }

    private void setOldview() {
        String path=share.getImgPath();
        if(path!=null)
            userImg.setImageBitmap(OptsBitmapUtil.calculatorBitmap(path, userImg));
        oldJson=share.getIndividualJson();
        if(oldJson.length()>10){
            setView(oldJson);
        }else{
            loading.showShapeLoding();
        }
    }

    private void setListener() {
        userImg.setOnClickListener(this);
        layout_set.setOnClickListener(this);
        layout_address.setOnClickListener(this);
        layout_popularized.setOnClickListener(this);
        layout_collect.setOnClickListener(this);
        layout_sign.setOnClickListener(this);
        layout_shopmoney.setOnClickListener(this);
        layout_commission.setOnClickListener(this);
        layout_travelmoneySum.setOnClickListener(this);
        layout_coupon.setOnClickListener(this);
        layout_shoukuan.setOnClickListener(this);
        layout_fahuo.setOnClickListener(this);
        layout_shouhuo.setOnClickListener(this);
        layout_pingjia.setOnClickListener(this);
        layout_wdqb.setOnClickListener(this);
        layout_allorder.setOnClickListener(this);
        exit.setOnClickListener(this);
        myseting.setOnClickListener(this);
        layout_title.setOnClickListener(this);
    }

    private void getJson() {
        HttpConnectionUtil.getGetJson(getActivity(), individualHostPath + memberid, null, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loading.disShapeLoding();
                share.setIndividualJson(str);
                if (str != oldJson)
                    setView(str);
            }
        });
    }

    private void getimg(String url){
        userImg.setTag(url);
        BitmapUtil.setImg(userImg);
//        HttpConnectionUtil.getGetImg(getActivity(), , new HttpConnectionUtil.OnimgCall() {
//            @Override
//            public void imgCallBack(Bitmap bitmap) {
//                userImg.setImageBitmap(bitmap);
//                share.setImgUrl(url);
//            }
//        });
    }

    private void setView(String str) {
        Log.i("lifetime",str);
        host=HttpConnectionUtil.getIndividualHost(str);
        if(Integer.parseInt(host.getContinuous())!=0){
            layout_signmesnor.setVisibility(View.GONE);
            layout_signmes.setVisibility(View.VISIBLE);
            signday.setText(host.getContinuous());
        }else {
            layout_signmesnor.setVisibility(View.VISIBLE);
            layout_signmes.setVisibility(View.GONE);
        }
        if (host.isSignIn()){
            signday.setVisibility(View.VISIBLE);
            signdayleft.setText("已签到");
            signdayright.setVisibility(View.VISIBLE);
            layout_sign.setClickable(false);
        }else{
            signday.setVisibility(View.GONE);
            signdayleft.setText("签到");
            signdayright.setVisibility(View.GONE);
            layout_sign.setClickable(true);
        }
        if(!share.getImgUrl().equals("http:"+host.getMemberAvatarImg()))
            getimg("http:"+host.getMemberAvatarImg());
        csiday.setText(host.getContinuous());
        travelMoney.setText(host.getTomorrowDrame());
        shopmoney.setText(host.getMyGwb()+"");
        commission.setText(host.getMyCommission()+"");
        travelMoneySum.setText(host.getDreamFund());
        coupon.setText(host.getMyCouponsNum());
        name.setText(host.getNickName());
        switch (host.getMemberType()){
            case "1":
                type.setText("普通会员");
                break;
            case "3":
                type.setText("俱乐部会员");
                break;
        }

        setNum();

    }

    private void setNum(){
        showNum(host.getUnpaidNumber(), shoukuanSum);
        showNum(host.getUnDeliveryNumber(), fahuoSum);
        showNum(host.getUnReceiveNumber(), shouhuoSum);
        showNum(host.getMyShareThreaNum(), pingjiaSum);
        loading.disShapeLoding();
    }

    private void showNum(int sum,TextView text) {
        if(sum>0){
            text.setVisibility(View.VISIBLE);
            text.setText(sum+"");
        }
        else{
            text.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.individual_userimg:
                photoUtil.setimg(userImg);
                photoUtil.showDialog();
                break;
            case R.id.individual_layout_sign:
                sign();
                break;
            case R.id.individual_wdqb:
                JumpUtil.jumpWithValue(getActivity(), DWdqbActivity.class, new String[]{"gwb", "god", "travel"}, new String[]{host.getMyGwb() + "", host.getMyCommission() + "", host.getDreamFund()}, true);
                break;
            case R.id.individual_layout_shopmoney:
                JumpUtil.jumpWithValue(getActivity(), DMAgwbActivity.class,new String[]{"gwb"},new String[]{host.getMyGwb()+""},true);
                break;
            case R.id.individual_layout_commission:
                JumpUtil.jumpWithValue(getActivity(), DMAgodActivity.class,new String[]{"god"},new String[]{host.getMyCommission()+""},true);
                break;
            case R.id.individual_layout_travelmoneysum:
                JumpUtil.jumpWithValue(getActivity(), DMAtravelActivity.class,new String[]{"travel"},new String[]{host.getDreamFund()},true);
                break;
            case R.id.individual_layout_coupon:
                JumpUtil.jumpWithValue(getActivity(), DMAcoupon.class,new String[]{},new String[]{},true);
                break;
            case R.id.individual_layout_allorder:
                    JumpUtil.jumpWithValue(getActivity(), DSAllActivity.class,new String[]{"count"},new String[]{"0"},true);
                break;
            case R.id.individual_layout_shoukuannum:
                JumpUtil.jumpWithValue(getActivity(), DSAllActivity.class,new String[]{"count"},new String[]{"1"},true);
                break;
            case R.id.individual_layout_fahuonum:
                JumpUtil.jumpWithValue(getActivity(), DSAllActivity.class,new String[]{"count"},new String[]{"3"},true);
                break;
            case R.id.individual_layout_shouhuonum:
                JumpUtil.jumpWithValue(getActivity(), DSAllActivity.class,new String[]{"count"},new String[]{"2"},true);
                break;
            case R.id.individual_layout_pingjianum:
                JumpUtil.jumpWithValue(getActivity(), DSAllActivity.class, new String[]{"count"}, new String[]{"4"}, true);
                break;
            case R.id.individual_layout_set:
                JumpUtil.jump(getActivity(), DSetingActivity.class, true);
                break;
            case R.id.individual_layout_address:
                JumpUtil.jump(getActivity(), AddressmanagerActivity.class,true);
                break;
            case R.id.individual_layout_popularize:
                JumpUtil.jump(getActivity(), PopulazeActivity.class,true);
                break;
            case R.id.individual_layout_collect:
                JumpUtil.jump(getActivity(), CollectActivity.class,true);
                break;
            case R.id.individual_myseting:
                JumpUtil.jump(getActivity(), MysetingActivity.class,true);
                break;
            case R.id.individual_layout_title:
                JumpUtil.jump(getActivity(), DSetingActivity.class,true);
                break;
            case R.id.individual_exit:
                share.clear();
                ImageUtil util=new ImageUtil(getActivity());
                util.clear();
                JumpUtil.jump2hdown(getActivity(), SignActivity.class,true);
                break;
            default:
                break;
        }
    }

    private void sign() {
////        host.getMemberId()
        loading.showloadingbutton();
        HttpConnectionUtil.getJsonJsonwithDialog(getActivity(), individualSign, new String[]{"memberId"}, new String[]{memberid}, loading,
        new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                dojson(str);
                loading.disloadingbutton("签到成功");
                Log.i("lifeweeker", str);
            }
        });
    }

    public void dojson(String str){
        LoginUser lu=HttpConnectionUtil.getLoginMes(str);
        layout_signmesnor.setVisibility(View.GONE);
        layout_signmes.setVisibility(View.VISIBLE);
        signday.setText((Integer.parseInt(host.getContinuous()) + lu.getData()) + "");
        signday.setVisibility(View.VISIBLE);
        signdayleft.setText("已签到");
        signdayright.setVisibility(View.VISIBLE);

        csiday.setText((Integer.parseInt(host.getContinuous())+lu.getData())+"");
        switch (lu.getData()){
            case 2:
                travelMoney.setText("3");
                break;
            case 3:
                travelMoney.setText("7");
                break;
            case 7:
                travelMoney.setText("8");
                break;
            case 8:
                travelMoney.setText("9");
                break;
            case 9:
                travelMoney.setText("10");
                break;
            case 10:
                travelMoney.setText("10");
                break;
        }


        layout_sign.setClickable(false);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoUtil.forresult(requestCode,resultCode,data);
    }
}
