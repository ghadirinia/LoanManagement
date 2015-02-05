package business.CRUD;

import business.util.HibernateUtil;
import domain.Customer;
import domain.LegalCustomer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Dotin school 2 on 1/13/2015.
 */
public class LegalCustomerCRUD {
    final static Logger logger = Logger.getLogger(String.valueOf(LegalCustomerCRUD.class));
    public  static void save(LegalCustomer legalCustomer){
        Session session = null;
        try {
            System.out.println("Hibernate one to one (XML mapping)");
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            //Start transaction block
            Transaction tx = session.beginTransaction();
            session.save(legalCustomer);
            tx.commit();
            logger.info("Legal customer with economic code equals:"+legalCustomer.getEconomicId()+" has saved to database.");
            //End of transaction block
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public  static void remove(String economicId){
        Session session = null;
        try {
            System.out.println("Hibernate one to one (XML mapping)");
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("SELECT * FROM legalcustomer where economicId=:economicId");
            query.setParameter("economicId",Integer.parseInt(economicId));
            List<Object[]> dataList = query.list();
            Object[] object;
            int i = dataList.size()-1;
            while(i>=0) {
               object = dataList.get(i);
                //(String registerDate, String name, int economicId)
                String Id = ""+object[0];
               LegalCustomer legalCustomer = new LegalCustomer((String)object[3],(String)object[2],(Integer)object[1]);
                legalCustomer.setCustomerId((Long.parseLong(Id)));
               session.delete(legalCustomer);
                i--;
            }
            tx.commit();
            logger.info("Legal customer with economic code equals:"+economicId+" have removed from dataBased.");
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public  static ArrayList<LegalCustomer> retrieve(String economicId,String customerId, String name){
        ArrayList<LegalCustomer> legalCustomers = new ArrayList<LegalCustomer>();
        Session session = null;
        try {
            System.out.println("Hibernate one to one (XML mapping)");
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            //Start transaction block
            Transaction tx = session.beginTransaction();
            String sql= "SELECT customerId,registerDate,name,economicId FROM legalcustomer where 1=1";
            if(!economicId.equals("")){
                sql += " and economicId=:economicId";
                if(!customerId.equals("")) {
                    sql += " and customerId=:customerId";
                    if(!name.equals("")){
                        sql += " and name=:name";
                    }
                }
            }
            else  if(!customerId.equals("")) {
                sql += " and customerId=:customerId";
                if(!name.equals("")){
                    sql += " and name=:name";
                }
            }
            else if(!name.equals("")){
                sql += " and name=:name";
            }
            Query query = session.createSQLQuery(sql);
            if(!economicId.equals("")){
                query.setParameter("economicId",economicId);
                if(!customerId.equals("")) {
                    query.setParameter("customerId",customerId);
                    if(!name.equals("")){
                        query.setParameter("name",name);
                    }
                }
            }
            else  if(!customerId.equals("")) {
                query.setParameter("customerId",customerId);
                if(!name.equals("")){
                    query.setParameter("name",name);
                }
            }
            else if(!name.equals("")){
                query.setParameter("name",name);
            }
            List<Object[]> dataList = query.list();
            Object[] object;
            int i = dataList.size()-1;
            while(i>=0) {
                object = dataList.get(i);
                LegalCustomer legalCustomer = new LegalCustomer((String)object[1],(String)object[2],(Integer)object[3]);
                legalCustomer.setCustomerId(Long.parseLong(""+object[0]));
                legalCustomers.add(legalCustomer);
                i--;
            }

            //End of transaction block
            tx.commit();
            logger.info("Legal customers have retrieved from dataBased.");
            //System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return legalCustomers;
    }
    public static void modify(String economicId,String customerId, String name, String registerDate){
        Session session = null;
        try {
            System.out.println("Hibernate one to one (XML mapping)");
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            //Start transaction block
            Transaction tx = session.beginTransaction();
            //(String registerDate, String name, int economicId)
            Query query = session.createSQLQuery("SELECT customerId,name,registerDate FROM legalcustomer where economicId= "+economicId+"");
            List<Object[]> dataList = query.list();
            Object[] object;
            int i = dataList.size()-1;
            while(i>=0) {
                object = dataList.get(i);
                LegalCustomer legalCustomer = new LegalCustomer((String)object[2],(String)object[1],Integer.parseInt(economicId));
                String Id = ""+object[0];
                legalCustomer.setCustomerId(Long.parseLong(Id));
                session.delete(legalCustomer);
                if(!registerDate.equals(""))
                    legalCustomer.setRegisterDate(""+registerDate);
                if(!customerId.equals(""))
                    legalCustomer.setCustomerId(Long.parseLong("" + customerId));
                if(!name.equals(""))
                    legalCustomer.setName(name);
                Customer customer = new Customer();
                customer.setCustomerId(Long.parseLong("" + customerId));
                session.save(legalCustomer);
                i--;
            }
            logger.info("Legal customer's information with economic code equals:"+economicId+" has modified");
            tx.commit();
            //End of transaction block
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
