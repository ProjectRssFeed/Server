/**
 * Created by Spoony on 24/01/2017.
 */
public class RSSMan {
    private  SQLConnect conn;

    public RSSMan() {
        this.conn = new SQLConnect();
    }

    public void AddRss(String link, String name) {
        this.conn.AddRSS(link, name);
    }
}
