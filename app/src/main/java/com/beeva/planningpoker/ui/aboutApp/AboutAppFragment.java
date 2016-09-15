package com.beeva.planningpoker.ui.aboutApp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;

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
}
