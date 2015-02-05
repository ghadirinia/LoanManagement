package business.CRUD;

import business.util.HibernateUtil;
import domain.GrantCondition;
import domain.LoanType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Dotin school 2 on 1/24/2015.
 */
public class GrantConditionsCRUD {

    final static Logger logger = Logger.getLogger(String.valueOf(GrantConditionsCRUD.class));
    public  static void save(GrantCondition grantCondition, LoanType loanType){
        Session session = null;
        try {
            System.out.println("Hibernate one to one (XML mapping)");
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            //Start transaction block
            Transaction tx = session.beginTransaction();
            grantCondition.setLoanType(loanType);
            session.save(grantCondition);
            tx.commit();
            logger.info("Grand Conditions of loanType "+loanType.getLoanName()+" has saved in dataBase.");
            //End of transaction block
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static ArrayList<GrantCondition> retrieve(String id){
        ArrayList<GrantCondition> grantConditionsArrayList = new ArrayList<GrantCondition>();
        Session session = null;
        try {
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT recordId,minimumDuration,maximumDuration,minimumAmount,maximumAmount,grantName FROM grantconditions where loantype_id=:id");
            query.setParameter("id",Integer.parseInt(id));
            List<Object[]> list = query.list();
            Object[] object;
            int i = list.size()-1;
            while(i>=0) {
                object = list.get(i);
                GrantCondition grantConditions = new GrantCondition();
                grantConditions.setGrantName((String)object[5]);
                grantConditions.setMinimumDuration((BigDecimal)object[1]);
                grantConditions.setMaximumDuration((BigDecimal)object[2]);
                grantConditions.setMinimumAmount((BigDecimal)object[3]);
                grantConditions.setMaximumAmount((BigDecimal)object[4]);
                String recordId =""+object[0];
                grantConditions.setRecordId(Long.parseLong(recordId));
                grantConditionsArrayList.add(grantConditions);
                i--;
            }
            logger.info("Grand conditions with loanType_id equals:"+id+" has retrieved from dataBase.");

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return  grantConditionsArrayList;
    }
}
