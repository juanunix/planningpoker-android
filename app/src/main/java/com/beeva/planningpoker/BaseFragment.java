package com.beeva.planningpoker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by david.gonzalez on 15/9/16.
 */
public abstract class BaseFragment extends Fragment {

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }
  protected abstract int getHeaderTitle();

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    setHeaderTitle(view);

    super.onViewCreated(view, savedInstanceState);
  }

  private void setHeaderTitle(View view){
    if(getHeaderTitle() != 0) {
      ((TextView) view.findViewById(R.id.txtHeaderTitle)).setText(getActivity().getString(getHeaderTitle()));
    }
  }
}
