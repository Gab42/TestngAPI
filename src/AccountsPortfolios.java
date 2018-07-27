import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class AccountsPortfolios {
    // GET ID for first portfolio of user
    public static String getPID(String accessToken) throws IOException, JSONException {
        HttpURLConnection conn = Utils.openConn("/v1/portfolios");
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

    // GET ID for account of user
    public static String getAID(String accessToken) throws IOException, JSONException {
        String portfolioID = getPID(accessToken);
        HttpURLConnection conn = Utils.openConn("/v1/accounts?portfolio_id=" + portfolioID);
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
