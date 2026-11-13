package exception.v4.validators;
import exception.v4.exceptions.ValidateException;
public interface IValidator {
    void validate(String value) throws ValidateException;
}
