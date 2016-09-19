package com.beeva.planningpoker.ui.decks;

/**
 * Created by david.gonzalez on 20/9/16.
 */

public class Card {

  int imageResource;
  String description;
  int value;

  public Card(int imageResource, String description, int value) {
    this.imageResource = imageResource;
    this.description = description;
    this.value = value;
  }

  public int getImageResource() {
    return imageResource;
  }

  public String getDescription() {
    return description;
  }

  public int getValue() {
    return value;
  }
}
