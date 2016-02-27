package com.greymax.vkplayer.player.audiobox;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.springframework.social.vkontakte.api.Audio;

import java.net.URL;
import java.util.ResourceBundle;

public class AudioBoxPresenter implements Initializable {

  private ObjectProperty<Audio> audioProperty = new SimpleObjectProperty<>();

  @FXML
  private Text audioText;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    audioProperty.addListener((observable, oldValue, newValue) -> {
      audioText.setText(newValue.getTitle());
    });
  }

  public ObjectProperty<Audio> audioProperty() {
    return audioProperty;
  }
}
