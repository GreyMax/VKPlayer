package com.greymax.vkplayer.ui.components.playlist;

import javafx.event.EventType;

public enum PlaylistEventType {

  REFRESH(new EventType("playlistRefresh")),
  DELETE(new EventType("playlistRemove"));

  private EventType eventType;

  PlaylistEventType(EventType type) {
    eventType = type;
  }

  public EventType getValue() {
    return eventType;
  }
}
