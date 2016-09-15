package com.beeva.planningpoker;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import com.beeva.planningpoker.ui.login.login.DrawerPresenter;
import javax.inject.Inject;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @Inject DrawerPresenter presenter;
  private Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override protected void initializeDagger() {
    PlanningPokerAplication app = (PlanningPokerAplication) getApplication();
    app.getMainComponent().inject(this);
  }

  @Override protected void initializePresenter() {

  }

  @Override protected void initializeToolbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  @SuppressWarnings("deprecation") @Override protected void initializeDrawer() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override public boolean onNavigationItemSelected(MenuItem item) {
    presenter.onNavigationItemSelected(item);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }
}
