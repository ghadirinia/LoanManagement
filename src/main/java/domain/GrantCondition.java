package domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Dotin school 2 on 1/18/2015.
 */
public class GrantCondition implements Serializable {
    private long recordId;
    private LoanType loanType;
    private String grantName;
    private BigDecimal minimumDuration;
    private BigDecimal maximumDuration;
    private BigDecimal minimumAmount;
    private BigDecimal maximumAmount;

    public GrantCondition() {
    }

    public GrantCondition(String grantName, BigDecimal minimumDuration, BigDecimal maximumDuration, BigDecimal minimumAmount, BigDecimal maximumAmount) {

        this.grantName = grantName;
        this.minimumDuration = minimumDuration;
        this.maximumDuration = maximumDuration;
        this.minimumAmount = minimumAmount;
        this.maximumAmount = maximumAmount;
    }


    public BigDecimal getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(BigDecimal maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(BigDecimal minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public BigDecimal getMaximumDuration() {
        return maximumDuration;
    }

    public void setMaximumDuration(BigDecimal maximumDuration) {
        this.maximumDuration = maximumDuration;
    }

    public BigDecimal getMinimumDuration() {
        return minimumDuration;
    }

    public void setMinimumDuration(BigDecimal minimumDuration) {
        this.minimumDuration = minimumDuration;
    }


    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getGrantName() {
        return grantName;
    }

    public void setGrantName(String grantName) {
        this.grantName = grantName;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
}
