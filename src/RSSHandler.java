/**
 * Created by Spoony on 25/01/2017.
 */

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.Arrays;

import static java.lang.System.*;

public class RSSHandler implements HttpHandler {
    private Actions act;
    private HttpExchange t;

    public RSSHandler() {
        this.act = new Actions();
    }

    public void handle(HttpExchange t) throws IOException {
        this.t = t;
        String[] split;
        switch (t.getRequestMethod()) {
            case "POST":
                this.act.AddLink(parseJSON());
                this.sendResponse("", 200);
                break;
            case "GET":
                split = t.getRequestURI().getPath().split("/");
                String res;
                if (split.length > 3) {
                    res = this.act.GetLink(split[3]);
                } else {
                    res = this.act.conn.Getfeeds();
                }
                this.sendResponse(res, 200);
                break;
            case "DELETE":
                split = t.getRequestURI().getPath().split("/");
                if (split.length > 3) {
                    this.act.conn.DeleteRSS(split[3]);
                }
                this.sendResponse("", 200);
                break;
        }
    }

    private void sendResponse(String msg, int code) {
        try {
            t.sendResponseHeaders(code, msg.getBytes().length);
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
