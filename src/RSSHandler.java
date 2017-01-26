/**
 * Created by Spoony on 25/01/2017.
 */

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

import static java.lang.System.*;

public class RSSHandler implements HttpHandler {
    private SQLConnect conn;
    private HttpExchange t;

    public RSSHandler(SQLConnect conn) {
        this.conn = conn;
    }

    public void handle(HttpExchange t) throws IOException {
        this.t = t;
        switch (t.getRequestMethod()) {
            case "POST":
                parseJSON();
                break;
            case "GET":
                break;
            case "PUT":
                parseJSON();
                break;
            case "DELETE":
                break;
        }
        parseRSS(null);
    }

    private void parseRSS(JSONObject obj) {
        String message = "hello world";
        try {
            t.sendResponseHeaders(200, message.length());
            OutputStream out = this.t.getResponseBody();
            out.write(message.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject parseJSON() throws IOException {
        InputStreamReader isr = new InputStreamReader(this.t.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        int b;
        StringBuilder buf = new StringBuilder();
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }
        JSONObject obj = null;
        try {
            obj = new JSONObject(buf.toString());
            try {
                System.out.println(obj.getString("Hello"));
                return obj;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
