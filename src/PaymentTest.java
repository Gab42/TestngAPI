import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class PaymentTest {
    String user = "Gab";
    String password = "Password";


    @Test
    public void makePaymentSuccessful() throws Exception {
        String accessToken = Login.getAccessToken(user, password);
        String id = Accounts.getPID(accessToken);


        String paymentData = "{ \"info\" }";

        //System.out.println(test.toString());
        JSONObject data = new JSONObject(paymentData);
        HttpURLConnection conn = Payment.postPayment( "/payments", data, accessToken);
        String accountID = AccountsPortfolios.getAID(accessToken);
        Assert.assertEquals(conn.getResponseCode(), 200);
    }

    @Test
    public void makePaymentWrongAccountNumber() throws Exception {
        String accessToken = Login.getAccessToken(user, password);
        String id = Accounts.getPID(accessToken);


        String paymentData = "{\"info\" }";

        //System.out.println(test.toString());
        JSONObject data = new JSONObject(paymentData);
        HttpURLConnection conn = Payment.postPayment( "/payments", data, accessToken);
        Assert.assertEquals(conn.getResponseCode(), 500);
    }

    @Test
    public void makePaymentBiggerAmountThanBalance() throws Exception {
        String accessToken = Login.getAccessToken(user, password);
        String id = Accounts.getPID(accessToken);


        String paymentData = "{\"info\" }";

        //System.out.println(test.toString());
        JSONObject data = new JSONObject(paymentData);
        HttpURLConnection conn = Payment.postPayment( "/payments", data, accessToken);
        Assert.assertEquals(conn.getResponseCode(), 500);
    }
}
