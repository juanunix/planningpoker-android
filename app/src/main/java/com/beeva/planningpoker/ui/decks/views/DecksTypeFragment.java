package com.beeva.planningpoker.ui.decks.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;
import com.beeva.planningpoker.ui.decks.enums.DeckEnum;
import com.beeva.planningpoker.utils.BundleConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class DecksTypeFragment extends BaseFragment {

  private Unbinder unbinder;

  public DecksTypeFragment() {
    // Required empty public constructor
  }

  public static DecksTypeFragment newInstance() {
    return new DecksTypeFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_decks, container, false);
    unbinder = ButterKnife.bind(this, rootView);

    return rootView;
  }

  @Override protected int getHeaderTitle() {
    return 0;
  }

  @Override protected void initializePresenter() {

  }

  @Override protected void initializeDagger(MainComponent mainComponent) {
  }

  @Override protected void unbindButterknife() {
    unbinder.unbind();
  }

  @OnClick(R.id.btnNumbers) public void onClickBtnNumbers() {
    sendEventAnalytics(getResources().getString(R.string.analytics_category_use),
        getResources().getString(R.string.analytics_category_use_consume_numbers));
    navigateToDeckActivity(DeckEnum.NUMBERS);
  }

  @OnClick(R.id.btnSizes) public void onClickBtnSizes() {
    sendEventAnalytics(getResources().getString(R.string.analytics_category_use),
        getResources().getString(R.string.analytics_category_use_consume_sizes));
    navigateToDeckActivity(DeckEnum.SIZES);
  }

  private void navigateToDeckActivity(DeckEnum deckEnum) {
    Intent intent = new Intent(getActivity(), DeckActivity.class);
    intent.putExtra(BundleConstants.DECK_TYPE, deckEnum);

    startActivity(intent);
  }
}
