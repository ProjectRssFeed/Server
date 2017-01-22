/**
 * Created by Spoony on 23/01/2017.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLConnect {
    private Connection conn;
    private Statement st;
    private ResultSet rs;


    public SQLConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rssfeed", "root", "helloworld");
            st = conn.createStatement();
        } catch(Exception ex) {
            System.out.println("Error:"+ex);
        }
    }

    public void getData() {
        try {

        } catch(Exception ex) {
            System.out.println("Error:"+ex);
        }
    }
}
