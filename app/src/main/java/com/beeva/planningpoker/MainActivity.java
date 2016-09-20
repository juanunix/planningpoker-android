package com.beeva.planningpoker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.beeva.planningpoker.di.MainComponent;
import com.beeva.planningpoker.ui.MainPresenter;
import com.beeva.planningpoker.ui.login.login.DrawerPresenter;
import javax.inject.Inject;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener, DrawerPresenter.View,
    MainPresenter.View {

  @Inject DrawerPresenter drawerPresenter;
  @Inject MainPresenter mainPresenter;
  private Toolbar toolbar;
  private Fragment fragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override protected void initializeDagger(MainComponent mainComponent) {
    mainComponent.inject(this);
  }

  @Override protected void initializePresenter() {
    drawerPresenter.setView(this);
    drawerPresenter.initialize();
    mainPresenter.setView(this);
    mainPresenter.initialize();
  }

  @Override protected void initializeToolbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
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
    drawerPresenter.initializeMenu(this, navigationView);
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    drawerPresenter.onNavigationItemSelected(item);

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

  @Override public void setFragment(Fragment fragment) {
    this.fragment = fragment;
    FragmentManager fm = getFragmentManager();
    fm.beginTransaction()
        .replace(R.id.container_main, fragment, fragment.getClass().getSimpleName())
        .commit();
  }
}
