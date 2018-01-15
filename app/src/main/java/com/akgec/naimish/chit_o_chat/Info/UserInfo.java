package com.akgec.naimish.chit_o_chat.Info;

public class UserInfo {
    private String userId;
    private String userName;

    public UserInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserInfo() {
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }
}
