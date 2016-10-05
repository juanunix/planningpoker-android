package com.beeva.planningpoker.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;
import com.beeva.planningpoker.application.PlanningPokerAplication;

/**
 * Created by David Gonzalez on 9/3/16.
 */
public class DialogsUtils {

    private static ProgressDialog progressDialog = null;

    public static void showLoadingMessage(Context context, String message) {
        progressDialog = ProgressDialog.show(context, null, message, true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void showLoadingMessage(Context context, int resourceMessage) {
        String message = context.getString(resourceMessage);
        showLoadingMessage(context, message);
    }

    public static void hideLoading() {
        progressDialog.dismiss();
    }

    public static void showToast(final Context context, final String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, int resourceMessage) {
        String message = PlanningPokerAplication.getContext().getResources().getString(resourceMessage);
        showToast(context, message);
    }
}