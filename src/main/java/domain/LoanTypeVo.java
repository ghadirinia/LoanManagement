package domain;

import java.io.Serializable;

/**
 * Created by Dotin school 2 on 1/24/2015.
 */
public class LoanTypeVo implements Serializable {
    private String loanTypeName;
    private String loanTypeId;

    public LoanTypeVo() {
    }

    public LoanTypeVo(String loanTypeName, String loanTypeId) {
        this.loanTypeName = loanTypeName;
        this.loanTypeId = loanTypeId;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    public String getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(String loanTypeId) {
        this.loanTypeId = loanTypeId;
    }
    //    ArrayList<LoanType> loanTypes ;
//
//    public LoanTypeVo() {
//        this.loanTypes =  new ArrayList<LoanType>();
//    }
//    public void delete(LoanType loanType){
//        loanTypes.remove(loanType);
//    }
//    public void save(LoanType loanType){
//        loanTypes.save(loanType);
//    }
//    public ArrayList<LoanType> getAllLoanTypes(){
//        return  loanTypes;
//    }
}
