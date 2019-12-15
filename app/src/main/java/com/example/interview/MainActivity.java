package com.example.interview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.editTextEmail)
//    MaterialTextField editTextEmail;
//
//    @BindView(R.id.editTextPassword)
//    MaterialTextField editTextPassword;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.login_button)
    Button Login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                Api myapi=retrofit.create(Api.class);
                Call<authres> call=myapi.signin(username.getText().toString(),password.getText().toString());
                call.enqueue(new Callback<authres>() {
                    @Override
                    public void onResponse(Call<authres> call, Response<authres> response) {
                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        if(response.body().getMessage().equals("success"))
                        {
                             startActivity(new Intent(MainActivity.this, ChatListActivity.class));
                             overridePendingTransition  (R.anim.right_in, R.anim.right_out);
                        }
                    }

                    @Override
                    public void onFailure(Call<authres> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"gfuyfuy",Toast.LENGTH_SHORT).show();
                        Log.d("!!!!!!!!!!!!!",t.getMessage());
                    }
                });
            }
        });
    }
}
