package com.greymax.vkplayer.player.playlistbox;

import com.greymax.vkplayer.player.playlist.PlaylistType;
import javafx.scene.Parent;

public class PlaylistBox extends Parent {

  private PlaylistBoxView playlistBoxView;
  private PlaylistType playlistType;
  private String playlistName;

  public PlaylistBox() {
    this.playlistBoxView = new PlaylistBoxView();
    playlistBoxView.getViewAsync(getChildren()::add);
  }

  public PlaylistBoxView getPlaylistBoxView() {
    return playlistBoxView;
  }

  public PlaylistType getPlaylistType() {
    return playlistType;
  }

  public void setPlaylistType(PlaylistType playlistType) {
    this.playlistType = playlistType;
    PlaylistBoxPresenter presenter = (PlaylistBoxPresenter) this.playlistBoxView.getPresenter();
    presenter.setPlaylistType(playlistType);
  }

  public String getPlaylistName() {
    return playlistName;
  }

  public void setPlaylistName(String playlistName) {
    this.playlistName = playlistName;
    PlaylistBoxPresenter presenter = (PlaylistBoxPresenter) this.playlistBoxView.getPresenter();
    presenter.setPlaylistName(playlistName);
  }
}
