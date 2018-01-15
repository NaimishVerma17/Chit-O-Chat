package com.akgec.naimish.chit_o_chat.Info;

public class Validations {

    public static boolean checkPassword(String password, String confirmPassword) {
        return (password.length() > 5 && confirmPassword.equals(password));
    }

    public static boolean checkEmail(String email) {
        return email.contains("@");
    }

    public static boolean isEmpty(String s) {
        return s.length() == 0;
    }

    public static boolean isLowerCase(String s) {
        char c = s.charAt(0);
        if (c >= 97 && c <= 122) {
            return true;
        } else {
            return false;
        }


    }
}
