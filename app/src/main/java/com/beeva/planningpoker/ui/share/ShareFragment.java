package com.beeva.planningpoker.ui.share;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends BaseFragment {

  public ShareFragment() {
    // Required empty public constructor
  }

  public static ShareFragment newInstance() {
    return new ShareFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_share, container, false);
  }

  @Override protected int getHeaderTitle() {
    return R.string.share_header_title;
  }

}
