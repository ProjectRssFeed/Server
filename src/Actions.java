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

    public Actions() {
        this.conn = new SQLConnect();
    }

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
            }
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

    public String GetLink(String id) {
        try {
            String link = this.conn.GetLink(id);
            URL rssURL = new URL(link);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String descr = null;
            String title = null;
            String sublink = null;
            String res = "[";
            String line;
            while ((line = in.readLine()) != null) {

                if (line.contains("<item>")) {
                    if (res.length() > 1) {
                    res += ",";
                    }
                    while (!line.contains("</item>")) {
                        if (line.contains("<title>")) {
                            title = line.substring(line.indexOf("<title>") + 7);
                            while (!title.contains("</title>")) {
                                line = in.readLine();
                                title += line;
                            }
                            title = title.substring(0, title.indexOf("</title>"));
                        }
                        if (line.contains("<description>")) {
                            descr = line.substring(line.indexOf("<description>") + 13);
                            while (!descr.contains("</description")) {
                                line = in.readLine();
                                descr += line;
                            }
                            descr = descr.substring(0, descr.indexOf("</description>"));
                        }
                        if (line.contains("<link>")) {
                            if (line.contains("<link>")) {
                                sublink = line.substring(line.indexOf("<link>") + 6);
                                while (!sublink.contains("</link>")) {
                                    line = in.readLine();
                                    sublink += line;
                                }
                                sublink = sublink.substring(0, sublink.indexOf("</link>"));
                            }
                        }
                        line = in.readLine();
                    }
                    res += "{\"Title\":\""+title+"\",\"Description\":\""+descr+"\",\"Link\":\""+link+"\"}";
                }
            }
            res += "]";
            in.close();
            return res;
        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
