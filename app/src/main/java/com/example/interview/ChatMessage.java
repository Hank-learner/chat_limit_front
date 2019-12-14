package com.example.interview;

public class ChatMessage {
    private String message, timeStamp, message_owner;
    private boolean mine;

    public ChatMessage(String message, String timeStamp, String message_owner, boolean mine) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.message_owner = message_owner;
        this.mine = mine;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage_owner() {
        return message_owner;
    }

    public void setMessage_owner(String message_owner) {
        this.message_owner = message_owner;
    }
}
