package com.greymax.vkplayer.player.audiolist;

import javafx.scene.layout.HBox;

public class AudioList extends HBox {
  public AudioList() {
    super();
    new AudioListView().getViewAsync(getChildren()::add);
  }
}
