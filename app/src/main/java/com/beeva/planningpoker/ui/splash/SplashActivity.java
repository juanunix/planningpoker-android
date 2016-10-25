package com.beeva.planningpoker.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import butterknife.ButterKnife;
import com.beeva.planningpoker.BaseActivity;
import com.beeva.planningpoker.MainActivity;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;
import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashPresenter.View {

  private static final long STARTUP_DELAY = 1000;
  private static final long ANIM_ITEM_DURATION = 1250;
  private static final long TIMER_TIME = STARTUP_DELAY + ANIM_ITEM_DURATION + 250;
  @Inject SplashPresenter presenter;
  private CountDownTimer countDownTimer;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
  }

  @Override protected void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override protected void onStop() {
    super.onStop();
    presenter.onStop();
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_splash;
  }

  @Override protected void initializeDagger(MainComponent mainComponent) {
    mainComponent.inject(this);
  }

  @Override protected void initializePresenter() {
    presenter.setView(this);
    presenter.initialize();
  }

  @Override protected void initializeToolbar() {
    //Do nothing
  }

  @Override protected void initializeDrawer() {
    //Do nothing
  }

  @Override protected int getHeaderTitle() {
    return 0;
  }

  @Override public void initTimer() {
    countDownTimer = new CountDownTimer(TIMER_TIME, 1) {

      public void onTick(long millisUntilFinished) {
      }

      public void onFinish() {
        navigateToMainActivity();
      }
    }.start();
  }

  @Override public void startTimer() {
    countDownTimer.start();
  }

  @Override public void stopTimer() {
    countDownTimer.cancel();
  }

  @Override public void navigateToMainActivity() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }
}
