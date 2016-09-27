package com.beeva.planningpoker.ui.decks.feature.detail;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeckDetailFragment extends BaseFragment {

  int TIMER;
  @BindView(R.id.cardFront) View cardFront;
  @BindView(R.id.cardBack) View cardBack;
  @BindView(R.id.frameLayout) View frameLayout;
  private AnimatorSet animSetRightOut;
  private AnimatorSet animSetLeftIn;
  private boolean isCardBackVisible = true;
  private boolean isAnimFinished = true;
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
    loadAnimations();
    changeCameraDistance();
  }

  private void loadAnimations() {
    animSetRightOut =
        (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.out_animator);
    animSetLeftIn =
        (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.in_animator);
  }

  private void changeCameraDistance() {
    int distance = 8000;
    float scale = getResources().getDisplayMetrics().density * distance;
    cardFront.setCameraDistance(scale);
    cardBack.setCameraDistance(scale);
  }

  @Override protected int getHeaderTitle() {
    return 0;
  }

  @Override protected void initializePresenter() {
    //Do nothing
  }

  @Override protected void initializeDagger(MainComponent mainComponent) {
    //Do nothing
  }

  @Override protected void unbindButterknife() {
    unbinder.unbind();
  }

  @OnClick(R.id.frameLayout) public void tryFlipCard() {
    if (isAnimFinished) {
      isAnimFinished = false;
      flipCard();

      new CountDownTimer(TIMER, TIMER) {
        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
          isAnimFinished = true;
        }
      }.start();
    }
  }

  public void flipCard() {
    animSetRightOut.setTarget(isCardBackVisible ? cardBack : cardFront);
    animSetLeftIn.setTarget(isCardBackVisible ? cardFront : cardBack);
    animSetRightOut.start();
    animSetLeftIn.start();
    isCardBackVisible = !isCardBackVisible;
  }
}