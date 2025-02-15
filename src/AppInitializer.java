import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppInitializer {
    public static void main(String[] args) {
        try {
            //Driver load
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create a connection
            Connection con = DriverManager.getConnection('','','')
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
