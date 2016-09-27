package com.beeva.planningpoker.ui.decks;

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
import com.beeva.planningpoker.views.dialogs.PickedCardDialogFragment;

public class DeckActivity extends BaseActivity
    implements PickedCardDialogFragment.PickedCardDialogListener {

  private Toolbar toolbar;
  private Fragment fragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
  }

  @Override protected void onResume() {
    super.onResume();
    setFragment(DeckChooseFragment.newInstance());
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

  @Override public void onDialogPositiveClick(DialogFragment dialog) {
    ((PickedCardDialogFragment.PickedCardDialogListener) fragment).onDialogPositiveClick(dialog);
  }

  @Override public void onDialogNegativeClick(DialogFragment dialog) {
    ((PickedCardDialogFragment.PickedCardDialogListener) fragment).onDialogNegativeClick(dialog);
  }
}
