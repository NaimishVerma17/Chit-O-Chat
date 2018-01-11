package com.akgec.naimish.chit_o_chat.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.akgec.naimish.chit_o_chat.Fragments.UserFragment;

public class UserActivity extends SingleFragmentActivity {
    private static final String TITLE = "USERS";

    @Override
    protected String setToolbarTitle() {
        return TITLE;
    }

    @Override
    protected Fragment getFragmentInstance() {
        return new UserFragment();
    }

}
