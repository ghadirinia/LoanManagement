package domain;


/**
 * Created by Dotin school 2 on 1/10/2015.
 */
public class LegalCustomer extends Customer{

    private int economicId;
    private String name;
    private String registerDate;

    public LegalCustomer(String registerDate, String name, int economicId) {
        this.registerDate = registerDate;
        this.name = name;
        this.economicId = economicId;
    }

    public LegalCustomer() {
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEconomicId() {
        return economicId;
    }

    public void setEconomicId(int economicId) {
        this.economicId = economicId;
    }
}
