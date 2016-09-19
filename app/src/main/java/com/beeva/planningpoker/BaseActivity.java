package com.beeva.planningpoker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import com.beeva.planningpoker.di.MainComponent;

/**
 * Created by david.gonzalez on 15/9/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements Presenter.View {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());

    initializeDagger(((PlanningPokerAplication) getApplication()).getMainComponent());
    initializePresenter();
    initializeToolbar();
    initializeDrawer();
  }

  @Override public void showProgress(int resourceMessage) {

  }

  @Override public void hideLoading() {

  }

  @Override public void showToast(int resourceMessage) {

  }

  //Abstract Classes
  protected abstract int getLayoutId();

  protected abstract void initializeDagger(MainComponent mainComponent);

  protected abstract void initializePresenter();

  protected abstract void initializeToolbar();

  protected abstract void initializeDrawer();
}