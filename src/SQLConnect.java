/**
 * Created by Spoony on 23/01/2017.
 */

import java.lang.reflect.Executable;
import java.sql.*;

public class SQLConnect {
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    private String dbName = "rss";
    private String user = "root";
    private String password = "helloworld";

    public SQLConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", this.user, this.password);
            st = conn.createStatement();
            st.executeUpdate("CREATE DATABASE "+this.dbName);
        } catch(SQLException sqlex) {
            if (sqlex.getErrorCode() == 1007) {
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+this.dbName, this.user, this.password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    st = conn.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                sqlex.printStackTrace();
            }
        } catch(Exception ex) {
            System.out.println("Error:"+ex);
        }
    }

    public void AddRSS(String link) {
        String query = "INSERT INTO rsslink (link) VALUES('"+link+"');";
        try {
            st.executeUpdate(query);
        } catch(Exception ex) {
            System.out.println("Error: "+ex);
        }
    }
}
