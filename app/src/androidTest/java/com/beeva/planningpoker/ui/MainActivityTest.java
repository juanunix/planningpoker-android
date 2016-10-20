package com.beeva.planningpoker.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.beeva.corporate.CheckBox;
import com.beeva.planningpoker.ElapsedTimeIdlingResource;
import com.beeva.planningpoker.MainActivity;
import com.beeva.planningpoker.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.beeva.planningpoker.CustomMatchers.withImageDrawable;
import static com.beeva.planningpoker.CustomMatchers.withRecyclerView;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@LargeTest @RunWith(AndroidJUnit4.class) public class MainActivityTest {

  @Rule public ActivityTestRule<MainActivity> mActivityTestRule =
      new ActivityTestRule<>(MainActivity.class);

  private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher,
      final int position) {

    return new TypeSafeMatcher<View>() {
      @Override public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(
            ((ViewGroup) parent).getChildAt(position));
      }
    };
  }

  @Test public void clickOnListNavigationItem_Play() {
    onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
    onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_play));
    onView(withId(R.id.btnNumbers)).check(matches(isDisplayed()));
    onView(withId(R.id.btnSizes)).check(matches(isDisplayed()));
  }

  @Test public void clickOnListNavigationItem_Settings() {
    onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
    onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_settings));
    onView(withId(R.id.container_settings)).check(matches(isDisplayed()));
  }

  @Test public void clickOnListNavigationItem_HowToPlay() {
    onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
    onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_howToPlay));
    onView(withId(R.id.txtHowToPlay)).check(matches(isDisplayed()));
  }

  @Test public void clickOnListNavigationItem_Share() {
    onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
    onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_share));
    onView(withId(R.id.txtShare)).check(matches(isDisplayed()));
  }

  @Test public void clickOnListNavigationItem_AboutApp() {
    onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
    onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_aboutApp));
    onView(withId(R.id.txtAboutUs)).check(matches(isDisplayed()));
  }

  @Test public void clickOnNumbers() {
    onView(withId(R.id.btnNumbers)).perform(click());
    onView(withId(R.id.recyclerViewDeck)).check(matches(isDisplayed()));
  }

  @Test public void clickOnSizes() {
    onView(withId(R.id.btnSizes)).perform(click());
    onView(withId(R.id.recyclerViewDeck)).check(matches(isDisplayed()));
  }

  @Test public void check_defaultSettings() {
    clickOnListNavigationItem_Settings();

    setDefaultSettings();

    onView(withId(R.id.cbPressToShow)).check(matches(isChecked()));
    onView(withId(R.id.cbShakeToShow)).check(matches(not(isChecked())));
  }

  @Test public void check_IfCheckBoxAreSuccessfullyStored() {
    check_defaultSettings();

    onView(withId(R.id.cbShakeToShow)).perform(click());
    onView(withId(R.id.cbPressToShow)).perform(click());

    clickOnListNavigationItem_Play();

    IdlingResource idlingResource = new ElapsedTimeIdlingResource(100);
    Espresso.registerIdlingResources(idlingResource);

    clickOnListNavigationItem_Settings();

    onView(withId(R.id.cbPressToShow)).check(matches(not(isChecked())));
    onView(withId(R.id.cbShakeToShow)).check(matches(isChecked()));

    Espresso.unregisterIdlingResources(idlingResource);
  }

  @Test public void check_IfPressCheckBoxWillGetSelectedWhenThereIsNoneCheckBoxSelected() {
    check_defaultSettings();

    onView(withId(R.id.cbShakeToShow)).perform(click());
    onView(withId(R.id.cbPressToShow)).perform(click());

    onView(withId(R.id.cbPressToShow)).check(matches(not(isChecked())));
    onView(withId(R.id.cbShakeToShow)).check(matches(isChecked()));

    onView(withId(R.id.cbShakeToShow)).perform(click());

    onView(withId(R.id.cbPressToShow)).check(matches(isChecked()));
    onView(withId(R.id.cbShakeToShow)).check(matches(not(isChecked())));
  }

  @Test public void check_ifTwitterIsNotInstalled() {
    clickOnListNavigationItem_Share();
    onView(withId(R.id.btnTwitter)).perform(click());

    checkToast(R.string.toast_error_message_application_not_installed);
  }

  @Test public void check_ifFacebookIsNotInstalled() {
    clickOnListNavigationItem_Share();
    onView(withId(R.id.btnFacebook)).perform(click());

    checkToast(R.string.toast_error_message_application_not_installed);
  }

  @Test public void check_ifLinkedinisNotInstalled() {
    clickOnListNavigationItem_Share();
    onView(withId(R.id.btnLinkedIn)).perform(click());

    checkToast(R.string.toast_error_message_application_not_installed);
  }

  @Test public void check_ifGooglePlusIsNotInstalled() {
    clickOnListNavigationItem_Share();
    onView(withId(R.id.btnGoogle)).check(matches(isDisplayed()));
    onView(withId(R.id.btnGoogle)).perform(scrollTo(), click());

    checkToast(R.string.toast_error_message_application_not_installed);
  }

  @Test public void check_ifWhatsappIsNotInstalled() {
    clickOnListNavigationItem_Share();
    onView(withId(R.id.btnWhatsapp)).check(matches(isDisplayed()));
    onView(withId(R.id.btnWhatsapp)).perform(scrollTo(), click());

    checkToast(R.string.toast_error_message_application_not_installed);
  }

  @Test public void check_ifNumberCardSelectedIsEqualDialogsCardAndDetailCard() {
    Context context = mActivityTestRule.getActivity().getApplicationContext();

    clickOnNumbers();

    TypedArray cardImages = context.getResources().obtainTypedArray(R.array.number_cards);
    TypedArray cardImagesBig = context.getResources().obtainTypedArray(R.array.number_cards_big);

    for (int i = 0; i < 14; i++) {
      onView(withRecyclerView(R.id.recyclerViewDeck).atPositionOnView(i, cardImages.getResourceId(i, 0)));
      onView(withId(R.id.recyclerViewDeck)).perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
      onView(withId(R.id.ivCard)).check(matches(withImageDrawable(cardImagesBig.getResourceId(i, 0))));
      onView(withId(R.id.btnAccept)).perform(click());

      onView(withId(R.id.frameLayout)).perform(click());
      onView(withId(R.id.ivCardFront)).check(matches(withImageDrawable(cardImagesBig.getResourceId(i, 0))));
      Espresso.pressBack();
    }

    cardImages.recycle();
    cardImagesBig.recycle();
  }

  @Test public void check_ifSizesCardSelectedIsEqualDialogsCardDetailCard() {
    Context context = mActivityTestRule.getActivity().getApplicationContext();

    clickOnSizes();

    TypedArray cardImages = context.getResources().obtainTypedArray(R.array.size_cards);
    TypedArray cardImagesBig = context.getResources().obtainTypedArray(R.array.size_cards_big);

    for (int i = 0; i < 8; i++) {
      onView(withRecyclerView(R.id.recyclerViewDeck).atPositionOnView(i, cardImages.getResourceId(i, 0)));
      onView(withId(R.id.recyclerViewDeck)).perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
      onView(withId(R.id.ivCard)).check(matches(withImageDrawable(cardImagesBig.getResourceId(i, 0))));
      onView(withId(R.id.btnAccept)).perform(click());

      onView(withId(R.id.frameLayout)).perform(click());
      onView(withId(R.id.ivCardFront)).check(matches(withImageDrawable(cardImagesBig.getResourceId(i, 0))));
      Espresso.pressBack();
    }

    cardImages.recycle();
    cardImagesBig.recycle();
  }

  private void setDefaultSettings() {
    final Activity activity = mActivityTestRule.getActivity();
    activity.runOnUiThread(new Runnable() {
      @Override public void run() {
        ((CheckBox) activity.findViewById(R.id.cbPressToShow)).setChecked(true);
        ((CheckBox) activity.findViewById(R.id.cbShakeToShow)).setChecked(false);
      }
    });
  }

  private void checkToast(int stringResource) {
    onView(withText(stringResource)).inRoot(
        withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
        .check(matches(isDisplayed()));
  }
}
