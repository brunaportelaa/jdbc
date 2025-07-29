package datasource;

import javax.sql.DataSource;

public interface DatasourceFactory {
    public DataSource createDataSource();

}
