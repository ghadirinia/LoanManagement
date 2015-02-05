package domain;

import java.io.Serializable;

/**
 * Created by Dotin school 2 on 1/10/2015.
 */
public class RealCustomer extends Customer{

    private int nationalId;
    private String first;
    private String last;
    private String fatherName;
    private String birthDate;

    public RealCustomer() {
    }


    public RealCustomer(int nationalId, String birthDate, String fatherName, String last, String first) {
        this.nationalId = nationalId;
        this.birthDate = birthDate;
        this.fatherName = fatherName;
        this.last = last;
        this.first = first;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

}
