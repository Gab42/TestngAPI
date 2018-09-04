import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class AccountsPortfolios {
    // GET ID for first portfolio of user
    public static String getPID(String accessToken) throws IOException, JSONException {
        HttpURLConnection conn = Utils.openConn("/api/p");
        conn.setRequestProperty("Authorization","Bearer "+accessToken);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String output;

        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        String[] response = sb.toString().split("\"" );
        String id = response[5];
        return id;
    }

    // GET ID for account of user
    public static String getAID(String accessToken) throws IOException, JSONException {
        String portfolioID = getPID(accessToken);
        HttpURLConnection conn = Utils.openConn("/api/p?id=" + id);
        conn.setRequestProperty("Authorization","Bearer "+accessToken);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String output;

        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        String[] response = sb.toString().split("\"" );
        String accountID = response[5];
        return accountID;
    }

}
