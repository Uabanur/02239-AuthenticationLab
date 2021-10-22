package server;

import java.sql.SQLException;

public interface IUserAuthenticator {
    public boolean checkEncryptedPassword(String username, String encryptedPassword) throws SQLException;
}
