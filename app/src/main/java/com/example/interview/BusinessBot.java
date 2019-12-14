package com.example.interview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BusinessBot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessbot_chat_layout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        overridePendingTransition  (R.anim.left_in, R.anim.left_out);
    }
}
