package com.akgec.naimish.chit_o_chat.Info;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class AddFragment {
    private static FragmentManager manager = null;
    private static FragmentTransaction transaction = null;

    public static void addFragment(AppCompatActivity activity, Fragment fragment, int container) {
        manager = activity.getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(container, fragment, "Tag").commit();
    }
}
