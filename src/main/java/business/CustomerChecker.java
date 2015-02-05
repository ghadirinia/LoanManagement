package business;

import business.CRUD.LegalCustomerCRUD;
import business.CRUD.RealCustomerCRUD;
import org.apache.log4j.Logger;

/**
 * Created by Dotin school 2 on 1/17/2015.
 */
public class CustomerChecker {

    public boolean check ;
    public boolean checkDuplicate ;
    public  boolean validNationalId ;
    final static Logger loggerCustomerChecker = Logger.getLogger(CustomerChecker.class);
    public void checkLegalCustomer(String registerDate, String economicId) throws ParentException {

        TypeChecker typeChecker = new TypeChecker();
        if(typeChecker.checkDateFormat(registerDate))
            if(typeChecker.checkInt(economicId))
                if(LegalCustomerCRUD.retrieve(economicId,"","").size()==0){
                    check = true;
                    checkDuplicate = false;
                }
                else{
                    check = true;
                    checkDuplicate = true;
                    loggerCustomerChecker.error("Duplicate Economic ID.");
                    throw  new DuplicateEconomicIdException();
                }
             else{

                loggerCustomerChecker.error("Economic Id must be inetger.");
                throw new InputValuesIncorrectException();
            }
        else{
            loggerCustomerChecker.error("Input Date format is incorrect,Please write in correct format:yyyy/mm/dd.");
            throw new InputValuesIncorrectException();
        }

    }
    public void checkRealCustomer(String birthDate, String nationalId) throws ParentException {

        TypeChecker typeChecker = new TypeChecker();
        if(typeChecker.checkDateFormat(birthDate))
            if(typeChecker.checkInt(nationalId))
                if(RealCustomerCRUD.retrieve(nationalId,"","","").size()==0){
                    if(typeChecker.checkNationalIdFormat(nationalId)){
                        check = true;
                        checkDuplicate = false;
                        validNationalId = true;
                    }
                    else{
                        check = true;
                        checkDuplicate = false;
                        validNationalId = false;
                        loggerCustomerChecker.error("The nationalId format is incorrect.");
                        throw new InvalidNationalCodeException();
                    }
                }
                else{
                    check = true;
                    checkDuplicate = true;
                    loggerCustomerChecker.error("This national Id is duplicate.");
                    throw  new DuplicateNationalIdException();
                }
            else{

                loggerCustomerChecker.error("The national Id must be a 10 digit number.");
                throw new InputValuesIncorrectException();
            }
        else{
            loggerCustomerChecker.error("Input Date format is incorrect,Please write in correct format:yyyy/mm/dd.");
            throw new InputValuesIncorrectException();
        }

    }
}
