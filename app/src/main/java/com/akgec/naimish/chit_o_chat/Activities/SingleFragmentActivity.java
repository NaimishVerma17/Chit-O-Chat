package com.akgec.naimish.chit_o_chat.Activities;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.akgec.naimish.chit_o_chat.Info.AddFragment;
import com.akgec.naimish.chit_o_chat.R;
import com.akgec.naimish.chit_o_chat.Views.MyBoldTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar_title)
    MyBoldTextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    protected abstract String setToolbarTitle();

    protected abstract Fragment getFragmentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        toolbarTitle = (MyBoldTextView) findViewById(R.id.tool_bar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarTitle.setText(setToolbarTitle());
        AddFragment.addFragment(this, getFragmentInstance(), R.id.container);

    }
}
