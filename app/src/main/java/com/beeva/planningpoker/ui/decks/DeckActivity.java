package com.beeva.planningpoker.ui.decks;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.beeva.planningpoker.BaseActivity;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;
import javax.inject.Inject;

public class DeckActivity extends BaseActivity implements DeckPresenter.View, OnItemClickListener {

  @BindView(R.id.recyclerViewDeck) RecyclerView recyclerViewDeck;
  @Inject DeckPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);

    presenter.start(getIntent(), this);
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_deck;
  }

  @Override protected void initializeDagger(MainComponent mainComponent) {
    mainComponent.inject(this);
  }

  @Override protected void initializePresenter() {
    presenter.setView(this);
    presenter.initialize();
  }

  @Override protected void initializeToolbar() {
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

  @Override public void prepareRecyclerView() {
    recyclerViewDeck.setHasFixedSize(true);
    recyclerViewDeck.setLayoutManager(new GridLayoutManager(this, 3));
  }

  @Override public void setDeckAdapter(DeckAdapter adapter) {
    recyclerViewDeck.setAdapter(adapter);
  }

  @Override public void onItemClick(Card card) {

  }

  @Override public void onItemLongClick(Card card) {
    //Do nothing
  }
}
