package com.beeva.planningpoker.ui.login.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class LoginFragment extends BaseFragment{
  @BindView(R.id.txtHeaderTitle) TextView txtHeaderTitle;
  @BindView(R.id.etEmail) TextView etEmail;
  @BindView(R.id.etPassword) TextView etPassword;
  @BindView(R.id.btnSignin) TextView btnSignin;

  public LoginFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_login, container, false);
  }

  @Override protected int getHeaderTitle() {
    return R.string.login_header_title;
  }

}
