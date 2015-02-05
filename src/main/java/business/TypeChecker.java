package business;


import domain.GrantCondition;
import business.CRUD.GrantConditionsCRUD;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.lang.Integer.*;

/**
 * Created by Marzieh on 12/25/2014.
 * In this class we check input information format.
 */
public class TypeChecker {
    private long grantId;

    public long getGrantId() {
        return grantId;
    }

    public void setGrantId(long grantId) {
        this.grantId = grantId;
    }

    public boolean checkInt(String value) {
        if (value.isEmpty()) return false;
        for (int i = 0; i < value.length(); i++) {
            if (i == 0 && value.charAt(i) == '-') {
                if (value.length() == 1) return false;
                else {
                    continue;
                }
            }
            if (Character.digit(value.charAt(i), 10) < 0) return false;
        }
        return true;
    }

    public int convertToInt(String value) {
        return parseInt(value);
    }

    public boolean checkDateFormat(String stringDate) {
        String[] date = stringDate.split("/");
        if (date.length == 3)
            if (date[0].length() == 4)
                if (date[1].length() == 2)
                    if (date[2].length() == 2)
                        if (checkInt(date[0]) && checkInt(date[1]) && checkInt(date[2]))
                            return true;
                        else
                            return false;
                    else
                        return false;
                else
                    return false;
        return false;
    }

    public boolean checkNationalIdFormat(String nationalId) {

        if (nationalId.length() == 10) {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += parseInt("" + nationalId.charAt(i)) * (i + 1);
            }
            if (sum > 0)
                return (sum % 11 == parseInt("" + nationalId.charAt(9))) ? true :
                        ((sum % 11) == (11 - (parseInt("" + nationalId.charAt(9))))) ? true :
                                false;
        }
        return false;
    }

    public boolean checkInValueAndDuration(String value, String id, String duration) {
        BigDecimal bigDecimalValue = new BigDecimal(Integer.parseInt(value));
        ArrayList<GrantCondition> grantConditionsArrayList = GrantConditionsCRUD.retrieve(id);
        if (grantConditionsArrayList.size() > 0) {
            for (GrantCondition grantConditions : grantConditionsArrayList) {
                if (grantConditions.getMaximumAmount().compareTo(bigDecimalValue) >= 0) {
                    if (grantConditions.getMinimumAmount().compareTo(new BigDecimal(Integer.parseInt(value))) <= 0)
                        if (grantConditions.getMaximumDuration().compareTo(new BigDecimal(parseInt(duration))) >= 0) {
                            if (grantConditions.getMinimumDuration().compareTo(new BigDecimal(parseInt(duration))) <= 0) {
                                setGrantId(grantConditions.getRecordId());
                                return true;
                            }
                        }
                }
            }
        }
        return false;
    }
}

