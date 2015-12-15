package com.greymax.vkplayer.auth;

import javafx.event.EventType;

public enum VkAuthEvent {
  SUCCESS(new EventType("VK_AUTH_SUCCESS")),
  FAIL(new EventType("VK_AUTH_FAIL"));

  private EventType eventType;

  VkAuthEvent(EventType eventType) {
    this.eventType = eventType;
  }

  public EventType getValue() {
    return eventType;
  }
}
