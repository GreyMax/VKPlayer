package com.greymax.vkplayer.db.main;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractDao<T> {

  private Class<T> entityClass;

  public AbstractDao(Class<T> clazz) {
    entityClass = clazz;
  }

  public SessionFactory getSessionFactory() {
    return HibernateUtil.getSessionFactory();
  }

  public Session getSession() {
    SessionFactory sessionFactory = getSessionFactory();
    return sessionFactory.openSession();
  }

  public Criteria createEntityCriteria() {
    return getSession().createCriteria(entityClass);
  }

  public T create(T object) {
    Session session = getSession();
    session.beginTransaction();
    session.save(object);
    session.getTransaction().commit();
    return object;
  }

  //TODO: this is not work!!!
  public T update(T object) {
    Session session = getSession();
    session.beginTransaction();
    session.update(object);
    session.getTransaction().commit();
    return object;
  }
}
