package com.example.interview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    private String TAG = ChatActivity.class.getSimpleName();
    private Intent intent;
    private EditText editText;
    private ImageView sendbutt;
    private List<ChatMessage> allMessages;
    private ChatRV_Adapter adapter;

    @BindView(R.id.chat_rv)
    RecyclerView rv;

    @BindView(R.id.mlayout)
    View message_enter;

    @BindView(R.id.chat_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        editText = message_enter.findViewById(R.id.toBeSent_message);
        sendbutt = message_enter.findViewById(R.id.send_button);

        allMessages = new ArrayList<>();
        adapter = new ChatRV_Adapter(getApplicationContext(), allMessages);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        sendbutt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                updateList();
                return true;
            }
        });
    }

    private void updateList(){
        if(!TextUtils.isEmpty(editText.getText().toString())){
            ChatMessage newMessage = new ChatMessage(editText.getText().toString(),
                    new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()),
                    "You", true);

            allMessages.add(newMessage);
        }else {

        }
        adapter.notifyDataSetChanged();
        editText.setText("");
    }
}
