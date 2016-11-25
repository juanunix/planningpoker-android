package com.beeva.planningpoker.ui.login.signup;

import android.content.Context;
import android.content.res.Resources;
import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 29/9/16.
 */

public class SignUpPresenter extends Presenter<SignUpPresenter.View> implements DoSignUp.Callback {

  Context context = PlanningPokerAplication.getContext();
  Resources resources = context.getResources();
  private View view;
  private DoSignUp doSignUp;
  private SignUp signUp;

  @Inject public SignUpPresenter(DoSignUp doSignUp) {
    this.doSignUp = doSignUp;
  }

  @Override public void initialize() {
    super.initialize();
    view = getView();
  }

  public void signup(SignUp signUp) {
    this.signUp = signUp;

    doSignUp.doSignUp(signUp, this);
  }

  @Override public void onSignUpFailure(SignUpError signUpError) {
    switch (signUpError) {
      case POLICY_ACCEPTED:
        view.showToast(R.string.signup_error_notaccepted_policy);
    }
  }

  @Override public void onSignUpFormFailure(SignUpError signUpError) {
    switch (signUpError) {
      case EMPTY_NAME:
        view.setErrorNameField(resources.getString(R.string.signup_error_emptyfield_name));
        break;
      case EMPTY_EMAIL:
        view.setErrorEmailField(resources.getString(R.string.signup_error_emptyfield_email));
        break;
      case EMPTY_PASSWORD:
        view.setErrorPasswordField(resources.getString(R.string.signup_error_emptyfield_password));
        break;
      case EMPTY_REPEATPASSWORD:
        view.setErrorPasswordRepeatField(
            resources.getString(R.string.signup_error_emptyfield_passwordrepeat));
        break;
      case NOTMATCH_PASSWORDS:
        view.setErrorPasswordRepeatField(resources.getString(R.string.signup_error_notmatch_passwords));
        break;
      case NOTVALID_EMAIL:
        view.setErrorEmailField(resources.getString(R.string.signup_error_notvalid_email));
        break;
    }
  }

  @Override public void onSignUpFormSuccess() {
    doSignUp.checkPolicy(signUp, this);
  }

  @Override public void onSignUpSuccess() {

  }

  public interface View extends Presenter.View {
    void setErrorNameField(String error);

    void setErrorEmailField(String error);

    void setErrorPasswordField(String error);

    void setErrorPasswordRepeatField(String error);
  }
}
