package com.beeva.planningpoker.ui.aboutApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.beeva.corporate.TextView;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutAppFragment extends BaseFragment {

  public AboutAppFragment() {
    // Required empty public constructor
  }

  public static AboutAppFragment newInstance() {
    return new AboutAppFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_about_app, container, false);
  }

  @Override protected int getHeaderTitle() {
    return R.string.aboutapp_header_title;
  }

  @Override protected void initializePresenter() {

  }

  @Override protected void initializeDagger(MainComponent mainComponent) {

  }

  @Override protected void unbindButterknife() {

  }
}
