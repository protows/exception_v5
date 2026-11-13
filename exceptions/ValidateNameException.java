package exception.v4.exceptions;

public class ValidateNameException extends ValidateException{
    private final String inputValue;
    // допишите код класса
    ValidateNameException(final String message, final String inputValue){
        super(message);
        this.inputValue = inputValue;
    }
    
    public String getDetailMessage(){
        return  getMessage() + ": " + inputValue;
    }
}
