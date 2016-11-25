package com.beeva.planningpoker.ui.share;

/**
 * Created by david.gonzalez on 16/9/16.
 */
public class Share {
  private String message;
  private String packageMessage;
  private String name;

  public Share(String message, String packageMessage, String name) {
    this.message = message;
    this.packageMessage = packageMessage;
    this.name = name;
  }

  public Share(String packageMessage, String name) {
    this.packageMessage = packageMessage;
    this.name = name;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPackageMessage() {
    return packageMessage;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
