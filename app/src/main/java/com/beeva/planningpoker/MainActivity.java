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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.beeva.planningpoker.di.MainComponent;
import com.beeva.planningpoker.manager.BrowserManager;
import com.beeva.planningpoker.ui.MainPresenter;
import com.beeva.planningpoker.ui.decks.views.DecksTypeFragment;
import com.beeva.planningpoker.ui.login.login.DrawerPresenter;
import com.beeva.planningpoker.utils.BundleConstants;

import javax.inject.Inject;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener, DrawerPresenter.View,
    MainPresenter.View {

  @Inject DrawerPresenter drawerPresenter;
  @Inject MainPresenter mainPresenter;

  @BindView(R.id.nav_view) NavigationView nav_view;

  private Toolbar toolbar;
  private Fragment fragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);

    putDefaultFragment();
  }

  @Override protected void onResume() {
    super.onRestart();
    setFragment(fragment);
    mainPresenter.onResume(getIntent());
    //We need remove Settings Extra, because if we force to reload view extra persists
    getIntent().removeExtra(BundleConstants.SETTINGS_FRAGMENT);
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

  @Override protected int getHeaderTitle() {
    return 0;
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
      if (!(fragment instanceof DecksTypeFragment)) {
        putDefaultFragment();
        setFragment(fragment);
        clearMenu();
      } else {
        super.onBackPressed();
      }
    }
  }

  @Override public void setFragment(Fragment fragment) {
    this.fragment = fragment;
    FragmentManager fm = getFragmentManager();
    fm.beginTransaction()
        .replace(R.id.container_main, fragment, fragment.getClass().getSimpleName())
        .commit();
  }

  @Override public void putDefaultFragment() {
    this.fragment = DecksTypeFragment.newInstance();
  }

  @Override public void clearMenu() {
    int size = nav_view.getMenu().size();
    for (int i = 0; i < size; i++) {
      nav_view.getMenu().getItem(i).setChecked(false);
    }
  }

  @Override public void setSettingsFragment() {
    drawerPresenter.onNavigationItemSelected(R.id.nav_settings);
  }

  @OnClick(R.id.ibBeevaLogo) public void onClickLogoBeeva(){
    BrowserManager.openBeevaWebsite(this);
  }
}
