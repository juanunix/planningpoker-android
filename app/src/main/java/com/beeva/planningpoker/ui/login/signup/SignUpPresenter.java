package com.beeva.planningpoker.ui.login.signup;

import com.beeva.planningpoker.Presenter;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 29/9/16.
 */

public class SignUpPresenter extends Presenter<SignUpPresenter.View> {

  private View view;

  @Inject public SignUpPresenter() {

  }

  @Override public void initialize() {
    super.initialize();
    view = getView();
  }

  public void signup(SignUp signUp) {
    try {
      checkName(signUp);
    } catch (SignUpException signUpException) {
      view.setErrorNameField(signUpException.getMessage());
    }

    try {
      checkEmail(signUp);
    } catch (SignUpException signUpException) {
      view.setErrorNameField(signUpException.getMessage());
    }

    try {
      checkPassword(signUp);
    } catch (SignUpException signUpException) {
      view.setErrorPasswordField(signUpException.getMessage());
    }

  }

  private void checkName(SignUp signUp) throws SignUpException {
    if (signUp.getName().isEmpty()) throw new SignUpException("");


  }

  private void checkEmail(SignUp signUp) throws SignUpException {
    if (signUp.getEmail().isEmpty()) throw new SignUpException("");
  }

  private void checkPassword(SignUp signUp) throws SignUpException {
    if (signUp.getPassword().isEmpty()) throw new SignUpException("");
    if (signUp.getPasswordRepeat().isEmpty()) throw new SignUpException("");
  }

  private void checkPolicy(SignUp signUp) throws SignUpException {
    if (signUp.isAcceptedPolicy()) throw new SignUpException("");
  }

  public interface View extends Presenter.View {
    void setErrorNameField(String error);

    void setErrorEmailField(String error);

    void setErrorPasswordField(String error);

    void setErrorPasswordRepeatField(String error);
  }
}
