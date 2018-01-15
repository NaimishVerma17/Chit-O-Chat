package com.akgec.naimish.chit_o_chat.Info;

public class UniqueNode {
    private static String uniqueString = "";

    public static String getUniqueNode(String myUserName, String otherUserName) {
        if (myUserName.compareTo(otherUserName) < 1) {
            uniqueString = myUserName + otherUserName;
        } else if (myUserName.compareTo(otherUserName) > 1) {
            uniqueString = otherUserName + myUserName;
        }
        return uniqueString;
    }
}
