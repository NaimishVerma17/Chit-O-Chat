package com.akgec.naimish.chit_o_chat.Info;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akgec.naimish.chit_o_chat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyViewHolder {
    @BindView(R.id.chat_message)
    TextView chatMessage;
    @BindView(R.id.my_username)
    TextView myUserName;
    LinearLayout.LayoutParams layoutParams;

    public MyViewHolder(View view){
        ButterKnife.bind(this,view);
        layoutParams= (LinearLayout.LayoutParams)myUserName.getLayoutParams();
    }
}
