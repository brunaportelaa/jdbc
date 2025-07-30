package datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSourceManager {
    public Connection getConnection()  throws SQLException;
}
