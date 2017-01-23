/**
 * Created by Spoony on 24/01/2017.
 */
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class GetRSSHandler implements HttpHandler {
    private SQLConnect conn;

    public GetRSSHandler(SQLConnect conn) {
        this.conn = conn;
    }

    public void handle(HttpExchange he) throws IOException {

    }
}
