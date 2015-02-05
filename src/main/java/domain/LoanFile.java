package domain;

import java.io.Serializable;

/**
 * Created by Dotin school 2 on 1/26/2015.
 */
public class LoanFile implements Serializable {
    private long customerId;
    private int contractDuration;
    private int contractValue;
    private long loanTypeId;
    private long grantConditionId;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LoanFile() {
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(long loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public void setLoanTypeId(int loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(int contractDuration) {
        this.contractDuration = contractDuration;
    }

    public int getContractValue() {
        return contractValue;
    }

    public void setContractValue(int contractValue) {
        this.contractValue = contractValue;
    }

    public long getGrantConditionId() {
        return grantConditionId;
    }

    public void setGrantConditionId(long grantConditionId) {
        this.grantConditionId = grantConditionId;
    }
}
