package datasource;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDataSourceManager {

    private static MysqlDataSource ds;
    private static Connection connection;

    private MySQLDataSourceManager(){};

    public MySQLDataSourceManager(String serverName, int port, String dbName) {
        ds = new MysqlDataSource();
        ds.setServerName(serverName);
        ds.setPort(port);
        ds.setDatabaseName(dbName);
        ds.setUser(System.getenv("MYSQL_USER"));
        ds.setPassword(System.getenv("MYSQL_PASS"));
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = ds.getConnection();
        }
        return connection;
    }
}
