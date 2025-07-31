import controller.Controller;
import datasource.MySQLDataSourceManager;

public class App {
    public static void main(String[] args) {

        //Iniciando o Data Source
        MySQLDataSourceManager dataSource = new MySQLDataSourceManager("localhost", 3306, "escola");

        Controller controller = new Controller();

        controller.start();
    }
}
