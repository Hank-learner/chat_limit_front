package com.example.interview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.editTextEmail)
//    MaterialTextField editTextEmail;
//
//    @BindView(R.id.editTextPassword)
//    MaterialTextField editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
    }
}
