package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthDatabase {
    private static Connection _connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(_connection != null && !_connection.isClosed()) return _connection;

        _connection = createInMemoryDatabase();
        populateTestData(_connection);
        printTestData(_connection);
        return _connection;
    }
    
    private static Connection createInMemoryDatabase() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite::memory:");
    }

    private static void populateTestData(Connection con) throws SQLException{
        Statement statement = con.createStatement();
        statement.executeUpdate("create table users (username varchar(30), password char(64))");
        statement.executeUpdate("insert into users values('nicu', 'RBJnRFRb8vJ8h8fJsg4kSwRvULJ9EJzne33zqoIciTkj4uh65nySLWxLkPcGt9df')");
        statement.executeUpdate("insert into users values('alex', 'vVOYR6iSnRp1zqe5560Hq3xbnwu6CDUvqK0v81bRvuxQTfoip2H7k8xI9NUU1y9i')");
        statement.executeUpdate("insert into users values('filip', 'BK7pr6aznGKFXuViYY2DAgTlBIlT5zf4b4Q4qcZr9CtZCQu6Yk49ri7UdkkFuF7I')");
        statement.executeUpdate("insert into users values('roar', 'OOL4fpi5DUESGgVvxNtDYNBsd4Pq0hmRXmr0zoPYNE5vH8Tw06d39eoamcVtF1qK')");
    }

    private static void printTestData(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("select * from users");
        System.out.println("User test data:");
        while(rs.next())
        {
            System.out.println("username: " + rs.getString("username") + " password: " + rs.getString("password"));
        }
        System.out.println();
    }
}

