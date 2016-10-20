package com.beeva.planningpoker.ui.decks.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by david.gonzalez on 20/9/16.
 */

public class Card implements Parcelable {

  int imageResource;
  int imageResourceBig;
  String description;
  int value;

  public Card(int imageResource, int imageResourceBig, String description, int value) {
    this.imageResource = imageResource;
    this.imageResourceBig = imageResourceBig;
    this.description = description;
    this.value = value;
  }

  public int getImageResource() {
    return imageResource;
  }

  public void setImageResource(int imageResource) {
    this.imageResource = imageResource;
  }

  public int getImageResourceBig() {
    return imageResourceBig;
  }

  public void setImageResourceBig(int imageResourceBig) {
    this.imageResourceBig = imageResourceBig;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.imageResource);
    dest.writeInt(this.imageResourceBig);
    dest.writeString(this.description);
    dest.writeInt(this.value);
  }


  protected Card(Parcel in) {
    this.imageResource = in.readInt();
    this.imageResourceBig = in.readInt();
    this.description = in.readString();
    this.value = in.readInt();
  }

  public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
    @Override public Card createFromParcel(Parcel source) {
      return new Card(source);
    }

    @Override public Card[] newArray(int size) {
      return new Card[size];
    }
  };
}
