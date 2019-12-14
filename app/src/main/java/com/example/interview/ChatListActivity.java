package com.example.interview;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

public class ChatListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.nav_drawer_toggle)
    ImageView toggler;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new ChatListFrag()).commit();
        navigationView.setNavigationItemSelectedListener(this);
        checkFriendsItem();
    }

    @OnTouch(R.id.nav_drawer_toggle)
    void drawertoggler(){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void checkFriendsItem(){
        navigationView.setCheckedItem(R.id.friends_chat);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.friends_chat : {
                navigationView.setCheckedItem(R.id.friends_chat);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChatListFrag()).commit();
                break;
            }
            case R.id.busibot_chat : {
                navigationView.setCheckedItem(R.id.busibot_chat);
                startActivity(new Intent(ChatListActivity.this, BusinessBot.class));
                overridePendingTransition  (R.anim.right_in, R.anim.right_out);
                break;
            }
            case R.id.settings : {
                navigationView.setCheckedItem(R.id.settings);
                startActivity(new Intent(ChatListActivity.this, Settings.class));
                overridePendingTransition  (R.anim.right_in, R.anim.right_out);
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkFriendsItem();
    }
}
