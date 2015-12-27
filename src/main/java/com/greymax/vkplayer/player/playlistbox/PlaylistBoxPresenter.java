package com.greymax.vkplayer.player.playlistbox;

import com.greymax.vkplayer.player.playlist.PlaylistType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaylistBoxPresenter implements Initializable {

  private PlaylistType playlistType;

  @FXML public Text playlistName;
  @FXML public Button refreshButton;
  @FXML public Button removeButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void setPlaylistName(String name) {
    this.playlistName.setText(name);
  }

  public void setPlaylistType(PlaylistType playlistType) {
    this.playlistType = playlistType;
  }

  @FXML
  public void onMouseEntered(MouseEvent event) {
    refreshButton.setVisible(true);
    removeButton.setVisible(true);
  }

  @FXML
  public void onMouseExited(MouseEvent event) {
    refreshButton.setVisible(false);
    removeButton.setVisible(false);
  }

  @FXML
  public void refreshPlaylist(ActionEvent event) {

  }

  @FXML
  public void removePlaylist(ActionEvent event) {

  }
}
