import java.sql.*;
import java.util.Date;

public class AppInitializer {
    public static void main(String[] args) {
        try {
            Customer customer = new Customer(1002, "kamal chandana", "Ratmalana", 95000, "2025-02-16");
            if (saveCustomer(customer)){
                System.out.println("Success!");
            }else {
                System.out.println("Try Again!");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    private static Customer findById(long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer WHERE id=?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setLong(1,id);
        ResultSet set = statement.executeQuery();
        if (set.next()){
            return new Customer(
                    set.getLong(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4),
                    set.getString(5)
            );
        }
        return null;
    }
    private static boolean saveCustomer(Customer c) throws ClassNotFoundException,SQLException{
            //create a query
            String sql = "INSERT INTO customer VALUES (?,?,?,?,?)";
            //statement (Prepared statement)
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1,c.getId());
            statement.setString(2,c.getName());
            statement.setString(3,c.getAddress());
            statement.setDouble(4,c.getSalary());
            statement.setObject(5,c.getDob());
            return statement.executeUpdate()>0;
    }
    private static Connection getConnection() throws ClassNotFoundException,SQLException{
        //Driver load
        Class.forName("com.mysql.cj.jdbc.Driver");
        //create a connection
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/haibarnate","root","BMTmadhuwa07");

    }
}
