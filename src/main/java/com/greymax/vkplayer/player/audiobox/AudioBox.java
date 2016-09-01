package com.greymax.vkplayer.player.audiobox;

import javafx.scene.layout.HBox;
import org.springframework.social.vkontakte.api.Audio;

public class AudioBox extends HBox {

    private AudioBoxPresenter presenter;

    public AudioBox() {
        super();
        AudioBoxView view = new AudioBoxView();
        this.presenter = (AudioBoxPresenter) view.getPresenter();
        view.getViewAsync(getChildren()::add);
    }

    public AudioBox(Audio audio) {
        this();
        presenter.audioProperty().setValue(audio);
    }
}
