package com.akgec.naimish.chit_o_chat.Fragments;

import android.app.ProgressDialog;
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

import com.akgec.naimish.chit_o_chat.Activities.RegisterActivity;
import com.akgec.naimish.chit_o_chat.Activities.UserActivity;
import com.akgec.naimish.chit_o_chat.Activities.UserNameActivity;
import com.akgec.naimish.chit_o_chat.Info.MyMessage;
import com.akgec.naimish.chit_o_chat.Info.Validations;
import com.akgec.naimish.chit_o_chat.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {
    private String mEmail;
    private String mPassword;
    private ProgressDialog progress;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 123;

    @BindView(R.id.login_email)
    EditText email;
    @BindView(R.id.login_password)
    EditText password;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.register_button)
    Button registerButton;

    @OnClick(R.id.register_button)
    public void registerClicked() {
        startActivity(new Intent(getActivity(), RegisterActivity.class));
    }

    @OnClick(R.id.login_button)
    public void loginClicked() {
        mEmail = email.getText().toString();
        mPassword = password.getText().toString();
        email.setError(null);
        password.setError(null);

        boolean killSwitch = false;
        View focusView = null;

        if (!Validations.checkEmail(mEmail) || Validations.isEmpty(mEmail)) {
            email.setError(getActivity().getString(R.string.email_error));
            focusView = email;
            killSwitch = true;
        }
        if (Validations.isEmpty(mPassword)) {
            password.setError(getActivity().getString(R.string.password_error));
            focusView = password;
            killSwitch = true;
        }

        if (killSwitch) {
            focusView.requestFocus();
        } else {
            progress = MyMessage.showProgress(getActivity(), "Logging In..");
            login();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
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
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getActivity(), UserActivity.class));
            getActivity().finish();
        }
           //  Log.i("DatabaseRef",""+ FirebaseDatabase.getInstance());

    }

    private void login() {
        mAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getActivity(), UserActivity.class));
                    progress.dismiss();
                } else {
                    MyMessage.showMessage(getActivity(), "Something went wrong!");
                    progress.dismiss();
                }
            }
        });
    }

}
