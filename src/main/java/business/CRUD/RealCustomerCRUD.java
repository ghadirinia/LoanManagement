package business.CRUD;

import business.util.HibernateUtil;
import domain.RealCustomer;
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
public class RealCustomerCRUD {
    final  static Logger logger = Logger.getLogger(String.valueOf(RealCustomerCRUD.class));
    public  static void save(RealCustomer realCustomer){
        Session session = null;
        try {
            System.out.println("Hibernate one to one (XML mapping)");
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            //Start transaction block
            Transaction tx = session.beginTransaction();
            session.save(realCustomer);
            tx.commit();
            logger.info("Real customer with national code equals to:"+realCustomer.getNationalId()+" has retrieved from dataBase");
            //End of transaction block
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public  static void remove(String nationalId){
        Session session = null;
        try {
            System.out.println("Hibernate one to one (XML mapping)");
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            //Start transaction block
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("SELECT customerId,birthDate,fatherName,last,first FROM realcustomer where nationalId=:nationalId");
            query.setParameter("nationalId",nationalId);
            List<Object[]> dataList = query.list();
            Object[] object;
            int i = dataList.size()-1;
            while(i>=0) {
                object = dataList.get(i);
                //(int nationalId, String birthDate, String fatherName, String last, String first)
                RealCustomer realCustomer = new RealCustomer(Integer.parseInt(nationalId),(String)object[1],(String)object[2],(String)object[3],(String)object[4]);
                String Id = ""+object[0];
                realCustomer.setCustomerId(Long.parseLong(Id));
                session.delete(realCustomer);
                i--;
            }

            tx.commit();
            logger.info("Real customer with national code equals to:"+nationalId+" has removed from dataBase.");
            //End of transaction block
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static ArrayList<RealCustomer> retrieve(String nationalId,String customerId, String first,String last){
        ArrayList<RealCustomer> realCustomers = new ArrayList<RealCustomer>();
        Session session = null;
        try {
            System.out.println("Hibernate one to one (XML mapping)");
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            //Start transaction block
            String sql= "SELECT nationalId,birthDate,fatherName,last,first FROM realcustomer where 1=1 ";
            if(!nationalId.equals("")){
                sql += " and nationalId="+nationalId;
                if(!customerId.equals("")) {
                    sql += " and customerId=" + customerId;
                    if(!first.equals("")){
                        sql += " and first='"+first+"'";
                        if(!last.equals("")) {
                            sql += " and last'" + last + "'";
                        }
                    }
                }
            }
            else  if(!customerId.equals("")) {
                sql += " and customerId=" + customerId;
                if(!first.equals("")){
                    sql += " and first='"+first+"'";
                    if(!last.equals("")) {
                        sql += " and last='" + last + "'";
                    }
                }
            }
            else if(!first.equals("")){
                sql += " and first='"+first+"'";
                if(!last.equals("")){
                    sql += " and last='"+last+"'";
                }
            }
            else if(!last.equals("")){
                sql += " and  last='"+last+"'";
            }
            Query query = session.createSQLQuery(sql);
            List<Object[]> dataList = query.list();
            Object[] object;
            int i = dataList.size()-1;
            while(i>=0) {
                object = dataList.get(i);
                //   public RealCustomer(int nationalId, String birthDate, String fatherName, String last, String first)
                RealCustomer realCustomer = new RealCustomer((Integer)object[0],(String)object[1],(String)object[2],(String)object[3],(String)object[4]);
                realCustomer.setCustomerId((Integer)object[0]);
                realCustomers.add(realCustomer);
                i--;
            }

            //End of transaction block
            //System.out.println("Done");
            logger.info("Real customers have retrieved from dataBase.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return realCustomers;
    }
    public static void modify(String nationalId,String customerId, String first, String last,String fatherName,String birthDate){
        Session session = null;
        try {
            System.out.println("Hibernate one to one (XML mapping)");
            HibernateUtil.buildSessionFactory();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            //Start transaction block
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("SELECT customerId,birthDate,fatherName,last,first FROM realcustomer where nationalId=:nationalId");
            query.setParameter("nationalId",nationalId);
            List<Object[]> dataList = query.list();
            Object[] object;
            int i = dataList.size()-1;
            while(i>=0) {
                object = dataList.get(i);
                //(int nationalId, String birthDate, String fatherName, String last, String first)
                RealCustomer realCustomer = new RealCustomer(Integer.parseInt(nationalId),(String)object[1],(String)object[2],(String)object[3],(String)object[4]);
                String Id = ""+object[0];
                realCustomer.setCustomerId(Long.parseLong(Id));
                if(!birthDate.equals(""))
                    realCustomer.setBirthDate(""+birthDate);
                if(!customerId.equals(""))
                    realCustomer.setCustomerId(Integer.parseInt(""+customerId));
                if(!first.equals(""))
                    realCustomer.setFirst(first);
                if(!last.equals(""))
                    realCustomer.setLast(last);
                if(!fatherName.equals(""))
                    realCustomer.setLast(fatherName);
                session.update(realCustomer);
                i--;
            }

            tx.commit();
            logger.info("Information of Real customer with national code equals to:"+nationalId+" has modified.");
            //End of transaction block
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
