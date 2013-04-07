package com.greymax.vkplayer.services.auth;

import com.greymax.vkplayer.Utils;
import com.greymax.vkplayer.objects.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserService {

  private static UserService instrance = null;
  private User loggedUser;

  public static UserService getInstance() {
    if (null == instrance)
      instrance = new UserService();

    return instrance;
  }

  public UserService() {
    // emptiness
  }

  public UserService(User loggedUser) {
    this();
    this.loggedUser = loggedUser;
  }

  public Collection<User> getAllUsers() {
    List<User> userList = new ArrayList<>();
    for (String username : getAllUserNames())
      userList.add(new User(username, ""));

    return userList;
  }

  public Collection<String> getAllUserNames() {

    return Utils.getPossibleLogin();
  }

  public String getPasswordByUser(String username) {

    return Utils.getPasswordForUser(username);
  }
}
