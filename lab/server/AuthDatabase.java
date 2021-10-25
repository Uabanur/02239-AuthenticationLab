package server;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Random;

public class AuthDatabase {
    private static Connection _connection;
    private static Random RANDOM = new SecureRandom();

    public static Connection getConnection() throws SQLException{
        if(_connection != null && !_connection.isClosed()) return _connection;

        _connection = createInMemoryDatabase();
        populateTestData(_connection);
        printTestData(_connection);
        return _connection;
    }
    
    private static Connection createInMemoryDatabase() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite::memory:");
    }

    private static void populateTestData(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate("create table users (username varchar(30), password BLOB(64), salt BLOB(64))");
        String queryString = "insert into users values(?, ?, ?)";
        PreparedStatement stm = con.prepareStatement(queryString);

        String[] users = new String[]{ 
            "nicu/test123",
            "alex/alex_pass",
            "filip/filip_pass",
            "roar/roar_pass",
        };

        for (String user : users) {
            String[] parts = user.split("/");
            String username = parts[0];
            String password = parts[1];
            stm.setString(1, username);
            byte[] salt = new byte[512];
            RANDOM.nextBytes(salt);
            stm.setBytes(2, PasswordHash.hashPassword(password.toCharArray(), salt));
            stm.setBytes(3, salt);
            stm.executeUpdate();
        }

        // stm.setString(1, "nicu");
        // byte[] salt = new byte[512];
        // RANDOM.nextBytes(salt);
        // stm.setBytes(2, PasswordHash.hashPassword("test123".toCharArray(), salt));
        // stm.setBytes(3, salt);
        // stm.executeUpdate();

        // stm.setString(1, "alex");
        // RANDOM.nextBytes(salt);
        // stm.setBytes(2, PasswordHash.hashPassword("alex_pass".toCharArray(), salt));
        // stm.setBytes(3, salt);
        // stm.executeUpdate();

        // stm.setString(1, "filip");
        // RANDOM.nextBytes(salt);
        // stm.setBytes(2, PasswordHash.hashPassword("filip_pass".toCharArray(), salt));
        // stm.setBytes(3, salt);
        // stm.executeUpdate();

        // stm.setString(1, "roar");
        // RANDOM.nextBytes(salt);
        // stm.setBytes(2, PasswordHash.hashPassword("roar_pass".toCharArray(), salt));
        // stm.setBytes(3, salt);
        // stm.executeUpdate();
    }

    private static void printTestData(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("select * from users");
        System.out.println("User test data:");
        while(rs.next())
        {
            System.out.println("username: " + rs.getString("username") + " password: " + rs.getBytes("password") + " salt: " + rs.getBytes("salt"));
        }
        System.out.println();
    }
}

