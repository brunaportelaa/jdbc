import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.Properties;

/**
 * This solution displays ust the track number, artist and Song name, unformatted
 */

public class A_SolutionOne {
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
        String albumName = "Tapestry";
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

            // Displaying ResultSet content table
            while (resultSet.next()) {
                System.out.printf("%d %s %s %n",
                        resultSet.getInt("track_number"),
                        resultSet.getString("artist_name"),
                        resultSet.getString("song_title"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
