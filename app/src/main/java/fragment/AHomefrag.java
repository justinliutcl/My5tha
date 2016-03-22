package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.transtion.my5th.R;

/**
 * Created by baicai on 2016/2/22.
 */
public class AHomefrag extends Fragment {
    String id="100000002003";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_ahome,null);

        return view;
    }
}
