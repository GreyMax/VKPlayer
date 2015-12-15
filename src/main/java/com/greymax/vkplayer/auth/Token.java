package com.greymax.vkplayer.auth;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Token {

  final private String id;
  final private String token;

  /**
   * @return Id of current user
   */
  public String getId() {
    return id;
  }

  /**
   * @return Token of current session
   */
  public String getToken() {
    return token;
  }

  public Token(String id, String token) {
    this.id = id;
    this.token = token;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(getId())
        .append(getToken())
        .toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (null == obj) {
      return false;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }

    Token rhs = (Token) obj;
    return new EqualsBuilder()
        .append(getId(), rhs.getId())
        .append(getToken(), rhs.getToken())
        .isEquals();
  }

  @Override
  public String toString() {
    return "Token [id=" + id + ", token=" + token + "]";
  }
}
