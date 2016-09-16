package com.beeva.planningpoker.ui.howToPlay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;

/**
 * A simple {@link Fragment} subclass.
 */
public class HowToPlayFragment extends BaseFragment {

  public HowToPlayFragment() {
    // Required empty public constructor
  }

  public static HowToPlayFragment newInstance() {
    return new HowToPlayFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_how_to_play, container, false);
  }

  @Override protected int getHeaderTitle() {
    return R.string.howtoplay_header_title;
  }

  @Override protected void initializePresenter() {

  }

  @Override protected void initializeDagger(MainComponent mainComponent) {

  }
}
