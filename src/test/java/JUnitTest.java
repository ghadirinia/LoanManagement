import business.CRUD.LegalCustomerCRUD;
import business.util.HibernateUtil;
import domain.LegalCustomer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;

/**
 * Created by DotinSchool2 on 2/3/2015.
 */
public class JUnitTest {
    @Test
    public void insertInDataBase(){
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setName("DataProcess");
        legalCustomer.setEconomicId(78711);
        legalCustomer.setRegisterDate("1391/12/05");
        LegalCustomerCRUD.save(legalCustomer);
    }
}
