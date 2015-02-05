package business;

/**
 * Created by Dotin school 2 on 1/1/2015.
 * This exception accure when economic code that input for registration has duplicated.
 */
public class DuplicateEconomicIdException extends ParentException {
    String excDuplicateEconomicId;
    public void setDuplicateNationalId(String excDuplicateEconomicId){
        this.excDuplicateEconomicId = excDuplicateEconomicId;
    }
    public String getExcDuplicateEconomicId(){
        return excDuplicateEconomicId;
    }
}
