package com.greymax.vkplayer.db.main;

import com.greymax.vkplayer.objects.User;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;

public class UserDao extends AbstractDao {

  private static UserDao instance = null;

  public static UserDao getInstance() {
    if (null == instance)
      synchronized (UserDao.class) {
        if (null == instance)
          instance = new UserDao();
      }

    return instance;
  }

  UserDao() {
    super(User.class);
  }

  public Collection<User> getAll() {
    return createEntityCriteria().list();
  }

  public User getByUsername(String username) {
    return (User) createEntityCriteria().add(Restrictions.eq("username", username))
        .uniqueResult();
  }

  public Collection<String> getAllUsernames() {
    return createEntityCriteria().setProjection(Projections.property("username")).list();
  }
}
