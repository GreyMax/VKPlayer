package com.greymax.vkplayer.db.main;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {

  private Class<T> entityClass;

  public AbstractDao(Class<T> clazz) {
    entityClass = clazz;
  }

  public Session getSession() {
    return HibernateUtil.getSession();
  }

  public Criteria createEntityCriteria() {
    return getSession().createCriteria(entityClass);
  }

  public T create(T object) {
    return (T) getSession().save(object);
  }

  public T update(T object) {
    return (T) getSession().merge(object);
  }

  public void delete(T object) {
    getSession().delete(object);
  }
}
