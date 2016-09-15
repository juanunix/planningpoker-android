package com.beeva.planningpoker.ui.login.login;


import android.app.Fragment;
import android.view.MenuItem;
import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.R;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 15/9/16.
 */
public class DrawerPresenter extends Presenter<DrawerPresenter.View> {

  private View view;

  @Inject public DrawerPresenter() {

  }

  @Override public void initialize() {
    super.initialize();
    view = getView();
  }

  public void onNavigationItemSelected(MenuItem item) {
    Fragment fragment;
    int id = item.getItemId();

    switch (id){
      case R.id.nav_numbers_deck: break;
      case R.id.nav_sizes_deck: break;
      case R.id.nav_settings: break;
      case R.id.nav_howToPlay: break;
      case R.id.nav_share: break;
      case R.id.nav_aboutApp: break;
      case R.id.nav_logOut: break;
    }

    fragment = LoginFragment.newInstance();
    view.setFragment(fragment);
  }

  public interface View extends Presenter.View{
    void setFragment(Fragment fragment);
  }
}
