package com.greymax.vkplayer.player;

import com.greymax.vkplayer.player.controlpanel.ControlPanelView;
import com.greymax.vkplayer.player.playlistbox.PlaylistBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.springframework.social.vkontakte.api.Audio;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerPresenter implements Initializable {

  @FXML public HBox controlPanelContainer;
  @FXML public SplitPane splitPane;
  @FXML public Button addPlaylistButton;
  @FXML public Text playlistTitle;
//  @FXML public Text friendsTitle;
  @FXML public ListView<PlaylistBox> playlistItems;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    ControlPanelView controlPanelView = new ControlPanelView();
    controlPanelView.getViewAsync(controlPanelContainer.getChildren()::add);

    DropShadow ds = new DropShadow();
    ds.setOffsetY(3.0f);
    ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
    playlistTitle.setEffect(ds);
    playlistTitle.setFont(Font.font(null, FontWeight.BOLD, 12));
    /*
    friendsTitle.setEffect(ds);
    friendsTitle.setFont(Font.font(null, FontWeight.BOLD, 12));
    */
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

  // TODO: not supported
  public void addPlaylist(ActionEvent actionEvent) {
//    if (!(playlistItems.getItems().get(playlistItems.getItems().size() - 1)).getStyleClass().contains("new-playlist")) {
//      Playlist playlist = new Playlist(PlaylistType.NEW_ALBUM, playlistItems);
//      playlist.addActionListener(this);
//      playlistItems.getItems().add(playlist);
//    }
  }
}
