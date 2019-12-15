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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    Thread Thread1 = null;
    private String TAG = ChatActivity.class.getSimpleName();
    private Intent intent;
    private EditText editText;
    private ImageView sendbutt;
    private List<ChatMessage> allMessages;
    private ChatRV_Adapter adapter;
    String SERVER_IP;
    int SERVER_PORT;

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

        SERVER_IP="172.17.239.137:1235";
        SERVER_PORT=1235;
        editText = message_enter.findViewById(R.id.toBeSent_message);
        sendbutt = message_enter.findViewById(R.id.send_button);
        Thread1 = new Thread(new Thread1());
        Thread1.start();
        allMessages = new ArrayList<>();
        adapter = new ChatRV_Adapter(getApplicationContext(), allMessages);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        sendbutt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String message = editText.getText().toString().trim();
                if (!message.isEmpty()) {
                    new Thread(new Thread3(message)).start();
                }
                updateList();
                return true;
            }
        });
    }
    private PrintWriter output;
    private BufferedReader input;
    class Thread1 implements Runnable {
        public void run() {
            Socket socket;
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("!!!!!!!!!","connected");
                    }
                });
                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("!!!!!!!!!","dksjhgiu");
            }
        }
    }
    class Thread2 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();
                    if (message!= null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //tvMessages.append("server: " + message + "\n");
                            }
                        });
                    } else {
                        Thread1 = new Thread(new Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Thread3 implements Runnable {
        private String message;
        Thread3(String message) {
            this.message = message;
        }
        @Override
        public void run() {
            output.write(message);
            output.flush();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
              //      tvMessages.append("client: " + message + "\n");
                //    etMessage.setText("");
                }
            });
        }
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
