/**
 * Created by Spoony on 23/01/2017.
 */

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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", this.user, this.password);
            st = conn.createStatement();
            st.executeUpdate("CREATE DATABASE "+this.dbName);
            conn.close();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+this.dbName+"?useSSL=false", this.user, this.password);
            st.close();
            st = conn.createStatement();
        } catch(SQLException sqlex) {
            if (sqlex.getErrorCode() == 1007) {
                try {
                    conn.close();
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+this.dbName+"?useSSL=false", this.user, this.password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    st.close();
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
        this.initTables();
    }

    private void initTables() {
        String query = "CREATE TABLE IF NOT EXISTS feed (id INT NOT NULL AUTO_INCREMENT, title VARCHAR(64), description VARCHAR(255), link VARCHAR(255), PRIMARY KEY (id))";
        try {
            this.st.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddRSS(String link, String name, String description) {
        String query = "INSERT INTO feed (link, title, description) VALUES('"+link+"', '"+name+"', '"+description+"');";
        try {
            st.executeUpdate(query);
        } catch(Exception ex) {
            System.out.println("Error: "+ex);
        }
    }

    public void DeleteRSS(String id) {
        String query = "DELETE FROM feed WHERE id = "+id+"";
        try {
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String GetLink(String id) {
        String query = "SELECT link FROM feed WHERE id = '"+id+"'";
        try {
            ResultSet res = st.executeQuery(query);
            res.first();
            String link = res.getString("link");
            res.close();
            return link;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String Getfeeds() {
        String query = "SELECT id, title, description FROM feed";
        String list = "[";
        try {
            ResultSet res = st.executeQuery(query);
           while (res.next()) {
                if (list.length() > 1) {
                    list += ",";
                }
                list += "{\"Id\":"+res.getString("id")+",\"Title\":\"" +res.getString("title")+"\",\"Description\":\""+res.getString("description")+"\"}";
            }
            list += "]";
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
