package com.beeva.planningpoker.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.widget.Button;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.utils.FontUtils;

/**
 * Created by david.gonzalez on 27/9/16.
 */

public class PickedCardDialogFragment extends DialogFragment
    implements DialogInterface.OnShowListener {

  PickedCardDialogListener mListener;

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    Context context = new ContextThemeWrapper(getActivity(), R.style.CustomAlertDialog);

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    LayoutInflater inflater = getActivity().getLayoutInflater();

    builder.setView(inflater.inflate(R.layout.layout_dialog_pick_card, null))
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int id) {
            mListener.onDialogPositiveClick(PickedCardDialogFragment.this);
          }
        })
        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            mListener.onDialogNegativeClick(PickedCardDialogFragment.this);
          }
        });

    AlertDialog alertDialog = builder.create();
    alertDialog.setOnShowListener(this);

    return alertDialog;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CustomAlertDialog);
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    try {
      mListener = (PickedCardDialogListener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString() + " must implement PickedCardDialogListener");
    }
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

  public interface PickedCardDialogListener {
    void onDialogPositiveClick(DialogFragment dialog);

    void onDialogNegativeClick(DialogFragment dialog);
  }
}