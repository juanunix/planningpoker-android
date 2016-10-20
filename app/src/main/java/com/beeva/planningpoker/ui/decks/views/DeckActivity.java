package com.beeva.planningpoker.ui.decks.views;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.ButterKnife;
import com.beeva.planningpoker.BaseActivity;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;
import com.beeva.planningpoker.ui.decks.feature.detail.DeckDetailFragment;
import com.beeva.planningpoker.ui.decks.feature.mainDeck.DeckChooseFragment;
import com.beeva.planningpoker.ui.decks.model.Card;
import com.beeva.planningpoker.views.dialogs.PickedCardDialogFragment;

import static com.beeva.planningpoker.utils.BundleConstants.CHOSEN_CARD;

public class DeckActivity extends BaseActivity
    implements PickedCardDialogFragment.PickedCardDialogListener {

  private Toolbar toolbar;
  private Fragment fragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);

    putDefaultFragment();
  }

  @Override protected void onResume() {
    super.onResume();
    setFragment(fragment);
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_deck;
  }

  @Override protected void initializeDagger(MainComponent mainComponent) {
    mainComponent.inject(this);
  }

  @Override protected void initializePresenter() {

  }

  @Override protected void initializeToolbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
  }

  @Override protected void initializeDrawer() {
    // Do nothing
  }

  @Override protected int getHeaderTitle() {
    return 0;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void setFragment(Fragment fragment) {
    this.fragment = fragment;
    FragmentManager fm = getFragmentManager();
    fm.beginTransaction()
        .replace(R.id.container_main, fragment, fragment.getClass().getSimpleName())
        .commit();
  }

  //TODO: Better implementation
  @Override public void onDialogPositiveClick(DialogFragment dialog) {
    ((PickedCardDialogFragment.PickedCardDialogListener) fragment).onDialogPositiveClick(dialog);

    Card pickedCard = ((DeckChooseFragment) fragment).getPickedCard();
    Bundle args = new Bundle();
    args.putParcelable(CHOSEN_CARD, pickedCard);

    Fragment deckDetailFragment = new DeckDetailFragment();
    deckDetailFragment.setArguments(args);
    setFragment(deckDetailFragment);
  }

  @Override public void onDialogNegativeClick(DialogFragment dialog) {
    ((PickedCardDialogFragment.PickedCardDialogListener) fragment).onDialogNegativeClick(dialog);
  }

  public void putDefaultFragment() {
    this.fragment = DeckChooseFragment.newInstance();
  }

  @Override public void onBackPressed() {
    if (fragment instanceof DeckDetailFragment) {
      putDefaultFragment();
      setFragment(fragment);
    } else {
      super.onBackPressed();
    }
  }
}
