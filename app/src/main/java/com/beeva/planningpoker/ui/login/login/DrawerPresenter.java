package com.beeva.planningpoker.ui.login.login;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.ui.aboutApp.AboutAppFragment;
import com.beeva.planningpoker.ui.decks.DecksFragment;
import com.beeva.planningpoker.ui.howToPlay.HowToPlayFragment;
import com.beeva.planningpoker.ui.settings.SettingsFragment;
import com.beeva.planningpoker.ui.share.ShareFragment;
import com.beeva.planningpoker.views.CustomTypefaceSpan;
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
        fragment = DecksFragment.newInstance();
        break;
      case R.id.nav_sizes_deck:
        fragment = DecksFragment.newInstance();
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

  public void initializeMenu(Context context, NavigationView navigationView) {
    Menu menu = navigationView.getMenu();

    for (int i = 0; i < menu.size(); i++) {
      MenuItem item = menu.getItem(i);

      SubMenu subMenu = item.getSubMenu();
      if (subMenu != null && subMenu.size() > 0) {
        for (int j = 0; j < subMenu.size(); j++) {
          MenuItem subMenuItem = subMenu.getItem(j);
          applyFontToMenuItem(context, subMenuItem);
        }
      }

      applyFontToMenuItem(context, item);
    }
  }

  private void applyFontToMenuItem(Context context, MenuItem item) {
    Typeface tf = Typeface.createFromAsset(context.getAssets(),
        "fonts/" + context.getString(R.string.font_corporative_book));
    SpannableString mNewTitle = new SpannableString(item.getTitle());
    mNewTitle.setSpan(new CustomTypefaceSpan("", tf), 0, mNewTitle.length(),
        Spannable.SPAN_INCLUSIVE_INCLUSIVE);
    item.setTitle(mNewTitle);
  }

  public interface View extends Presenter.View {
    void setFragment(Fragment fragment);
  }
}
