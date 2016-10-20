package com.beeva.planningpoker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by david.gonzalez on 20/10/16.
 */

public class CustomMatchers {
  public static Matcher<View> withBackground(final int resourceId) {
    return new TypeSafeMatcher<View>() {

      @Override
      public boolean matchesSafely(View view) {
        return sameBitmap(view.getContext(), view.getBackground(), resourceId);
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("has background resource " + resourceId);
      }
    };
  }

  public static Matcher<View> withCompoundDrawable(final int resourceId) {
    return new BoundedMatcher<View, TextView>(TextView.class) {
      @Override
      public void describeTo(Description description) {
        description.appendText("has compound drawable resource " + resourceId);
      }

      @Override
      public boolean matchesSafely(TextView textView) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
          if (sameBitmap(textView.getContext(), drawable, resourceId)) {
            return true;
          }
        }
        return false;
      }
    };
  }

  public static Matcher<View> withImageDrawable(final int resourceId) {
    return new BoundedMatcher<View, ImageView>(ImageView.class) {
      @Override
      public void describeTo(Description description) {
        description.appendText("has image drawable resource " + resourceId);
      }

      @Override
      public boolean matchesSafely(ImageView imageView) {
        return sameBitmap(imageView.getContext(), imageView.getDrawable(), resourceId);
      }
    };
  }

  private static boolean sameBitmap(Context context, Drawable drawable, int resourceId) {
    Drawable otherDrawable = context.getResources().getDrawable(resourceId);
    if (drawable == null || otherDrawable == null) {
      return false;
    }
    if (drawable instanceof StateListDrawable && otherDrawable instanceof StateListDrawable) {
      drawable = drawable.getCurrent();
      otherDrawable = otherDrawable.getCurrent();
    }
    if (drawable instanceof BitmapDrawable) {
      Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
      Bitmap otherBitmap = ((BitmapDrawable) otherDrawable).getBitmap();
      return bitmap.sameAs(otherBitmap);
    }
    return false;
  }

  public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
    return new RecyclerViewMatcher(recyclerViewId);
  }
}