package com.akgec.naimish.chit_o_chat.Info;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.akgec.naimish.chit_o_chat.Activities.ChatActivity;
import com.akgec.naimish.chit_o_chat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter {

    private static final String CHILD_NAME = "USERS";
    private Context context;
    private ArrayList<DataSnapshot> userList;
    private String email;
    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            userList.add(dataSnapshot);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    private String myUserName;
    private FirebaseDatabase mDB;
    private DatabaseReference mRef;

    public UserListAdapter(Context context, String myUserName) {
        this.context = context;
        this.myUserName = myUserName;
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        userList = new ArrayList<>();
        mRef = FirebaseDatabase.getInstance().getReference().child(CHILD_NAME);
        mRef.addChildEventListener(childEventListener);

    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public UserInfo getItem(int i) {
        DataSnapshot snapshot = userList.get(i);
        return snapshot.getValue(UserInfo.class);

    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        UserViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.single_user_row, viewGroup, false);
            holder = new UserViewHolder(view);
            view.setTag(holder);
        }
        UserInfo info = getItem(i);
        holder = (UserViewHolder) view.getTag();
        holder.individualUserName.setText(info.getUserName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataSnapshot snapshot = userList.get(i);
                UserInfo temp = snapshot.getValue(UserInfo.class);
                if (temp.getUserName().equals(MySharedPreferences.getPreference(context, email + ""))) {
                    MyMessage.showMessage(context, "Opps it seems like you are trying to talk to your self");
                } else {
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("Receiver Username", temp.getUserName());
                    context.startActivity(intent);
                }

            }
        });

        return view;
    }
}
