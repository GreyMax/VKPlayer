package com.greymax.vkplayer.player;

import com.greymax.vkplayer.player.playlistbox.PlaylistBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.springframework.social.vkontakte.api.Audio;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerPresenter implements Initializable {

  @FXML public Text playlistTitle;
  @FXML public ListView<PlaylistBox> playlistItems;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    DropShadow ds = new DropShadow();
    ds.setOffsetY(3.0f);
    ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
    playlistTitle.setEffect(ds);
    playlistTitle.setFont(Font.font(null, FontWeight.BOLD, 12));

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
