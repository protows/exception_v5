package exception.v4.exceptions;
public class ValidatePasswordException extends ValidateException {
    private final String inputValue;

    public ValidatePasswordException(final String message, final String inputValue) {
        super(message);
        this.inputValue= inputValue;
    }
    
     public String getDetailMessage(){
        return getMessage() + ": " + inputValue;
    }
}
