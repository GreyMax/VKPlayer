package com.greymax.vkplayer.player.controlpanel;

import com.greymax.vkplayer.ui.components.UIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlPanelPresenter implements Initializable {

  @FXML public Button prevButton;
  @FXML public Button playButton;
  @FXML public Button nextButton;
  @FXML public Label songTitle;
  @FXML public Slider seekSlider;
  @FXML public Label progressTimeLabel;
  @FXML public Button settingsButton;
  @FXML public Button repeatButton;
  @FXML public ToggleButton randomButton;
  @FXML public Slider volumeSlider;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    repeatButton.setFocusTraversable(false);
    randomButton.setFocusTraversable(false);
    volumeSlider.setFocusTraversable(false);
    volumeSlider.setMin(0);
    volumeSlider.setMax(100);
    seekSlider.setFocusTraversable(false);
    seekSlider.setMin(0);
    seekSlider.setMax(1);

    // TODO: hardcoded
    songTitle.setText("Test song");
    progressTimeLabel.setText("5:50");
  }

  @FXML
  public void play(ActionEvent actionEvent) {
    UIUtils.toggleClass(playButton, "playing");
  }

  public void prev(ActionEvent actionEvent) {

  }

  public void next(ActionEvent actionEvent) {

  }

  @FXML
  public void repeat(ActionEvent actionEvent) {
    UIUtils.addClassFromQueue(repeatButton, "repeat-all", "repeat-one");
  }

  @FXML
  public void random(ActionEvent actionEvent) {

  }

  @FXML
  public void mute(MouseEvent mouseEvent) {
    volumeSlider.setValue(0.0d);
  }

  @FXML
  public void maxVolume(MouseEvent mouseEvent) {
    volumeSlider.setValue(100.0d);
  }

}
