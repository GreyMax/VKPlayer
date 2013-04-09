package com.greymax.vkplayer.db.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

  private static final SessionFactory sessionFactory;
  private static ServiceRegistry serviceRegistry;

  static {
    try {
      Configuration configuration = new Configuration();
      configuration.configure();
      serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    } catch (Throwable ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  public static Transaction openTransaction() {
    return getSession().beginTransaction();
  }

  public static void commitTransaction() {
    if (getSession().getTransaction().isActive())
      getSession().getTransaction().commit();
  }

  public static void rollbackTransaction() {
    if (getSession().getTransaction().isActive())
      getSession().getTransaction().rollback();
  }

}
