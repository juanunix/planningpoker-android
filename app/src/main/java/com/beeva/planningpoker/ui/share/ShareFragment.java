package com.beeva.planningpoker.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends BaseFragment implements SharePresenter.View {

  @Inject SharePresenter presenter;

  private Unbinder unbinder;

  public ShareFragment() {
  }

  public static ShareFragment newInstance() {
    return new ShareFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_share, container, false);
    unbinder = ButterKnife.bind(this, rootView);

    return rootView;
  }

  @Override protected int getHeaderTitle() {
    return R.string.share_header_title;
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

  @OnClick(R.id.btnLinkedIn) @Override public void onClickLinkedIn() {
    presenter.shareTo(ShareEnum.LINKEDIN);
  }

  @OnClick(R.id.btnFacebook) @Override public void onClickFacebook() {
    presenter.shareTo(ShareEnum.FACEBOOK);
  }

  @OnClick(R.id.btnTwitter) @Override public void onClickTwitter() {
    presenter.shareTo(ShareEnum.TWITTER);
  }

  @OnClick(R.id.btnGoogle) @Override public void onClickGoogle() {
    presenter.shareTo(ShareEnum.GOOGLE);
  }

  @OnClick(R.id.btnMail) @Override public void onClickMail() {
    presenter.shareTo(ShareEnum.MAIL);
  }

  @OnClick(R.id.btnWhatsapp) @Override public void onClickWhatsapp() {
    presenter.shareTo(ShareEnum.WHATSAPP);
  }

  @Override public void sendSharedMessage(Share share) {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.putExtra(Intent.EXTRA_TEXT, share.getMessage());
    intent.setType("text/plain");
    intent.setClassName(share.getPackageMessage(), share.getName());

    startActivity(intent);
  }
}
