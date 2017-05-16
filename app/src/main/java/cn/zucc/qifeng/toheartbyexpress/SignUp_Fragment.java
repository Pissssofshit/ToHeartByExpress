package cn.zucc.qifeng.toheartbyexpress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 80421 on 2017/5/16.
 */

public class SignUp_Fragment extends Fragment {
    EditText txtsignupAccount, txtsignupPassword, txtsignupname;
    Button buttonsignup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        txtsignupAccount = (EditText) view.findViewById(R.id.Signupaccount);
        txtsignupPassword = (EditText) view.findViewById(R.id.Signuppassword);
        txtsignupname = (EditText) view.findViewById(R.id.Signupname);
        buttonsignup = (Button) view.findViewById(R.id.btn_signup);

        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "功能有待完善", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });
        return view;
    }
}
