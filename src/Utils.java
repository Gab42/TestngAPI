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

    static String url = "http://***/api/";
    public static String loginUrl = url + "login";

    // GET response message from API endpoint 2
    public static HttpURLConnection GET(String endpoint, String accessToken) throws IOException {
        HttpURLConnection conn = openConn(endpoint, null);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization","Bearer "+accessToken);
        return conn;
    }

    // Login and get access token
    public static String getAccessToken(String user, String password) throws Exception {
        HttpURLConnection conn = loginConnection(user, password);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String output;

        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        JSONObject json = new JSONObject(sb.toString());
        String accessToken = json.getString("access_token");
        return accessToken;
    }

    // Open an HTTP connection to URL endPoint
    public static HttpURLConnection openConn(String endpoint, String urlParameters) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);

        return conn;
    }

  /*  // GET data from API endpoint
    public static HttpURLConnection GET(String webAddress, String urlParameters, String accessToken) throws IOException {
        HttpURLConnection conn = openConn(webAddress, urlParameters);

        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));

        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }

        return conn;
    }*/



    // POST data to API endpoint with parameters
    public static HttpURLConnection POST(String endpoint, String urlParameters) throws IOException {
        HttpURLConnection conn = openConn(endpoint, urlParameters);

        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));

        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }

        return conn;
    }

    // Login
    public static HttpURLConnection loginConnection(String user, String password) throws Exception {
        String urlParams = "grant_type=" + "password" + "&username=" + user + "&password=" + password;
        HttpURLConnection conn = Utils.POST(Utils.loginUrl, urlParams);
        return conn;
    }


}


