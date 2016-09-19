package com.beeva.planningpoker.ui.login.login;

import android.app.Fragment;
import android.view.MenuItem;
import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.ui.aboutApp.AboutAppFragment;
import com.beeva.planningpoker.ui.howToPlay.HowToPlayFragment;
import com.beeva.planningpoker.ui.settings.SettingsFragment;
import com.beeva.planningpoker.ui.share.ShareFragment;
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
    Fragment fragment = null;
    int id = item.getItemId();

    switch (id) {
      case R.id.nav_numbers_deck:
        break;
      case R.id.nav_sizes_deck:
        break;
      case R.id.nav_settings:
        fragment = SettingsFragment.newInstance();
        break;
      case R.id.nav_howToPlay:
        fragment = HowToPlayFragment.newInstance();
        break;
      case R.id.nav_share:
        fragment = ShareFragment.newInstance();
        break;
      case R.id.nav_aboutApp:
        fragment = AboutAppFragment.newInstance();
        break;
      case R.id.nav_logOut:
        break;
      default:
        fragment = LoginFragment.newInstance();
        break;
    }

    if (fragment != null) view.setFragment(fragment);
  }

  public interface View extends Presenter.View {
    void setFragment(Fragment fragment);
  }
}
