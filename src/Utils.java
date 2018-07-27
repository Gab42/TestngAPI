import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class Utils {

    static String url = "http://api.com";

    // GET API endpoint using access token for authorization
    public static HttpURLConnection getRequest(String endpoint, String accessToken) throws IOException {
        HttpURLConnection conn = openConn(endpoint);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization","Bearer "+accessToken);
        return conn;
    }

    // Open an HTTP connection to API endPoint
    public static HttpURLConnection openConn(String endpoint) throws IOException {
        URL address = new URL(url + endpoint);
        HttpURLConnection conn = (HttpURLConnection) address.openConnection();
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);

        return conn;
    }

    // POST data to API endpoint
    public static HttpURLConnection postLogin(String endpoint, String data) throws Exception {
        HttpURLConnection conn = openConn(endpoint);

        byte[] postData = data.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));

        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }

        return conn;
    }




}


