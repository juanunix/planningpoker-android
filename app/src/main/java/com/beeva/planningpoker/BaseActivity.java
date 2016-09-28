package com.beeva.planningpoker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
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
    setHeaderTitle(this);
  }

  @Override public void showProgress(int resourceMessage) {

  }

  @Override public void hideLoading() {

  }

  @Override public void showToast(int resourceMessage) {

  }

  private void setHeaderTitle(BaseActivity view) {
    if (getHeaderTitle() != 0) {
      try {
        ((TextView) view.findViewById(R.id.txtHeaderTitle)).setText(getString(getHeaderTitle()));
      } catch (NullPointerException nullPointerException) {
        System.err.println(getString(R.string.error_missing_header));
      }
    }
  }

  //Abstract Classes
  protected abstract int getLayoutId();

  protected abstract void initializeDagger(MainComponent mainComponent);

  protected abstract void initializePresenter();

  protected abstract void initializeToolbar();

  protected abstract void initializeDrawer();

  protected abstract int getHeaderTitle();

}