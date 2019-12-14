package com.example.interview;

public class FriendsListPOJO {

    private String name, profileImageURL, lastChat;

    public FriendsListPOJO(String name, String profileImageURL, String lastChat) {
        this.name = name;
        this.profileImageURL = profileImageURL;
        this.lastChat = lastChat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getLastChat() {
        return lastChat;
    }

    public void setLastChat(String lastChat) {
        this.lastChat = lastChat;
    }
}
