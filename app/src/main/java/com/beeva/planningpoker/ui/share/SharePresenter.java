package com.beeva.planningpoker.ui.share;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 16/9/16.
 */
public class SharePresenter extends Presenter<SharePresenter.View> {

  @Inject com.beeva.planningpoker.manager.PackageManager packageManager;

  private View view;

  @Inject public SharePresenter() {

  }

  @Override public void initialize() {
    super.initialize();
    view = getView();
  }

  //TODO: Share Messaging (Multiples messages?)
  public void shareTo(ShareEnum shareEnum) {
    String packageName = shareEnum.getPackageName(shareEnum);
    boolean isSocialAppInstalled = packageManager.isApplicationInstalled(packageName);

    if (isSocialAppInstalled) {
      Share share = getDataForSocialSharing(packageName);
      share.setMessage(getStringToShare(shareEnum));
      view.sendSharedMessage(share);
    } else {
      view.showToast(R.string.toast_error_message_application_not_installed);
    }
  }

  private Share getDataForSocialSharing(String packageName) {
    Share share = null;
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");

    List<ResolveInfo> resolvedInfoList = packageManager.getApplicationListAvailable(intent);

    for (ResolveInfo resolveInfo : resolvedInfoList) {
      if (resolveInfo.activityInfo.packageName.startsWith(packageName)) {
        share = new Share(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        break;
      }
    }
    return share;
  }

  private String getStringToShare(ShareEnum shareEnum) {
    Resources resources = PlanningPokerAplication.getContext().getResources();

    switch (shareEnum) {
      case TWITTER:
        return String.format("%s - %s", resources.getString(R.string.label_share_message_twitter),
            resources.getString(R.string.url_play_store));
      case LINKEDIN:
      case FACEBOOK:
      case GOOGLE:
      case MAIL:
      case WHATSAPP:
        return String.format("%s - %s", resources.getString(R.string.label_share_message_another),
            resources.getString(R.string.url_play_store));
    }
    return null;
  }

  public interface View extends Presenter.View {
    void onClickLinkedIn();

    void onClickFacebook();

    void onClickTwitter();

    void onClickGoogle();

    void onClickMail();

    void onClickWhatsapp();

    void sendSharedMessage(Share share);
  }
}
