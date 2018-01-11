package com.akgec.naimish.chit_o_chat.Info;

import android.view.View;
import android.widget.TextView;

import com.akgec.naimish.chit_o_chat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserViewHolder {
    @BindView(R.id.individual_user_name)
    TextView individualUserName;

    public UserViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
