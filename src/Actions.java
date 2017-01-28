/**
 * Created by Spoony on 27/01/2017.
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Actions {
    public SQLConnect conn;

    public void AddLink(JSONObject obj) {
        try {
            String link = obj.getString("Link");
            URL rssURL = new URL(link);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String descr = null;
            String title = null;
            String line;
            while ((line = in.readLine()) != null && (title == null || descr == null)) {
                if (line.contains("<title>")) {
                    title = line.substring(line.indexOf("<title>")+7);
                    while (!title.contains("</title>")) {
                        line = in.readLine();
                        title += line;
                    }
                    title = title.substring(0, title.indexOf("</title>"));
                }
                if (line.contains("<description>")) {
                    descr = line.substring(line.indexOf("<description>")+13);
                    while (!descr.contains("</description")) {
                        line = in.readLine();
                        descr += line;
                    }
                    descr = descr.substring(0, descr.indexOf("</description>"));
                }
                System.out.println(descr);
            }
            //System.out.println(link+" "+title+" "+descr);
            this.conn.AddRSS(link, title, descr);
            in.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GetLink(JSONObject obj) {
        try {
            String link = obj.getString("Link");
            URL rssURL = new URL(link);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String descr = null;
            String title = null;
            String line;
            while ((line = in.readLine()) != null && (title == null || descr == null)) {
                if (line.contains("<title>")) {
                    title = line.substring(line.indexOf("<title>")+7);
                    while (!title.contains("</title>")) {
                        line = in.readLine();
                        title += line;
                    }
                    title = title.substring(0, title.indexOf("</title>"));
                }
                if (line.contains("<description>")) {
                    descr = line.substring(line.indexOf("<description>")+13);
                    while (!descr.contains("</description")) {
                        line = in.readLine();
                        descr += line;
                    }
                    descr = descr.substring(0, descr.indexOf("</description>"));
                }
                System.out.println(descr);
            }
            //System.out.println(link+" "+title+" "+descr);
            this.conn.AddRSS(link, title, descr);
            in.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
