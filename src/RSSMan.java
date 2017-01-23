/**
 * Created by Spoony on 24/01/2017.
 */

import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class RSSMan {
    private SQLConnect conn;

    public RSSMan() throws Exception {
        this.conn = new SQLConnect();
        this.Listener();
    }

    public void Listener() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/v0.1/api/addrss/", new AddRSSHandler(this.conn));
        server.createContext("/v0.1/api/getrss/", new GetRSSHandler(this.conn));
        server.createContext("/v0.1/api/delete/", new DeleteRSSHandler(this.conn));
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
