package com.beeva.planningpoker.ui.login.signup;

/**
 * Created by david.gonzalez on 29/9/16.
 */

public class SignUpException extends Throwable {
  public SignUpException() { super(); }
  public SignUpException(String message) { super(message); }
  public SignUpException(String message, Throwable cause) { super(message, cause); }
  public SignUpException(Throwable cause) { super(cause); }
}
