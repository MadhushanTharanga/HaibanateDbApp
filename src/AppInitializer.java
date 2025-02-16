import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppInitializer {
    public static void main(String[] args) {
//        try {
//            Customer customer = new Customer(1002, "kamal chandana", "Ratmalana", 95000, "2025-02-16");
//            if (saveCustomer(customer)){
//                System.out.println("Success!");
//            }else {
//                System.out.println("Try Again!");
//            }
//        }catch (ClassNotFoundException | SQLException e){
//            e.printStackTrace();
//        }
//        try {
//            Customer customer = findById(1003);
//            if (customer!=null){
//                System.out.println("Success!");
//                System.out.println(customer.toString());
//            }else {
//                System.out.println("Try Again!");
//            }
//        }catch (ClassNotFoundException | SQLException e){
//            e.printStackTrace();
//        }
//        try {
//            List<Customer> customers = findAll();
//            if (!customers.isEmpty()){
//                System.out.println("Success!");
//                for (Customer c:customers){
//                    System.out.println(c.toString());
//                }
//
//            }else {
//                System.out.println("Try Again!");
//            }
//        }catch (ClassNotFoundException | SQLException e){
//            e.printStackTrace();
//        }
        try {
            Customer customer = new Customer(1006, "Amith", "kandy", 1000000, "2026-12-12");
            if (updateCustomer(customer)){
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
    private static List<Customer> findAll() throws SQLException, ClassNotFoundException {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        ResultSet set = statement.executeQuery();
        while (set.next()){
            customerList.add(
                    new Customer(
                            set.getLong(1),
                            set.getString(2),
                            set.getString(3),
                            set.getDouble(4),
                            set.getString(5)
                    )
            );
        }
        return customerList;
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
    private static boolean updateCustomer(Customer c) throws ClassNotFoundException,SQLException{
        //create a query
        String sql = "UPDATE customer SET name=?, address = ?, salary=?, dob=? WHERE id=?";
        //statement (Prepared statement)
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1,c.getName());
        statement.setString(2,c.getAddress());
        statement.setDouble(3,c.getSalary());
        statement.setObject(4,c.getDob());
        statement.setLong(5,c.getId());
        return statement.executeUpdate()>0;
    }
    private static Connection getConnection() throws ClassNotFoundException,SQLException{
        //Driver load
        Class.forName("com.mysql.cj.jdbc.Driver");
        //create a connection
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/haibarnate","root","BMTmadhuwa07");

    }
}
