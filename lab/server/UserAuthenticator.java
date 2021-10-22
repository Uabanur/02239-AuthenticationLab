package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthenticator implements IUserAuthenticator{
    private Connection _connection;

    private void connect() throws SQLException{
        _connection = AuthDatabase.getConnection();
    }

    public boolean checkEncryptedPassword(String username, String encryptedPassword) throws SQLException{
        if(_connection == null) connect();

        String query = "select password from users where username = ?";
        PreparedStatement statement = _connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();

        if(!result.next()) return false;
        String dbEncryptedPassword = result.getString("password");
        return encryptedPassword.equals(dbEncryptedPassword);
    }
}
