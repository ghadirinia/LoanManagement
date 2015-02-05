package business.CRUD;

import business.util.HibernateUtil;
import domain.GrantCondition;
import domain.LoanType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Dotin school 2 on 1/18/2015.
 */
public class LoanTypeCRUD {
      final static Logger logger = Logger.getLogger(String.valueOf(LoanTypeCRUD.class));
    public static void save(LoanType loanType, HashSet<GrantCondition> grantConditionsHashSet){
        Session session = null;
        try {
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            for(GrantCondition grantCondition:grantConditionsHashSet)
            {
                grantCondition.setLoanType(loanType);
                session.save(grantCondition);
            }
            loanType.setGrantConditions(grantConditionsHashSet);
            session.save(loanType);
            tx.commit();
            logger.info("Loan Type with name:"+loanType.getLoanName()+" has saved to dataBase");

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public static ArrayList<LoanType> retrieve(){
        ArrayList<LoanType> loanTypesList = new ArrayList<LoanType>();
        Session session = null;
        try {
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT id,loanName,interestRate FROM loantype where 1=1");
            List<Object[]> list = query.list();
            Object[] object;
            int i = list.size()-1;
            while(i>=0) {
                object = list.get(i);
                LoanType loanType = new LoanType();
                loanType.setInterestRate((Integer) object[2]);
                loanType.setLoanName((String) object[1]);
                String typeCast = ""+ object[0];
                loanType.setId(Long.parseLong(typeCast));
                loanTypesList.add(loanType);
                i--;
            }
            logger.info("Loan Types has extracted from database.");
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return  loanTypesList;
    }
}
