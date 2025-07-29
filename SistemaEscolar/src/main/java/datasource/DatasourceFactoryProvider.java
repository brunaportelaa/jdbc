package datasource;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DatasourceFactoryProvider {
    public static DataSource createDatasource(String provider) {
        if (provider == null || provider.isEmpty()) {
            return null;
        }
        switch (provider.toLowerCase()) {
            case "postgres":
                return new PGSimpleDataSource();
            case "mysql":
                return new MysqlDataSource();
            case "h2":
                return new JdbcDataSource();
            default:
                throw new IllegalArgumentException("Unsupported database provider: " + provider);
        }
    }
}
