/**
 * Created by Spoony on 25/01/2017.
 */

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.*;

public class RSSHandler implements HttpHandler {
    private SQLConnect conn;

    public RSSHandler(SQLConnect conn) {
        this.conn = conn;
    }

    public void handle(HttpExchange t) throws IOException {
        switch (t.getRequestMethod()) {
            case "POST":
                parseJSON(t);
                break;
            case "GET":
                break;
            case "PUT":
                parseJSON(t);
                break;
            case "DELETE":
                break;
        }

    }

    private JSONObject parseJSON(HttpExchange t) throws IOException {
        InputStreamReader isr = new InputStreamReader(t.getRequestBody(), "utf-8");
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
