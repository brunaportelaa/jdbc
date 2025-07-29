package datasource;

import org.h2.jdbcx.JdbcDataSource;
import javax.sql.DataSource;

public class H2DatasourceFactory implements DatasourceFactory {
        @Override
        public DataSource createDataSource() {
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL("jdbc:h2:mem:testdb");
            dataSource.setUser("sa");
            dataSource.setPassword("");
            return dataSource;
    }
}
