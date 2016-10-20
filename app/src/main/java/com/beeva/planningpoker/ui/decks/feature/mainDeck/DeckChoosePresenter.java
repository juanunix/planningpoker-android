package com.beeva.planningpoker.ui.decks.feature.mainDeck;

import android.content.Intent;
import android.content.res.TypedArray;
import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import com.beeva.planningpoker.ui.decks.adapter.DeckChooseAdapter;
import com.beeva.planningpoker.ui.decks.enums.DeckEnum;
import com.beeva.planningpoker.ui.decks.model.Card;
import com.beeva.planningpoker.utils.BundleConstants;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by dagonco on 19/9/16
 */

public class DeckChoosePresenter extends Presenter<DeckChoosePresenter.View> {

  private View view;

  @Inject public DeckChoosePresenter() {

  }

  @Override public void initialize() {
    super.initialize();
    view = getView();
  }

  //TODO: Remove mocked
  public void start(Intent intent, DeckChooseAdapter.OnItemClickListener onItemClickListener) {
    DeckEnum deckEnum = ((DeckEnum) intent.getSerializableExtra(BundleConstants.DECK_TYPE));
    boolean isNumbersSelected = deckEnum == DeckEnum.NUMBERS;

    int spanCount = isNumbersSelected ? 4 : 3;

    TypedArray cardImages = PlanningPokerAplication.getContext()
        .getResources()
        .obtainTypedArray(isNumbersSelected ? R.array.number_cards : R.array.size_cards);
    TypedArray stringImages = PlanningPokerAplication.getContext()
        .getResources()
        .obtainTypedArray(
            isNumbersSelected ? R.array.number_cards_string : R.array.size_cards_string);

    int limit = (isNumbersSelected ? 14 : 8);

    List<Card> cardList = new ArrayList<>();

    for (int i = 0; i < limit; i++) {
      String description = PlanningPokerAplication.getContext()
          .getResources()
          .getString(stringImages.getResourceId(i, 0));
      cardList.add(new Card(cardImages.getResourceId(i, 0), description, i));
    }

    cardImages.recycle();
    stringImages.recycle();
    DeckChooseAdapter adapter = new DeckChooseAdapter(cardList, onItemClickListener);
    view.prepareRecyclerView(spanCount);
    view.setDeckAdapter(adapter);
  }

  public interface View extends Presenter.View {
    void prepareRecyclerView(int spanCount);

    void setDeckAdapter(DeckChooseAdapter adapter);
  }
}
