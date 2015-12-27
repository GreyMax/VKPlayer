package com.greymax.vkplayer.player.playlist;

import com.greymax.vkplayer.ui.components.playlist.Playlist;
import javafx.event.Event;
import javafx.event.EventType;

public class PlaylistEvent extends Event {

  private Playlist targetPlaylist;

  public PlaylistEvent(EventType<Event> eventType, Playlist target) {
    super(eventType);
    this.targetPlaylist = target;
  }

  public Playlist getTargetPlaylist() {
    return targetPlaylist;
  }

  @Override
  public String toString() {
    return eventType.toString();
  }
}
