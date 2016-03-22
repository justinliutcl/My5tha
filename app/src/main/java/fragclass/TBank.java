package fragclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.transtion.my5th.R;

import fifthutil.LodingUtil;
import httpConnection.HttpConnectionUtil;
import httpConnection.Path;
import sharedPreferencesUtil.ShareUtil;

/**
 * Created by baicai on 2016/3/7.
 */
public class TBank extends Fragment {
    EditText name, account, tmoney, password;
    Button commit;
    String path;
    ShareUtil share;
    LodingUtil loding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_t_bank, null);
        name = (EditText) view.findViewById(R.id.tbank_name);
        account = (EditText) view.findViewById(R.id.tbank_banknum);
        tmoney = (EditText) view.findViewById(R.id.tbank_timoney);
        password = (EditText) view.findViewById(R.id.tbank_paycode);
        commit = (Button) view.findViewById(R.id.tbank_tixian);
        path= Path.HOST+Path.ip+Path.TIXIAN_PATH;
        share=ShareUtil.getInstanse(getActivity());
        loding=new LodingUtil(getActivity());
        setListener();
        return view;
    }

    private void setListener() {
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }
    public void submit(){
        loding.showloadingbutton();
        String Account=account.getText().toString();
        String Name=name.getText().toString();
        String PayPwd=password.getText().toString();
        String Amount=tmoney.getText().toString();
        HttpConnectionUtil.getJsonJsonwithDialog(getActivity(), path, new String[]{"MemberId","Account","Name","PayPwd","WithdrawCashType","Amount"},
                new String[]{share.getMemberID(),Account,Name,PayPwd,"1",Amount},loding, new HttpConnectionUtil.OnJsonCall() {
            @Override
            public void JsonCallBack(String str) {
                loding.disloadingbutton("提现成功");
            }
        });
    }
}
