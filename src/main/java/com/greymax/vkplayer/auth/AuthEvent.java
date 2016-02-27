package com.greymax.vkplayer.auth;

import javafx.event.Event;
import javafx.event.EventType;

public class AuthEvent extends Event {

  public AuthEvent(EventType<? extends Event> eventType) {
    super(eventType);
  }

  public static final EventType<AuthEvent> SUCCESS =
      new EventType<>(Event.ANY, "VK_AUTH_SUCCESS");

  public static final EventType<AuthEvent> FAIL =
      new EventType<>(Event.ANY, "VK_AUTH_FAIL");
}
