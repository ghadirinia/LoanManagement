package business;

/**
 * Created by Dotin school 2 on 1/3/2015.
 * This exception accure when format of date and integer type for input information has witen incorrect.
 */
public class InputValuesIncorrectException extends ParentException {
    String inputValuesIncorrect;
    public void setInputValuesIncorrect(String inputValuesIncorrect){
        this.inputValuesIncorrect = inputValuesIncorrect;
    }
    public String getInputValuesIncorrect(){
        return inputValuesIncorrect;
    }
}
