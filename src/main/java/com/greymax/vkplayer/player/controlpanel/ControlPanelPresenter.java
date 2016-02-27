package com.greymax.vkplayer.player.controlpanel;

import com.greymax.vkplayer.UIUtils;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlPanelPresenter implements Initializable {

  @FXML public HBox controlPanel;
  @FXML public Button prevButton;
  @FXML public Button playButton;
  @FXML public Button nextButton;
  @FXML public Label songTitle;
  @FXML public Slider seekSlider;
  @FXML public Label progressTimeLabel;
  @FXML public ToggleButton repeatButton;
  @FXML public ToggleButton randomButton;
  @FXML public Slider volumeSlider;
  @FXML public JFXRippler optionsRippler;
  @FXML public StackPane optionsBurger;
  @FXML public JFXPopup optionsPopup;

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
    UIUtils.disableSliderAnimatedThumb(seekSlider);

    // TODO: hardcoded
    songTitle.setText("Test song");
    progressTimeLabel.setText("110:50");

    // init options
    optionsPopup.sceneProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.getRoot() instanceof JFXDecorator) {
        optionsPopup.setPopupContainer((Pane) ((JFXDecorator) newValue.getRoot()).getChildren().get(1));
        optionsPopup.setSource(optionsRippler);
      }
    });
  }

  @FXML
  public void play(ActionEvent actionEvent) {
    UIUtils.toggleClass(playButton, "playing");
  }

  @FXML
  public void prev(ActionEvent actionEvent) {

  }

  @FXML
  public void next(ActionEvent actionEvent) {

  }

  @FXML
  public void repeat(ActionEvent actionEvent) {
  }

  @FXML
  public void random(ActionEvent actionEvent) {

  }

  @FXML
  public void mute(ActionEvent actionEvent) {
    volumeSlider.setValue(0.0d);
  }

  @FXML
  public void maxVolume(ActionEvent actionEvent) {
    volumeSlider.setValue(100.0d);
  }

  @FXML
  public void showOptionsPopup(Event event) {
    optionsPopup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -20, 15);
  }

  @FXML
  public void exit(Event event) {
    Platform.exit();
  }

  @FXML
  public void about(Event event) {
    // TODO: show dialog with info
  }
}
