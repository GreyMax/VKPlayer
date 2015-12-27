package com.greymax.vkplayer.auth;

import javafx.event.EventType;

public enum AuthEventTypes {
  SUCCESS(new EventType("VK_AUTH_SUCCESS")),
  FAIL(new EventType("VK_AUTH_FAIL"));

  private EventType eventType;

  AuthEventTypes(EventType eventType) {
    this.eventType = eventType;
  }

  public EventType getValue() {
    return eventType;
  }
}
