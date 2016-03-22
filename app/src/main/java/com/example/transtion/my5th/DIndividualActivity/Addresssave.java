package com.example.transtion.my5th.DIndividualActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.transtion.my5th.BaseActivity;
import com.example.transtion.my5th.R;
import com.mrwujay.cascade.model.CityModel;
import com.mrwujay.cascade.model.DistrictModel;
import com.mrwujay.cascade.model.ProvinceModel;
import com.mrwujay.cascade.service.XmlParserHandler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

public class Addresssave extends BaseActivity implements OnWheelChangedListener {
    EditText name, phone, idcard, detail, zipcode;
    TextView area;
    Button save, mBtnConfirm;
    String Province, City, Area, AreaCode;
    String path = Path.HOST + Path.ip + Path.ADDRESS_SAVE_PATH;
    LinearLayout selectarea;
    WheelView mViewProvince, mViewCity, mViewDistrict;
    AlertDialog ad;
    String areaCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresssave);
        initView();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.addresssave_user);
        phone = (EditText) findViewById(R.id.addresssave_phone);
        idcard = (EditText) findViewById(R.id.addresssave_idcard);
        detail = (EditText) findViewById(R.id.addresssave_detail);
        zipcode = (EditText) findViewById(R.id.addresssave_zipcode);
        area = (TextView) findViewById(R.id.addresssave_area);
        save = (Button) findViewById(R.id.addresssave_save);
        selectarea = (LinearLayout) findViewById(R.id.addresssave_layout_selectarea);
        Intent intent = getIntent();
        if (intent.getStringExtra("name") !=null) {
            name.setText(intent.getStringExtra("name"));
            phone.setText(intent.getStringExtra("phone"));
            idcard.setText(intent.getStringExtra("IdCard"));
            detail.setText(intent.getStringExtra("Address"));
            zipcode.setText(intent.getStringExtra("zipcode"));
            Province=intent.getStringExtra("Province");
            City=intent.getStringExtra("City");
            Area=intent.getStringExtra("Area");
            area.setText(Province+" "+City+" "+Area);
            areaCode=intent.getStringExtra("AreaCode");

        }
        setdialog();

    }

    private void setdialog() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this, R.style.dialog);
        View view = View.inflate(this, R.layout.dialog_areaselect, null);
        mViewProvince = (WheelView) view.findViewById(R.id.id_province);
        mViewCity = (WheelView) view.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) view.findViewById(R.id.id_district);
        mBtnConfirm = (Button) view.findViewById(R.id.btn_confirm);
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);
        mBtnConfirm.setOnClickListener(this);
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(this, mProvinceDatas));
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
        ab.setView(view);
        ad = ab.create();
        ad.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ad.getWindow().setWindowAnimations(R.style.dialog_updown_anim);
        ad.getWindow().setGravity(Gravity.BOTTOM);

    }

    @Override
    public void setListener() {
        selectarea.setOnClickListener(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
    }

    private void commit() {
        loding.showShapeLoding();
        String Name = name.getText().toString();
        String Mobile = phone.getText().toString();
        String IdCard = idcard.getText().toString();
        String Address = detail.getText().toString();
        String Zipcode = zipcode.getText().toString();
        HttpConnectionUtil.getJsonJsonwithDialog(this, path,
                new String[]{"memberId", "Name", "Mobile", "Zipcode", "IdCard", "Address", "Province", "City", "Area", "AreaCode"},
                new String[]{share.getMemberID(), Name, Mobile, Zipcode, IdCard, Address, Province, City, Area, AreaCode}, loding, new HttpConnectionUtil.OnJsonCall() {
                    @Override
                    public void JsonCallBack(String str) {
                        loding.disShapeLoding();
                        show("添加成功");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addresssave_layout_selectarea:
                ad.show();
                break;
            case R.id.btn_confirm:
                showSelectedResult();
                break;
        }
    }

    protected String[] mProvinceDatas;
    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();
    protected String mCurrentProviceName;
    protected String mCurrentCityName;
    protected String mCurrentDistrictName = "";
    protected String mCurrentZipCode = "";

    protected void initProvinceDatas() {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            provinceList = handler.getDataList();
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);
    }

    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    private void showSelectedResult() {
        area.setText(mCurrentProviceName + " " + mCurrentCityName + " " + mCurrentDistrictName);
        Province = mCurrentProviceName;
        City = mCurrentCityName;
        Area = mCurrentDistrictName;
        AreaCode = mCurrentZipCode;
        ad.dismiss();
        setdialog();
//		Toast.makeText(this, "选择城市:" + mCurrentProviceName + "," + mCurrentCityName + ","
//                + mCurrentDistrictName + "," + mCurrentZipCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
			updateCities();
		} else if (wheel == mViewCity) {
			updateAreas();
		} else if (wheel == mViewDistrict) {
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
		}

    }
}
