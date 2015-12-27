package com.greymax.vkplayer.player;

import com.greymax.vkplayer.player.controlpanel.ControlPanelView;
import com.greymax.vkplayer.ui.components.playlist.Playlist;
import com.greymax.vkplayer.ui.components.playlist.PlaylistEvent;
import com.greymax.vkplayer.ui.components.playlist.PlaylistEventType;
import com.greymax.vkplayer.ui.components.playlist.PlaylistType;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerPresenter implements Initializable, EventHandler {

  @FXML
  public HBox controlPanelContainer;

  @FXML
  public SplitPane splitPane;

  @FXML
  public Button addPlaylistButton;

  @FXML
  public Text playlistTitle;

  @FXML
  public Text friendsTitle;

  @FXML
  public ListView playlistItems;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    ControlPanelView controlPanelView = new ControlPanelView();
    controlPanelContainer.getChildren().add(controlPanelView.getView());

    DropShadow ds = new DropShadow();
    ds.setOffsetY(3.0f);
    ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
    playlistTitle.setEffect(ds);
    playlistTitle.setFont(Font.font(null, FontWeight.BOLD, 12));
    friendsTitle.setEffect(ds);
    friendsTitle.setFont(Font.font(null, FontWeight.BOLD, 12));
    // TODO: use IAudioOperations
//    for (Album album : audioService.getAlbums())
//      playlistItems.getItems().add(new Playlist(PlaylistType.ALBUM, album.getTitle()));

    for (Object playlist : playlistItems.getItems())
      ((Playlist) playlist).addActionListener(this);
  }

  public void addPlaylist(ActionEvent actionEvent) {
    if (!((Playlist)playlistItems.getItems().get(playlistItems.getItems().size() - 1)).getStyleClass().contains("new-playlist")) {
      Playlist playlist = new Playlist(PlaylistType.NEW_ALBUM, playlistItems);
      playlist.addActionListener(this);
      playlistItems.getItems().add(playlist);
    }
  }

  @Override
  public void handle(Event event) {
    if (event.getEventType().equals(PlaylistEventType.DELETE.getValue()))
      playlistItems.getItems().remove(((PlaylistEvent) event).getTargetPlaylist());
  }
}
