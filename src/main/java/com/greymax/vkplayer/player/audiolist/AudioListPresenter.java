package com.greymax.vkplayer.player.audiolist;

import com.greymax.vkplayer.model.PlayerModel;
import com.greymax.vkplayer.player.audiobox.AudioBox;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.social.vkontakte.api.Audio;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AudioListPresenter implements Initializable {

  @Inject
  private PlayerModel playerModel;

  @FXML
  public JFXListView<AudioBox> audioBoxList;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (!playerModel.audioListProperty().isEmpty()) populateAudioBoxes(playerModel.getAudioList());
    playerModel.audioListProperty().addListener((observable, oldValue, newValue) -> populateAudioBoxes(newValue));
  }

  private void populateAudioBoxes(List<Audio> list) {
    audioBoxList.getItems().clear();
    list.forEach((audio) -> audioBoxList.getItems().add(new AudioBox(audio)));
  }
}
