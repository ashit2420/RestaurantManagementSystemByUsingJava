package restaurant_management;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
	static Connection con;     //To store connection    //non-static member can't be used inside static function so con is of static type
    public static Connection createConnection() {
 	   
 	   
 	   //load the driver
 	   try {
 		   Class.forName("com.mysql.cj.jdbc.Driver");
 		   
 		   //create connection
 		   String user="root";
 		   String password="passw0rd";
 		   String url="jdbc:mysql://localhost:3306/Restaurant_management";
 		   
 		   con=DriverManager.getConnection(url,user,password);
 		   
 	   }
 	   catch(Exception e) {
 		   e.printStackTrace();
 	   }
 	   
 	   return con;
 	   
    }
}
