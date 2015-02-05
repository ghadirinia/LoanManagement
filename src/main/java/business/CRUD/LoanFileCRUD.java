package business.CRUD;

import business.util.HibernateUtil;
import domain.LoanFile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.logging.Logger;

/**
 * Created by Dotin school 2 on 1/27/2015.
 */
public class LoanFileCRUD {
    final static Logger logger = Logger.getLogger(String.valueOf(LoanFileCRUD.class));
    public static void save(LoanFile loanFile){
        Session session = null;
        try {
            System.out.println("Hibernate one to one (XML mapping)");
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(loanFile);
            tx.commit();

            logger.info("Loan file for loan type Id equals to :"+loanFile.getLoanTypeId()+" hav saved to dataBase.");
            //System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
