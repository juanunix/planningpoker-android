package com.beeva.planningpoker.ui;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
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

  @Test public void navigation_settings() throws InterruptedException {
    onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
    onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_settings));

    IdlingResource idlingResource = new ElapsedTimeIdlingResource(500);
    Espresso.registerIdlingResources(idlingResource);

    onView(withId(R.id.cbPressToShow)).check(matches(isChecked()));
    onView(withId(R.id.cbShakeToShow)).check(matches(not(isChecked())));

    onView(withId(R.id.cbPressToShow)).perform(click());
    onView(withId(R.id.cbShakeToShow)).perform(click());

    onView(withId(R.id.drawer_layout)).perform(open());
    onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_play));
    onView(withId(R.id.drawer_layout)).perform(open());
    onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_settings));

    onView(withId(R.id.cbPressToShow)).check(matches(not(isChecked())));
    onView(withId(R.id.cbShakeToShow)).check(matches(isChecked()));

    Espresso.unregisterIdlingResources(idlingResource);
  }
}
