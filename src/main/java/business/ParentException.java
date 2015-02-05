package business;

/**
 * Created by Dotin school 2 on 1/17/2015.
 */
public class ParentException extends Exception {
    String excDuplicateEconomicId;
    public void setDuplicateNationalId(String excDuplicateEconomicId){
        this.excDuplicateEconomicId = excDuplicateEconomicId;
    }
    public String getExcDuplicateEconomicId(){
        return excDuplicateEconomicId;
    }
}
