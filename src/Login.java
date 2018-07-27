import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Login {

    // Login
    public static HttpURLConnection loginConnection(String user, String password) throws Exception {
        String urlParams = "&username=" + user + "&password=" + password;
        HttpURLConnection conn = Utils.postLogin("/login", urlParams);
        return conn;
    }

    // Login with user, password and get access token
    public static String getAccessToken(String user, String password) throws Exception {
        HttpURLConnection conn = loginConnection(user, password);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String output;

        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        JSONObject json = new JSONObject(sb.toString());
        String accessToken = json.getString("accessToken");
        return accessToken;
    }
}
