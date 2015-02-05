package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dotin school 2 on 1/18/2015.
 */

public class LoanType implements Serializable {
    private Long id;
    private String loanName;
    private int interestRate;
    private Set<GrantCondition> grantConditions = new HashSet<GrantCondition>(0);

    public Set<GrantCondition> getGrantConditions() {
        return grantConditions;
    }

    public void setGrantConditions(Set<GrantCondition> grantConditions) {
        this.grantConditions = grantConditions;
    }

    public LoanType() {
    }

    public LoanType(String loanName, int interestRate, Set<GrantCondition> grantConditionsHashSet) {
        this.loanName = loanName;
        this.interestRate = interestRate;
        this.grantConditions = grantConditionsHashSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

//    private Collection<GrantConditions> grantConditions;
//
//    @OneToMany(mappedBy = "LoanType")
//    public Collection<GrantConditions> getGrantConditions() {
//        return grantConditions;
//    }
//
//    public void setGrantConditions(Collection<GrantConditions> grantConditions) {
//        this.grantConditions = grantConditions;
//    }
}
