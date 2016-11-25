package com.beeva.planningpoker.ui.aboutApp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;
import com.beeva.planningpoker.manager.BrowserManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutAppFragment extends BaseFragment {

  private Unbinder unbinder;

  public AboutAppFragment() {
    // Required empty public constructor
  }

  public static AboutAppFragment newInstance() {
    return new AboutAppFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_about_app, container, false);
    unbinder = ButterKnife.bind(this, rootView);

    return rootView;
  }

  @Override protected int getHeaderTitle() {
    return R.string.aboutapp_header_title;
  }

  @Override protected void initializePresenter() {

  }

  @Override protected void initializeDagger(MainComponent mainComponent) {

  }

  @Override protected void unbindButterknife() {
    unbinder.unbind();
  }

  @OnClick(R.id.ibBeevaLogo) public void onClickLogoBeeva() {
    BrowserManager.openBeevaWebsite(getActivity());
  }
}
