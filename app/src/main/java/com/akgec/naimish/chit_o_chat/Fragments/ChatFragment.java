package com.akgec.naimish.chit_o_chat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.akgec.naimish.chit_o_chat.Info.ChatInformation;
import com.akgec.naimish.chit_o_chat.Info.ListAdapter;
import com.akgec.naimish.chit_o_chat.Info.MyMessage;
import com.akgec.naimish.chit_o_chat.Info.MySharedPreferences;
import com.akgec.naimish.chit_o_chat.Info.UniqueNode;
import com.akgec.naimish.chit_o_chat.Info.Validations;
import com.akgec.naimish.chit_o_chat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChatFragment extends Fragment {
    @BindView(R.id.list_chat)
    ListView listView;
    @BindView(R.id.message_chat)
    EditText messageChat;
    @BindView(R.id.send_message)
    ImageButton sendMessage;

    private String mUsername;
    private String receiverUsername;
    private String uniqueNode;
    private DatabaseReference mRef;
    private ListAdapter adapter;
    private FirebaseAuth mAuth;
    private String email;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_fragment, container, false);
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
        email = mAuth.getCurrentUser().getEmail().toString();
        mRef = FirebaseDatabase.getInstance().getReference();
        mUsername = MySharedPreferences.getPreference(getContext(), email+"");
        receiverUsername=getActivity().getIntent().getExtras().getString("Receiver Username");
        uniqueNode= UniqueNode.getUniqueNode(mUsername,receiverUsername);
        adapter = new ListAdapter(getContext(), mUsername,uniqueNode);
        listView.setAdapter(adapter);
    }


    @OnClick(R.id.send_message)
    public void sendMessageToFirebase() {
        String myMessage = messageChat.getText().toString();
        if (Validations.isEmpty(myMessage)) {
            MyMessage.showMessage(getActivity(), "Message cannot be empty!");
        } else {
            ChatInformation information = new ChatInformation(myMessage, mUsername);
            mRef.child("CHATS").child(uniqueNode).push().setValue(information);
            messageChat.setText("");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.freeUpResources();
    }
}
