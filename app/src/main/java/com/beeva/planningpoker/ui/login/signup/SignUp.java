package com.beeva.planningpoker.ui.login.signup;

/**
 * Created by david.gonzalez on 29/9/16.
 */

public class SignUp {
  String name;
  String email;
  String password;
  String passwordRepeat;
  boolean isAcceptedPolicy;

  public SignUp() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPasswordRepeat() {
    return passwordRepeat;
  }

  public void setPasswordRepeat(String passwordRepeat) {
    this.passwordRepeat = passwordRepeat;
  }

  public boolean isAcceptedPolicy() {
    return isAcceptedPolicy;
  }

  public void setAcceptedPolicy(boolean acceptedPolicy) {
    isAcceptedPolicy = acceptedPolicy;
  }
}
