import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class D_LearningExecute_2 {
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

           String tableName = "music.artists";
           String columnName = "artist_name";
           String columnValue = "Elf"; // Existing artist

            if (executeSelect(statement, tableName, columnName, columnValue)) {
                // Prints existing artist
            } else {
                System.out.println("Artist not found!");
            }

            columnValue = "Neil Young"; // Unexisting artist
            if (executeSelect(statement, tableName, columnName, columnValue)) {
            } else {
                System.out.println("Artist not found!"); // Gets printed to console
                System.out.println("Inserting new artist");
                insertRecord(statement, tableName, new String[]{columnName},new String[]{columnValue});
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean printRecords (ResultSet resultSet) throws SQLException {
        boolean foundData = false;
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
            foundData = true;
        }

        return foundData;
    }

    private static boolean executeSelect(Statement statement, String table, String columnName, String columnValue) throws SQLException {
        String query = "SELECT * FROM %s WHERE %s ='%s'".formatted(table, columnName, columnValue);
        var rs = statement.executeQuery(query);
        if (rs != null) {
            return printRecords(rs);
        }
        return false;
    }

    private static boolean insertRecord(Statement statement, String table, String[] columnNames, String[] columnValues) throws SQLException{
        String colNames = String.join(",", columnNames);
        String colValues = String.join(",", columnValues);
        String query = "INSERT INTO %s (%s) VALUES ('%S')".formatted(table, colNames, colValues);
        statement.execute(query);
        if (statement.getUpdateCount() > 0) {
            return executeSelect(statement, table, columnNames[0], columnValues[0]);
        } else {
            return false;
        }
    }
}
