package com.akgec.naimish.chit_o_chat.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akgec.naimish.chit_o_chat.Fragments.LoginFragment;
import com.akgec.naimish.chit_o_chat.R;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends SingleFragmentActivity {
    private static final String TITLE = "LOGIN";

    @Override
    protected String setToolbarTitle() {
        return TITLE;
    }

    @Override
    protected Fragment getFragmentInstance() {
        return new LoginFragment();
    }

}
