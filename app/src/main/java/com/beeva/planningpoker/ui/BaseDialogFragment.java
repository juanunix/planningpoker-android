package com.beeva.planningpoker.ui;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import com.beeva.planningpoker.utils.FontUtils;

/**
 * Created by david.gonzalez on 19/10/16.
 */

public class BaseDialogFragment extends DialogFragment implements DialogInterface.OnShowListener {

  Drawable drawableBackground;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CustomAlertDialog);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    drawableBackground = ContextCompat.getDrawable(PlanningPokerAplication.getContext(), R.drawable.bg_main);
    drawableBackground.setAlpha(191);

    getDialog().getWindow().setBackgroundDrawable(drawableBackground);

    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override public void onShow(DialogInterface dialog) {
    AlertDialog alertDialog = (AlertDialog) dialog;
    Typeface typeface = FontUtils.getDefaultTypeface(getActivity());

    Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
    button.setAllCaps(true);
    button.setTypeface(typeface);

    button = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
    button.setTypeface(typeface);

    button = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
    button.setTypeface(typeface);
  }

  //Alpha is setted to 255 because all R.drawable.bg_main gets that alpha on the entire app
  @Override public void onDismiss(DialogInterface dialog) {
    super.onDismiss(dialog);
    drawableBackground.setAlpha(255);
  }
}
