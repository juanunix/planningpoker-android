package com.beeva.planningpoker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by david.gonzalez on 15/9/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());

    initializeDagger();
    initializePresenter();
    initializeToolbar();
    initializeDrawer();
  }

  protected abstract int getLayoutId();

  protected abstract void initializeDagger();

  protected abstract void initializePresenter();

  protected abstract void initializeToolbar();

  protected abstract void initializeDrawer();
}