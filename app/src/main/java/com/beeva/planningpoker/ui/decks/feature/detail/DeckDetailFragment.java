package com.beeva.planningpoker.ui.decks.feature.detail;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;
import com.beeva.planningpoker.utils.ShakeDetector;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeckDetailFragment extends BaseFragment
    implements DeckDetailPresenter.View, ShakeDetector.OnShakeListener {

  private static final int VIBRATE_INTERVAL = 100;
  @BindView(R.id.cardFront) View cardFront;
  @BindView(R.id.cardBack) View cardBack;
  @BindView(R.id.frameLayout) View frameLayout;
  @Inject DeckDetailPresenter presenter;
  private int TIMER;

  private AnimatorSet animSetRightOut;
  private AnimatorSet animSetLeftIn;

  private boolean isCardBackVisible = true;
  private boolean isAnimFinished = true;

  private SensorManager sensorManager;
  private Sensor accelerometer;
  private ShakeDetector shakeDetector;

  private Unbinder unbinder;

  public DeckDetailFragment() {
    // Required empty public constructor
  }

  public static DeckDetailFragment newInstance() {
    return new DeckDetailFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_deck_detail, container, false);
    unbinder = ButterKnife.bind(this, rootView);

    return rootView;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    TIMER = getActivity().getResources().getInteger(R.integer.flip_card_anim);
  }

  @Override public void onResume() {
    super.onResume();
    if (sensorManager != null) {
      sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }
  }

  @Override public void onPause() {
    super.onPause();
    if (sensorManager != null) sensorManager.unregisterListener(shakeDetector);
  }

  @Override protected int getHeaderTitle() {
    return 0;
  }

  @Override protected void initializePresenter() {
    presenter.setView(this);
    presenter.initialize();
  }

  @Override protected void initializeDagger(MainComponent mainComponent) {
    mainComponent.inject(this);
  }

  @Override protected void unbindButterknife() {
    unbinder.unbind();
  }

  @Override public void loadAnimations() {
    animSetRightOut =
        (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.card_out_animator);
    animSetLeftIn =
        (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.card_in_animator);
  }

  @Override public void changeCameraDistance() {
    int distance = 8000;
    float scale = getResources().getDisplayMetrics().density * distance;
    cardFront.setCameraDistance(scale);
    cardBack.setCameraDistance(scale);
  }

  @Override public void initializationPressOnClick() {
    frameLayout.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onClickCard();
      }
    });
  }

  @Override public void initializationShakeDetector() {
    sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
    accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    shakeDetector = new ShakeDetector();
    shakeDetector.setOnShakeListener(this);
  }

  private void onClickCard() {
    tryFlipCard(false);
  }

  @Override public void onShake() {
    tryFlipCard(true);
  }

  private void tryFlipCard(boolean vibrate) {
    if (isAnimFinished) {
      isAnimFinished = false;
      flipCard();
      if (vibrate) vibrateDevice();

      new CountDownTimer(TIMER, TIMER) {
        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
          isAnimFinished = true;
        }
      }.start();
    }
  }

  private void flipCard() {
    animSetRightOut.setTarget(isCardBackVisible ? cardBack : cardFront);
    animSetLeftIn.setTarget(isCardBackVisible ? cardFront : cardBack);
    animSetRightOut.start();
    animSetLeftIn.start();
    isCardBackVisible = !isCardBackVisible;
  }

  private void vibrateDevice() {
    Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    v.vibrate(VIBRATE_INTERVAL);
  }
}