package pl.edu.wat.wcy.isi.prz.database;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {

    private SessionFactory factory;
    private static DatabaseConnection instance  = null;
    private static final Logger logger = LogManager.getLogger(DatabaseConnection.class.getName());


    public static DatabaseConnection get() {
        if (instance == null) instance = new DatabaseConnection();
        return instance;
    }

    private DatabaseConnection() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            logger.error(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void deleteAll() {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Stop").executeUpdate();
            session.createQuery("DELETE FROM StopGroup").executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        }
    }

    private Object addObject(Object object) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.save(object);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        }
        return object;
    }

    public SessionFactory getFactory() { return factory; }
    public void addStop (Stop stop) { addObject(stop); }
    public StopGroup addStopGroup (StopGroup stopGroup) { return (StopGroup) addObject(stopGroup); }
}
