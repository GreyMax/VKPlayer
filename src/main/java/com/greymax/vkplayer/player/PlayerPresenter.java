package com.greymax.vkplayer.player;

import com.greymax.vkplayer.model.PlayerModel;
import com.greymax.vkplayer.player.playlistbox.PlaylistBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerPresenter implements Initializable {

  @FXML public ListView<PlaylistBox> playlistItems;

  @Inject
  private PlayerModel playerModel;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // TODO: Not supported yet
//    for (Album album : audioService.getAlbums())
//      playlistItems.getItems().add(new Playlist(PlaylistType.ALBUM, album.getTitle()));

    playlistItems.getItems().forEach(PlaylistBox::init);
    playlistItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      playerModel.setAudioList(newValue.getAudio());
    });

    playlistItems.getSelectionModel().select(0);
  }
}
