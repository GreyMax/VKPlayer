package com.greymax.vkplayer.objects;

public class User {

  public static final String USER_FIST_NAME_PARAMETER = "first_name";
  public static final String USER_LAST_NAME_PARAMETER = "last_name";
  public static final String USER_ID_PARAMETER = "uid";

  private Long id;
  private Long uid;
  private String firstName;
  private String lastName;
  private String username;
  private String password;
  private String status;
  private Token token;
  private Settings settings;

  public User() {
    // emptiness
  }

  public User(String usr, String pass) {
    this();
    this.username = usr;
    this.password = pass;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Token getToken() {
    return token;
  }

  public void setToken(Token token) {
    this.token = token;
  }

  public Settings getSettings() {
    return settings;
  }

  public void setSettings(Settings settings) {
    this.settings = settings;
  }

  public String toString() {
    return getFirstName() + " " + getLastName();
  }
}
