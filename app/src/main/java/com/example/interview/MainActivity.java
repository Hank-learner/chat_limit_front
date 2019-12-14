package com.example.interview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.editTextEmail)
//    MaterialTextField editTextEmail;
//
//    @BindView(R.id.editTextPassword)
//    MaterialTextField editTextPassword;

    @BindView(R.id.login_button)
    Button Login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChatListActivity.class));
                overridePendingTransition  (R.anim.right_in, R.anim.right_out);
            }
        });
    }
}
