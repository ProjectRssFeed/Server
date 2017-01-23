/**
 * Created by Spoony on 23/01/2017.
 */

import java.lang.reflect.Executable;
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

    public void AddRSS(String link, String name) {
        String query = "INSERT INTO rsslink (link, name) VALUES('"+link+"', '"+name+"');";
        try {
            st.executeUpdate(query);
        } catch(Exception ex) {
            System.out.println("Error: "+ex);
        }
    }
}
