package com.greymax.vkplayer.ui.components.playlist;

import javafx.event.EventType;

public enum PlaylistEventType {

  REFRESH(new PlaylistRefreshEventType()),
  DELETE(new PlaylistDeleteEventType());

  private EventType eventType;

  PlaylistEventType(EventType type) {
    eventType = type;
  }

  public EventType getValue() {
    return eventType;
  }
}
