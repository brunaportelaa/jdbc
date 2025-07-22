import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class D_LearningExecute {
    public static void main(String[] args) {

        // Instantiating DataSource
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("music");
        dataSource.setPort(3306);

        // Establish connection
        try (Connection connection = dataSource.getConnection(
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
                Statement statement = connection.createStatement()) {

            // Unexisting artist name
            String artist = "Neil Young";

            String query = "SELECT * FROM music.artists WHERE artist_name='%s'".formatted(artist);

            // Result always returns true, even if ResultSet is empty
            // Execute will always return true for SELECT queries
            boolean result = statement.execute(query);
            System.out.println("Result: " + result); // true
            System.out.println("Result has any content:" + statement.getResultSet().next()); // false

            // Getting the result set
            ResultSet rs = statement.getResultSet();
            boolean found = (rs!=null && rs.next());
            System.out.println("Artist was " + (found ? "found" : "not found"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
