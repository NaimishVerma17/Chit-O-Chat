package com.akgec.naimish.chit_o_chat.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.akgec.naimish.chit_o_chat.Fragments.UserFragment;
import com.akgec.naimish.chit_o_chat.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends SingleFragmentActivity {
    private static final String TITLE = "USERS";

    @Override
    protected String setToolbarTitle() {
        return TITLE;
    }

    @Override
    protected Fragment getFragmentInstance() {
        return new UserFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== R.id.signout){
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
