package com.greymax.vkplayer.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Album {

  private Long id;
  private Long ownerId;
  private String title;

  public Album() {

  }

  public Album(JSONObject jsonAlbum) {
    try {
      this.id = jsonAlbum.getLong("album_id");
      this.ownerId = jsonAlbum.getLong("owner_id");
      this.title = jsonAlbum.getString("title");
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Long ownerId) {
    this.ownerId = ownerId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
