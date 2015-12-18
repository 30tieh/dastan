package database;

import database.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * Created by hossein on 11/19/15.
 */
public class DBManager
{
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public static void shutdown() {
        HibernateUtil.shutdown();
    }

    public static void insert(Object o) {
        Transaction transaction = session.beginTransaction();
        session.save(o);
        if (!transaction.wasCommitted()) {
            transaction.commit();
        }
    }

    public static List getQueryResult(String hql) {
        Query query = session.createQuery(hql);
        List results = query.list();
        return results;
    }
}