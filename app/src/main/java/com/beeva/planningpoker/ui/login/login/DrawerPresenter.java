package com.beeva.planningpoker.ui.login.login;

import android.view.MenuItem;
import com.beeva.planningpoker.R;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 15/9/16.
 */
public class DrawerPresenter {

  @Inject public DrawerPresenter() {

  }

  public void onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
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

  }

}
