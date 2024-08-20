import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            String url= "jdbc:mysql://localhost:3306/test";

            Connection con = DriverManager.getConnection(url, "root","root@123");

            if(con!= null) System.out.println("Connection done");

            Statement st= con.createStatement();

            ResultSet rs= st.executeQuery("select * from product");

            while(rs.next()){
                System.out.println("Id: "+ rs.getInt("pid"));
                System.out.println("Name: "+ rs.getString(2));
                System.out.println("Qty: "+ rs.getInt(3));
                System.out.println("price: "+ rs.getDouble(4));
                System.out.println("-----------");
            }
            int id= 109, qty= 2;
            double price= 2345;
            String nm= "bed";

            String query= "insert into product values("+id+",'"+nm+"',"+qty+","+price+")";

            System.out.println(query);

            int num= st.executeUpdate(query) ;

            if(num > 0) System.out.println(num +" record inserted");


        }catch (SQLException throwables) {
            System.out.println("Connection not done in catch");
            throwables.printStackTrace() ;
//                throw new RuntimeException(e);
        }

    }
}