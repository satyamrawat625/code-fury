import java.sql.*;

public class TestPreparedStatement {
    public static void main(String[] args) {

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            String url= "jdbc:mysql://localhost:3306/test?useSSL=false";

//            step2
            Connection con = DriverManager.getConnection(url, "root","root@123");

            if(con!= null) System.out.println("Connection done");

            // step3
            PreparedStatement pst= con.prepareStatement("select * from product");

            ResultSet rs= pst.executeQuery();

            while(rs.next()){
                System.out.println("Id: "+ rs.getInt("pid"));
                System.out.println("Name: "+ rs.getString(2));
                System.out.println("Qty: "+ rs.getInt(3));
                System.out.println("price: "+ rs.getDouble(4));
                System.out.println("-----------");
            }
            int id= 123, qty= 2;
            double price= 2345;
            String nm= "bed";

            PreparedStatement insdata= con.prepareStatement("insert into product values(?,?,?,?");

            insdata.setInt(1,10);
            insdata.setString(2,nm);
            insdata.setInt(3,qty);
            insdata.setDouble(4,price);

            int num= insdata.executeUpdate();

            if(num > 0) System.out.println(num +" record inserted");


        }catch (SQLException throwables) {
            System.out.println("Connection not done in catch");
            throwables.printStackTrace() ;
        }

    }
}