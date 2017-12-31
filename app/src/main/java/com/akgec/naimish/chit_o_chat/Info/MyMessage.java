package com.akgec.naimish.chit_o_chat.Info;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class MyMessage {
    private static AlertDialog.Builder dialog;
    private static final String TITLE = "MESSAGE";
    private static final String POSITIVE_BUTTON = "OKAY";

    public static void showMessage(Context context, String message) {
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(TITLE)
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(POSITIVE_BUTTON, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    public static ProgressDialog showProgress(Context context, String message) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;

    }
}
