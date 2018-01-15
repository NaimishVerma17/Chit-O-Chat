package com.akgec.naimish.chit_o_chat.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.akgec.naimish.chit_o_chat.Activities.ChatActivity;
import com.akgec.naimish.chit_o_chat.Activities.UserActivity;
import com.akgec.naimish.chit_o_chat.Activities.UserNameActivity;
import com.akgec.naimish.chit_o_chat.Info.MySharedPreferences;
import com.akgec.naimish.chit_o_chat.Info.UserInfo;
import com.akgec.naimish.chit_o_chat.Info.Validations;
import com.akgec.naimish.chit_o_chat.R;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsernameFragment extends Fragment {
    private FirebaseAuth mAuth;
    private String email;
    private String uid;
    private DatabaseReference mRootRef;
    private static final String CHILD_NAME = "USERS";

    @BindView(R.id.user_name_et)
    EditText userName;
    @BindView(R.id.submit_username)
    Button submit;

    @OnClick(R.id.submit_username)
    public void submit() {
        String mUserName = userName.getText().toString();

        if (Validations.isEmpty(mUserName)) {
            userName.setError(getActivity().getString(R.string.u_error));

        } else {
            MySharedPreferences.setPreference(getContext(), email + "", mUserName + "");
            UserInfo userInfo = new UserInfo("Hello", mUserName);
            mRootRef.child(CHILD_NAME).push().setValue(userInfo);
            getActivity().finish();
            startActivity(new Intent(getContext(), UserActivity.class));

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.username_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        email = mAuth.getCurrentUser().getEmail().toString();
        uid = mAuth.getCurrentUser().getUid().toString();
        mRootRef = FirebaseDatabase.getInstance().getReference();
    }

}
