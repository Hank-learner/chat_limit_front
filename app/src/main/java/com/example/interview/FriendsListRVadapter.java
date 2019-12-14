package com.example.interview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendsListRVadapter extends RecyclerView.Adapter {

    private String TAG = FriendsListRVadapter.class.getSimpleName();
    private Context context;
    private List<FriendsListPOJO> fullList;

    public FriendsListRVadapter(Context context, List<FriendsListPOJO> fullList) {
        this.context = context;
        this.fullList = fullList;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        final FriendsListVH viewholder;
        if (viewType==1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_list_item, parent,
                    false);
            viewholder = new FriendsListVH(view);
        }
        else return null;

        viewholder.lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("chatID", viewholder.name.getText().toString()+"'s Chat");
                Log.d(TAG,viewholder.name.getText().toString()+"'s Chat");
                context.startActivity(new Intent(context, ChatActivity.class));
                //overridePendingTransition  (R.anim.right_in, R.anim.right_out);

            }
        });
        return viewholder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FriendsListVH) holder).bind(fullList.get(position));
    }

    @Override
    public int getItemCount() {
        return fullList.size();
    }

    private class FriendsListVH extends RecyclerView.ViewHolder {

        private ImageView proImage;
        private TextView name, lchat;
        private LinearLayout lv;

        public FriendsListVH(@NonNull View itemView) {
            super(itemView);
            proImage = itemView.findViewById(R.id.profileimageView);
            name = itemView.findViewById(R.id.Name_textView);
            lchat = itemView.findViewById(R.id.LastChat_textView);
            lv = itemView.findViewById(R.id.openchat_lv);
        }

        void bind(FriendsListPOJO pojo){
            //Glide to set image
            name.setText(pojo.getName());
            lchat.setText(pojo.getLastChat());
        }
    }
}
