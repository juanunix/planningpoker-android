package com.beeva.planningpoker.manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.beeva.planningpoker.R;

/**
 * Created by david.gonzalez on 25/11/16.
 */

public class BrowserManager {

  public static void openBeevaWebsite(Context context){
    String url = context.getResources().getString(R.string.url_beeva_website);
    Intent i = new Intent(Intent.ACTION_VIEW);
    i.setData(Uri.parse(url));
    context.startActivity(i);
  }
}
