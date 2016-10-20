package com.beeva.planningpoker.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.ui.BaseDialogFragment;
import com.beeva.planningpoker.ui.decks.model.Card;

import static com.beeva.planningpoker.utils.BundleConstants.CHOSEN_CARD;

/**
 * Created by david.gonzalez on 27/9/16.
 */

public class PickedCardDialogFragment extends BaseDialogFragment
    implements DialogInterface.OnShowListener {

  @BindView(R.id.ivCard) ImageView ivCard;

  PickedCardDialogListener mListener;

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    Context context = new ContextThemeWrapper(getActivity(), R.style.CustomAlertDialog);

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    View view = View.inflate(getContext(), R.layout.layout_dialog_pick_card, null);
    ButterKnife.bind(this, view);

    builder.setView(view);

    AlertDialog alertDialog = builder.create();
    alertDialog.setOnShowListener(this);

    Card card = getArguments().getParcelable(CHOSEN_CARD);
    ivCard.setImageResource(card.getImageResourceBig());

    return alertDialog;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    try {
      mListener = (PickedCardDialogListener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString() + " must implement PickedCardDialogListener");
    }
  }

  @OnClick(R.id.btnAccept) public void onClickAcceptDialog(){
    mListener.onDialogPositiveClick(this);
  }

  @OnClick(R.id.btnCancel) public void onClickCancelDialog(){
    mListener.onDialogNegativeClick(this);
  }

  public interface PickedCardDialogListener {
    void onDialogPositiveClick(DialogFragment dialog);

    void onDialogNegativeClick(DialogFragment dialog);
  }

}