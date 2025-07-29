package datasource;

import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;

public class PostgresSQLDatasourceFactory implements DatasourceFactory {
    @Override
    public DataSource createDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{"localhost"});
        dataSource.setDatabaseName("your_database");
        dataSource.setUser("your_user");
        dataSource.setPassword("your_password");
        return dataSource;
    }
}
