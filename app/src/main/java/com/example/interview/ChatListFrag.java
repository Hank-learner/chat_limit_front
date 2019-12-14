package com.example.interview;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatListFrag extends Fragment {

    private List<FriendsListPOJO> allPojos;
    private FriendsListRVadapter adapter;

    @BindView(R.id.friendslist_rv)
    RecyclerView rv;

    @BindView(R.id.fl_floatingActionButton)
    FloatingActionButton fab;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chatlistfragmentlay, container, false);
        ButterKnife.bind(this, view);

        allPojos = new ArrayList<>();
        adapter = new FriendsListRVadapter(getContext(), allPojos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allPojos.add(new FriendsListPOJO("Kundi", "", "Twerk it"));
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }
}


