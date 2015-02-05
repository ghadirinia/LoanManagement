package domain;

import business.util.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 * Created by Dotin school 2 on 1/12/2015.
 */
public class Main {
    public static void main(String[] args) {
        HibernateUtil.buildSessionFactory();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.openSession();
        sessionFactory.close();

    }
}
