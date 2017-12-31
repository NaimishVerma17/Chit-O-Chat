package com.akgec.naimish.chit_o_chat.Info;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.akgec.naimish.chit_o_chat.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private ArrayList<DataSnapshot> snapshots;
    private Context context;
    private DatabaseReference dbReference;
    private String userName;

    public ListAdapter(Context context, String userName) {
        this.context = context;
        this.userName = userName;
        dbReference = FirebaseDatabase.getInstance().getReference().child("CHATS");
        dbReference.addChildEventListener(childEventListener);
        snapshots = new ArrayList<>();
    }


    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            snapshots.add(dataSnapshot);
            notifyDataSetChanged();
            Log.i("CHATS: ", dataSnapshot.toString());
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


    @Override
    public int getCount() {
        return snapshots.size();
    }

    @Override
    public ChatInformation getItem(int i) {
        DataSnapshot snapshot = snapshots.get(i);
        return snapshot.getValue(ChatInformation.class);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.single_chat_row, viewGroup, false);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        }
        holder = (MyViewHolder) view.getTag();
        ChatInformation information = getItem(i);
        holder.chatMessage.setText(information.message);
        holder.myUserName.setText(information.userName);
        chatStyling(userName.equals(information.userName), holder);
        return view;
    }

    public void freeUpResources() {
        dbReference.removeEventListener(childEventListener);
    }

    private void chatStyling(Boolean state, MyViewHolder holder) {
        if (state) {
            holder.layoutParams.gravity = Gravity.END;
            holder.myUserName.setTextColor(Color.BLUE);
            holder.chatMessage.setBackgroundResource(R.drawable.speech_bubble_green);
        }
        else{
            holder.layoutParams.gravity = Gravity.START;
            holder.myUserName.setTextColor(Color.GREEN);
            holder.chatMessage.setBackgroundResource(R.drawable.speech_bubble_orange);
        }
        holder.chatMessage.setLayoutParams(holder.layoutParams);
        holder.myUserName.setLayoutParams(holder.layoutParams);
    }

}
