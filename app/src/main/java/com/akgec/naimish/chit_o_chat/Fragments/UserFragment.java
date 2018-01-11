package com.akgec.naimish.chit_o_chat.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.akgec.naimish.chit_o_chat.Info.MySharedPreferences;
import com.akgec.naimish.chit_o_chat.Info.UserListAdapter;
import com.akgec.naimish.chit_o_chat.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFragment extends Fragment {
    @BindView(R.id.user_list_view)
    ListView userListView;

    private String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        email=FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        userListView.setAdapter(new UserListAdapter(getActivity(), MySharedPreferences.getPreference(getContext(), email+"")));
    }
}
