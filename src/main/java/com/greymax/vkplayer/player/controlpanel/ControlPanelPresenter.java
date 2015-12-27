package com.greymax.vkplayer.player.controlpanel;

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

  @FXML
  public Button prevButton;
  @FXML
  public Button playButton;
  @FXML
  public Button nextButton;
  @FXML
  public Slider seekSlider;
  @FXML
  public Label progressTimeLabel;
  @FXML
  public Button settingsButton;
  @FXML
  public Button repeatButton;
  @FXML
  public ToggleButton randomButton;
  @FXML
  public Slider volumeSlider;

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
  }

  @FXML
  public void play(ActionEvent actionEvent) {
    ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResource("com/greymax/vkplayer/player/controlpanel/images/pause_icon.png").toExternalForm()));
    imageView.setFitHeight(20.0d);
    imageView.setFitWidth(20.0d);
    playButton.setGraphic(imageView);
  }

  @FXML
  public void repeat(ActionEvent actionEvent) {
    if (repeatButton.getStyleClass().contains("round-button-repeat-selected")) {
      repeatButton.getStyleClass().remove("round-button-repeat-selected");
      repeatButton.getStyleClass().add("round-button-repeat-selected1");
      ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResource("com/greymax/vkplayer/player/controlpanel/images/repeat_icon_one_inverse.png").toExternalForm()));
      imageView.setFitHeight(16.0d);
      imageView.setFitWidth(16.0d);
      repeatButton.setGraphic(imageView);
    }
    else if (repeatButton.getStyleClass().contains("round-button-repeat-selected1")) {
      repeatButton.getStyleClass().remove("round-button-repeat-selected1");
      ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResource("com/greymax/vkplayer/player/controlpanel/images/repeat_icon_white.png").toExternalForm()));
      imageView.setFitHeight(16.0d);
      imageView.setFitWidth(16.0d);
      repeatButton.setGraphic(imageView);
    }
    else
      repeatButton.getStyleClass().add("round-button-repeat-selected");
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
