package com.beeva.planningpoker.ui.decks;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import java.util.List;

/**
 * Created by david.gonzalez on 20/9/16.
 */
public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.ViewHolder> {

  private List<Card> cardList;
  private OnItemClickListener onItemClickListener;

  public DeckAdapter(List<Card> cardList, OnItemClickListener onItemClickListener) {
    this.cardList = cardList;
    this.onItemClickListener = onItemClickListener;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_list_deck_card, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Card card = cardList.get(position);
    holder.setClickListener(card, onItemClickListener);

    String description = card.getDescription();
    int imageResource = card.getImageResource();
    int value = card.getValue();
    Drawable drawable =
        ContextCompat.getDrawable(PlanningPokerAplication.getContext(), imageResource);

    holder.ibCard.setBackground(null);
    holder.ibCard.setImageDrawable(drawable);
  }

  @Override public int getItemCount() {
    return cardList.size();
  }

  public interface OnItemClickListener {
    void onItemClick(Card card);

    void onItemLongClick(Card card);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ibCard) ImageView ibCard;
    private View view;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.view = itemView;
    }

    private void setClickListener(final Card card, final OnItemClickListener listener) {
      view.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          listener.onItemClick(card);
        }
      });

      view.setOnLongClickListener(new View.OnLongClickListener() {
        @Override public boolean onLongClick(View view) {
          listener.onItemLongClick(card);
          return false;
        }
      });
    }
  }
}
