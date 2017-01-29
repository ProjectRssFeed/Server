/**
 * Created by Spoony on 24/01/2017.
 */

import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class RSSMan {
    public RSSMan() throws Exception {
        this.Listener();
    }

    public void Listener() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8443), 0);
        server.createContext("/v0.1/rss", new RSSHandler());
        server.setExecutor(null);
        server.start();
    }
}
