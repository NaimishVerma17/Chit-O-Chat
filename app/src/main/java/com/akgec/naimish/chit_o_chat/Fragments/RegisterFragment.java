package com.akgec.naimish.chit_o_chat.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akgec.naimish.chit_o_chat.Activities.LoginActivity;
import com.akgec.naimish.chit_o_chat.Activities.UserNameActivity;
import com.akgec.naimish.chit_o_chat.Info.MyMessage;
import com.akgec.naimish.chit_o_chat.Info.MySharedPreferences;
import com.akgec.naimish.chit_o_chat.Info.Validations;
import com.akgec.naimish.chit_o_chat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends Fragment {
    // private String mUserName;
    private String mEmail;
    private String mPassword;
    private String mConfirmPassword;
    // private static final String KEY_USERNAME = "username";
    private FirebaseAuth mAuth;
    private ProgressDialog progress;

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;

    @OnClick(R.id.submit)
    public void submitClicked() {
        registerUser();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    private void registerUser() {
        //mUserName = userName.getText().toString();
        mEmail = email.getText().toString();
        mPassword = password.getText().toString();
        mConfirmPassword = confirmPassword.getText().toString();

        // userName.setError(null);
        email.setError(null);
        password.setError(null);

        boolean killSwitch = false;
        View focusView = null;

//        if (Validations.isEmpty(mUserName)) {
//            userName.setError(getActivity().getString(R.string.email_error));
//            focusView = userName;
//            killSwitch = true;
//        }

        if (!Validations.checkEmail(mEmail) || Validations.isEmpty(mEmail)) {
            email.setError(getActivity().getString(R.string.email_error));
            focusView = email;
            killSwitch = true;
        }
        if (!Validations.checkPassword(mPassword, mConfirmPassword) || Validations.isEmpty(mPassword)) {
            password.setError(getActivity().getString(R.string.password_error));
            focusView = password;
            killSwitch = true;
        }

        if (killSwitch) {
            focusView.requestFocus();
        } else {
            progress = MyMessage.showProgress(getActivity(), "Loading..");
            signup();
        }
    }

    private void signup() {
        //MySharedPreferences.setPreference(getContext(), KEY_USERNAME, mUserName);
        mAuth.createUserWithEmailAndPassword(mEmail, mConfirmPassword)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Registered successfully!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), UserNameActivity.class));
                            progress.dismiss();
                        } else {
                            MyMessage.showMessage(getActivity(), "Something went wrong!");
                            progress.dismiss();
                        }
                    }
                });
    }

}
