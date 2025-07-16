import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.Properties;

/**
 * This solution adds a layer of security preventing SQL Injections.
 */


public class C_SecurityJDBC {
    public static void main(String[] args) {
        Properties props = new Properties();

        // Loading Connection properties from music.properties text file
        try {
            props.load(Files.newInputStream(Path.of("music.properties"), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Instantiating data source object
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        /*
         Validating the ID against SQL Injections
         */
        String artistIdInput = Leitor.lerString("Enter an artist ID:");
        int artistId = Integer.parseInt(artistIdInput);

        // Create query String
        String query = "SELECT * FROM music.artists WHERE artist_id=%s".formatted(artistId);

        // Establishing the connection
        try (Connection connection = dataSource.getConnection(
                props.getProperty("user"),
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            System.out.println("Success!");
            ResultSet resultSet = statement.executeQuery(query);

            // Getting ResultSet Metadata
            ResultSetMetaData meta = resultSet.getMetaData();

            // Displaying headers for the table
            for (int i = 1; i <= meta.getColumnCount(); i++ ) {
                System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
            }

            System.out.println();


            // Displaying ResultSet content table
            while (resultSet.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++ ) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
