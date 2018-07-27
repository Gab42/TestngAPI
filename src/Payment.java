import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class Payment {
    // postPayment JSON object to API endpoint - Payments
    public static HttpURLConnection postPayment(String endpoint, JSONObject data, String accessToken) throws IOException {

        HttpURLConnection conn = Utils.openConn(endpoint);
        conn.setRequestProperty("Authorization", accessToken);
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
}
