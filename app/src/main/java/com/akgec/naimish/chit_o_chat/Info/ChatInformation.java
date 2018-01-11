package com.akgec.naimish.chit_o_chat.Info;

public class ChatInformation {
     private String message;
     private String userName;

    public ChatInformation(String message, String userName) {
        this.message = message;
        this.userName = userName;
    }

    public ChatInformation(){}

    public String getMessage() {
        return message;
    }

    public String getUserName() {
        return userName;
    }
}
