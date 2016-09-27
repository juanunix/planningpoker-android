package com.beeva.planningpoker.ui.decks;

import android.content.Intent;
import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.utils.BundleConstants;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by dagonco on 19/9/16
 */

public class DeckPresenter extends Presenter<DeckPresenter.View> {

  private View view;

  @Inject public DeckPresenter() {

  }

  @Override public void initialize() {
    super.initialize();
    view = getView();
  }

  //TODO: Remove mocked
  public void start(Intent intent, OnItemClickListener onItemClickListener) {
    DeckEnum deckEnum = ((DeckEnum) intent.getSerializableExtra(BundleConstants.DECK_TYPE));
    int spanCount = deckEnum == DeckEnum.NUMBERS ? 4 : 3;

    //MOCK
    int limit = (deckEnum == DeckEnum.NUMBERS ? 14 : 8);

    List<Card> cardList = new ArrayList<>();

    for (int i = 0; i < limit; i++) {
      cardList.add(new Card(R.mipmap.ic_launcher, "Descript " + i, i));
    }
    //END MOCK

    DeckAdapter adapter = new DeckAdapter(cardList, onItemClickListener);

    view.prepareRecyclerView(spanCount);
    view.setDeckAdapter(adapter);
  }

  public interface View extends Presenter.View {
    void prepareRecyclerView(int spanCount);

    void setDeckAdapter(DeckAdapter adapter);
  }
}
