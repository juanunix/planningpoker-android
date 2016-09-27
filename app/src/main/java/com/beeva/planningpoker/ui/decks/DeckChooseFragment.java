package com.beeva.planningpoker.ui.decks;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;
import com.beeva.planningpoker.utils.SpacesItemDecoration;
import com.beeva.planningpoker.views.dialogs.PickedCardDialogFragment;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeckChooseFragment extends BaseFragment
    implements DeckChoosePresenter.View, DeckAdapter.OnItemClickListener,
    PickedCardDialogFragment.PickedCardDialogListener {

  @Inject DeckChoosePresenter presenter;

  @BindView(R.id.recyclerViewDeck) RecyclerView recyclerViewDeck;
  private Unbinder unbinder;
  private FragmentActivity context;

  public DeckChooseFragment() {
    // Required empty public constructor
  }

  public static DeckChooseFragment newInstance() {
    return new DeckChooseFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_deck_choose, container, false);
    unbinder = ButterKnife.bind(this, rootView);

    return rootView;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter.start(getActivity().getIntent(), this);
  }

  @Override public void onAttach(Activity activity) {
    context = (FragmentActivity) activity;
    super.onAttach(activity);
  }

  @Override protected int getHeaderTitle() {
    return 0;
  }

  @Override protected void initializePresenter() {
    presenter.setView(this);
    presenter.initialize();
  }

  @Override protected void initializeDagger(MainComponent mainComponent) {
    mainComponent.inject(this);
  }

  @Override protected void unbindButterknife() {
    unbinder.unbind();
  }

  @Override public void prepareRecyclerView(int spanCount) {
    int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space_deck_gridLayout);

    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
    recyclerViewDeck.setHasFixedSize(true);
    recyclerViewDeck.setLayoutManager(gridLayoutManager);
    recyclerViewDeck.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
  }

  @Override public void setDeckAdapter(DeckAdapter adapter) {
    recyclerViewDeck.setAdapter(adapter);
  }

  @Override public void onItemClick(Card card) {
    DialogFragment dialog = new PickedCardDialogFragment();
    dialog.show(context.getSupportFragmentManager(), "PickedCardDialogFragment");
  }

  @Override public void onItemLongClick(Card card) {
    //Do nothing
  }

  /**
   * The implementation is in the parent {@link DeckActivity}.
   */
  @Override public void onDialogPositiveClick(DialogFragment dialog) {
    //Do nothing
  }

  @Override public void onDialogNegativeClick(DialogFragment dialog) {
    //Do nothing
  }
}
