package com.beeva.planningpoker.ui.login.signup;

import com.beeva.planningpoker.views.RegexUtils;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 5/10/16.
 */

public class DoSignUp {

  @Inject public DoSignUp() {
  }

  public void doSignUp(SignUp signUp, final Callback callback) {
    boolean isValid = true;
    if (signUp.getName().isEmpty()) {
      callback.onSignUpFormFailure(SignUpError.EMPTY_NAME);
      isValid = false;
    }

    if (signUp.getEmail().isEmpty()) {
      callback.onSignUpFormFailure(SignUpError.EMPTY_EMAIL);
      isValid = false;
    } else if (!RegexUtils.isValidEmail(signUp.getEmail())) {
      callback.onSignUpFormFailure(SignUpError.NOTVALID_EMAIL);
      isValid = false;
    }

    if (signUp.getPassword().isEmpty()) {
      callback.onSignUpFormFailure(SignUpError.EMPTY_PASSWORD);
      isValid = false;
    } else if (signUp.getPasswordRepeat().isEmpty()) {
      callback.onSignUpFormFailure(SignUpError.EMPTY_REPEATPASSWORD);
      isValid = false;
    } else if (!(signUp.getPassword().equals(signUp.getPasswordRepeat()))) {
      callback.onSignUpFormFailure(SignUpError.NOTMATCH_PASSWORDS);
      isValid = false;
    }

    if (isValid) callback.onSignUpFormSuccess();
  }

  public void checkPolicy(SignUp signUp, final Callback callback) {
    if (!signUp.isAcceptedPolicy()) {
      callback.onSignUpFailure(SignUpError.POLICY_ACCEPTED);
    }
  }

  public interface Callback {
    void onSignUpFormFailure(SignUpError signUpError);

    void onSignUpFormSuccess();

    void onSignUpFailure(SignUpError signUpError);

    void onSignUpSuccess();
  }
}
