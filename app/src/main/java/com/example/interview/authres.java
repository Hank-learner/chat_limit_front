package com.example.interview;

import com.google.gson.annotations.SerializedName;

public class authres {
    @SerializedName("message")
    String message;

    public authres(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}