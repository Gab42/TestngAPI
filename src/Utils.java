import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Utils {

    static String url = "http://api";
    public static String loginUrl = url + "login";



    public static HttpURLConnection openConn(String webAddress, String urlParameters) throws IOException {
        URL url = new URL(webAddress);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);

        return conn;
    }

    public static HttpURLConnection POST(String webAddress, String urlParameters) throws IOException {
        HttpURLConnection conn = openConn(webAddress, urlParameters);

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


    public static HttpURLConnection login(String user, String password) throws Exception {
        String urlParams = "grant_type=" + "password" + "&username=" + user + "&password=" + password;
        HttpURLConnection conn = Utils.POST(Utils.loginUrl, urlParams);
        return conn;
    }
}


