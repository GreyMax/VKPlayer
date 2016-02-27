package com.greymax.vkplayer.player;

import com.greymax.vkplayer.player.playlistbox.PlaylistBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.springframework.social.vkontakte.api.Audio;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerPresenter implements Initializable {

  @FXML public ListView<PlaylistBox> playlistItems;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // TODO: Not supported yet
//    for (Album album : audioService.getAlbums())
//      playlistItems.getItems().add(new Playlist(PlaylistType.ALBUM, album.getTitle()));

    playlistItems.getItems().forEach(PlaylistBox::init);
    playlistItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      List<Audio> audio = newValue.getAudio();
      // TODO: show audio on right panel
    });

    playlistItems.getSelectionModel().select(0);
  }
}
