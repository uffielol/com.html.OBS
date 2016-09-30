package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    
  public static Connection prepareConnection() throws SQLException, ClassNotFoundException{
        
        //database url
        String connectionURL = "jdbc:mysql://localhost:3306/dbs";
        
        //database credentials
        String uname = "root";
        String pwd = "optsql@2016";
        
        //register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        //forname -create instance of driver class
        
        //open connection
        return DriverManager.getConnection(connectionURL,uname,pwd);
        
    }

}

