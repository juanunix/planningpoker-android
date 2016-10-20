package com.beeva.planningpoker.ui.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.Unbinder;
import com.beeva.planningpoker.BaseFragment;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.MainComponent;
import com.beeva.planningpoker.manager.language.LanguageEnum;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment
    implements SettingsPresenter.View, RadioGroup.OnCheckedChangeListener {
  @BindView(R.id.cbPressToShow) CheckBox cbPressToShow;
  @BindView(R.id.cbShakeToShow) CheckBox cbShakeToShow;
  @BindView(R.id.rgLanguage) RadioGroup rgLanguage;
  @BindView(R.id.rbSpanish) RadioButton rbSpanish;
  @BindView(R.id.rbEnglish) RadioButton rbEnglish;

  @Inject SettingsPresenter presenter;

  private Unbinder unbinder;

  public SettingsFragment() {
    // Required empty public constructor
  }

  public static SettingsFragment newInstance() {
    return new SettingsFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
    unbinder = ButterKnife.bind(this, rootView);

    return rootView;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    rgLanguage.setOnCheckedChangeListener(this);
  }

  @Override protected int getHeaderTitle() {
    return R.string.settings_header_title;
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

  @OnCheckedChanged(R.id.cbPressToShow) public void onClickPressToShow() {
    presenter.onClickPressToShow(cbPressToShow.isChecked());
  }

  @OnCheckedChanged(R.id.cbShakeToShow) public void onClickShakeToShow() {
    presenter.onClickShakeToShow(cbShakeToShow.isChecked());
  }

  @Override public void onCheckedChanged(RadioGroup group, int checkedId) {

    switch (checkedId) {
      case R.id.rbEnglish:
        presenter.onLanguageChanged(LanguageEnum.ENGLISH);
        break;
      case R.id.rbSpanish:
        presenter.onLanguageChanged(LanguageEnum.SPANISH);
        break;
      default:
        presenter.onLanguageChanged(LanguageEnum.getDefaultLanguage());
        break;
    }
  }


  @Override public void setCheckPressToShow(boolean state) {
    cbPressToShow.setChecked(state);
  }

  @Override public void setCheckShakeToShow(boolean state) {
    cbShakeToShow.setChecked(state);
  }

  @Override public void setRadioButtonLanguage(LanguageEnum language) {
    switch (language) {
      case ENGLISH:
        rbEnglish.setChecked(true);
        break;
      case SPANISH:
        rbSpanish.setChecked(true);
        break;
      default:
        rbEnglish.setChecked(true);
        break;
    }
  }

  //TODO: Dialog
  //We need cbPressToShow as default check
  @Override public void checkDefaultPressed() {
    if (!cbPressToShow.isChecked() && !cbShakeToShow.isChecked()) {
      cbPressToShow.setChecked(true);
    }
  }
}
