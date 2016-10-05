package com.beeva.planningpoker.ui.login.signup;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.beeva.corporate.Button;
import com.beeva.corporate.CheckBox;
import com.beeva.corporate.EditText;
import com.beeva.planningpoker.BaseActivity;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;
import javax.inject.Inject;

public class SignUpActivity extends BaseActivity implements SignUpPresenter.View {

  @BindView(R.id.etName) EditText etName;
  @BindView(R.id.etMail) EditText etEmail;
  @BindView(R.id.etPassword) EditText etPassword;
  @BindView(R.id.etPasswordRepeat) EditText etPasswordRepeat;
  @BindView(R.id.cbPrivacity) CheckBox cbPrivacity;
  @BindView(R.id.btnSignup) Button btnSignup;

  @Inject SignUpPresenter presenter;

  private Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_sign_up;
  }

  @Override protected void initializeDagger(MainComponent mainComponent) {
    mainComponent.inject(this);
  }

  @Override protected void initializePresenter() {
    presenter.setView(this);
    presenter.initialize();
  }

  @Override protected void initializeToolbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
  }

  @Override protected int getHeaderTitle() {
    return R.string.settings_header_title;
  }

  @Override protected void initializeDrawer() {
    // Do nothing
  }

  @OnClick(R.id.btnSignup) public void signin() {
    SignUp signUp = new SignUp();
    signUp.setName(etName.getText().toString().trim());
    signUp.setEmail(etEmail.getText().toString().trim());
    signUp.setPassword(etPassword.getText().toString().trim());
    signUp.setPasswordRepeat(etPasswordRepeat.getText().toString().trim());
    signUp.setAcceptedPolicy(cbPrivacity.isChecked());

    presenter.signup(signUp);
  }

  @Override public void setErrorNameField(String error) {
    etName.setError(error);
  }

  @Override public void setErrorEmailField(String error) {
    etEmail.setError(error);
  }

  @Override public void setErrorPasswordField(String error) {
    etPassword.setError(error);
  }

  @Override public void setErrorPasswordRepeatField(String error) {
    etPasswordRepeat.setError(error);
  }
}
