package datasource;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class MySQLDatasourceFactory implements DatasourceFactory {
    @Override
    public DataSource createDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("escola");
        return dataSource;
    }
}
