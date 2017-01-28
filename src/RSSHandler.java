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
    private Actions act;
    private SQLConnect conn;
    private HttpExchange t;

    public RSSHandler() {
        this.act = new Actions();
        this.act.conn = new SQLConnect();
    }

    public void handle(HttpExchange t) throws IOException {
        this.t = t;
        switch (t.getRequestMethod()) {
            case "POST":
                this.act.AddLink(parseJSON());
                this.sendResponse("", 200);
                break;
            case "GET":
                break;
            case "PUT":
                parseJSON();
                break;
            case "DELETE":
                break;
        }
    }

    private void sendResponse(String msg, int code) {
        try {
            t.sendResponseHeaders(code, msg.length());
            OutputStream out = this.t.getResponseBody();
            out.write(msg.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseRSS(JSONObject obj) {

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
                return obj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
