/**
 * Created by Spoony on 24/01/2017.
 */

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddRSSHandler implements HttpHandler {
    private SQLConnect conn;

    public AddRSSHandler(SQLConnect conn) {
        this.conn = conn;
    }

    public void handle(HttpExchange t) throws IOException {
        InputStreamReader isr =  new InputStreamReader(t.getRequestBody(),"utf-8");
        BufferedReader br = new BufferedReader(isr);
        int b;
        StringBuilder buf = new StringBuilder();
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }
        System.out.println(buf);
    }
}
