package com.beeva.planningpoker.ui.login.login;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {
  @BindView(R.id.txtHeaderTitle) TextView txtHeaderTitle;
  @BindView(R.id.etEmail) TextView etEmail;
  @BindView(R.id.etPassword) TextView etPassword;
  @BindView(R.id.btnSignin) TextView btnSignin;

  private Unbinder unbinder;

  public LoginFragment() {
    // Required empty public constructor
  }

  public static LoginFragment newInstance() {
    LoginFragment fragment = new LoginFragment();
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_login, container, false);
    unbinder = ButterKnife.bind(this, rootView);

    return rootView;
  }

  @Override protected int getHeaderTitle() {
    return R.string.login_header_title;
  }

  @Override protected void initializePresenter() {

  }

  @Override protected void initializeDagger(MainComponent mainComponent) {

  }

  @Override protected void unbindButterknife() {
    unbinder.unbind();
  }
}
