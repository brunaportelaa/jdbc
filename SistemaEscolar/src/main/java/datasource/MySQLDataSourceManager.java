package datasource;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDataSourceManager implements DataSourceManager {

    private static MysqlDataSource ds;

    public MySQLDataSourceManager(String serverName, int port, String dbName) {
        ds = new MysqlDataSource();
        ds.setServerName(serverName);
        ds.setPort(port);
        ds.setDatabaseName(dbName);
        ds.setUser(System.getenv("MYSQL_USER"));
        ds.setPassword(System.getenv("MYSQL_PASS"));
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
