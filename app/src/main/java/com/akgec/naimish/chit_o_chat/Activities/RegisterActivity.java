package com.akgec.naimish.chit_o_chat.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.akgec.naimish.chit_o_chat.Fragments.RegisterFragment;

public class RegisterActivity extends SingleFragmentActivity {
    private static final String TITLE = "REGISTER";

    @Override
    protected String setToolbarTitle() {
        return TITLE;
    }

    @Override
    protected Fragment getFragmentInstance() {
        return new RegisterFragment();
    }

}
