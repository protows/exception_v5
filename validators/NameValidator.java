package exception.v4.validators;
import exception.v4.exceptions.ValidateException;

public class NameValidator implements IValidator{
    
    @Override
    public void validate(String value) throws ValidateException{
        if(value==null || value.isEmpty()){
            throw new ValidateException("Ошибка валидации имени");
        }
    }
}
