package Model;

import java.sql.*;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection()
    {
        //JDBC and database properties.
          String DB_DRIVER ="com.mysql.jdbc.Driver";
          String DB_URL ="jdbc:mysql://localhost:3306/bibliotheque";
          String DB_USERNAME = "root";
          String DB_PASSWORD = "";
        try{
            //Register the JDBC driver
            Class.forName(DB_DRIVER);

            //Open the connection
            databaseLink = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            }catch(Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
