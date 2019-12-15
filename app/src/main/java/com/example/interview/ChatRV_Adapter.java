package com.example.interview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatRV_Adapter extends RecyclerView.Adapter {

    private String TAG = ChatRV_Adapter.class.getSimpleName();
    private static final int viewholderType_sent = 1;
    private static final int viewholderType_received = 2;

    private Context context;
    private List<ChatMessage> allMessages;

    public ChatRV_Adapter(Context context, List<ChatMessage> allMessages) {
        this.context = context;
        this.allMessages = allMessages;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage currentMessage = allMessages.get(position);

        if(currentMessage.isMine()){
            return viewholderType_sent;
        }
        else {
            return viewholderType_received;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if(viewType == viewholderType_sent) {
            Log.d(TAG, "SENT TYPE");
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_messages, parent,
                    false);
            return new SentChatViewHolder(view);
        } else if(viewType == viewholderType_received) {
            Log.d(TAG, "RECEIVED TYPE");
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.received_messages, parent,
                    false);
            return new ReceivedChatViewHolder(view);
        }
        else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = allMessages.get(position);

        if(holder.getItemViewType()==viewholderType_sent){
            ((SentChatViewHolder) holder).bind(message);
        }
        if(holder.getItemViewType()==viewholderType_received){
            ((ReceivedChatViewHolder) holder).bind(message);
        }

    }

    @Override
    public int getItemCount() {
        return allMessages.size();
    }

    private class SentChatViewHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentChatViewHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        void bind(ChatMessage message) {
            messageText.setText(message.getMessage());
            timeText.setText(message.getTimeStamp());
        }
    }

    private class ReceivedChatViewHolder extends RecyclerView.ViewHolder {

        TextView messageText, timeText, nameText;

        ReceivedChatViewHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
        }

        void bind(ChatMessage message) {
            messageText.setText(message.getMessage());
            timeText.setText(message.getTimeStamp());
            nameText.setText(message.getMessage_owner());
        }
    }
}
