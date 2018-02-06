package pl.edu.wat.wcy.isi.prz.listofstops;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.edu.wat.wcy.isi.prz.database.DatabaseConnection;
import pl.edu.wat.wcy.isi.prz.database.Stop;
import pl.edu.wat.wcy.isi.prz.database.StopGroup;
import pl.edu.wat.wcy.isi.prz.exceptions.DatabaseLoadingException;

import java.util.Iterator;
import java.util.List;

public class StopsList implements ListOfStops {

    private final SessionFactory factory;
    private List stops;
    private List stopGroups;
    private ObservableList<StopGroup> list;
    private static final Logger logger = LogManager.getLogger(ZTMFileParser.class.getName());

    StopsList() throws DatabaseLoadingException {
        factory = DatabaseConnection.get().getFactory();
        try {
            download();
            connectStops();
            makeArrayList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DatabaseLoadingException();
        }
    }

    private void makeArrayList () {
        list = FXCollections.observableArrayList();
        for (Object stopGroup : stopGroups) list.add((StopGroup) stopGroup);
        stops = stopGroups = null;
    }

    private void connectStops() {
        for (Object stop : stops) {
            Iterator j = stopGroups.iterator();
            StopGroup stopGroup = (StopGroup) j.next();
            while (stopGroup.getIdStopGroup() != ((Stop)stop).getIdStopGroup())
                stopGroup = (StopGroup) j.next();
            stopGroup.addStop(((Stop)stop));
        }
    }

    private void download() {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            stops = session.createQuery("FROM Stop").list();
            stopGroups = session.createQuery("FROM StopGroup").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public ObservableList<StopGroup> getList() { return list; }

}
