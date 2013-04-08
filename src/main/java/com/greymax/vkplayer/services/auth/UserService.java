package com.greymax.vkplayer.services.auth;

import com.greymax.vkplayer.db.main.UserDao;
import com.greymax.vkplayer.objects.User;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.Collection;

public class UserService {

  private static Logger logger = Logger.getLogger(User.class);

  private static UserService instrance = null;

  private User loggedUser;
  private UserDao userDao = UserDao.getInstance();

  public static UserService getInstance() {
    if (null == instrance)
      instrance = new UserService();

    return instrance;
  }

  public User getLoggedUser() {
    return loggedUser;
  }

  public void setLoggedUser(User loggedUser) {
    this.loggedUser = loggedUser;
  }

  public Collection<User> getAllUsers() {

    return userDao.getAll();
  }

  public Collection<String> getAllUserNames() {

    return userDao.getAllUsernames();
  }

  public User getUserByUsername(String username) {

//    return Utils.getPasswordForUser(username);
    return userDao.getByUsername(username);
  }

  public User convertFromJson(JSONObject userJson) {
    User result = new User();
    try {
      result.setUid(userJson.getLong(User.USER_ID_PARAMETER));
      result.setFirstName(userJson.getString(User.USER_FIST_NAME_PARAMETER));
      result.setLastName(userJson.getString(User.USER_LAST_NAME_PARAMETER));
    } catch (Exception e) {
      logger.error("Can not create user:", e);
    }

    return result;
  }

  //TODO: refactor and redesign
  public void createOrUpdateUser(User user) {
    User persistentUser = userDao.getByUsername(user.getUsername());
    if (null == persistentUser)
      userDao.create(user);
    else {
      persistentUser.setFirstName(user.getFirstName());
      persistentUser.setLastName(user.getLastName());
      persistentUser.setUid(user.getUid());
      userDao.update(persistentUser);
    }
  }
}
