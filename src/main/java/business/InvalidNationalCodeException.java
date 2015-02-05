package business;

/**
 * Created by Dotin school 2 on 1/12/2015.
 */
public class InvalidNationalCodeException extends ParentException{
    String excDuplicateEconomicId;
    public void setDuplicateNationalId(String excDuplicateEconomicId){
        this.excDuplicateEconomicId = excDuplicateEconomicId;
    }
    public String getExcDuplicateEconomicId(){
        return excDuplicateEconomicId;
    }
}
