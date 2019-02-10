package com.news.ram.newsapp.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

import com.news.ram.newsapp.R;

public class HelperClass {

    public  boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public void showAlert(final boolean check, String alertMessage, final Activity context)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(context.getString(R.string.alert_label));
        alertDialog.setMessage(alertMessage);
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);

        alertDialog.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,int which) {
                if(check){
                    context.finish();
                }

            }
        });
        alertDialog.setCancelable(false);
        if(!context.isFinishing()) {
            alertDialog.show();
        }
    }


}
