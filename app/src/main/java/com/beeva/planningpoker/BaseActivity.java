package com.beeva.planningpoker;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.TextView;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import com.beeva.planningpoker.di.MainComponent;
import com.beeva.planningpoker.manager.TrackerManager;
import com.beeva.planningpoker.utils.DialogsUtils;
import com.google.android.gms.analytics.Tracker;

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

    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
  }

  @Override public void showProgress(int resourceMessage) {
    DialogsUtils.showLoadingMessage(this, resourceMessage);
  }

  @Override public void hideLoading() {
    DialogsUtils.hideLoading();
  }

  @Override public void showToast(int resourceMessage) {
    DialogsUtils.showToast(this, resourceMessage);
  }

  @Override public void sendEventAnalytics(String category, String action) {
    TrackerManager.sendEvent(this, category, action);
  }

  //Abstract Classes
  protected abstract int getLayoutId();

  protected abstract void initializeDagger(MainComponent mainComponent);

  protected abstract void initializePresenter();

  protected abstract void initializeToolbar();

  protected abstract void initializeDrawer();

  protected abstract int getHeaderTitle();

  private void setHeaderTitle(BaseActivity view) {
    if (getHeaderTitle() != 0) {
      try {
        ((TextView) view.findViewById(R.id.txtHeaderTitle)).setText(getString(getHeaderTitle()));
      } catch (NullPointerException nullPointerException) {
        System.err.println(getString(R.string.error_missing_header));
      }
    }
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    //InputMethodManager imm = (InputMethodManager) getSystemService(Context.
    //    INPUT_METHOD_SERVICE);
    //imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    return true;
  }


}