//https://github.com/practicetasks/java_tasks/blob/main/exceptions/task_6/README.md
//Доработайте код приложения для хранения и обработки паролей пользователей.
//По аналогии с классом ValidatePasswordException для ошибок при вводе пароля допишите код класса ValidateNameException для ошибок при вводе имени пользователя. Оба класса должны наследовать от ValidateException и принимать короткое сообщение об ошибке.
//В методах класса PasswordMemoryStorage пропущены предупреждения об исключении IOException — добавьте их.
//Допишите код класса NameValidator — он должен реализовывать интерфейс Validator и проверять, не передана ли пустая строка. В обратном случае — генерировать исключение ValidateNameException с сообщением «Имя не должно быть пустым».
//Добавьте реализованный экземпляр класса NameValidator в список nameValidators.
//Добавьте отлов исключений ValidateNameException и ValidatePasswordException в методы класса Practice. При этом выведите сообщения формата:
//Ошибка валидации имени: + короткое описание ошибки.
//Ошибка валидации пароля: + короткое описание ошибки.
//Организуйте закрытие хранилища storage при помощи метода close() при любом развитии событий в методах addUser() и showUserPassword() класса Practice.
package exception.v4;
import exception.v4.exceptions.ValidateException;
import exception.v4.storage.PasswordMemoryStorage;
import exception.v4.storage.IPasswordStorage;
import exception.v4.validators.PasswordLengthValidator;
import exception.v4.validators.PasswordStrengthValidator;
import exception.v4.validators.IValidator;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import exception.v4.validators.NameValidator;
import  exception.v4.exceptions.ValidateNameException;
import  exception.v4.exceptions.ValidatePasswordException;

public class Start {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<IValidator> passwordValidators = List.of(
            new PasswordLengthValidator(5), new PasswordStrengthValidator()
    );

    private static final List<IValidator> nameValidators = List.of(new NameValidator()); // поработайте со списком

    public static void main(String[] args) {
        loop();
    }

    public static void loop() {
        while (true) {
            final String action = getAction();
            if ("1".equals(action)) {
                addUser();
            } else if ("2".equals(action)) {
                showUserPassword();
            }else if ("q".equals(action)) {
                break;
            } else {
                break;
            }
        }
    }

    private static void checkValidatorRules(
            final List<IValidator> validators, final String value
    ) throws ValidateException {
        for (IValidator validator : validators) {
            validator.validate(value);
        }
    }

    private static void addUser() {
        final IPasswordStorage storage = new PasswordMemoryStorage();
        // добавьте отлов исключений ValidateNameException и ValidatePasswordException
        // закройте хранилище
        try {
            storage.open();
            System.out.println("Введите имя пользователя => ");
            final String name = scanner.nextLine();
            checkValidatorRules(nameValidators, name);
            System.out.println("Введите пароль пользователя => ");
            final String password = scanner.nextLine();           
            checkValidatorRules(passwordValidators, password);
            storage.store(name, password);
        } catch(ValidateNameException e){
            System.out.println(e.getDetailMessage());
        }catch(ValidatePasswordException e){
            System.out.println(e.getDetailMessage());
        }catch (ValidateException e) {
            System.out.println("Ошибка валидации: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка работы с хранилищем: " + e.getMessage());
        }finally{
            storage.close();
        }
    }

    private static void showUserPassword() {
        final IPasswordStorage storage = new PasswordMemoryStorage();
        // добавьте отлов исключения ValidateNameException
        // закройте хранилище
        try {
            storage.open();
            System.out.println("Введите имя пользователя => ");
            final String name = scanner.nextLine();
            checkValidatorRules(nameValidators, name);
            final String password = storage.get(name);
            System.out.println(String.format("Пароль пользователя %s = %s", name, password));
        } catch (ValidateException e) {
            System.out.println("Ошибка валидации: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка работы с хранилищем: " + e.getMessage());
        }finally{
            storage.close();
        }
    }

    private static String getAction() {
        System.out.println("1 - добавить пользователя с паролем");
        System.out.println("2 - отобразить пароль пользователя");
         System.out.println("q - чтобы выйти из цикла");
        System.out.println("другие символы - выход");
        System.out.println("Выберите действие => ");
        return scanner.nextLine();
    }
}
