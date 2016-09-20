package com.beeva.planningpoker.ui.decks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;
import com.beeva.planningpoker.ui.aboutApp.AboutAppFragment;
import com.beeva.planningpoker.utils.BundleConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class DecksFragment extends BaseFragment {

  public DecksFragment() {
    // Required empty public constructor
  }

  public static DecksFragment newInstance() {
    return new DecksFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_decks, container, false);
    ButterKnife.bind(this, rootView);

    return rootView;
  }

  @Override protected int getHeaderTitle() {
    return 0;
  }

  @Override protected void initializePresenter() {

  }

  @Override protected void initializeDagger(MainComponent mainComponent) {
  }

  @OnClick(R.id.btnNumbers) public void onClickBtnNumbers() {
    navigateToDeckActivity(DeckEnum.NUMBERS);
  }

  @OnClick(R.id.btnSizes) public void onClickBtnSizes() {
    navigateToDeckActivity(DeckEnum.SIZES);
  }

  private void navigateToDeckActivity(DeckEnum deckEnum) {
    Intent intent = new Intent(getActivity(), DeckActivity.class);
    intent.putExtra(BundleConstants.DECK_TYPE, deckEnum);

    startActivity(intent);
  }
}
