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

    static String url = "http://api/api";

    // GET response message from API endpoint
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

    // Get ID of user
    public static String getID(String accessToken) throws IOException, JSONException {
        HttpURLConnection conn = openConn(url + "/v1/ID", null);
        conn.setRequestProperty("Authorization","Bearer "+accessToken);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String output;

        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        String[] response = sb.toString().split("\"" );
        String portfolioID = response[5];
        return portfolioID;
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

    // POST String data to API endpoint
    public static HttpURLConnection POST(String endpoint, String data) throws Exception {
        HttpURLConnection conn = openConn(url + endpoint, data);


        byte[] postData = data.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
       //
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));

        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }

        return conn;
    }

    // POST with JSON object in body to API endpoint
    public static HttpURLConnection POST(String endpoint, JSONObject data, String accessToken) throws IOException {

        HttpURLConnection conn = openConn(url + endpoint, null);
        conn.setRequestProperty("Authorization","Bearer "+accessToken);
        byte[] postData = data.toString().getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setRequestProperty("Content-Type", "application/json");

        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }

        return conn;
    }

    // Login
    public static HttpURLConnection loginConnection(String user, String password) throws Exception {
        String urlParams = "grant_type=" + "password" + "&username=" + user + "&password=" + password;
        HttpURLConnection conn = Utils.POST("/login", urlParams);
        return conn;
    }


}


