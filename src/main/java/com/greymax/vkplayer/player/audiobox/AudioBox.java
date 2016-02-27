package com.greymax.vkplayer.player.audiobox;

import javafx.scene.layout.HBox;
import org.springframework.social.vkontakte.api.Audio;

public class AudioBox extends HBox {

  private AudioBoxView view;
  private AudioBoxPresenter presenter;

  public AudioBox() {
    super();
    this.view = new AudioBoxView();
    this.presenter = (AudioBoxPresenter) view.getPresenter();
    view.getViewAsync(getChildren()::add);
  }

  public AudioBox(Audio audio) {
    this();
    presenter.audioProperty().setValue(audio);
  }
}
