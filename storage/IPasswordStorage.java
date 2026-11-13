package exception.v4.storage;
import java.io.IOException;
public interface IPasswordStorage {
    void open() throws IOException;

    void store(String user, String password) throws IOException;

    String get(String user) throws IOException;

    void close();
}
