import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.Properties;

/**
 * This solution displays a complete table, as you would see in Workbench or similar.
 */


public class B_SolutionTwo {
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

        // Creating the query
        String albumName = Leitor.lerString("Enter an album name:");
        String query = "SELECT * FROM music.albumview WHERE album_name='%s'".formatted(albumName);

        // Establishing the connection
        try (Connection connection = dataSource.getConnection(
                props.getProperty("user"),
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            System.out.println("Success!");
            ResultSet resultSet = statement.executeQuery(query);

            // Displaying ResultSet Metadata
            ResultSetMetaData meta = resultSet.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%d %s %s %n", i, meta.getColumnName(i), meta.getColumnTypeName(i));
            }

            System.out.println("---------------------------");

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
