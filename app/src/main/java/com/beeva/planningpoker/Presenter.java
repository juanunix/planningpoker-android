package com.beeva.planningpoker;

/**
 * Created by david.gonzalez on 15/9/16.
 */
public class Presenter<T extends Presenter.View> {

  private T view;

  public T getView() {
    return view;
  }

  public void setView(T view) {
    this.view = view;
  }

  public void initialize() {

  }

  public interface View {
    void showProgress(int resourceMessage);

    void hideLoading();

    void showToast(int resourceMessage);

    void sendEventAnalytics(String category, String action);
  }
}